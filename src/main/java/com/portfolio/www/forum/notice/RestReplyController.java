package com.portfolio.www.forum.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.www.board.dto.BoardCommentDto;
import com.portfolio.www.forum.notice.service.BoardCommentService;

@RestController
public class RestReplyController {

	@Autowired
	private BoardCommentService commentService;
	
	//댓글 추가 
	@RequestMapping("/forum/notice/reply.do")
	@ResponseBody
	public int addComment(@RequestBody BoardCommentDto dto) {
		//나중에 세션 처리...
		dto.setMemberSeq(51);

		System.out.println("======댓글 controller"+dto);
		System.out.println(dto.getBoardSeq());
		System.out.println(dto.getBoardTypeSeq());
		return commentService.addComment(dto);
	}
	
	//댓글 수정 
	@RequestMapping("/forum/notice/modifyComment.do")
	@ResponseBody
	public int modifyComment(@RequestBody BoardCommentDto dto) {
		//나중에 세션 처리...
		dto.setMemberSeq(51);

		System.out.println("======댓글 controller"+dto);
		System.out.println(dto.getBoardSeq());
		System.out.println(dto.getBoardTypeSeq());
		return commentService.addComment(dto);
	}
	
	//댓글 삭제 
	@RequestMapping("/forum/notice/deleteComment.do")
	@ResponseBody
	public int modifyComment(@RequestParam("commentSeq") int commentSeq) {
		
	System.out.println("==========controller"+commentSeq);
		return commentService.deleteComment(commentSeq);
	}
	
}
