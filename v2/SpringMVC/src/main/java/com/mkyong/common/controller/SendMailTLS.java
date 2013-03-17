package com.mkyong.common.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMailTLS {
	
	public void sendEmail( String recipientEmail, String subject, String recipientName, String appointmentType, String date, String time, String location ) {
		EmailNotificationComposer composer = new EmailNotificationComposer();
		String messageContent = composer.compose( recipientName, appointmentType, date, time, location ); 
		sendEmail( recipientEmail, subject, messageContent );
	}
 
	public void sendEmail( String recipientEmail, String subject, String content ) {
 
		String senderEmail = "patientscheduler@gmail.com"; // replace
		final String username = "patientscheduler"; // replace with username of sender's email address
		final String password = "hacklocal123"; // replace with password of sender's email address
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse( recipientEmail ));
			message.setSubject( subject );
			message.setText( content );
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

