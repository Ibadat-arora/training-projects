package com.codeinsight.service;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Value("${mail.smtp.host}")
	private String HOST;
	
	@Value("${mail.smtp.port}")
	private String PORT ;
	
	@Value("${mail.smtp.ssl.enable}")
	private String SSL_ENABLE;
	
	@Value("${mail.smtp.auth}")
	private String SMTP_AUTH;
	
	@Value("${mail.smtp.starttls.enable}")
	private String START_TLS_ENABLE; 
	
	@Value("${mail.username}")
	private String USERNAME; 
	
	@Value("${mail.password}")
	private String PASSWORD;

	public void sendEmail(String reciever ,String emailBody, String subject) {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.host", HOST);
		properties.put("mail.smtp.port",PORT);
		properties.put("mailsmtp.ssl.enable",SSL_ENABLE);
		properties.put("mail.smtp.auth", SMTP_AUTH);
		properties.put("mail.smtp.starttls.enable", START_TLS_ENABLE);
		
		Session session = Session.getInstance(properties,new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME,PASSWORD);
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
