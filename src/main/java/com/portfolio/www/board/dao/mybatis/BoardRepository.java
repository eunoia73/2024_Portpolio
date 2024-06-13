package com.portfolio.www.board.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.portfolio.www.board.dto.BoardDto;

@Repository
public interface BoardRepository {
 
	//board List 가져오기 
	public List<BoardDto> getList(HashMap<String, String> params);
	
	//페이징 전체 게시물 개수 
	public int getTotalCount();
	
	//readPage
	public BoardDto getRead(String boardSeq);
	
	//write 
	public int addBoard(HashMap<String, String> params);
	

	
}
