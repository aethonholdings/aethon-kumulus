import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kumulus_layoutshome_gsp extends GroovyPage {
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
printHtmlPart(2)
expressionOut.print(resource(dir: 'css/jquery/ui/base', file: 'jquery-ui.css'))
printHtmlPart(3)
invokeTag('javascript','g',8,['library':("jquery")],-1)
printHtmlPart(4)
invokeTag('layoutResources','r',9,[:],-1)
printHtmlPart(5)
invokeTag('javascript','g',10,['src':("jquery/ui/jquery-ui.js")],-1)
printHtmlPart(4)
invokeTag('javascript','g',11,['src':("jquery/cookie/jquery.cookie.js")],-1)
printHtmlPart(4)
invokeTag('javascript','g',12,['src':("pure/ui.js")],-1)
printHtmlPart(6)
invokeTag('javascript','g',13,['src':("kumulus/base.js")],-1)
printHtmlPart(4)
invokeTag('layoutHead','g',14,[:],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',15,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',22,['controller':("home"),'action':("index")],2)
printHtmlPart(10)
createClosureForHtmlPart(11, 2)
invokeTag('link','g',23,['controller':("home"),'action':("manage")],2)
printHtmlPart(12)
createClosureForHtmlPart(13, 2)
invokeTag('link','g',25,['controller':("home"),'action':("download")],2)
printHtmlPart(14)
createClosureForHtmlPart(15, 2)
invokeTag('link','g',26,['controller':("home"),'action':("access")],2)
printHtmlPart(10)
createClosureForHtmlPart(16, 2)
invokeTag('link','g',27,['controller':("home"),'action':("collect")],2)
printHtmlPart(14)
createClosureForHtmlPart(17, 2)
invokeTag('link','g',28,['controller':("home"),'action':("upload")],2)
printHtmlPart(14)
createClosureForHtmlPart(18, 2)
invokeTag('link','g',29,['controller':("home"),'action':("build")],2)
printHtmlPart(19)
createClosureForHtmlPart(20, 2)
invokeTag('link','g',33,['controller':("home"),'action':("process")],2)
printHtmlPart(21)
invokeTag('pageTitle','g',41,['text':(pageTitle)],-1)
printHtmlPart(22)
invokeTag('userCompany','g',45,[:],-1)
printHtmlPart(23)
invokeTag('loggedInUserInfo','sec',46,['field':("username")],-1)
printHtmlPart(24)
createClosureForHtmlPart(25, 2)
invokeTag('link','g',47,['controller':("logout")],2)
printHtmlPart(26)
invokeTag('layoutBody','g',53,[:],-1)
printHtmlPart(27)
})
invokeTag('captureBody','sitemesh',57,[:],1)
printHtmlPart(28)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1391502631000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
