import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_frontEnd_collectindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/collect/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',3,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',3,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',4,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
for( projectInstance in (projects) ) {
printHtmlPart(5)
expressionOut.print(projectInstance?.id)
printHtmlPart(6)
createTagBody(3, {->
expressionOut.print(projectInstance?.projectName)
})
invokeTag('link','g',19,['controller':("collect"),'action':("workflow"),'id':(projectInstance?.id)],3)
printHtmlPart(6)
expressionOut.print(projectInstance?.clientLDAPId)
printHtmlPart(7)
}
printHtmlPart(8)
})
invokeTag('captureBody','sitemesh',26,[:],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1389708005000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
