package com.lab5.controller;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Mailer {

    public static void send(String from, String password, String to,
                            String subject, String body) throws MessagingException {

        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // DÙNG KIỂU MimeMessage, không dùng Message
        MimeMessage mail = new MimeMessage(session);
        mail.setFrom(new InternetAddress(from));
        mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

        // Các hàm này là của MimeMessage
        mail.setSubject(subject, "UTF-8");
        mail.setText(body, "UTF-8", "html");
        mail.setReplyTo(mail.getFrom());

        Transport.send(mail);
    }
}