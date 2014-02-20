package com.kumulus.services

import com.kumulus.domain.*
import com.kumulus.classes.ImageTool
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import com.lucastex.grails.fileuploader.UFile

@Transactional
class FilesystemService {
    
    def grailsApplication
    def permissionsService  // push this to controllers
    def tikaService
    
    def generateLiteral() {
        String literal = System.currentTimeMillis()
        literal += RandomStringUtils.random(2, true, true).toUpperCase()
        return(literal)
    }
        
    def indexDocument(document, uFile) {
        document.file = uFile
        File file = new File(uFile.path)
        document.text = tikaService.parseFile(file)
        document.save()
    }
    
    def newProject(params) {
        def literal = generateLiteral()
        
        // create the necessary directories
        String path = grailsApplication.config.filesystem.main + literal + "/"
        File targetPath = new File(path)
        targetPath.mkdir()
        targetPath = new File(path + "docs/")
        targetPath.mkdir()
        targetPath = new File(path + "pages/")
        targetPath.mkdir()
        
        // create the database instances
        def client = new Company([name: params?.client])         // NEED TO CHECK THE PREEXISTENCE OF THE COMPANY
        client.save()
        def project = new Project([projectName: params?.projectName, comment: params?.comment, status: "A", company: permissionsService.getCompany(), lineItems:[], nodes:[], client: client, literal: literal, path: path])
        project.save()
        return(project)
    }
    
    def indexImageInFilesystem(literal, page, uFile, timestamp) {
        
        // NEED ERROR HANDLING HERE
        
        // create the necessary directories
        File stagingPath = new File(uFile.path.replace(uFile.name, ""))
        File targetPath = new File(grailsApplication.config.filesystem.main + "/" + page.node.project.literal + "/pages/" + literal + "/")
        targetPath.mkdir()
        
        // create the necessary output files
        String tmpNameBase = stagingPath.canonicalPath + "/" + literal
        def imageFiles = [
            scanImage: new File(tmpNameBase + "-S.tiff"), 
            viewImage: new File(tmpNameBase +"-V.jpg"),
            thumbnailImage: new File(tmpNameBase +"-T.jpg")
        ]
        
        // load the imported image to buffer and generate the write to the staging area output files
        def imageTool = new ImageTool()
        imageTool.load(uFile.path)            
        imageTool.writeResult(imageFiles.scanImage.getAbsolutePath(), "TIFF")
        imageTool.writeResult(imageFiles.viewImage.getAbsolutePath(), "JPEG")
        imageTool.thumbnail(300)
        imageTool.writeResult(imageFiles.thumbnailImage.getAbsolutePath(), "JPEG")   
        
        // move the files from the staging area to the main area
        def images = [:]
        imageFiles.each() { key, value ->
            def targetFile = new File(targetPath.getAbsolutePath() + "/" + value.name)
            value.renameTo(targetFile)
            def file = new UFile(
                size: targetFile.size(),
                path: targetFile.getAbsolutePath(),
                name: targetFile.name,
                extension: value.name.tokenize('.')?.last(),
                dateUploaded: timestamp,
                downloads: 0
            )
            file.save()
            println(file)
            
            // need to modify entry here based on whether it is a scan image or thumbnail or compressed
            imageTool.load(file.path)
            def image = new Image(
                height: imageTool.getHeight(),
                width: imageTool.getWidth(),
                file: file,
                thumbnail: false,
                compressed: false,
                page: page
            )
            image.save()
            println(image)
            images.put(key, image)
        }
        
        // clean up the staging entities
        // uFile.delete(flush:true)
        // stagingPath.deleteDir()
        return(images)
    }
   
}