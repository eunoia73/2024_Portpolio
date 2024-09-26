package com.portfolio.www.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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

			if (email.getAttachment() != null) {
//				FileSystemResource file = new FileSystemResource(new File("/home/ec2-user/dev/resume/sookyung_resume.pdf"));
				FileSystemResource file = new FileSystemResource(new File("/Users/sookyung/Documents/sookyung_resume.pdf"));
				messageHelper.addAttachment(email.getAttachment(), file);

			}

			mailSender.send(message);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Error";
		}
		return "Sucess";

	}

}
