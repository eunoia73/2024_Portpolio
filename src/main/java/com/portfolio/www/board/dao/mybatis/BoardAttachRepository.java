package com.portfolio.www.board.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.portfolio.www.board.dto.BoardAttachDto;

public interface BoardAttachRepository {
	
	//파일 저장하기 
	public int addFile(BoardAttachDto attachDto);

	
	//전체 파일 정보 가져오기 
	public List<BoardAttachDto> getAllAttachInfo(@Param("boardSeq") int boardSeq, @Param("boardTypeSeq") int boardTypeSeq);

	//파일 다운로드하기 
	public BoardAttachDto getAttachInfo(int attachSeq);

}
