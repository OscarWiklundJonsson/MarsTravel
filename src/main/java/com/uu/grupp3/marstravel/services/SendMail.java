package com.uu.grupp3.marstravel.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMail {

    public void sendEmail(String recipient, String subject, String content) {
        final String username = "marstravelg3@gmail.com";
        final String password = "pwjj ifkb xdnp llkn";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2 TLSv1.3"); // Specify the SSL protocols

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}