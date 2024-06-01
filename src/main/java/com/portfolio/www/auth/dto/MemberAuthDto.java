package com.portfolio.www.auth.dto;

public class MemberAuthDto {

	private int authSeq;
	private int memberSeq;
	private String authNum; // 인증 문자
	private String authUri; // 인증메일 uri
	private String regDtm; // 등록일자
	private long expireDtm; // 인증만료일자
	private String authYn; // 인증여부

	public int getAuthSeq() {
		return authSeq;
	}

	public void setAuthSeq(int authSeq) {
		this.authSeq = authSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getAuthNum() {
		return authNum;
	}

	public void setAuthNum(String authNum) {
		this.authNum = authNum;
	}

	public String getAuthUri() {
		return authUri;
	}

	public void setAuthUri(String authUri) {
		this.authUri = authUri;
	}

	public String getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}

	public long getExpireDtm() {
		return expireDtm;
	}

	public void setExpireDtm(long expireDtm) {
		this.expireDtm = expireDtm;
	}

	public String getAuthYn() {
		return authYn;
	}

	public void setAuthYn(String authYn) {
		this.authYn = authYn;
	}

}
