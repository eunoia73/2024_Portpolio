package com.portfolio.www.board.dto;

public class BoardAttachDto {
	private int attachSeq;
	private int boardSeq;
	private int boardTypeSeq;
	private String orgFileNm;
	private String savePath;
	private String chngFileNm;
	private long fileSize;
	private String fileType;
	private String accessUri;
	private String regDtm;

	public int getAttachSeq() {
		return attachSeq;
	}

	public void setAttachSeq(int attachSeq) {
		this.attachSeq = attachSeq;
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

	public String getOrgFileNm() {
		return orgFileNm;
	}

	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getChngFileNm() {
		return chngFileNm;
	}

	public void setChngFileNm(String chngFileNm) {
		this.chngFileNm = chngFileNm;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getAccessUri() {
		return accessUri;
	}

	public void setAccessUri(String accessUri) {
		this.accessUri = accessUri;
	}

	public String getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}

}
