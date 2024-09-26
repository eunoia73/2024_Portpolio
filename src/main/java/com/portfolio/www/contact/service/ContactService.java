package com.portfolio.www.contact.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.portfolio.www.auth.dto.EmailDto;
import com.portfolio.www.contact.dao.mybatis.ContactRepository;
import com.portfolio.www.contact.dto.ContactDto;
import com.portfolio.www.message.MessageEnum;
import com.portfolio.www.util.EmailUtil;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private EmailUtil emailUtil;

	// 이메일 정규표현식 (기본 이메일 형식)
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

	public int contact(ContactDto contactDto) {

		System.out.println("=======contactDto");
		System.out.println(contactDto.getContactName());
		System.out.println(contactDto.getContactEmail());

		// 메일 구조 만들어서 보내기
		EmailDto email = new EmailDto();
		email.setForm("soo97703@naver.com"); // 보내는 사람
		email.setReceiver(contactDto.getContactEmail()); // 받는 사람
		email.setSubject("엄수경 이력서"); // 제목
		email.setAttachment("sookyung_resume.pdf");
		String html = "test";

		email.setText(html);

		// 보내기
		sendMail(email);

		// table에 insert하기
		return contactRepository.addContactInfo(contactDto);
	}

	// 메일보내기
	private void sendMail(EmailDto email) {
		emailUtil.sendMail(email, true);
	}

	// 회사 이름 유효성검사
	public String nameCheck(String name) {

		System.out.println("serviec=======" + name);
		String code = MessageEnum.FAIL.getCode();
		// 이름이 빈칸이거나
		if (name != null && StringUtils.hasText(name) && name.length() >= 2 && name.length() <= 12) {
			code = MessageEnum.SUCCESS.getCode();
		} else {
			code = MessageEnum.VERIFY_NAME_LENGTH.getCode();
		}

		return code;
	}

	// 회사 이메일 유효성검사
	public String emailCheck(String email) {

		System.out.println("serviec=======" + email);
		String code = MessageEnum.FAIL.getCode();
		// 이메일이 빈칸이거나
		if (email != null && StringUtils.hasText(email) && isValidEmailFormat(email)) {
			code = MessageEnum.VERIFY_EMAIL_SUCCESS.getCode();
		} else {
			
			if(!isValidEmailFormat(email)) {
				code = MessageEnum.VERIFY_EMAIL_FORM.getCode();

			}
		}

		return code;
	}

	// 이메일 형식 유효성 검사
	private boolean isValidEmailFormat(String email) {
		return EMAIL_PATTERN.matcher(email).matches();
	}

}
