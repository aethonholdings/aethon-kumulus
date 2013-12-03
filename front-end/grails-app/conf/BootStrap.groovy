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
            
            UserRole.create user, Role.findByAuthority('ROLE_COLLECT')
            UserRole.create user, Role.findByAuthority('ROLE_IMPORT')
            UserRole.create user, Role.findByAuthority('ROLE_VALIDATE')
            UserRole.create user, Role.findByAuthority('ROLE_EXPORT')
            UserRole.create user, Role.findByAuthority('ROLE_VIEW')
            
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
            
            UserRole.create user, Role.findByAuthority('ROLE_ADMIN')
            UserRole.create user, Role.findByAuthority('ROLE_COLLECT')
            UserRole.create user, Role.findByAuthority('ROLE_IMPORT')
            UserRole.create user, Role.findByAuthority('ROLE_VALIDATE')
            UserRole.create user, Role.findByAuthority('ROLE_EXPORT')
            UserRole.create user, Role.findByAuthority('ROLE_VIEW')
            
        }
        
    }
    def destroy = {
        
    }
}
