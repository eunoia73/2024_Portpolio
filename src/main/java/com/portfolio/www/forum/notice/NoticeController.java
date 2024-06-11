package com.portfolio.www.forum.notice;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.forum.notice.service.BoardService;
import com.portfolio.www.message.MessageEnum;

@Controller
public class NoticeController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/forum//notice/listPage.do")
	public ModelAndView listPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/notice/list");

		if (!params.containsKey("page")) {
			params.put("page", "0");
		}
		if (!params.containsKey("size")) {
			params.put("size", "10");
		}

		int start = Integer.parseInt(params.get("page")) * Integer.parseInt(params.get("size"));
		params.put("start", String.valueOf(start));

		//// 페이징

		int size = Integer.parseInt(params.get("size"));
		int page = Integer.parseInt(params.get("page"));

		// 전체 페이지 개수
		int totalCnt = boardService.getTotalCount();
		System.out.println("=====totalCnt==" + totalCnt);
		// 전체 페이지 네비
		int totalPage = (int) Math.ceil(totalCnt / (double) size);
		int naviSize = 10;
		int beginPage = (page - 1) / naviSize * naviSize + 1;
		int endPage = Math.min(beginPage + naviSize - 1, totalPage);
		// beginpage가 1이면 endpage 10
		// endpage=beginpage+10-1
		boolean showPrev = beginPage != 1;
		boolean showNext = endPage != totalPage;

		mv.addObject("beginPage", beginPage);
		mv.addObject("endPage", endPage);
		mv.addObject("showPrev", showPrev);
		mv.addObject("showNext", showNext);

		mv.addObject("list", boardService.getList(params));

		return mv;
	}

	@RequestMapping("/forum/notice/writePage.do")
	public ModelAndView writePage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/notice/write");

		return mv;
	}

	//CREATE
	@RequestMapping("/forum/notice/write.do")
	public ModelAndView write(@RequestParam HashMap<String, String> params,
			@RequestParam(value = "attFile", required = false) MultipartFile[] attFiles
			) {
		
		System.out.println("controller==="+params);
		System.out.println(attFiles);
		
		boolean result = boardService.write(params, attFiles);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.addObject("result", result);
		if (result) {
			mv.addObject("code", MessageEnum.SUCCESS.getCode());
			mv.addObject("msg", MessageEnum.SUCCESS.getDescription());
		}else {
			mv.addObject("code","9000");
			mv.addObject("msg", "작성 실패");
		}
		mv.setViewName("forum/notice/list");

		return mv;
	}


	@RequestMapping("/forum/notice/readPage.do")
	public ModelAndView readPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/notice/read");
		mv.addObject("board", boardService.getRead(params.get("boardSeq")));

		return mv;
	}

}
