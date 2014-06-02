import com.kumulus.domain.*
import javax.imageio.ImageIO

class BootStrap {
    
    def filesystemService
    
    def init = { servlentContext ->                         
        ImageIO.scanForPlugins()
    }
    
    def destroy = {
        
    }
}
