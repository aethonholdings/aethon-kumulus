from twisted.internet import reactor, ssl
from twisted.web import server
import proxy
import config

site = server.Site(proxy.ReverseProxyResource(config.ORIGINAL,
                                              config.ORIGINAL_PORT, ''))
if config.USE_SSL:
    reactor.listenSSL(config.PROXIED_PORT, site,
                      ssl.DefaultOpenSSLContextFactory('keys/server.key',
                                                       'keys/server.crt'))
else:
    reactor.listenTCP(config.PROXIED_PORT, site)
reactor.run()
