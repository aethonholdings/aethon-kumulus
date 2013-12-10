import com.kumulus.domain.*

class BootStrap {

    def init = { servletContext ->
                        
        // create a project
        if(!Project.findByProjectName('test project')) {
            def project = new Project(
                projectName: 'test project',
                status: '001',
                clientLDAPId: 'Kumulus Pte Ltd'
            )
            if(!project.save()) project.errors.allErrors.each { error -> println "${error}" }
        }
        
        // create a set of nodes for the project
        if(!Nodes.findByProject(Project?.findByProjectName('test project'))) {
            def project = Project.findByProjectName('test project') 
            def rootNode = new Nodes(
                name: 'root node',
                comment: 'no comment',
                type: 'ROOT', 
                createDateTime: new Date(),
                lastUpdateDatetime: new Date(),
                project: project
            )
            if(!rootNode.save()) rootNode.errors.allErrors.each { error -> println "${error}" }
            
            def containerNode = new Nodes(
                name: 'Container 1',
                comment: 'no comment',
                barcode: 'AE18101978',
                type: 'CONTAINER', 
                creatorLDAPUid: 'kumulus', 
                createDateTime: new Date(),
                lastUpdateDatetime: new Date(),
                parent: rootNode, 
                project: project       
            ).save()
            
            containerNode = new Nodes(
                name: 'Container 2',
                comment: 'no comment',
                barcode: 'AE26051979',
                type: 'CONTAINER', 
                creatorLDAPUid: 'kumulus', 
                createDateTime: new Date(),
                lastUpdateDatetime: new Date(),
                parent: rootNode, 
                project: project       
            ).save()
            
            def documentNode = new Nodes(
                name: 'Document 1',
                comment: 'no comment',
                type: 'DOCUMENT', 
                creatorLDAPUid: 'kumulus', 
                createDateTime: new Date(),
                lastUpdateDatetime: new Date(),
                parent: containerNode, 
                project: project       
            ).save()
            
            documentNode = new Nodes(
                name: 'Document 2',
                comment: 'no comment',
                type: 'DOCUMENT', 
                creatorLDAPUid: 'kumulus', 
                createDateTime: new Date(),
                lastUpdateDatetime: new Date(),
                parent: containerNode, 
                project: project       
            ).save()
            
            documentNode = new Nodes(
                name: 'Document 3',
                comment: 'no comment',
                type: 'DOCUMENT', 
                creatorLDAPUid: 'kumulus', 
                createDateTime: new Date(),
                lastUpdateDatetime: new Date(),
                parent: containerNode, 
                project: project       
            ).save()
            
        }
        
        // create a task assigned to the test user
        if(!Task.findByOwnerLDAPUid('kumulus')) {
            def task = new Task (
                ownerLDAPUid: 'kumulus',
                type: 'TASK_VALIDATE',
                created: new Date(), 
                status: 'PENDING'
            )
            task.addToNodes(Nodes.findByName('Document 1'))
            task.addToNodes(Nodes.findByName('Document 2'))
            if(!task.save()) task.errors.allErrors.each { error -> println "${error}" }
            
            task = new Task (
                ownerLDAPUid: 'kumulus',
                type: 'TASK_CLASSIFY',
                created: new Date(), 
                status: 'PENDING'
            )
            task.addToNodes(Nodes.findByName('Document 3'))
            if(!task.save()) task.errors.allErrors.each { error -> println "${error}" }
        }
        
    }

    def destroy = {
        
    }
}
