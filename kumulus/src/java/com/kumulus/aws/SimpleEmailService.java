/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kumulus.aws;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author theo
 */
public class SimpleEmailService
{
    private final String smtp_server;
    private final int smtp_port;
    private final String smtp_username;
    private final String smtp_password;
    private final String smtp_from;
    private String smtp_default_to;
    
    public SimpleEmailService(String smtp_server, int smtp_port,
                              String smtp_username, String smtp_password,
                              String smtp_from, String smtp_to)
    {
        this.smtp_server = smtp_server;
        this.smtp_port = smtp_port;
        this.smtp_username = smtp_username;
        this.smtp_password = smtp_password;
        this.smtp_from = smtp_from;
    }

    public void setDefaultRecipient(String smtp_default_to)
    {
        this.smtp_default_to = smtp_default_to;
    }
    
    public void sendEmail(String subject, String text)
            throws Exception
    {
        sendEmail(smtp_default_to, subject, text);
    }
    
    public void sendEmail(String to, String subject, String text)
            throws Exception
    {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", this.smtp_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        final javax.mail.Session session = javax.mail.Session.getInstance(props);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.smtp_from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(text);
        
        Transport transport = session.getTransport();
        try
        {
            transport.connect(this.smtp_server, this.smtp_username, this.smtp_password);
            transport.sendMessage(message, message.getAllRecipients());
        }
        finally
        {
            transport.close();
        }
    }
}
