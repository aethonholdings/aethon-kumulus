/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.kumulus.utility

import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DirContextOperations
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper

class MyUserDetailsContextMapper implements UserDetailsContextMapper {

    UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection authorities) {

        // String fullname =  ctx.originalAttrs.attrs['name'].values[0]
        // String email = ctx.originalAttrs.attrs['mail'].values[0].toString().toLowerCase()
        // username = ctx.originalAttrs.attrs['samaccountname'].values[0].toString().toLowerCase()
        // String username = ctx.originalAttrs.attrs['samaccountname'].values[0].toString().toLowerCase()
        // def title = ctx.originalAttrs.attrs['title']

        def userDetails = new User(username, null, true, true, true, true, authorities)
        return userDetails
    }        

    void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
        throw new IllegalStateException("Only retrieving data from LDAP is currently supported")
    }
}
