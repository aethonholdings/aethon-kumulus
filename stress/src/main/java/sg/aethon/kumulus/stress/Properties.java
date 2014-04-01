/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.aethon.kumulus.stress;

import java.io.File;
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
    }
}
