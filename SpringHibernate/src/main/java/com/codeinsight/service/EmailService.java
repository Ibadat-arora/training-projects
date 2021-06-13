package com.codeinsight.service;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public void sendEmail(String reciever ,String emailBody, String subject) {
		
		String host="smtp.gmail.com";
		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port",.465);
		properties.put("mailsmtp.ssl.enable","true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ibadat.arora@thecodeinsight.com","rraygjbeoolmqwlm");
			}
		});
		
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom("ibadat.arora@thecodeinsight.com");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
			message.setSubject(subject);
			message.setText(emailBody);
			
			Transport.send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
