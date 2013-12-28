from twisted.internet import reactor
from twisted.web import server
import proxy
import config

site = server.Site(proxy.ReverseProxyResource(config.ORIGINAL,
                                              config.ORIGINAL_PORT, ''))
reactor.listenTCP(config.PROXIED_PORT, site)
reactor.run()
