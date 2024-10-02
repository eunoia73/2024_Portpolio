package com.portfolio.www.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

	// 저장 경로
	@Value("#{config['file.save.path']}")
	private String SAVE_PATH;

	//private String SAVE_PATH = "/Users/sookyung/Dev/tmp/";

	public File saveFile(MultipartFile mf) {

		// 현재 날짜를 특정 형식 문자열로 변환(날짜별 파일 저장 폴더 만들기)
		Date date = new Date(); // 현재 날짜와 시간을 가져옴
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 날짜를 지정 형식으로 변환
		String fileDate = sdf.format(date); // date를 지정 형식으로 변환하여 저장

		File destFile = new File(SAVE_PATH + fileDate);

		try {
			if (!destFile.exists()) { // 경로 존재하지 않으면
				destFile.mkdirs(); // 만들고
			}
			// 파일 저장 경로(기본저장 경로 + 날짜폴더) & 파일명 암호화
			destFile = new File(SAVE_PATH + fileDate, UUID.randomUUID().toString().replaceAll("-", "")); // 파일이름 중복방지용
																											// 암호화

			mf.transferTo(destFile);

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}
}
