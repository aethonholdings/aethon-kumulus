/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.aethon.kumulus.manager;

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
    final public String db_username;
    final public String db_password;
    final public String db_url;
    final public String fs_root;
    final public String known_hosts;
    final public String smtp_server;
    final public int smtp_port;
    final public String smtp_username;
    final public String smtp_password;
    final public String smtp_from;
    
    final public String eph_db_user;
    final public String eph_db_pass;
    final public String eph_db_url;
    final public String eph_ssh_host;
    final public int eph_ssh_port;
    final public String eph_ssh_user;
    final public String eph_ssh_pass;

    public class PerProjectProperties
    {
        final public String eph_ssh_path;

        public PerProjectProperties(String dest_path)
        {
            this.eph_ssh_path = dest_path;
        }
    }
    
    final private Map<String, PerProjectProperties> proj = new HashMap<>();
    
    public PerProjectProperties get(String project_name)
    {
        return proj.get(project_name);
    }
    
    public Properties() throws Exception
    {
        final String filename = System.getenv().get("KUMULUS_MANAGER_CONFIG");
        Wini ini = new Wini(new File(filename));
        known_hosts = ini.get("Global", "known_hosts", String.class);

        db_username = ini.get("Kumulus", "kum_db_username", String.class);
        db_password = ini.get("Kumulus", "kum_db_password", String.class);
        db_url = ini.get("Kumulus", "kum_db_url", String.class);
        fs_root = ini.get("Kumulus", "kum_fs_root", String.class);

        eph_db_user = ini.get("Ephesoft", "eph_db_user", String.class);
        eph_db_pass = ini.get("Ephesoft", "eph_db_pass", String.class);
        eph_db_url = ini.get("Ephesoft", "eph_db_url", String.class);
        eph_ssh_host = ini.get("Ephesoft", "eph_ssh_host", String.class);
        eph_ssh_port = ini.get("Ephesoft", "eph_ssh_port", Integer.class);
        eph_ssh_user = ini.get("Ephesoft", "eph_ssh_username", String.class);
        eph_ssh_pass = ini.get("Ephesoft", "eph_ssh_password", String.class);
        
        smtp_server = ini.get("SMTP", "smtp_server", String.class);
        smtp_port = ini.get("SMTP", "smtp_port", Integer.class);
        smtp_username = ini.get("SMTP", "smtp_username", String.class);
        smtp_password = ini.get("SMTP", "smtp_password", String.class);
        smtp_from = ini.get("SMTP", "smtp_from", String.class);

        for (Section section: ini.values())
        {
            final String sname = section.getName();
            if (sname.startsWith(":") && sname.length()>1)
            {
                PerProjectProperties p3 = 
                        new PerProjectProperties(section.get("eph_ssh_path", String.class));
                proj.put(section.getName().substring(1), p3);
            }
        }
    }
}
