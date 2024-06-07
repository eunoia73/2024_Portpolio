package com.portfolio.www.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.www.auth.service.JoinService;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class) // junit5부터 사
@ContextConfiguration(locations = { "classpath:context-beans-test.xml", "classpath:**/pf-servlet.xml" })
@Slf4j
class JoinServiceTest {

//	@Autowired  //부트에서는 가능. spring에서는 autowired로 의존성 주입 불가능 
	@Resource(name = "joinService")
	private JoinService joinService;

	@Test
	@Order(1)
	@Transactional
	void testJoin() {

		System.out.println("=========joinService=========" + joinService);
//		fail("Not yet implemented");

//		MemberDto memberDto = new MemberDto();
//		memberDto.setMemberId("test001");
//		memberDto.setPasswd("test001");
//		memberDto.setMemberNm("테스트계정");
//		memberDto.setEmail("eunoia7373@naver.com");
//		int cnt = joinService.join(memberDto);
//		assertEquals(1, cnt);
		
//		int memberSeq = MemberDto.getMemberSeq();
//		assertNotEquals(0, memberSeq);
//		assertEquals(1, joinService.deleteMember(memberSeq));


		HashMap<String, String> member = new HashMap<>();
		member.put("memberId", "test002");
		member.put("passwd", "test002");
		member.put("memberNm", "테스트계정");
		member.put("email", "eunoia7373@naver.com");
		
		
		//아이디 중복 체크 
		int exist = joinService.existMemberId(member);
		assertNotEquals(1, exist);
		
		
		//insert test
		int cnt = joinService.join(member);
		assertEquals(1, cnt);
		
		
		int memberSeq = Integer.parseInt(member.get("memberSeq"));
		assertNotEquals(0, memberSeq);


	}
	
	

//	@Transactional
//	@Test
//	@Order(2)
//	void testExistMemberId() {
//		
//	
//
//	
//	
//	}
//	
//	
	
	
	
	
	//아이디랑 패스워드 맞는지 
	
	
	
//	@Transactional
//	@Test
//	@Order(2)
//	void testMailAuth() {
//		
//		HashMap<String, String> member = new HashMap<>();
//		member.put("memberId", "test003");
//		member.put("passwd", "test003");
//		member.put("memberNm", "테스트계정");
//		member.put("email", "eunoia7373@naver.com");
//		int cnt = joinService.join(member);
//		assertEquals(1, cnt);
//		
//		MemberAuthDto memberAuth = new MemberAuthDto();
//		memberAuth.setRegDtm("20240601012532");
//		memberAuth.setExpireDtm(20240602012532L);
//		
////		assertFalse(false);
//		
//		//uri
////		boolean result = mailAuth()
//		
//
//		
//		
//		
//		
//		
//	}
	

}
