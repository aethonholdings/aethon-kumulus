import com.kumulus.domain.*

class BootStrap {
    
    def filesystemService
    
    def init = { servlentContext ->                         
//        def container = Nodes.findByBarcode('25031821ZHTW')
//        def project = Project.findBydId('1')
//        def node, literal, page, document, taskItem, scanImage, thumbnailImage, viewImage
//        (0..9).each {
//            literal = filesystemService.generateLiteral()
//            node = new Node([type: 'P', status: 0, project: project, parent: container, creatorId: 'kumulus', lastUpdateId: 'kumulus'])
//            node.save()
//            page = new Page([])
//        }
    }
    
    def destroy = {
        
    }
}
