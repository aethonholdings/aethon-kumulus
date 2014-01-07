# -*- test-case-name: twisted.web.test.test_proxy -*-
# Copyright (c) Twisted Matrix Laboratories.
# See LICENSE for details.

"""
Simplistic HTTP proxy support.

This comes in two main variants - the Proxy and the ReverseProxy.

When a Proxy is in use, a browser trying to connect to a server (say,
www.yahoo.com) will be intercepted by the Proxy, and the proxy will covertly
connect to the server, and return the result.

When a ReverseProxy is in use, the client connects directly to the ReverseProxy
(say, www.yahoo.com) which farms off the request to one of a pool of servers,
and returns the result.

Normally, a Proxy is used on the client end of an Internet connection, while a
ReverseProxy is used on the server end.
"""

import urlparse
from urllib import quote as urlquote

from twisted.internet import reactor
from twisted.internet.protocol import ClientFactory
from twisted.web.resource import Resource
from twisted.web.server import NOT_DONE_YET
from twisted.web.http import HTTPClient, Request, HTTPChannel
from twisted.enterprise import adbapi

import config, cshelve

class BasicProxyClient(HTTPClient):
    """
    Used by ProxyClientFactory to implement a simple web proxy.

    @ivar _finished: A flag which indicates whether or not the original request
        has been finished yet.
    """
    _finished = False

    def __init__(self, command, rest, version, headers, data, father):
        self.father = father
        self.command = command
        self.rest = rest
        if "proxy-connection" in headers:
            del headers["proxy-connection"]
        headers["connection"] = "close"
        headers.pop('keep-alive', None)
        self.headers = headers
        self.data = data


    def connectionMade(self):
        self.sendCommand(self.command, self.rest)
        for header, value in self.headers.items():
            self.sendHeader(header, value)
        self.endHeaders()
        self.transport.write(self.data)


    def handleStatus(self, version, code, message):
        self.father.setResponseCode(int(code), message)


    def handleHeader(self, key, value):
        # t.web.server.Request sets default values for these headers in its
        # 'process' method. When these headers are received from the remote
        # server, they ought to override the defaults, rather than append to
        # them.
        if key.lower() in ['server', 'date', 'content-type']:
            self.father.responseHeaders.setRawHeaders(key, [value])
        else:
            self.father.responseHeaders.addRawHeader(key, value)


    def handleResponsePart(self, buffer):
        self.father.write(buffer)


    def handleResponseEnd(self):
        """
        Finish the original request, indicating that the response has been
        completely written to it, and disconnect the outgoing transport.
        """
        if not self._finished:
            self._finished = True
            self.father.finish()
            self.transport.loseConnection()


class ProxyClient(BasicProxyClient):
    orig_js = """onload="document.getElementById('j_username').focus();"""
    extra_js = """document.getElementById('j_username').value='ephesoft';
    document.getElementById('j_password').value='Ke$haIsGreat666';"""

    def handleHeader(self, key, value):
        if key == "Location":
            value = value.replace(config.fq_original(), config.fq_proxied())
        BasicProxyClient.handleHeader(self, key, value)

    def handleResponsePart(self, buffer):
        i = buffer.find(self.orig_js)
        if i > 0:
            i += len(self.orig_js)
            buffer = buffer[:i] + self.extra_js + buffer[i:]
            bytes_to_remove = len(self.extra_js)
            lines = []
            for line in buffer.split("\r\n"):
                if bytes_to_remove > 0 and line.startswith('<!--') and line.endswith('-->'):
                    capacity = len(line) - 7
                    line = line[:4] + line[4:4 + max(0, capacity-bytes_to_remove)] + line[-3:]
                    bytes_to_remove -= capacity - (len(line)-7)
                lines.append(line)
            buffer = "\r\n".join(lines)
            if bytes_to_remove > 0:
                print 'Warning: Could not remove enough comments from page source'
        BasicProxyClient.handleResponsePart(self, buffer)


class ProxyClientFactory(ClientFactory):
    """
    Used by ProxyRequest to implement a simple web proxy.
    """

    protocol = ProxyClient


    def __init__(self, command, rest, version, headers, data, father):
        self.father = father
        self.command = command
        self.rest = rest
        self.headers = headers
        self.data = data
        self.version = version


    def buildProtocol(self, addr):
        return self.protocol(self.command, self.rest, self.version,
                             self.headers, self.data, self.father)


    def clientConnectionFailed(self, connector, reason):
        """
        Report a connection failure in a response to the incoming request as
        an error.
        """
        self.father.setResponseCode(501, "Gateway error")
        self.father.responseHeaders.addRawHeader("Content-Type", "text/html")
        self.father.write("<H1>Could not connect</H1>")
        self.father.finish()



