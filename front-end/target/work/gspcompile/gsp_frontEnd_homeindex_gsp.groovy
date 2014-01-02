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
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
expressionOut.print(resource(dir: 'css', file: 'side-menu.css'))
printHtmlPart(3)
invokeTag('javascript','g',5,['src':("ui.js")],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(4, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(tasks?.count())
printHtmlPart(7)
if(true && (tasks?.count()>1)) {
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('sortableColumn','g',27,['property':("id"),'title':("Id")],-1)
printHtmlPart(10)
invokeTag('sortableColumn','g',28,['property':("created"),'title':("Time created")],-1)
printHtmlPart(10)
invokeTag('sortableColumn','g',29,['property':("barcode"),'title':("Task")],-1)
printHtmlPart(10)
invokeTag('sortableColumn','g',30,['property':("action"),'title':("Action pending")],-1)
printHtmlPart(11)
loop:{
int i = 0
for( taskInstance in (tasks?.list()) ) {
printHtmlPart(12)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(13)
expressionOut.print(taskInstance.id)
printHtmlPart(14)
expressionOut.print(taskInstance.created.format("dd/MM/yyyy - HH:mm:ss"))
printHtmlPart(15)
if(true && (taskInstance.type=='TASK_VALIDATE')) {
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (taskInstance.type=='TASK_CLASSIFY')) {
printHtmlPart(18)
}
printHtmlPart(19)
if(true && (taskInstance.type=='TASK_VALIDATE')) {
createClosureForHtmlPart(20, 4)
invokeTag('link','g',43,['controller':("home"),'action':("validate")],4)
}
printHtmlPart(21)
i++
}
}
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',51,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1388663058000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
