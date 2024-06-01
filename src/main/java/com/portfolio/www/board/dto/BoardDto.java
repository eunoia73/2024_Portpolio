package com.portfolio.www.board.dto;

public class BoardDto {

	private int boardSeq;
	private int boardTypeSeq;
	private String title;
	private String content;
	private int hit;
	private String delYn;
	private String regDtm;
	private String regMemberSeq;
	private String updateDtm;
	private String updateMemberSeq;
	private String boardTypeNm;
	private String regMemberId;

	public String getRegMemberId() {
		return regMemberId;
	}

	public void setRegMemberId(String regMemberId) {
		this.regMemberId = regMemberId;
	}

	public String getBoardTypeNm() {
		return boardTypeNm;
	}

	public void setBoardTypeNm(String boardTypeNm) {
		this.boardTypeNm = boardTypeNm;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public int getBoardTypeSeq() {
		return boardTypeSeq;
	}

	public void setBoardTypeSeq(int boardTypeSeq) {
		this.boardTypeSeq = boardTypeSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}

	public String getRegMemberSeq() {
		return regMemberSeq;
	}

	public void setRegMemberSeq(String regMemberSeq) {
		this.regMemberSeq = regMemberSeq;
	}

	public String getUpdateDtm() {
		return updateDtm;
	}

	public void setUpdateDtm(String updateDtm) {
		this.updateDtm = updateDtm;
	}

	public String getUpdateMemberSeq() {
		return updateMemberSeq;
	}

	public void setUpdateMemberSeq(String updateMemberSeq) {
		this.updateMemberSeq = updateMemberSeq;
	}

}
