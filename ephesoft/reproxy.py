from twisted.internet import reactor
from twisted.web import server
import proxy

site = server.Site(proxy.ReverseProxyResource('test.ephesoft.kumulus.sg', 8080, ''))
reactor.listenTCP(80, site)
reactor.run()
