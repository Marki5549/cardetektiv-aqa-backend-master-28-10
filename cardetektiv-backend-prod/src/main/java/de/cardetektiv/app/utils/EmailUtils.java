package de.cardetektiv.app.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {
    public static void sendEmail(String toEmail, String header, String message) {
        try {
            final String fromEmail = "aqa@cardetektiv.de"; //requires valid gmail id
            final String password = "2af&hW87"; // correct password for gmail id

            Properties props = new Properties();
            props.put("mail.smtp.host", "mail.cardetektiv.de"); //SMTP Host
            props.put("mail.smtp.addSession", "true"); //Enabling SMTP Authentication
            props.put("mail.smtp.port", "25"); //SMTP Port
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };

            Session session = Session.getDefaultInstance(props, auth);
            EmailUtils.sendEmail(session, toEmail, fromEmail, header, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void sendEmail(Session session, String toEmail, String fromEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(fromEmail, "Jenkins build is still unstable: cardetektiv_aqa_backend"));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject(subject, "UTF-8");
            msg.setContent(body, "text/html; charset=utf-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
