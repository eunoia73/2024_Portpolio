package com.portfolio.www.message;

public enum MessageEnum {

	SUCCESS("0000","Request Success"),
	EXISTS_LOGIN_NM("0101", "이미 사용중인 아이디입니다. 다른 아이디를 사용해주세요."),
	FAIL_SEND_EMAIL("0110", "이메일 발송에 실패하였습니다."),
	EXISTS_EMAIL_ADDR("0114", "이미 사용중인 이메일입니다. 다른 이메일을 사용해주세요."),
	INCORRECT_ID_PW("0121", "입력하신 아이디와 비밀번호를 확인할 수 없습니다. 아이디와 비밀번호를 다시 입력해주세요."),
	USER_NOT_FOUND("0202", "존재하지 않는 사용자 입니다."),
	INVALID_ID_OR_PASSWORD("0203", "아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해 주세요."),
	PASSWD_NOT_EQUAL("9001","비밀번호가 맞지 않습니다."),
	FAIL("9999","실패했습니다."),

	
	VERIFY_ID_LENGTH("9001", "아이디는 7자리 이상이어야 합니다."),
	VERIFY_EMAIL_FORM("9002", "이메일 형식에 맞게 입력해주세요.")
	;
	
	MessageEnum(String code, String description){
		this.code = code;
		this.description = description;
	}
	
	private String code;
	private String description;
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	//enum은 setter 필요 없음
	//객체생성할 필요도 없음!
	
	
}
