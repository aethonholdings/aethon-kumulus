import com.kumulus.domain.*

class BootStrap {

    def init = { servletContext ->
        
        // create Spring roles
        if(!Role.findByAuthority('admin')) def adminRole = new Role(authority: 'admin').save()
        if(!Role.findByAuthority('collect')) def collectRole = new Role(authority: 'collect').save()
        if(!Role.findByAuthority('import')) def importRole = new Role(authority: 'import').save()
        if(!Role.findByAuthority('validate')) def validateRole = new Role(authority: 'validate').save()
        if(!Role.findByAuthority('export')) def exportRole = new Role(authority: 'export').save()
        if(!Role.findByAuthority('view')) def viewRole = new Role(authority: 'view').save()
        
        // create a client company
        if(!Company.findByName('Jee Ah Chian Co')) {
            
            def company = new Company(
                name: 'Jee Ah Chian Co',
                street: 'Kelantan Lane', 
                buildingNumber: '9',
                unitNumber: '06/01',
                postcode: '208628'
            ).save()
            
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
                company: company
            ).save()
            
            UserRole.create user, Role.findByAuthority('collect')
            UserRole.create user, Role.findByAuthority('import')
            UserRole.create user, Role.findByAuthority('validate')
            UserRole.create user, Role.findByAuthority('export')
            UserRole.create user, Role.findByAuthority('view')
            
        }
        
        if(!Company.findByName('Kumulus Pte Ltd')) {
            
            def company = new Company(
                name: 'Kumulus Pte Ltd',
                street: 'Waterloo Street', 
                buildingNumber: '261',
                unitNumber: '03/32',
                postcode: '180261'
            ).save()
            
            def user = new User(
                username: 'admin',
                userId: 'admin',
                password: 'password',
                useridPassword: 'password',
                name: 'admin',
                telephone: '+65 62952533', 
                email: 'konstantinos.dimitriou@aethon.sg',
                accountExpired: false,
                accountLocked: false,
                passwordExpired: false,
                status: 'A',
                collectionRight: 'Y',
                importRight: 'Y',
                separationRight: 'Y',
                importKpiTarget: 1000,
                separationKpiTarget: 0,
                company: company
            ).save()
            
            UserRole.create user, Role.findByAuthority('admin')
            UserRole.create user, Role.findByAuthority('collect')
            UserRole.create user, Role.findByAuthority('import')
            UserRole.create user, Role.findByAuthority('validate')
            UserRole.create user, Role.findByAuthority('export')
            UserRole.create user, Role.findByAuthority('view')
            
        }
        
    }
    def destroy = {
        
    }
}
