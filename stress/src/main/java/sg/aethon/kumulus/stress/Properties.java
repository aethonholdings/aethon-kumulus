/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.aethon.kumulus.stress;

import com.sun.jersey.core.util.Base64;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.ini4j.Wini;

class MandatoryPropertyNotSet extends RuntimeException { }

/**
 *
 * @author theo
 */
public class Properties
{
    final public String db_username;
    final public String db_password;
    final public String db_url;
    final public int db_timeout;

    final public String site_url;
    final public String auth_username;
    final public String auth_password;

    final public int stress_threads;
    final public int stress_batch;
    final public String stress_image;
    
    public Properties() throws Exception
    {
        final String filename = System.getenv().get("KUMULUS_STRESS_CONFIG");
        Wini ini = new Wini(new File(filename))
        {
            @Override
            public <T> T get(Object sectionName, Object optionName, Class<T> clazz)
            {
                T result = super.get(sectionName, optionName, clazz);
                if (!"".equals(result))
                    return result;
                else
                    throw new MandatoryPropertyNotSet();
            }
        };
        db_username = ini.get("Database", "username", String.class);
        db_password = ini.get("Database", "password", String.class);
        db_url = ini.get("Database", "url", String.class);
        db_timeout = ini.get("Database", "timeout", Integer.class);
        site_url = ini.get("Website", "url", String.class);
        auth_username = ini.get("Authentication", "username", String.class);
        auth_password = ini.get("Authentication", "password", String.class);
        stress_threads = ini.get("Stress", "threads", Integer.class);
        stress_batch = ini.get("Stress", "batch", Integer.class);
        stress_image = new String(Base64.encode(FileUtils.readFileToByteArray(new File(ini.get("Stress", "image", String.class)))));
    }
}
