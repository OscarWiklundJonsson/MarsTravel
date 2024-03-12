package com.uu.grupp3.marstravel.services;

import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class SendMail {


    /**
     * Skickar e-post med faktura som bifogad fil.
     * @param recipient E-postadressen som fakturan ska skickas till.
     * @param subject Ämnesraden i e-posten.
     * @param content Innehållet i e-posten.
     * @param attachmentFile Fakturafil som ska bifogas e-posten.
     */
    public void sendEmail(String recipient, String subject, String content, File attachmentFile) {
        final String username = "marstravelg3@gmail.com";
        final String password = "pwjj ifkb xdnp llkn"; // Tyvärr är detta lösenord det rätta, men orkar inte lägga det i en separat fil just nu.

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
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(content);

            multipart.addBodyPart(messageBodyPart);

            
            // Bifoga fil (faktura)
            messageBodyPart = new MimeBodyPart();
            String filename = attachmentFile.getAbsolutePath();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);


            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Mail skickat!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}