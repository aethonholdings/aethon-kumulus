package com.kumulus.services

import com.kumulus.domain.*
import org.grails.plugins.imagetools.ImageTool
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import com.lucastex.grails.fileuploader.UFile
import com.lucastex.grails.fileuploader.FileUploaderService
import com.sun.jersey.core.util.*
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.apache.commons.fileupload.disk.DiskFileItem
import org.codehaus.groovy.grails.commons.ConfigurationHolder

@Transactional
class FilesystemService {
    
    def grailsApplication
    def permissionsService  // push this to controllers
    def tikaService
    def fileUploaderService
    
    def generateLiteral() {
        String literal = System.currentTimeMillis()
        literal += RandomStringUtils.random(2, true, true).toUpperCase()
        return(literal)
    }

    def indexDocument(Document document) {
        if(document.file?.path) {
            File file = new File(document.file.path)
            return tikaService.parseFile(file)
        } else {
            return null
        }
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
        def client = new Company([name: params?.ClientName])         // NEED TO CHECK THE PREEXISTENCE OF THE COMPANY
        client.save()
        def project = new Project([
                projectName: params?.projectName, 
                comment: params?.comment, 
                status: "A", 
                company: permissionsService.getCompany(), 
                lineItems:[], 
                nodes:[], 
                client: client, 
                literal: literal, 
                path: path, 
                ownerId: permissionsService.getUsername()])
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
            new File(targetPath.getAbsolutePath()).mkdirs()
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
            images.put(key, image)
        }
        return(images)
    }
    
    def createUFile(filename) {
        def file = new File(filename)
        def ufile = new UFile()
        ufile.name = filename.substring(filename.lastIndexOf('/') + 1)
        ufile.size = file.size()
        ufile.extension = filename.substring(filename.lastIndexOf('.') + 1)
        ufile.dateUploaded = new Date()
        ufile.path = file.getAbsolutePath()
        ufile.downloads = 0
        ufile.save()
        return(ufile)
    }
    
    def deriveFilenameForPdf(document) {
        return grailsApplication.config.filesystem.staging + document.literal + '.pdf'
    }
        
    def indexPdfInFilesystem(document, filename) {
        def path = grailsApplication.config.filesystem.main + document.project.literal + '/docs/'
        new File(path).mkdirs()
        def dest = path + document.literal + '.pdf'
        new File(filename).renameTo(dest)
        return createUFile(dest)
    }
    
    def stagingFlush(uFile) {
        // clean up the staging entities
        File stagingPath = new File(uFile.path.substring(0, uFile.path.size()-uFile.name.size()))
        stagingPath.deleteDir()
        return(true)
    }
    
    def writeStringToImageFile(encodedImageString, filename, locale) {
        
        // PARAMETRISE THE MAX SIZE
        DiskFileItem imageFileItem = new DiskFileItem("file", null, false, filename, 40000000, new File(grailsApplication.config.filesystem.staging))
        byte[] scannedImageBytes = encodedImageString.decodeBase64()
        imageFileItem.getOutputStream().write(scannedImageBytes)
        imageFileItem.getOutputStream().close()
        CommonsMultipartFile imageFile = new CommonsMultipartFile(imageFileItem)
        
        // save the file to the filesystem
        def path = ConfigurationHolder.config.fileuploader["image"].path
        if (!path.endsWith('/')) path = path + "/"
        path = path + generateLiteral() + "/"
        if (!new File(path).mkdirs()) log.info "FileUploader plugin couldn't create directories: [${path}]"
        path = path + filename
        imageFile.transferTo(new File(path))
        
        //save the file on the database
        def ufile = new UFile()
        ufile.name = filename
        ufile.size = imageFile.size
        ufile.extension = filename.substring(filename.lastIndexOf('.') + 1)        
        ufile.dateUploaded = new Date()
        ufile.path = path
        ufile.downloads = 0
        ufile.save()
        return(ufile)
        
    }
    
    def renderFileInBase64(UFile ufile) {
        
        // get the image byte buffer
        FileInputStream inputStream = new FileInputStream(ufile.path)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        byte[] block = new byte[1024]
        int bytesRead = 0
        while ((bytesRead = inputStream.read(block)) != -1) {
            outputStream.write(block, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();
        
        // convert to Base 64
        String render = imageBytes.encodeBase64().toString()

        return(render)
    }
    
   
}