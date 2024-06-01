package com.portfolio.www.board.dao.mybatis;

import com.portfolio.www.board.dto.BoardAttachDto;

public interface BoardAttachRepository {
	
	public int addFile(BoardAttachDto attachDto);


}
