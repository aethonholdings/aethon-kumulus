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
expressionOut.print(resource(dir: 'css', file: 'main.css'))
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
invokeTag('loggedInUserInfo','sec',9,['field':("username")],-1)
printHtmlPart(5)
expressionOut.print(tasks?.count())
printHtmlPart(6)
if(true && (tasks?.count()>1)) {
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('sortableColumn','g',18,['property':("id"),'title':("Id")],-1)
printHtmlPart(9)
invokeTag('sortableColumn','g',19,['property':("created"),'title':("Time created")],-1)
printHtmlPart(9)
invokeTag('sortableColumn','g',20,['property':("barcode"),'title':("Task")],-1)
printHtmlPart(9)
invokeTag('sortableColumn','g',21,['property':("action"),'title':("Action pending")],-1)
printHtmlPart(10)
loop:{
int i = 0
for( taskInstance in (tasks.list()) ) {
printHtmlPart(11)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(12)
expressionOut.print(taskInstance.id)
printHtmlPart(13)
expressionOut.print(taskInstance.created.format("dd/MM/yyyy - HH:mm:ss"))
printHtmlPart(14)
if(true && (taskInstance.type=='TASK_VALIDATE')) {
printHtmlPart(15)
}
printHtmlPart(16)
if(true && (taskInstance.type=='TASK_CLASSIFY')) {
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (taskInstance.type=='TASK_VALIDATE')) {
createClosureForHtmlPart(19, 4)
invokeTag('link','g',34,['controller':("home"),'action':("validate")],4)
}
printHtmlPart(20)
i++
}
}
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',1,[:],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1388390096000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
