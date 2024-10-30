package com.portfolio.www.forum.notice;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.forum.notice.service.BoardService;

@RestController
public class RestNoticeController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/forum/notice/thumb-up.do")
	@ResponseBody
	public int thumbUp(@RequestParam("boardSeq") int boardSeq,
			@RequestParam("boardTypeSeq") int boardTypeSeq,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int memberSeq = -1;  //사용자가 음수의 값은 없으므로 시스템적으로 문제있음을 빠르게 알 수 있음 
		try {
			memberSeq = (int) session.getAttribute("memberSeq");

		} catch (NullPointerException nep) {
			System.out.println("사용자 없음");
			//exception 던지거나 로그인 페이지로 
			
		}
		
		//IP 가져오기 요청에서 보내진 마지막 프록시의 IP값 
		String ip = request.getRemoteAddr();
		
		return boardService.thumbUp(boardSeq, boardTypeSeq, memberSeq, ip);
		
	}
	
	@GetMapping("/forum/notice/{boardTypeSeq}/{boardSeq}/thumb-down.do")
	@ResponseBody
	public int thumbDown(
			@PathVariable("boardSeq") int boardSeq,
			@PathVariable("boardTypeSeq") int boardTypeSeq,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int memberSeq = -1;  //사용자가 음수의 값은 없으므로 시스템적으로 문제있음을 빠르게 알 수 있음 
		try {
			memberSeq = (int) session.getAttribute("memberSeq");

		} catch (NullPointerException nep) {
			System.out.println("사용자 없음");
			//exception 던지거나 로그인 페이지로 
			
		}
		
		//IP 가져오기 요청에서 보내진 마지막 프록시의 IP값 
		String ip = request.getRemoteAddr();
		
		return boardService.thumbDown(boardSeq, boardTypeSeq, memberSeq, ip);
		
	}
	
//	//첨부파일 DELETE
//	@RequestMapping("/forum/notice/deleteAttach.do")
//	@ResponseBody
//	public int deleteAttach(@RequestParam("attachSeq") int attachSeq ) {
//		
//		return boardService.deleteAttach(attachSeq);
//	}
	
}
