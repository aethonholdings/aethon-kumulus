package com.kumulus.services

import com.kumulus.domain.*
import com.kumulus.classes.ImageTool
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import com.lucastex.grails.fileuploader.UFile

@Transactional
class FilesystemService {
    
    def grailsApplication
    
    def generateLiteral() {
        String literal = System.currentTimeMillis()
        literal += RandomStringUtils.random(2, true, true).toUpperCase()
        return(literal)
    }
    
    def processUploadFile(node, uFile) {
        if(node && uFile) {
            
            // define the necessary paths and files
            def literal = generateLiteral()
            Date timestamp = new Date()
            File stagingPath = new File(uFile.path.replace(uFile.name, ""))
            File targetPath = new File(grailsApplication.config.filesystem.main + "/" + node.project.literal + "/pages/" + literal + "/")
            String tmpName = stagingPath.canonicalPath + "/" + literal
            def imageFiles = [
                scanImage: new File(tmpName + "-S.tiff"), 
                viewImage: new File(tmpName +"-V.jpg"),
                thumbnailImage: new File(tmpName +"-T.jpg")
            ]
                        
            // load the imported image to buffer and generate the image files
            def imageTool = new ImageTool()
            imageTool.load(uFile.path)            
            imageTool.writeResult(imageFiles.scanImage.getAbsolutePath(), "TIFF")
            imageTool.writeResult(imageFiles.viewImage.getAbsolutePath(), "JPEG")
            imageTool.thumbnail(300)
            imageTool.writeResult(imageFiles.thumbnailImage.getAbsolutePath(), "JPEG")   
            
            def document = new Document(
                status: Document.EDITABLE,
                type: null,
                company: null,
                date: null,
                literal: literal,
                file: null
                )
            document.save()
            
            def page = new Page(
                number: 1,
                first: true,
                last: true,
                literal: literal,
                lineItems: [],
                node: node, 
                document: document
            )
                                    
            // now move the image files to the main filesystem and generate the relevant images and UFiles
            def images = [:]
            targetPath.mkdir()
            imageFiles.each() { key, value ->
                def targetFile = new File(targetPath.getAbsolutePath() + "/" + value.name)
                value.renameTo(targetFile)
                def file = new UFile(
                    size: value?.length(),
                    path: targetFile.getAbsolutePath(),
                    name: targetFile.name,
                    extension: value.name.tokenize('.')?.last(),
                    dateUploaded: timestamp,
                    downloads: 0
                )
                file.save()
                imageTool.load(file.path)
                def image = new Image(
                    height: imageTool.getHeight(),
                    width: imageTool.getWidth(),
                    file: file,
                    thumbnail: false,
                    compressed: false
                )
                image.save()
                images.put(key, image)
            }
            page.scanImage = images.scanImage
            page.viewImage = images.viewImage
            page.thumbnailImage = images.thumbnailImage
            page.save()
            
            // clean up the staging entities
            uFile.delete()
            stagingPath.deleteDir() 
        }
    }
}