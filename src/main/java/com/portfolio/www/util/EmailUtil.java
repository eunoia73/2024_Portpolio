package com.portfolio.www.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.portfolio.www.auth.dto.EmailDto;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender mailSender;

	// setter
//	public void setMailSender(JavaMailSender mailSender) {
//		this.mailSender = mailSender;
//	}
	
	public String sendMail(EmailDto email) {
		return sendMail(email, false);
	}

	public String sendMail(EmailDto email, boolean isHtml) {

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setTo(email.getReceiver());
			messageHelper.setFrom(email.getReceiver());
			messageHelper.setSubject(email.getSubject());
			messageHelper.setText(email.getText(), isHtml);
			
			mailSender.send(message);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Error";
		}
		return "Sucess";

	}

}
