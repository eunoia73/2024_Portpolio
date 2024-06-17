package com.portfolio.www.board.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.portfolio.www.board.dto.BoardAttachDto;

public interface BoardAttachRepository {
	
	//파일 저장하기 
	public int addFile(BoardAttachDto attachDto);

	//전체 파일 개수 가져오기 
	public int getAllAttachCount(@Param("boardSeq") int boardSeq, @Param("boardTypeSeq") int boardTypeSeq);

	
	//전체 파일 정보 가져오기 
	public List<BoardAttachDto> getAllAttachInfo(@Param("boardSeq") int boardSeq, @Param("boardTypeSeq") int boardTypeSeq);

	//파일 다운로드하기 
	public BoardAttachDto getAttachInfo(int attachSeq);
	
	//파일 삭제하기 
	public int deleteAttach(int attachSeq);
	
	//게시글 번호별 모든 파일 삭제하기 
	public int deleteAllAttach(@Param("boardSeq") int boardSeq, @Param("boardTypeSeq") int boardTypeSeq);

}
