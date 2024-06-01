package com.portfolio.www.auth.dao.mybatis;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.portfolio.www.auth.dto.MemberDto;


@Repository
public interface MemberRepository {

	// 회원가입
	public int join(HashMap<String, String> params);

	// 중복아이디 여부
	public int existMemberId(String memberId);

	// memberId로 memberSeq 찾기
	public int getMemberSeq(String memberId);
	
	//memberId로 memberDto
	public MemberDto getMember(String memberId);

}
