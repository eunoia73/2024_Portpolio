package com.portfolio.www.auth.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.portfolio.www.auth.dao.mybatis.MemberAuthRepository;
import com.portfolio.www.auth.dao.mybatis.MemberRepository;
import com.portfolio.www.auth.dto.EmailDto;
import com.portfolio.www.auth.dto.MemberAuthDto;
import com.portfolio.www.auth.dto.MemberDto;
import com.portfolio.www.util.EmailUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class JoinService {

//	private MemberRepository memberRepository;
//
//	public void setMemberRepository(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}

	@Autowired
	private MemberRepository memberRepository;

	@Autowired 
	private MemberAuthRepository authRepository;

//	public void setMemberAuthRepository(MemberAuthRepository authRepository) {
//		this.authRepository = authRepository;
//	}

	@Autowired
	private EmailUtil emailUtil;
////
//	public void setEmailUtil(EmailUtil emailUtil) {
//		this.emailUtil = emailUtil;
//	}

	
	public MemberDto login(HashMap<String, String> params) throws EmptyResultDataAccessException {
		// 사용자 찾기
		MemberDto member = memberRepository.getMember(params.get("memberId"));

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
	
	
	//회원가입 
	public int join(HashMap<String, String> params) {

		System.out.println("service========" + params);

		// 비밀번호 암호화
		String passwd = params.get("passwd");
		String encPasswd = BCrypt.withDefaults().hashToString(12, passwd.toCharArray());
		System.out.println("encPasswd >>>>>>>" + encPasswd);
		BCrypt.Result result = BCrypt.verifyer().verify(passwd.toCharArray(), encPasswd);
		System.out.println("result.verified >>>>>>> " + result.verified);

		params.put("passwd", encPasswd);

		// 회원가입 검증하기
		// 아이디 중복 체크
//		String code = "-1";
//		int checkCnt = memberRepository.existMemberId(params.get("memberId"));
//		System.out.println("service:::::====checkCnt===" + checkCnt);
//		if (checkCnt == 1) {
//			code =  MessageEnum.EXISTS_LOGIN_NM.getCode();
//		}
//		// 아이디 6자 이하 회원가입 불가
//		else if (params.get("memberId").length() <= 6) {
//			code =  MessageEnum.VERIFY_ID_LENGTH.getCode();
//			// 이메일 형식 체크
//		} else if (!(params.get("email").contains("@"))) {
//			code = MessageEnum.VERIFY_EMAIL_FORM.getCode();
//		} else {
//

		// 중복아이디 여부 확인하기
		int memberCnt = memberRepository.existMemberId(params.get("memberId"));

		int cnt = -1;
		if (memberCnt != 1) {
			// membertable에 insert
			cnt = memberRepository.join(params);
		}

		int memberSeq = memberRepository.getMemberSeq(params.get("memberId"));

		// member table에 join 되었다면, 인증메일 보내기
		if (cnt == 1) {
			// 메일 인증 구조 만들기
			MemberAuthDto authDto = new MemberAuthDto();

			authDto.setMemberSeq(memberSeq);
			authDto.setAuthUri(UUID.randomUUID().toString().replaceAll("-", ""));

			// 메일 만료 시간
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, 30);
			authDto.setExpireDtm(cal.getTimeInMillis());
			authRepository.addAuthInfo(authDto); // insert

			// 인증 메일 발송하기
			EmailDto email = new EmailDto();
			email.setForm("eunoia7373@naver.com"); // 보내는 사람
			email.setReceiver(params.get("email")); // 받는 사람
			email.setSubject("인증하세요"); // 제목

			String html = "<a href='http://localhost:8080/pf/emailAuth.do?uri=" + authDto.getAuthUri() + "'>인증하기</a>";
			email.setText(html);

			// 보내기
			emailUtil.sendMail(email, true);
//				
//			}

		}

		return cnt;

	}

	public boolean mailAuth(String uri) {

		MemberAuthDto dto = authRepository.getMemberAuthDto(uri);

		// 현재시간
		long now = Calendar.getInstance().getTimeInMillis();

		boolean result = now < dto.getExpireDtm();

		// 인증여부 변경하기
		
		int update = authRepository.updateAuthYn(dto.getAuthSeq());
		
		return result;
	}
}
