import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kumulus_documentprocess_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/document/process.gsp" }
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
printHtmlPart(1)
invokeTag('javascript','g',4,['src':("kumulus/preview.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',5,['src':("kumulus/process.js")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
for( page in (document.pages) ) {
printHtmlPart(5)
expressionOut.print(document.id)
printHtmlPart(6)
invokeTag('kumulusImg','g',14,['image':(page.thumbnailImage),'class':("kumulus-thumbnail kumulus-element-border"),'height':("140"),'width':("100"),'viewId':(page.viewImage.id),'scanId':(page.scanImage.id),'pageNumber':(page.number)],-1)
printHtmlPart(7)
}
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
expressionOut.print(document.type)
printHtmlPart(10)
for( documentType in (documentTypes) ) {
printHtmlPart(11)
expressionOut.print(documentType.id)
printHtmlPart(12)
if(true && (documentType.id==4)) {
printHtmlPart(13)
}
printHtmlPart(14)
expressionOut.print(documentType.name)
printHtmlPart(15)
}
printHtmlPart(16)
expressionOut.print(document.company?.name)
printHtmlPart(17)
expressionOut.print(document.date)
printHtmlPart(18)
expressionOut.print(document.identifier)
printHtmlPart(19)
})
invokeTag('form','g',56,['name':("document"),'action':("update"),'id':(document.id),'class':("pure-form pure-form-stacked")],2)
printHtmlPart(20)
for( page in (document.pages) ) {
printHtmlPart(21)
for( lineItem in (page.lineItems) ) {
printHtmlPart(22)
}
printHtmlPart(23)
}
printHtmlPart(24)
for( currency in (currencies) ) {
printHtmlPart(25)
expressionOut.print(currency.shortName)
printHtmlPart(12)
if(true && (currency.shortName=='SGD')) {
printHtmlPart(13)
}
printHtmlPart(14)
expressionOut.print(currency.shortName)
printHtmlPart(26)
}
printHtmlPart(27)
createClosureForHtmlPart(28, 2)
invokeTag('link','g',118,['class':("pure-button"),'controller':("document"),'action':("update"),'id':(document.id)],2)
printHtmlPart(29)
})
invokeTag('captureBody','sitemesh',123,[:],1)
printHtmlPart(30)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1391075920000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
