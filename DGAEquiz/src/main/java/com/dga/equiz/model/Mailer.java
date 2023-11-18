package com.dga.equiz.model;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer {
    public static void send(String from, String password, String to, String sub, String msg) {
        // Cài đặt thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Lấy Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Soạn tin nhắn
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            // Gửi tin nhắn
            Transport.send(message);
            System.out.println("Tin nhắn gửi thành công");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

