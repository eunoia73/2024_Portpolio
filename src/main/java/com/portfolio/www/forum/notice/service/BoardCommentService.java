package com.portfolio.www.forum.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.www.board.dao.mybatis.BoardCommentRepository;
import com.portfolio.www.board.dto.BoardCommentDto;

@Service
public class BoardCommentService {

	@Autowired
	BoardCommentRepository commentRepository;
	
	//댓글 추가 
	public int addComment(BoardCommentDto commentDto) {
		
		
		System.out.println("service============"+commentDto.getLvl());
		System.out.println("service============"+commentDto.getContent());
		System.out.println("service============"+commentDto.getParentCommentSeq());
		return commentRepository.addComment(commentDto);
	}
	
	//댓글 불러오기 
	public List<BoardCommentDto> getComment(int boardSeq, int boardTypeSeq){
		
		return commentRepository.getComment(boardSeq, boardTypeSeq);
	}
	
	//댓글 개수 
	public int getCommentCnt(int boardSeq, int boardTypeSeq) {
		return commentRepository.getCommentCnt(boardSeq, boardTypeSeq);
	}
	
	//댓글 삭제 
	public int deleteComment(int commentseq) {
		return commentRepository.deleteComment(commentseq);
	}
	
}
