import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kumulus_captureupload_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/capture/upload.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
expressionOut.print(resource(dir: 'css/dynatree/skin', file: 'ui.dynatree.css'))
printHtmlPart(3)
invokeTag('javascript','g',11,['src':("dynatree/jquery.dynatree.js")],-1)
printHtmlPart(4)
invokeTag('javascript','g',12,['src':("kumulus/nodeTree.js")],-1)
printHtmlPart(4)
createTagBody(2, {->
createClosureForHtmlPart(5, 3)
invokeTag('captureTitle','sitemesh',13,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',13,[:],2)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',14,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
expressionOut.print(project.id)
printHtmlPart(8)
invokeTag('form','fileuploader',65,['name':("uploader"),'class':("pure-button-disabled"),'upload':("image"),'successController':("capture"),'successAction':("upload"),'errorController':("test"),'errorAction':("error"),'nodeId':("-1"),'projectId':(project.id),'disabled':("true")],-1)
printHtmlPart(9)
})
invokeTag('captureBody','sitemesh',71,[:],1)
printHtmlPart(10)
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
