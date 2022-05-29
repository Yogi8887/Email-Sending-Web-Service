package com.email.service;

import com.email.Entity.EmailRequest;
import com.email.repository.EmailRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private EmailRequestRepository emailRequestRepository;

    @Override
    public boolean emailSend(EmailRequest emailRequest) {
        boolean f = false;

        // this is the main email ID
        String from = "kyogi3125@gmail.com";

        // variable for gmail
        String host = "smtp.gmail.com";
        // get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties" + properties);

        // setting important information to properties object

        // host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: to get the session object..
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kyogi3125@gmail.com", "*******");

            }
        });
        session.setDebug(true);

        // Step2 : compose the message [text, multi media]
        MimeMessage m = new MimeMessage(session);
        try {
            // from email
            m.setFrom(from);
            // adding recipient to message
            // if you want pass multi Address the use InternetAddress Array
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRequest.getTo()));

            // adding subject to message
            m.setSubject(emailRequest.getSubject());
            // adding message of Email
            m.setText(emailRequest.getMessage());

            // Step 3: send the message using transport class
            Transport.send(m);
            f= true;
            System.out.println("sent Success...!!");
            EmailRequest e = new EmailRequest();
            e.setTo(emailRequest.getTo());
            e.setSubject(emailRequest.getSubject());
            e.setMessage(emailRequest.getMessage());

            emailRequestRepository.save(e);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
