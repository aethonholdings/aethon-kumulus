/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syncservice;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

/**
 *
 * @author theo
 */
public class Properties
{
    /* TODO: Read from file */
    final public String db_username;
    final public String db_password;
    final public String db_url;
    final public String fs_root;
    final public String known_hosts;

    public class PerProjectProperties
    {
        final public String dest_host;
        final public int dest_port;
        final public String dest_user;
        final public String dest_sshkey;
        final public String dest_path;

        public PerProjectProperties(String dest_host, int dest_port, String dest_user, String dest_sshkey, String dest_path)
        {
            this.dest_host = dest_host;
            this.dest_port = dest_port;
            this.dest_user = dest_user;
            this.dest_sshkey = dest_sshkey;
            this.dest_path = dest_path;
        }
    }
    
    final private Map<String, PerProjectProperties> dest = new HashMap<>();
    
    public PerProjectProperties get(String project_name)
    {
        return dest.get(project_name);
    }
    
    public Properties() throws Exception
    {
        final String filename = System.getenv().get("CUMULUS_SYNCSERVICE");
        Wini ini = new Wini(new File(filename));
        db_username = ini.get("Global", "db_username", String.class);
        db_password = ini.get("Global", "db_password", String.class);
        db_url = ini.get("Global", "db_url", String.class);
        fs_root = ini.get("Global", "fs_root", String.class);
        known_hosts = ini.get("Global", "known_hosts", String.class);

        for (Section section: ini.values())
        {
            final String sname = section.getName();
            if (sname.startsWith(":") && sname.length()>1)
            {
                PerProjectProperties p3 = 
                        new PerProjectProperties(section.get("dest_host", String.class),
                                                 section.get("dest_port", Integer.class),
                                                 section.get("dest_user", String.class),
                                                 section.get("dest_sshkey", String.class),
                                                 section.get("dest_path", String.class));
                dest.put(section.getName().substring(1), p3);
            }
        }
    }
}
