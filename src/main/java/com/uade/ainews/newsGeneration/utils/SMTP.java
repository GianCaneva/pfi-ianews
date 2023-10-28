package com.uade.ainews.newsGeneration.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SMTP {

    public static final String UNCHAINEDNEWS_FREE_GMAIL_COM = "unchainednews.free@gmail.com";
    public static final String GMAIL_PASSWORD = "hbil hztp nozp isui";

    // Send an email with the new password
    public static void sendEmail(String recipient, String subject, String bodyMessage) {

        // Configure mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP Server
        props.put("mail.smtp.port", "587"); // Gmail SMTP port
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        // Create an authenticated email session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(UNCHAINEDNEWS_FREE_GMAIL_COM, GMAIL_PASSWORD);
            }
        });

        try {
            // Create a MimeMessage objet
            Message message = new MimeMessage(session);

            // Set the sender
            message.setFrom(new InternetAddress(UNCHAINEDNEWS_FREE_GMAIL_COM));

            // Set the recipient
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

            // Set the subject
            message.setSubject(subject);

            // Set the content of the body message
            message.setText(bodyMessage);

            // Send the message
            Transport.send(message);

            System.out.println("Correo electrónico enviado con éxito.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
