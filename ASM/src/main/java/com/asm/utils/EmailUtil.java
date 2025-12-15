package com.asm.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtil {
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    private static final String FROM_EMAIL = "loinguyen300899@gmail.com";
    private static final String APP_PASSWORD = "vuxu zbnz yaxo avxw";

    public static void sendOtp(String toEmail, String otp) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL, "ABCNews"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Mã OTP đặt lại mật khẩu");
            message.setText("Mã OTP của bạn là: " + otp + "\nMã có hiệu lực trong 5 phút.");

            Transport.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Gửi email thất bại: " + e.getMessage(), e);
        }
    }
}
