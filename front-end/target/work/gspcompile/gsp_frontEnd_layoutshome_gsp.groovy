import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_frontEnd_layoutshome_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/home.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(resource(dir: 'css/pure', file: 'pure-min.css'))
printHtmlPart(2)
expressionOut.print(resource(dir: 'css/pure', file: 'side-menu.css'))
printHtmlPart(2)
expressionOut.print(resource(dir: 'css/kumulus', file: 'main.css'))
printHtmlPart(3)
invokeTag('javascript','g',7,['src':("pure/ui.js")],-1)
printHtmlPart(4)
invokeTag('javascript','g',8,['library':("jquery")],-1)
printHtmlPart(4)
invokeTag('layoutResources','r',9,[:],-1)
printHtmlPart(4)
invokeTag('layoutHead','g',10,[:],-1)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',18,['controller':("home"),'action':("index")],2)
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',19,['controller':("collect"),'action':("index")],2)
printHtmlPart(8)
createClosureForHtmlPart(10, 2)
invokeTag('link','g',20,['controller':("extract"),'action':("index")],2)
printHtmlPart(11)
invokeTag('layoutBody','g',27,[:],-1)
printHtmlPart(12)
})
invokeTag('captureBody','sitemesh',30,[:],1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1389176021000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
