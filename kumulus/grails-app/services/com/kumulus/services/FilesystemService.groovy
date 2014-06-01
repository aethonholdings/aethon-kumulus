package com.kumulus.services

import com.kumulus.domain.*
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import com.lucastex.grails.fileuploader.UFile
import com.lucastex.grails.fileuploader.FileUploaderService
import com.sun.jersey.core.util.*
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.apache.commons.fileupload.disk.DiskFileItem
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import java.nio.file.Files
import java.nio.file.Paths
import javax.imageio.ImageIO
import net.coobird.thumbnailator.Thumbnails

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
    
    Project newProject(Project project) {
        def literal = generateLiteral()
        
        // create the necessary directories
        String path = grailsApplication.config.filesystem.main + literal + "/"
        File targetPath = new File(path)
        targetPath.mkdir()
        targetPath = new File(path + "docs/")
        targetPath.mkdir()
        targetPath = new File(path + "pages/")
        targetPath.mkdir()
        
        // initialise the relevant project variables
        project.literal = literal
        project.path = targetPath
        return(project)
    }
    
    def writeStringToFile(encodedString, filename) {
        def bytes = encodedString.decodeBase64()
        def f = new File(filename)
        f.delete()
        f.withOutputStream { s ->
          s << bytes
        }
    }
    
    def indexImageInFilesystem(literal, page, uFile, timestamp, view, thumbnail) {
        
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
        if (view && thumbnail) {
            // if view and thumbnail is supplied, then everything is already done by scando
            Files.copy(Paths.get(uFile.path), Paths.get(imageFiles.scanImage.getAbsolutePath()))
            writeStringToFile(view, imageFiles.viewImage.getAbsolutePath())
            writeStringToFile(thumbnail, imageFiles.thumbnailImage.getAbsolutePath())
        }
        else {
            def image = ImageIO.read(new File(uFile.path));
            ImageIO.write(image, "TIFF", new File(imageFiles.scanImage.getAbsolutePath()));
            ImageIO.write(image, "JPEG", new File(imageFiles.viewImage.getAbsolutePath()));
            // create thumbnail from the view image (for scando)
            Thumbnails.of(new File(imageFiles.viewImage.getAbsolutePath()))
                    .size(200, 200)
                    .toFile(new File(imageFiles.thumbnailImage.getAbsolutePath()));
        }
        
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
            def imageIO = ImageIO.read(new File(file.path))
            def image = new Image(
                height: imageIO.getHeight(),
                width: imageIO.getWidth(),
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
    
    def writeStringToImageFile(encodedImageString, filename) {
        
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
}