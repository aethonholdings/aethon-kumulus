from twisted.internet import reactor
from twisted.web import server
import proxy
import config

site = server.Site(proxy.ReverseProxyResource(config.ORIGINAL,
                                              config.ORIGINAL_PORT, ''))
if config.USE_SSL:
    from twisted.internet import ssl    
    reactor.listenSSL(config.PROXIED_PORT, site,
                      ssl.DefaultOpenSSLContextFactory('keys/server.key',
                                                       'keys/server.crt'))
else:
    reactor.listenTCP(config.PROXIED_PORT, site)
reactor.run()
