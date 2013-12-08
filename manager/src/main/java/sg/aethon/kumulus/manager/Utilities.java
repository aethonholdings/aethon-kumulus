/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager;

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

    public static JdbcTemplate getConnection(String username, String password, String url)
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(url);
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
        message.setText(text, "text/plain");
        
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
