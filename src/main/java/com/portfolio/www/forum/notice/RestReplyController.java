package com.portfolio.www.forum.notice;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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

	// 댓글 추가
	@RequestMapping("/forum/notice/reply.do")
	@ResponseBody
	public int addComment(@RequestBody BoardCommentDto dto, HttpServletRequest request) {
		// 세션 처리
		int memberSeq = (int) request.getSession().getAttribute("memberSeq");
		dto.setMemberSeq(memberSeq);

		System.out.println("======댓글추가아아아 controller" + dto);
		System.out.println(dto.getBoardSeq());
		System.out.println(dto.getBoardTypeSeq());
		return commentService.addComment(dto);
	}

	// 댓글 수정
	@RequestMapping("/forum/notice/modifyComment.do")
	@ResponseBody
	public int modifyComment(@RequestBody HashMap<String, Object> params, HttpServletRequest request) {

		int commentSeq = (int) params.get("commentSeq");
		String content = (String) params.get("content");

		System.out.println("==========controller commentSeq: " + commentSeq);
		System.out.println("==========controller content: " + content);

		// 세션 처리
		int memberSeq = (int) request.getSession().getAttribute("memberSeq");
		System.out.println("======댓글 controller content" + content);

		// 세션에 있는 memberSeq랑 db에 있는 memberSeq가 같을 경우에만 수정 가능하게 해야 해서
		// service 단에서 비교하기 위해 session의 memberSeq 같이 넘긴다.

		return commentService.updateComment(commentSeq, content, memberSeq);
	}

	// 댓글 삭제
	@RequestMapping("/forum/notice/deleteComment.do")
	@ResponseBody
	public int modifyComment(@RequestParam("commentSeq") int commentSeq) {

		System.out.println("==========controller" + commentSeq);
		return commentService.deleteComment(commentSeq);
	}

}
