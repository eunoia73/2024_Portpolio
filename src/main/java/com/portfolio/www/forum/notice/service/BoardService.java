package com.portfolio.www.forum.notice.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.www.board.dao.mybatis.BoardAttachRepository;
import com.portfolio.www.board.dao.mybatis.BoardRepository;
import com.portfolio.www.board.dto.BoardAttachDto;
import com.portfolio.www.board.dto.BoardDto;
import com.portfolio.www.util.FileUtil;

@Service
public class BoardService {
	private BoardRepository boardRepository;
	public BoardService() {}

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	private BoardAttachRepository boardAttachRepository;

	public BoardService(BoardAttachRepository boardAttachRepository) {
		this.boardAttachRepository = boardAttachRepository;
	}

	private FileUtil fileUtil;

	public BoardService(FileUtil fileUtil) {
		this.fileUtil = fileUtil;
	}

	// 리스트 가져오기
	public List<BoardDto> getList(HashMap<String, String> params) {
		System.out.println("service====params===" + params);
		return boardRepository.getList(params);
	}

	// 페이징 전체 게시물 개수 가져오
	public int getTotalCount() {
		return boardRepository.getTotalCount();
	}

	// readPage
	public BoardDto getRead(String boardSeq) {
		return boardRepository.getRead(boardSeq);
	}

	// 글 작성
	public boolean write(HashMap<String, String> params, MultipartFile[] mfs) {
		// 첨부파일 DB에 저장
		File destFile = null;

		try {
			// 게시글 입력
			int pk = boardRepository.addBoard(params);
			System.out.println("--------------------pk : " + pk);
			// DTO만든다 값을 매핑 -> 테이블에 입력
			for (MultipartFile mf : mfs) {
				if (!mf.isEmpty()) { // 업로드된 파일이 비어있지 않은 경우에만 파일 저장 및 DB에 정보 저장
					// 물리적 파일 저장
					destFile = fileUtil.saveFile(mf);

					BoardAttachDto attachDto = new BoardAttachDto();
					attachDto.setBoardSeq(pk);
					attachDto.setBoardTypeSeq(3);
					attachDto.setOrgFileNm(mf.getOriginalFilename());
					attachDto.setChngFileNm(destFile.getName());
					attachDto.setFileType(mf.getContentType());
					attachDto.setFileSize(mf.getSize());
					attachDto.setSavePath(destFile.getAbsolutePath());

					// Dao만들어 테이블 저장
					boardAttachRepository.addFile(attachDto);
				}
			}
			return true;

		} catch (Exception e) {
			if (ObjectUtils.isEmpty(destFile)) {
				destFile.delete(); // 저장 안될경우 파일 삭제 안할경우 나중에 쌓이면 터짐
			}
			return false;
		}
		// 첨부파일 물리적 저장
	}

}
