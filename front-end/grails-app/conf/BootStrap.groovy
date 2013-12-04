import com.kumulus.domain.*

class BootStrap {

    def init = { servletContext ->
        
        // create Spring roles
        if(!Role.findByAuthority('ROLE_ADMIN')) def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        if(!Role.findByAuthority('ROLE_COLLECT')) def collectRole = new Role(authority: 'ROLE_COLLECT').save()
        if(!Role.findByAuthority('ROLE_IMPORT')) def importRole = new Role(authority: 'ROLE_IMPORT').save()
        if(!Role.findByAuthority('ROLE_VALIDATE')) def validateRole = new Role(authority: 'ROLE_VALIDATE').save()
        if(!Role.findByAuthority('ROLE_EXPORT')) def exportRole = new Role(authority: 'ROLE_EXPORT').save()
        if(!Role.findByAuthority('ROLE_VIEW')) def viewRole = new Role(authority: 'ROLE_VIEW').save()
        
        // create a client company
        if(!Company.findByName('Jee Ah Chian Co')) def company = new Company(
                name: 'Jee Ah Chian Co',
                street: 'Kelantan Lane', 
                buildingNumber: '9',
                unitNumber: '06/01',
                postcode: '208628'
            ).save()
        
        if(!User.findByUsername('test')) { 
            def user = new User(
                username: 'test',
                userId: 'test',
                password: 'password',
                useridPassword: 'password',
                name: 'test',
                telephone: '+65 62952533', 
                email: 'test@singnet.com.sg',
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                status: 'A',
                collectionRight: 'Y',
                importRight: 'Y',
                separationRight: 'Y',
                importKpiTarget: 1000,
                separationKpiTarget: 0,
                company: Company.findByName('Jee Ah Chian Co')
            ).save()
            
            UserRole.create user, Role.findByAuthority('ROLE_ADMIN')
            UserRole.create user, Role.findByAuthority('ROLE_COLLECT')
            UserRole.create user, Role.findByAuthority('ROLE_IMPORT')
            UserRole.create user, Role.findByAuthority('ROLE_VALIDATE')
            UserRole.create user, Role.findByAuthority('ROLE_EXPORT')
            UserRole.create user, Role.findByAuthority('ROLE_VIEW')
            
        }
        
        // create a project
        if(!Project.findByProjectName('test project')) {
            def project = new Project(
                projectName: 'test project',
                status: '001',
                client: Company.findByName('Jee Ah Chian Co')
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
                createDateTime: new Date(),
                lastUpdateDatetime: new Date(),
                parent: rootNode, 
                project: project       
            ).save()
            
            def documentNode = new Nodes(
                name: 'Document 1',
                comment: 'no comment',
                type: 'DOCUMENT', 
                createDateTime: new Date(),
                lastUpdateDatetime: new Date(),
                parent: containerNode, 
                project: project       
            ).save()
            
            documentNode = new Nodes(
                name: 'Document 2',
                comment: 'no comment',
                type: 'DOCUMENT', 
                createDateTime: new Date(),
                lastUpdateDatetime: new Date(),
                parent: containerNode, 
                project: project       
            ).save()
            
        }
        
        // create a task assigned to the test user
        if(Nodes.findByBarcode('AE18101978') && Nodes.findByBarcode('AE26051979') && !Task.findByOwner(User.findByUsername('test'))) {
            def task = new Task (
                owner: User.findByUsername('test'),
                type: 'TASK_VALIDATION',
                created: new Date(), 
                status: 'PENDING'
            )
            task.addToNodes(Nodes.findByName('Document 1'))
            task.addToNodes(Nodes.findByName('Document 2'))
            if(!task.save()) task.errors.allErrors.each { error -> println "${error}" } 
        }
    }

    def destroy = {
        
    }
}
