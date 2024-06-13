package com.portfolio.www.util.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView {

	public FileDownloadView() {
		setContentType("application/download; charset=UTF-8");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 파일 다운로드 하기 위해서
		Map<String, Object> fileInfo = (Map<String, Object>) model.get("fileInfo");
		File file = (File) fileInfo.get("downloadFile");
		String orgFileNm = (String) fileInfo.get("orgFileNm");

		// 헤더 세팅
		response.setContentType(getContentType());
		response.setContentLength((int) file.length());

		String userAgent = request.getHeader("User-Agent");

		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileNm = null;
		if (ie) {
			fileNm = URLEncoder.encode(orgFileNm, "UTF-8");
		} else {
			fileNm = new String(orgFileNm.getBytes("UTF-8"), "ISO-8859-1");
		}

		// 이게 가장 중요한 헤더
		// 이게 빠지면 파일명이 안 떨어짐
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNm + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		// 헤더 세팅 끝 (거의 다 비슷)

		// 파일 읽어와 Response에 보내기
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		// 스트림 이용
		// response는 output 가지고 있
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ioe) {
				}
			}
			out.flush(); // 배출해줘야
		}
	}

}
