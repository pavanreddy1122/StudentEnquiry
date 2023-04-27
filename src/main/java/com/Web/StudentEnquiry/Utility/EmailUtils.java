package com.Web.StudentEnquiry.Utility;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String subject, String body, String to) {
		boolean isSet = false;
		try {

			/*
			 * SimpleMailMessage message=new SimpleMailMessage();
			 * message.setSubject(subject); message.setTo(to); message.setText(body);
			 * mailSender.send(message);
			 */

			MimeMessage mime = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mime);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			mailSender.send(mime);

			isSet = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSet;
	}

}
