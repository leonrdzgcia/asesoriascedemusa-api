package com.example.demo.config;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void sendEmail(String to, String subject, String body) {


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Cambia esto a tu servidor SMTP
        props.put("mail.smtp.port", "587"); // El puerto SMTP
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Usar STARTTLS

    // Autenticación
    final String username = "leongzagza@gmail.com"; // Cambia esto a tu correo electrónico
    final String password = "coky efwg jlbo gxre"; // Cambia esto a tu contraseña

    Session session = Session.getInstance(props, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

        try {
        // Crear un mensaje
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("leongzagza@gmail.com"));
        // Cambia esto a tu correo electrónico
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        // Enviar el mensaje
        Transport.send(message);

        System.out.println("Correo enviado correctamente");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
    }

    public static void main(String[] args) {
        // Llamar al método para enviar el correo electrónico
        // correo destino / asunto del correo / cuerpo el correo
        sendEmail("jonathan21dic@gmail.com",
                "Prueba correo Jonathan",
                "Correo para restablecer contraseña");
    }


}
