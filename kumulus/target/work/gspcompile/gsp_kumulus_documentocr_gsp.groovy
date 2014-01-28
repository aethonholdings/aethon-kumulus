import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_kumulus_documentocr_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/document/ocr.gsp" }
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
invokeTag('javascript','g',4,['src':("kumulus/documentBuilder.js")],-1)
printHtmlPart(1)
invokeTag('javascript','g',5,['src':("kumulus/autocomplete.js")],-1)
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
invokeTag('kumulusImg','g',14,['image':(page.thumbnailImage),'class':("kumulus-thumbnail kumulus-element-border"),'height':("140"),'width':("100"),'onClick':("selectPage(this);"),'viewId':(page.viewImage.id),'scanId':(page.scanImage.id)],-1)
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
for( currency in (currencies) ) {
printHtmlPart(11)
expressionOut.print(currency.fullName)
printHtmlPart(12)
if(true && (currency.shortName=='SGD')) {
printHtmlPart(13)
}
printHtmlPart(14)
expressionOut.print(currency.fullName)
printHtmlPart(15)
}
printHtmlPart(20)
})
invokeTag('form','g',90,['name':("document"),'action':("update"),'id':(document.id),'class':("pure-form pure-form-aligned")],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',98,[:],1)
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
