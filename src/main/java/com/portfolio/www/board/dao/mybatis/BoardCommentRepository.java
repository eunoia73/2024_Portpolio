package com.portfolio.www.board.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.www.board.dto.BoardCommentDto;

@Repository
public interface BoardCommentRepository {

	
	//댓글 추가
	public int addComment(BoardCommentDto commentDto);
	
	//댓글 가져오기 
	public List<BoardCommentDto> getComment(@Param("boardSeq")int boardSeq, @Param("boardTypeSeq")int boardTypeSeq );

	//댓글 개수 
	public int getCommentCnt(@Param("boardSeq")int boardSeq, @Param("boardTypeSeq")int boardTypeSeq);
	
	//댓글 수정 
	public int updateComment(@Param("commentSeq")int commentSeq, BoardCommentDto commentDto);
	
	//댓글 삭제 
	public int deleteComment(@Param("commentSeq")int commentSeq);
	
}