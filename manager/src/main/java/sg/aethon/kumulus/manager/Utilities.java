/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

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

    private static JdbcTemplate getConnection(String username, String password, String url)
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

    public static JdbcTemplate getEphesoftConnection(Properties p)
    {
        return Utilities.getConnection(p.eph_db_user, p.eph_db_pass, p.eph_db_url);
    }

    public static JdbcTemplate getKumulusConnection(Properties p)
    {
        return Utilities.getConnection(p.db_username, p.db_password, p.db_url);
    }

    public static class Transaction implements AutoCloseable
    {
        private final PlatformTransactionManager manager;
        private final TransactionStatus trans;
        private boolean success = false;
        Transaction(final Properties p, JdbcTemplate conn, final int isolation)
        {
            this.manager = new DataSourceTransactionManager(conn.getDataSource());
            this.trans = manager.getTransaction(new TransactionDefinition()
            {
                @Override
                public int getPropagationBehavior() { return PROPAGATION_REQUIRED; }
                @Override
                public int getIsolationLevel() { return isolation; }
                @Override
                public int getTimeout() { return p.db_timeout; }
                @Override
                public boolean isReadOnly() { return false; }
                @Override
                public String getName() { return null; }
            });
        }
        @Override
        public void close()
        {
            if (success)
                manager.commit(trans);
            else
                manager.rollback(trans);
        }
        public void success()
        {
            this.success = true;
        }
    }

    public static Transaction createTransaction(Properties p, JdbcTemplate conn)
    {
        return new Transaction(p, conn, TransactionDefinition.ISOLATION_READ_COMMITTED);
    }
    
    public static Transaction createSerializableTransaction(Properties p, JdbcTemplate conn)
    {
        return new Transaction(p, conn, TransactionDefinition.ISOLATION_SERIALIZABLE);
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

    /**
     * Method to get the current timestamp: the whole idea is that we select
     * the Ephesoft database to be the definitive one.
     * @param p
     * @return
     */
    public static Timestamp now(Properties p)
    {
        JdbcTemplate conn = Utilities.getEphesoftConnection(p);
        String time = conn.queryForObject("select now()", String.class);
        return Timestamp.valueOf(time);
    }

}
