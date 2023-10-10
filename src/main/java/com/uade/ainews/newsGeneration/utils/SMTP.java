package com.uade.ainews.newsGeneration.utils;


import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTP {
    public static void sendEmail(String destinatario, String asunto, String mensaje) {

        // Configura las propiedades del servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Gmail
        props.put("mail.smtp.port", "587"); // Puerto SMTP de Gmail
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        // Crea una sesión de correo electrónico autenticada
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("unchainednews.free@gmail.com", "hbil hztp nozp isui");
            }
        });

        try {
            // Crea un objeto MimeMessage
            Message message = new MimeMessage(session);

            // Establece el remitente
            message.setFrom(new InternetAddress(destinatario)); // Cambia a tu dirección de correo

            // Establece el destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

            // Establece el asunto
            message.setSubject(asunto);

            // Establece el contenido del mensaje
            message.setText(mensaje);

            // Envía el mensaje
            Transport.send(message);

            System.out.println("Correo electrónico enviado con éxito.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
