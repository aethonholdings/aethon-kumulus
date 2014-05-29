import com.kumulus.domain.*

class BootStrap {
    
    def filesystemService
    
    def init = { servlentContext ->                         
        ImageIO.scanForPlugins()
    }
    
    def destroy = {
        
    }
}
