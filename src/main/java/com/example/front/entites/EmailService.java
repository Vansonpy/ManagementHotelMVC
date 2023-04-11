package com.example.front.entites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public void sendMail(String email, String checkinDate, String checkoutDate, String type, Integer price,
			String roomNumber) throws MessagingException {

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			boolean multipart = true;
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, multipart, "utf-8");
			String htmlMsg = "<div class=\"container\">\r\n"
					+ "			<img src=\"path/to/logo.png\" alt=\"Logo\">\r\n"
					+ "			<p>Created: dd-mm-yyyy</p>	\r\n" + "		<h1>Fsoft Hotel, Inc</h1>\r\n"
					+ "		<p>Phone: 0912121212</p>\r\n" + "		<p>Address: F-town 1</p>\r\n" + "	</div>\r\n"
					+ "\r\n" + "		<div style=\"display: flex;justify-content:space-between;\">\r\n"
					+ "			<span>Price Room :     </span>\r\n" + "			<span>" + " " + price + "</span>\r\n"
					+ "		</div>\r\n" + "\r\n"
					+ "		<div style=\"display: flex;justify-content:space-between;\">\r\n"
					+ "			<span>Room Number :     </span>\r\n" + "			<span>" + " " + roomNumber
					+ "</span>\r\n" + "		</div>\r\n"
					+ "		<div style=\"display: flex;justify-content:space-between;\">\r\n"
					+ "			<span>Room type :     </span>\r\n" + "			<span>" + " " + type + "</span>\r\n"
					+ "		</div>\r\n" + "		<div style=\"display: flex;justify-content:space-between;\">\r\n"
					+ "			<span>Check-in Date :     </span>\r\n" + "			<span>" + " " + checkinDate
					+ "</span>\r\n" + "		</div>\r\n"
					+ "		<div style=\"display: flex;justify-content:space-between;\">\r\n"
					+ "			<span>Check-out Date :     </span>\r\n" + "			<span>" + " " + checkoutDate
					+ "</span>\r\n" + "		</div>\r\n" + "\r\n" + "	<div class=\"signature\">\r\n"
					+ "		<p>Signature</p>\r\n" + "	</div>\r\n";

			mimeMessage.setContent(htmlMsg, "text/html");

			messageHelper.setFrom("hotelfsoft@gmail.com");
			messageHelper.setTo(email);
			messageHelper.setSubject("Hotel Fsoft confirm your booking");
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void sendContact(String email, String name, String phone, String subject, String message) throws MessagingException {

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			boolean multipart = true;
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, multipart, "utf-8");
			String htmlMsg = "Contact to : "+ name 
					+ "Phone Number : " + phone
					+ "Message :" +message;

			mimeMessage.setContent(htmlMsg, "text/html");

			messageHelper.setFrom("hotelfsoft@gmail.com");
			messageHelper.setTo(email);
			messageHelper.setSubject(subject);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