class ProxyRequest(Request):
    """
    Used by Proxy to implement a simple web proxy.

    @ivar reactor: the reactor used to create connections.
    @type reactor: object providing L{twisted.internet.interfaces.IReactorTCP}
    """

    protocols = {'http': ProxyClientFactory}
    ports = {'http': 80}

    def __init__(self, channel, queued, reactor=reactor):
        Request.__init__(self, channel, queued)
        self.reactor = reactor


    def process(self):
        parsed = urlparse.urlparse(self.uri)
        protocol = parsed[0]
        host = parsed[1]
        port = self.ports[protocol]
        if ':' in host:
            host, port = host.split(':')
            port = int(port)
        rest = urlparse.urlunparse(('', '') + parsed[2:])
        if not rest:
            rest = rest + '/'
        class_ = self.protocols[protocol]
        headers = self.getAllHeaders().copy()
        if 'host' not in headers:
            headers['host'] = host
        self.content.seek(0, 0)
        s = self.content.read()
        clientFactory = class_(self.method, rest, self.clientproto, headers,
                               s, self)
        self.reactor.connectTCP(host, port, clientFactory)



class Proxy(HTTPChannel):
    """
    This class implements a simple web proxy.

    Since it inherits from L{twisted.web.http.HTTPChannel}, to use it you
    should do something like this::

        from twisted.web import http
        f = http.HTTPFactory()
        f.protocol = Proxy

    Make the HTTPFactory a listener on a port as per usual, and you have
    a fully-functioning web proxy!
    """

    requestFactory = ProxyRequest



class ReverseProxyRequest(Request):
    """
    Used by ReverseProxy to implement a simple reverse proxy.

    @ivar proxyClientFactoryClass: a proxy client factory class, used to create
        new connections.
    @type proxyClientFactoryClass: L{ClientFactory}

    @ivar reactor: the reactor used to create connections.
    @type reactor: object providing L{twisted.internet.interfaces.IReactorTCP}
    """

    proxyClientFactoryClass = ProxyClientFactory

    def __init__(self, channel, queued, reactor=reactor):
        Request.__init__(self, channel, queued)
        self.reactor = reactor


    def process(self):
        """
        Handle this request by connecting to the proxied server and forwarding
        it there, then forwarding the response back as the response to this
        request.
        """
        self.requestHeaders.setRawHeaders(b"host", [self.factory.host])
        clientFactory = self.proxyClientFactoryClass(
            self.method, self.uri, self.clientproto, self.getAllHeaders(),
            self.content.read(), self)
        self.reactor.connectTCP(self.factory.host, self.factory.port,
                                clientFactory)



class ReverseProxy(HTTPChannel):
    """
    Implements a simple reverse proxy.

    For details of usage, see the file examples/reverse-proxy.py.
    """

    requestFactory = ReverseProxyRequest



class BasicReverseProxyResource(Resource):
    """
    Resource that renders the results gotten from another server

    Put this resource in the tree to cause everything below it to be relayed
    to a different server.

    @ivar proxyClientFactoryClass: a proxy client factory class, used to create
        new connections.
    @type proxyClientFactoryClass: L{ClientFactory}

    @ivar reactor: the reactor used to create connections.
    @type reactor: object providing L{twisted.internet.interfaces.IReactorTCP}
    """

    proxyClientFactoryClass = ProxyClientFactory


    def __init__(self, host, port, path, reactor=reactor):
        """
        @param host: the host of the web server to proxy.
        @type host: C{str}

        @param port: the port of the web server to proxy.
        @type port: C{port}

        @param path: the base path to fetch data from. Note that you shouldn't
            put any trailing slashes in it, it will be added automatically in
            request. For example, if you put B{/foo}, a request on B{/bar} will
            be proxied to B{/foo/bar}.  Any required encoding of special
            characters (such as " " or "/") should have been done already.

        @type path: C{str}
        """
        Resource.__init__(self)
        self.host = host
        self.port = port
        self.path = path
        self.reactor = reactor


    def getChild(self, path, request):
        """
        Create and return a proxy resource with the same proxy configuration
        as this one, except that its path also contains the segment given by
        C{path} at the end.
        """
        return ReverseProxyResource(
            self.host, self.port, self.path + '/' + urlquote(path, safe=""),
            self.reactor)


    def render(self, request):
        """
        Render a request by forwarding it to the proxied server.
        """
        # RFC 2616 tells us that we can omit the port if it's the default port,
        # but we have to provide it otherwise
        if self.port == 80:
            host = self.host
        else:
            host = "%s:%d" % (self.host, self.port)
        request.requestHeaders.setRawHeaders(b"host", [host])
        request.content.seek(0, 0)
        qs = urlparse.urlparse(request.uri)[4]
        if qs:
            rest = self.path + '?' + qs
        else:
            rest = self.path
        clientFactory = self.proxyClientFactoryClass(
            request.method, rest, request.clientproto,
            request.getAllHeaders(), request.content.read(), request)
        self.reactor.connectTCP(self.host, self.port, clientFactory)
        return NOT_DONE_YET


