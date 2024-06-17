package com.portfolio.www.board.dao.mybatis;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.www.board.dto.BoardDto;
import com.portfolio.www.board.dto.BoardLikeDto;

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
	
	//update
	public int updateBoard(@Param("boardSeq") int boardSeq, @Param("boardTypeSeq") int boardTypeSeq, @Param("title") String title, @Param("content") String content);
	
	//delete
	public int deleteBoard(@Param("boardSeq") int boardSeq, @Param("boardTypeSeq") int boardTypeSeq);
	
	//좋아요 insert 
	public int addBoardLike(BoardLikeDto likeDto);
	
	//좋아요 여부 
	public int existsLike(@Param("boardSeq") int boardSeq, @Param("boardTypeSeq") int boardTypeSeq, @Param("memberSeq") int memberSeq);
	
	//좋아요 삭제하기
	public int deleteLike(@Param("boardSeq") int boardSeq,  @Param("boardTypeSeq")int boardTypeSeq, @Param("memberSeq")int memberSeq);
	
	//싫어요 insert 
	public int addBoardDisLike(BoardLikeDto likeDto);
		
	//싫어요 여부 
	public int existsDisLike(@Param("boardSeq") int boardSeq, @Param("boardTypeSeq") int boardTypeSeq, @Param("memberSeq") int memberSeq);
		
	//싫어요 삭제하기
	public int deleteDisLike(@Param("boardSeq") int boardSeq,  @Param("boardTypeSeq")int boardTypeSeq, @Param("memberSeq")int memberSeq);
		
	
}
