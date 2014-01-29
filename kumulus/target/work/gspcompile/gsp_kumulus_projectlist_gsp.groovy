import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kumulus_projectlist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/project/list.gsp" }
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
for( _it1674270571 in (projectList) ) {
changeItVariable(_it1674270571)
printHtmlPart(5)
expressionOut.print(it.projectName)
printHtmlPart(6)
expressionOut.print(it.client.name)
printHtmlPart(6)
expressionOut.print(it.status)
printHtmlPart(7)
for( action in (actions) ) {
printHtmlPart(8)
if(true && (action=="Edit" && it.status=="A")) {
printHtmlPart(9)
createClosureForHtmlPart(10, 5)
invokeTag('link','g',24,['controller':("project"),'action':("edit"),'id':(it.id)],5)
printHtmlPart(8)
}
printHtmlPart(8)
if(true && (action=="Delete" && it.status=="A")) {
printHtmlPart(9)
createClosureForHtmlPart(11, 5)
invokeTag('link','g',27,['controller':("project"),'action':("delete"),'id':(it.id)],5)
printHtmlPart(8)
}
printHtmlPart(8)
if(true && (action=="Close" && it.status=="A")) {
printHtmlPart(9)
createClosureForHtmlPart(12, 5)
invokeTag('link','g',30,['controller':("project"),'action':("close"),'id':(it.id)],5)
printHtmlPart(8)
}
printHtmlPart(8)
if(true && (action=="Download")) {
printHtmlPart(9)
createClosureForHtmlPart(13, 5)
invokeTag('link','g',33,['controller':("project"),'action':("download"),'id':(it.id)],5)
printHtmlPart(8)
}
printHtmlPart(8)
if(true && (action=="Access")) {
printHtmlPart(9)
createClosureForHtmlPart(13, 5)
invokeTag('link','g',36,['controller':("project"),'action':("access"),'id':(it.id)],5)
printHtmlPart(8)
}
printHtmlPart(8)
if(true && (action=="Collect")) {
printHtmlPart(9)
createClosureForHtmlPart(14, 5)
invokeTag('link','g',39,['controller':("node"),'action':("collect"),'id':(it.id)],5)
printHtmlPart(8)
}
printHtmlPart(8)
if(true && (action=="Upload")) {
printHtmlPart(9)
createClosureForHtmlPart(15, 5)
invokeTag('link','g',42,['controller':("image"),'action':("upload"),'id':(it.id)],5)
printHtmlPart(8)
}
printHtmlPart(16)
}
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (actions.containsAll("Create"))) {
printHtmlPart(19)
createClosureForHtmlPart(20, 3)
invokeTag('link','g',51,['controller':("project"),'action':("create"),'class':("pure-button")],3)
printHtmlPart(21)
}
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',53,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1390917381000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
