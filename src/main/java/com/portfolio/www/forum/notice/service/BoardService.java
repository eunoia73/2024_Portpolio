package com.portfolio.www.forum.notice.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private BoardRepository boardRepository;

//	public BoardService() {}
//
//	public BoardService(BoardRepository boardRepository) {
//		this.boardRepository = boardRepository;
//	}
//	
	@Autowired
	private BoardAttachRepository boardAttachRepository;

//	public BoardService(BoardAttachRepository boardAttachRepository) {
//		this.boardAttachRepository = boardAttachRepository;
//	}
//
	@Autowired
	private FileUtil fileUtil;

//	public BoardService(FileUtil fileUtil) {
//		this.fileUtil = fileUtil;
//	}

	// 파일 다운로드
	public BoardAttachDto getDownloadFileInfo(int attachSeq) {
		return boardAttachRepository.getAttachInfo(attachSeq);
	}

	// 모든 파일 정보 불러오기
	public List<BoardAttachDto> getAllAttFile(int boardSeq, int boardTypeSeq) {
		return boardAttachRepository.getAllAttachInfo(boardSeq, boardTypeSeq);
	}

	// 리스트 가져오기
	public List<BoardDto> getList(HashMap<String, String> params) {
		System.out.println("service====params===" + params);
		return boardRepository.getList(params);
	}

	// 페이징 전체 게시물 개수 가져오기
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
			// board table에 insert
			boardRepository.addBoard(params);
			int boardSeq = Integer.parseInt(params.get("boardSeq"));

			System.out.println("service=====" + mfs);

			// DTO만든다 값을 매핑 -> 테이블에 입력
			for (MultipartFile mf : mfs) {

				if (!mf.isEmpty()) { // 업로드된 파일이 비어있지 않은 경우에만 파일 저장 및 DB에

					// 1. 첨부파일 물리적 저장
					destFile = fileUtil.saveFile(mf);

					BoardAttachDto attachDto = new BoardAttachDto();
					attachDto.setBoardSeq(boardSeq);
					attachDto.setBoardTypeSeq(3);
					attachDto.setOrgFileNm(mf.getOriginalFilename());
					attachDto.setChngFileNm(destFile.getName());
					attachDto.setFileType(mf.getContentType());
					attachDto.setFileSize(mf.getSize());
					attachDto.setSavePath(destFile.getAbsolutePath());

					System.out.println("attachDto=====" + attachDto);

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

	public boolean write(HashMap<String, String> params) {

		boardRepository.addBoard(params);
		return true;
	}

}
