$(document).ready(function(){
    $("#myHomeDiv").show()
    $("#captureDocumentDiv").hide()
    $("#validateDocumentDiv").hide()
    $("#searchForDocumentDiv").hide()
    $("#DownloadDocumentDiv").hide()
})
function myHome(){
    $("#myHomeDiv").show()
    $("#validateDocumentDiv").hide()
    $("#searchForDocumentDiv").hide()
    $("#DownloadDocumentDiv").hide()
    $("#captureDocumentDiv").hide()
}
function captureDocument(){
    $("#myHomeDiv").hide()
    $("#validateDocumentDiv").hide()
    $("#searchForDocumentDiv").hide()
    $("#DownloadDocumentDiv").hide()
    $("#captureDocumentDiv").show()
}
function validateDocument(){
    $("#myHomeDiv").hide()
    $("#validateDocumentDiv").show()
    $("#captureDocumentDiv").hide()
    $("#searchForDocumentDiv").hide()
    $("#DownloadDocumentDiv").hide()
}
function searchDocument(){
    $("#myHomeDiv").hide()
    $("#searchForDocumentDiv").show()
    $("#validateDocumentDiv").hide()
    $("#captureDocumentDiv").hide()
    $("#DownloadDocumentDiv").hide()
}
function downloadDocument(){
    $("#myHomeDiv").hide()
    $("#DownloadDocumentDiv").show()
    $("#validateDocumentDiv").hide()
    $("#searchForDocumentDiv").hide()
    $("#captureDocumentDiv").hide()
}