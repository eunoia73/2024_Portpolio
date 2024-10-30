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
	public int updateComment(@Param("commentSeq")int commentSeq, @Param("content")String content);
	
	//댓글 삭제 
	public int deleteComment(@Param("commentSeq")int commentSeq);
	
	//댓글 전체 삭제(게시글 삭제 시)
	public int deleteAllComment(@Param("boardSeq")int boardSeq, @Param("boardTypeSeq")int boardTypeSeq);

	
	//commentSeq로 memberSeq 불러오기 
	public int getMemberSeq(@Param("commentSeq") int commentSeq);
	
}
