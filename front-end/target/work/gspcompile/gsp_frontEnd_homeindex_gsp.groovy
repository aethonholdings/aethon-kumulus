import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_frontEnd_homeindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
createTagBody(2, {->
createClosureForHtmlPart(1, 3)
invokeTag('captureTitle','sitemesh',2,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',2,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',3,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
expressionOut.print(tasks?.count())
printHtmlPart(4)
if(true && (tasks?.count()>1)) {
printHtmlPart(5)
}
printHtmlPart(6)
invokeTag('sortableColumn','g',9,['property':("id"),'title':("Id")],-1)
printHtmlPart(7)
invokeTag('sortableColumn','g',10,['property':("created"),'title':("Time created")],-1)
printHtmlPart(7)
invokeTag('sortableColumn','g',11,['property':("barcode"),'title':("Task")],-1)
printHtmlPart(7)
invokeTag('sortableColumn','g',12,['property':("action"),'title':("Action pending")],-1)
printHtmlPart(8)
loop:{
int i = 0
for( taskInstance in (tasks?.list()) ) {
printHtmlPart(9)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(10)
expressionOut.print(taskInstance.id)
printHtmlPart(11)
expressionOut.print(taskInstance.created.format("dd/MM/yyyy - HH:mm:ss"))
printHtmlPart(12)
if(true && (taskInstance.type=='TASK_VALIDATE')) {
printHtmlPart(13)
}
printHtmlPart(14)
if(true && (taskInstance.type=='TASK_CLASSIFY')) {
printHtmlPart(15)
}
printHtmlPart(16)
if(true && (taskInstance.type=='TASK_VALIDATE')) {
createClosureForHtmlPart(17, 4)
invokeTag('link','g',25,['controller':("home"),'action':("validate")],4)
}
printHtmlPart(18)
i++
}
}
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',1,[:],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1388398344000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
