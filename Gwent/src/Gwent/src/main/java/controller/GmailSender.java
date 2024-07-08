package controller;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GmailSender {
    private static String EMAIL_FROM = "kooroshimani2023@gmail.com";
    private String EMAIL_TO;
    private static String APP_PASSWORD = "yourAppPassword";//todo goto your email and find app password and generate one, then put it here
    private String text;

    public GmailSender(String EMAIL_TO, String text) throws Exception {
        this.EMAIL_TO = EMAIL_TO;
        this.text = text;
//        this.send();
    }

    public void send() throws Exception {
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(EMAIL_FROM));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.EMAIL_TO));
        message.setSubject("Email subject");
        message.setText(text);
//        String link = "<a href=\"http://www.example.com\">Click here</a>";
//        String body = "Please verify your email address by clicking on this link: " + link;
//        message.setText(body, "UTF-8", "html");
        Transport.send(message);
    }

    private static Session getEmailSession() {
        return Session.getInstance(getGmailProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
            }
        });
    }

    private static Properties getGmailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return prop;
    }
}
//    public class TrackingLinkGenerator {
//        public static String generateTrackingLink(String baseUrl, String userId) {
//            String uniqueId = UUID.randomUUID().toString();
//            return baseUrl + "?user=" + userId + "&id=" + uniqueId;
//        }
//    }

//    ActionCodeSettings actionCodeSettings = ActionCodeSettings.builder()
//            .setUrl("https://www.example.com/checkout?cartId=1234")
//            .setHandleCodeInApp(true)
//            .setIosBundleId("com.example.ios")
//            .setAndroidPackageName("com.example.android")
//            .setAndroidInstallApp(true)
//            .setAndroidMinimumVersion("12")
//            .setDynamicLinkDomain("coolapp.page.link")
//            .build();

