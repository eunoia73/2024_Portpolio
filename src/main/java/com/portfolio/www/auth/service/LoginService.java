package com.portfolio.www.auth.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.portfolio.www.auth.dao.mybatis.MemberRepository;
import com.portfolio.www.auth.dto.EmailDto;
import com.portfolio.www.auth.dto.MemberDto;
import com.portfolio.www.util.EmailUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class LoginService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private EmailUtil emailUtil;

	// 저장 경로
	@Value("#{config['mail.domain.path']}")
	private String MAIL_PATH;

	public MemberDto login(HashMap<String, String> params) throws EmptyResultDataAccessException {
		// 사용자 찾기
		MemberDto member = memberRepository.getMemberByMemberId(params.get("memberId"));

		// 비밀번호 비교하기
		String passwd = params.get("passwd"); // 사용자가 입력
		String dbPasswd = member.getPasswd(); // 암호화해서 저장된 값

		// 사용자가 입력한 비밀번호 비교

		String encPasswd = BCrypt.withDefaults().hashToString(12, passwd.toCharArray());
		BCrypt.Result result = BCrypt.verifyer().verify(passwd.toCharArray(), dbPasswd);

		System.out.println("dbPasswd : " + dbPasswd);
		System.out.println("encPasswd : " + encPasswd);
		System.out.println("result: " + result.verified);

		return result.verified ? member : null;
		// 예외의 전파 예외를 다시 앞으로 던진다.

	}

	// 비밀번호 재설정하기
	// 1. 사용자가 아이디와 이메일을 입력하면, db의 아이디와 이메일이 맞는지 확인한 후,
	// 2. 인증메일을 보낸다.
	// 3. 인증메일 링크를 통해 비밀번호 변경 페이지로
	public int searchPasswd(String memberId, String email) {
		// 1.db에서 비밀번호 있는지 확인
		Integer memberSeq = memberRepository.searchMemberSeq(memberId, email);
		// 일단 확인
		System.out.println("======================memberSeq확인!" + memberSeq);
		if (memberSeq != 0) {
			// 2. 사용자의 메일로 인증메일 보내기

			// 인증 메일 발송하기
			EmailDto emailDto = new EmailDto();
			emailDto.setFrom("eunoia7373@naver.com"); // 보내는 사람
			emailDto.setReceiver(email); // 받는 사람
			emailDto.setSubject("비밀번호를 변경하세요."); // 제목

			String html = "<a href='" + MAIL_PATH + "/pf/auth/changePwPage.do?memberSeq=" + memberSeq
					+ "'>비밀번호 변경하기</a>";
			emailDto.setText(html);

			// 보내기
			sendMail(emailDto);
//								
		}
		return memberSeq;
	}

	// 비밀번호 변경하기
	public int changePasswd(int memberSeq, String memberId, String passwd) {

		String dbMemberId = memberRepository.getMemberId(memberSeq);

		// db에서 가져온 아이디와 사용자가 입력한 아이디가 같으면 비밀번호 변경 허용
		if (memberId.equals(dbMemberId)) {

			// 새로운 비밀번호 암호화
			String encPasswd = BCrypt.withDefaults().hashToString(12, passwd.toCharArray());
			System.out.println("encPasswd >>>>>>>" + encPasswd);
			BCrypt.Result result = BCrypt.verifyer().verify(passwd.toCharArray(), encPasswd);
			System.out.println("result.verified >>>>>>> " + result.verified);

			return memberRepository.changePw(memberId, encPasswd);

		} else {
			return -1;
		}

	}

	// 메일보내기
	private void sendMail(EmailDto email) {
		emailUtil.sendMail(email, true);
	}

	// memberSeq로 member정보 불러오기(게시판에서 사용)
	public MemberDto getMemberByMemberSeq(int memberSeq) {
		return memberRepository.getMemberByMemberSeq(memberSeq);
	}
}
