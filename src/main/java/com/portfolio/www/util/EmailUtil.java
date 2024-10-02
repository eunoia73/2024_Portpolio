package com.portfolio.www.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.portfolio.www.auth.dto.EmailDto;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender mailSender;

	// 저장 경로
	@Value("#{config['resume.path']}")
	private String RESUME_PATH;

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
			messageHelper.setFrom(email.getFrom());
			messageHelper.setSubject(email.getSubject());
			messageHelper.setText(email.getText(), isHtml);

			System.out.println("=====EmailUtil의" + email.getFrom());

			if (email.getAttachment() != null) {
				// FileSystemResource file = new FileSystemResource(new
				// File("/home/ec2-user/dev/resume/sookyung_resume.pdf"));
				// FileSystemResource file = new FileSystemResource(new
				// File("/Users/sookyung/Documents/sookyung_resume.pdf"));
				FileSystemResource file = new FileSystemResource(new File(RESUME_PATH + "sookyung_resume.pdf"));

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
