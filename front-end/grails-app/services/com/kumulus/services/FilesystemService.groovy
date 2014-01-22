package com.kumulus.services

import com.kumulus.domain.*
import com.kumulus.classes.ImageTool
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils

@Transactional
class FilesystemService {
    
    def generateLiteral() {
        String literal = System.currentTimeMillis()
        literal += RandomStringUtils.random(2, true, true).toUpperCase()
        return(literal)
    }
    
    def processUploadFile(node, uFile) {
        if(node && uFile) {
            
            def literal = generateLiteral()
            String path = uFile.path.replaceAll(uFile.name, {""})
            
            // load the imported image to buffer
            def imageTool = new ImageTool()
            imageTool.load(uFile.path)
            
            // create the scan image
            def scanImage = new Image(
                status: Image.STAGED,
                file: uFile,
                thumbnail: false,
                compressed: false,
                height: imageTool.getHeight(),
                width: imageTool.getWidth(),
                literal: literal
            )
            
            imageTool.saveOriginal()
            imageTool.writeResult(path+literal+"-S.tiff", "TIFF")
            imageTool.writeResult(path+literal+"-S.jpg", "JPEG")
            imageTool.thumbnail(300)
            imageTool.writeResult(path+literal+"-T.jpg", "JPEG")   
            
        }
    }
}