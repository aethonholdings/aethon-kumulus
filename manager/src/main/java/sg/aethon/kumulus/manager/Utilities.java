/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author theo
 */
public class Utilities {
    
    private static class CacheKey
    {
        private final String username;
        private final String password;
        private final String url;
        public CacheKey(String username, String password, String url)
        {
            this.username = username;
            this.password = password;
            this.url = url;
        }
        @Override
        public boolean equals(Object object)
        {
            if (this == object)
                    return true;
            if (object == null)
                    return false;
            if (!(object instanceof CacheKey))
                    return false;
            CacheKey that = (CacheKey) object;
            return this.username.equals(that.username) &&
                    this.password.equals(that.password) &&
                    this.url.equals(that.url);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 31 * hash + Objects.hashCode(this.username);
            hash = 31 * hash + Objects.hashCode(this.password);
            hash = 31 * hash + Objects.hashCode(this.url);
            return hash;
        }
    }
    
    private static final Map<CacheKey, DriverManagerDataSource> dscache = new HashMap();

    public static JdbcTemplate getConnection(String username, String password, String url)
    {
        DriverManagerDataSource ds;
        CacheKey key = new CacheKey(username, password, url);
        synchronized (dscache)
        {
            if (dscache.containsKey(key))
                ds = dscache.get(key);
            else
            {
                ds = new DriverManagerDataSource();
                ds.setDriverClassName("com.mysql.jdbc.Driver");
                ds.setUsername(username);
                ds.setPassword(password);
                ds.setUrl(url);
                dscache.put(key, ds);
            }
        }
        return new JdbcTemplate(ds);
    }

    public static void sendEmail(Properties p, String recipient, String subject, String text)
            throws Exception
    {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", p.smtp_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        final javax.mail.Session session = javax.mail.Session.getInstance(props);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(p.smtp_from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setText(text);
        
        Transport transport = session.getTransport();
        try
        {
            transport.connect(p.smtp_server, p.smtp_username, p.smtp_password);
            transport.sendMessage(message, message.getAllRecipients());
        }
        finally
        {
            transport.close();
        }
    }

}
