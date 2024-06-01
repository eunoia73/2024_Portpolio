package com.portfolio.www.auth.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.portfolio.www.auth.dto.MemberAuthDto;

@Repository
public interface MemberAuthRepository {

	// AuthInfo insert하기
	public int addAuthInfo(MemberAuthDto dto);
	 
	
	//uri로 MemberAuthDto 불러오기 
	public MemberAuthDto getMemberAuthDto(String uri);
	
	//인증여부 변경하기
	public int updateAuthYn(int authSeq);
}
