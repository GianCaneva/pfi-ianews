package com.uade.ainews.utils;


import com.uade.ainews.newsGeneration.utils.SMTP;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SMTPTest extends TestCase {

    @Test
    public void testSendEmail() {
        //send email with the new password
        String destinatario = "gfocaneva@gmail.com";
        String asunto = "Correo de prueba";
        String mensaje = "Hola, esto es un correo de prueba desde Java.";
        SMTP.sendEmail(destinatario, asunto, mensaje);
    }
}