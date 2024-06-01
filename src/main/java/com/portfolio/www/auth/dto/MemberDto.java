package com.portfolio.www.auth.dto;

public class MemberDto {
	private int memberSeq;
	private String memberId;
	private String passwd;
	private String memberNm;
	private String email;
	private String authYn;
	private String pwdChngDtm;
	private String joinDtm;
	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthYn() {
		return authYn;
	}
	public void setAuthYn(String authYn) {
		this.authYn = authYn;
	}
	public String getPwdChngDtm() {
		return pwdChngDtm;
	}
	public void setPwdChngDtm(String pwdChngDtm) {
		this.pwdChngDtm = pwdChngDtm;
	}
	public String getJoinDtm() {
		return joinDtm;
	}
	public void setJoinDtm(String joinDtm) {
		this.joinDtm = joinDtm;
	}
	
	

}
