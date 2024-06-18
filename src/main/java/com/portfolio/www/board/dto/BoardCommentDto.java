package com.portfolio.www.board.dto;

public class BoardCommentDto {

	private int commentSeq;
	private int lvl;
	private String content;
	private int boardSeq;
	private int boardTypeSeq;
	private int memberSeq;
	private int parentCommentSeq;
	private String regDtm;
	private String updateDtm;
	private String deleteDtm;

	private String memberNm;

	public String getMemberNm() {
		return memberNm;
	}

	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}

	public int getCommentSeq() {
		return commentSeq;
	}

	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public int getParentCommentSeq() {
		return parentCommentSeq;
	}

	public void setParentCommentSeq(int parentCommentSeq) {
		this.parentCommentSeq = parentCommentSeq;
	}

	public String getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}

	public String getUpdateDtm() {
		return updateDtm;
	}

	public void setUpdateDtm(String updateDtm) {
		this.updateDtm = updateDtm;
	}

	public String getDeleteDtm() {
		return deleteDtm;
	}

	public void setDeleteDtm(String deleteDtm) {
		this.deleteDtm = deleteDtm;
	}

}
