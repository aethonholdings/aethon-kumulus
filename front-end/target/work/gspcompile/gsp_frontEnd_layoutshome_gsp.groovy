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
invokeTag('javascript','g',7,['library':("jquery")],-1)
printHtmlPart(4)
invokeTag('layoutResources','r',8,[:],-1)
printHtmlPart(5)
invokeTag('javascript','g',9,['src':("jquery/ui/jquery-ui.js")],-1)
printHtmlPart(4)
invokeTag('javascript','g',10,['src':("jquery/cookie/jquery.cookie.js")],-1)
printHtmlPart(4)
invokeTag('javascript','g',11,['src':("pure/ui.js")],-1)
printHtmlPart(6)
invokeTag('layoutHead','g',12,[:],-1)
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',20,['controller':("home"),'action':("index")],2)
printHtmlPart(10)
createClosureForHtmlPart(11, 2)
invokeTag('link','g',22,['controller':("project"),'action':("index")],2)
printHtmlPart(12)
createClosureForHtmlPart(13, 2)
invokeTag('link','g',23,['controller':("collect"),'action':("index")],2)
printHtmlPart(14)
createClosureForHtmlPart(15, 2)
invokeTag('link','g',25,['controller':("review"),'action':("index")],2)
printHtmlPart(12)
createClosureForHtmlPart(16, 2)
invokeTag('link','g',26,['controller':("extract"),'action':("index")],2)
printHtmlPart(17)
createClosureForHtmlPart(18, 2)
invokeTag('link','g',28,['controller':("access"),'action':("index")],2)
printHtmlPart(19)
invokeTag('userCompany','g',39,[:],-1)
printHtmlPart(20)
invokeTag('loggedInUserInfo','sec',40,['field':("username")],-1)
printHtmlPart(21)
createClosureForHtmlPart(22, 2)
invokeTag('link','g',41,['controller':("logout")],2)
printHtmlPart(23)
invokeTag('layoutBody','g',47,[:],-1)
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',51,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1389951526000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
