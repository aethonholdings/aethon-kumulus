import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_frontEnd_extractindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/extract/index.gsp" }
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
loop:{
int i = 0
for( taskInstance in (tasks?.list()) ) {
printHtmlPart(5)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(6)
expressionOut.print(taskInstance.id)
printHtmlPart(7)
if(true && (taskInstance.status==5)) {
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (taskInstance.status==4)) {
printHtmlPart(10)
}
printHtmlPart(11)
expressionOut.print(taskInstance.created.format("dd/MM/yyyy - HH:mm:ss"))
printHtmlPart(12)
expressionOut.print(taskInstance.batchInstanceUrlId)
printHtmlPart(13)
i++
}
}
printHtmlPart(14)
})
invokeTag('captureBody','sitemesh',36,[:],1)
printHtmlPart(15)
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