valid_uris = ['/dcma/less-1.3.1.min.js',
              '/dcma/utility.js',
              '/dcma/login/*',
              '/dcma/i18n/*',
              '/dcma/images/*',
              '/dcma/themes/*',
              '/dcma/reviewValidate/*',
              '/dcma/j_security_check',
              '/dcma/ReviewValidate.css',
              '/dcma/ReviewValidate.html?batch_id=*',
              '/dcma/jquery/*',
              '/favicon.ico']

valid_prefix = '/dcma/ReviewValidate.html?batch_id=BI'

def is_uri_valid(uri):
    for valid in valid_uris:
        if valid.endswith('*') and uri.startswith(valid[:-1]):
            return True
        elif valid == uri:
            return True
    return False


class ReverseProxyResource(BasicReverseProxyResource):

    sessions = cshelve.CShelve(config.STATE_DB, 'c', False)

    cp = adbapi.ConnectionPool('MySQLdb',
                               host=config.DB_HOST, port=config.DB_PORT,
                               user=config.DB_USER, passwd=config.DB_PASS,
                               db=config.DB_NAME)

    def render(self, request):
        # add a twisted cookie if not already there
        request.getSession()
        if not is_uri_valid(request.uri):
            if request.uri == '/dcma/BatchList.html':
                try:
                    user_id = self.sessions[request.received_cookies['TWISTED_SESSION']]
                except KeyError:
                    request.requestHeaders.removeHeader('Cookie')
                    return BasicReverseProxyResource.render(self, request)
                else:
                    print 'Find extra work for user %s' % user_id

                    def get_more_work(user_id):
                        return self.cp.runQuery("SELECT k.id "
                                                "FROM kumulus_batch_instance k "
                                                "INNER JOIN batch_instance b "
                                                "ON k.id = b.id "
                                                "WHERE k.user_id = %s "
                                                "AND batch_status in ('READY_FOR_VALIDATION', "
                                                                     "'READY_FOR_REVIEW') "
                                                "LIMIT 1", user_id)
                    
                    def handle_new_work(batch_id):
                        if batch_id:
                            request.write('<meta http-equiv="refresh" content="0; '\
                                          'url=http://%s/dcma/ReviewValidate.html?batch_id=BI%s" />'\
                                          % (config.fq_proxied(), batch_id[0][0]))
                        else:
                            request.write('No more validation work for you at this time!')
                        request.finish()
                    
                    get_more_work(user_id).addCallback(handle_new_work)
                    return NOT_DONE_YET
            else:
                print 'Blocked URL: %s' % request.uri
                return "Invalid option. "\
                       "Please click your browser's back button."
        else:
            # the uri is valid
            if request.uri == '/dcma/j_security_check':
                # make a note of the username attempting login wrt the twisted cookie
                self.sessions[request.received_cookies['TWISTED_SESSION']] =\
                    request.args['j_username'][0]
                reactor.callInThread(self.sessions.sync)
            elif request.uri.startswith(valid_prefix):
                batch_id = request.uri[len(valid_prefix):]
                # kumulus access rights check
                if batch_id.isdigit() and not batch_id.startswith('0'):
                    try:
                        user_id = self.sessions[request.received_cookies['TWISTED_SESSION']]
                    except KeyError:
                        request.requestHeaders.removeHeader('Cookie')
                    else:
                        print 'Batch instance %s accessed by user %s' % (batch_id, user_id)
        
                        def get_owner(batch_id):
                            return self.cp.runQuery("SELECT user_id "
                                                    "FROM kumulus_batch_instance "
                                                    "WHERE id = %s", batch_id)
                        
                        def validate(owner):
                            if not owner or owner[0][0] != user_id:
                                request.requestHeaders.removeHeader('Cookie')
                            return BasicReverseProxyResource.render(self, request)
                        
                        get_owner(batch_id).addCallback(validate)
                        return NOT_DONE_YET
                else:
                    request.requestHeaders.removeHeader('Cookie')
            return BasicReverseProxyResource.render(self, request)
