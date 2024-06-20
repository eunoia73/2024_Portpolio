package com.portfolio.www.forum.notice.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.www.board.dao.mybatis.BoardAttachRepository;
import com.portfolio.www.board.dao.mybatis.BoardRepository;
import com.portfolio.www.board.dto.BoardAttachDto;
import com.portfolio.www.board.dto.BoardDto;
import com.portfolio.www.board.dto.BoardLikeDto;
import com.portfolio.www.util.FileUtil;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;


	@Autowired
	private BoardAttachRepository boardAttachRepository;

	@Autowired
	private FileUtil fileUtil;


	///////////////// 게시글 좋아요 싫어요////////////////

	// 좋아요 여부
	public int getLike(int boardSeq, int boardTypeSeq, int memberSeq) {
		return boardRepository.existsLike(boardSeq, boardTypeSeq, memberSeq);
	}

	// 좋아요
	public int thumbUp(int boardSeq, int boardTypeSeq, int memberSeq, String ip) {

		int cnt = boardRepository.existsLike(boardSeq, boardTypeSeq, memberSeq);
		int result = -1;

		// 좋아요 있으면 삭제
		if (cnt == 1) {
			boardRepository.deleteLike(boardSeq, boardTypeSeq, memberSeq);
			boardRepository.deleteDisLike(boardSeq, boardTypeSeq, memberSeq);

			result = 0;
		} else {
			boardRepository.deleteDisLike(boardSeq, boardTypeSeq, memberSeq);
			BoardLikeDto likeDto = new BoardLikeDto();
			likeDto.setBoardSeq(boardSeq);
			likeDto.setBoardTypeSeq(boardTypeSeq);
			likeDto.setMemberSeq(memberSeq);
			likeDto.setIp(ip);

			// insert
			result = boardRepository.addBoardLike(likeDto);

		}

		return result;

	}

	// 싫어요 여부
	public int getDisLike(int boardSeq, int boardTypeSeq, int memberSeq) {
		return boardRepository.existsDisLike(boardSeq, boardTypeSeq, memberSeq);
	}

	// 싫어요
	public int thumbDown(int boardSeq, int boardTypeSeq, int memberSeq, String ip) {

		int cnt = boardRepository.existsDisLike(boardSeq, boardTypeSeq, memberSeq);
		int result = -1;

		// 싫어요 있으면 삭제
		if (cnt == 1) {

			boardRepository.deleteDisLike(boardSeq, boardTypeSeq, memberSeq);
			result = 0;
		} else {
			// 좋아요와 싫어요는 중복될 수 없다!
			boardRepository.deleteLike(boardSeq, boardTypeSeq, memberSeq);
			BoardLikeDto disLikeDto = new BoardLikeDto();
			disLikeDto.setBoardSeq(boardSeq);
			disLikeDto.setBoardTypeSeq(boardTypeSeq);
			disLikeDto.setMemberSeq(memberSeq);
			disLikeDto.setIp(ip);

			// insert
			result = boardRepository.addBoardDisLike(disLikeDto);

		}

		return result;

	}

	///////////////// 게시글 좋아요 싫어요 끝/////////////////

	// 파일 다운로드
	public BoardAttachDto getDownloadFileInfo(int attachSeq) {
		return boardAttachRepository.getAttachInfo(attachSeq);
	}

	// 모든 파일 개수 불러오기
	public int getAllAttFileCount(int boardSeq, int boardTypeSeq) {
		return boardAttachRepository.getAllAttachCount(boardSeq, boardTypeSeq);
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
	public boolean write(HashMap<String, String> params, MultipartFile[] mfs ) {
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

	//수정 
	public int modify(HashMap<String, String> params) {

		Integer boardSeq = Integer.parseInt(params.get("boardSeq"));
		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq"));
		System.out.println("===========service=========" + params);
		String title = params.get("title");
		String content = params.get("trumbowyg-demo");
		return boardRepository.updateBoard(boardSeq, boardTypeSeq, title, content);
	}
	
	// 첨부파일만 저장
	public boolean addFile(HashMap<String, String> params, MultipartFile[] mfs) {
		// 첨부파일 DB에 저장
		File destFile = null;

		try {
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
	
	
	// 첨부파일 삭제
	public int deleteAttach(int attachSeq) {

		return boardAttachRepository.deleteAttach(attachSeq);
	}

	//게시글 삭제 (첨부파일도) 
	public int deleteBoard(int boardSeq, int boardTypeSeq) {
		
		int attachResult = boardAttachRepository.deleteAllAttach(boardSeq, boardTypeSeq);
		
		return boardRepository.deleteBoard(boardSeq, boardTypeSeq);
	}
	
	// 글 작성 
	public boolean write(HashMap<String, String> params) {

		boardRepository.addBoard(params);
		return true;
	}

}
