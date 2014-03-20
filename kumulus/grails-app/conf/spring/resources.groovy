import com.kumulus.sessioncontext.KumulusUserDetailsContextMapper
import org.krysalis.barcode4j.impl.code39.Code39Bean

// Place your Spring DSL code here
beans = {
    //ldapUserDetailsMapper(KumulusUserDetailsContextMapper) {    
        // bean attributes
    // }
    
    code39Generator(Code39Bean) {
        height = 10
    }
}
