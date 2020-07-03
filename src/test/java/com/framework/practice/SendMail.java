package com.framework.practice;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	
	public static void main(String[] args) {
		// Recipient's email ID needs to be mentioned.
        String to = "jayakumar@totient.co.in";

        // Sender's email ID needs to be mentioned
        String from = "jakay34@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("jakay34@gmail.com", "bullet07@");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            message.addRecipients(Message.RecipientType.CC, "yatheesh@totient.co.in,uday@totient.co.in");
            // Set Subject: header field
            message.setSubject("TESTING REPORT!!!");
            
            MimeBodyPart mimebody1 = new MimeBodyPart();
            mimebody1.setText("Hi Dev Team,\n"
            		+ "Automation test script has been executed successfully!. Please find the attachment. \n \n \n \n "
            		+ "Thanks & Regrads \n"
            		+ "Testting Team");
            MimeBodyPart mimebody = new MimeBodyPart();
        
            try {
				mimebody.attachFile("D:\\Jakay\\RestAPIFramework\\src\\main\\java\\com\\framework\\testdata\\TestDataWithValidation.xlsx");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Multipart multipart = new MimeMultipart();
            
            multipart.addBodyPart(mimebody);
            multipart.addBodyPart(mimebody1);
            message.setContent(multipart);            
            
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }  
}
