package com.portfolio.www.forum.notice;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.www.board.dto.BoardAttachDto;
import com.portfolio.www.forum.notice.service.BoardCommentService;
import com.portfolio.www.forum.notice.service.BoardService;
import com.portfolio.www.message.MessageEnum;

@Controller
public class NoticeController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardCommentService commentService;
	
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

	// CREATE
	@RequestMapping("/forum/notice/write.do")
	public ModelAndView write(@RequestParam HashMap<String, String> params,
			@RequestParam(value = "attFile", required = false) MultipartFile[] attFiles,
			HttpServletRequest request,
			RedirectAttributes ra) {

		System.out.println("controller===" + params);
		params.put("memberSeq", request.getSession().getAttribute("memberSeq")+"");
		System.out.println("controller===" + params);

		boolean result = boardService.write(params, attFiles);

		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.addObject("result", result);
		if (result) {
			ra.addFlashAttribute("code", MessageEnum.SUCCESS.getCode());
			ra.addFlashAttribute("msg", MessageEnum.SUCCESS.getDescription());

		} else {
			ra.addFlashAttribute("code", MessageEnum.FAIL.getCode());
			ra.addFlashAttribute("msg", MessageEnum.FAIL.getDescription());
		}
		mv.setViewName("redirect:/forum//notice/listPage.do");

		return mv;
	}

	// READ
	@RequestMapping("/forum/notice/readPage.do")
	public ModelAndView readPage(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/notice/read");

		if (!params.containsKey("boardSeq")) {

		}

		// 만약 boardTypeSeq가 없으면?
		if (!params.containsKey("boardTypeSeq")) {
			params.put("boardTypeSeq", "3");
		}

		Integer boardSeq = Integer.parseInt(params.get("boardSeq"));
		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq"));
		//session에서 memberSeq 받아오기 
		Integer memberSeq = (Integer) request.getSession().getAttribute("memberSeq");

		mv.addObject("board", boardService.getRead(params.get("boardSeq")));

		// 첨부파일
		mv.addObject("attFile", boardService.getAllAttFile(boardSeq, boardTypeSeq));

		// 좋아요
		mv.addObject("liked", boardService.getLike(boardSeq, boardTypeSeq, memberSeq));
//		
//		//싫어요 
		mv.addObject("disLiked", boardService.getDisLike(boardSeq, boardTypeSeq, memberSeq));

		//댓글 
		mv.addObject("comments", commentService.getComment(boardSeq, boardTypeSeq));
		
		//댓글 개수 
		mv.addObject("commentCnt", commentService.getCommentCnt(boardSeq, boardTypeSeq));
		
		return mv;
	}

	// 파일 다운로드
	@GetMapping("/forum/download.do")
	public String download2(Model model, @RequestParam("attachSeq") int attachSeq) {
		BoardAttachDto dto = boardService.getDownloadFileInfo(attachSeq);
		File file = new File(dto.getSavePath());

		Map<String, Object> fileInfo = new HashMap<>();
		fileInfo.put("downloadFile", file);
		fileInfo.put("orgFileNm", dto.getOrgFileNm());
		model.addAttribute("fileInfo", fileInfo);

		return "fileDownloadView"; // pf-servlet에 등록한 View
	}

	// MODIFY page
	@RequestMapping("/forum/notice/modifyPage.do")
	public ModelAndView modifyPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/notice/modify");

		mv.addObject("board", boardService.getRead(params.get("boardSeq")));
		// 첨부파일
		mv.addObject("attFile", boardService.getAllAttFile(Integer.parseInt(params.get("boardSeq")),
				Integer.parseInt(params.get("boardTypeSeq"))));
		// 저장 안 한 첨부파일 개수
		mv.addObject("count", 3 - boardService.getAllAttFileCount(Integer.parseInt(params.get("boardSeq")),
				Integer.parseInt(params.get("boardTypeSeq"))));

		return mv;
	}

	// UPDATE
	@RequestMapping("/forum/notice/modify.do")
	public ModelAndView modify(@RequestParam HashMap<String, String> params,
			@RequestParam(value = "attFile", required = false) MultipartFile[] attFiles,
			RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		Integer boardSeq = Integer.parseInt(params.get("boardSeq"));
		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq"));

		// 새로 추가하는 첨부파일만 저장한다
		boolean result = boardService.addFile(params, attFiles);

		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.addObject("result", result);
		if (result) {
			ra.addFlashAttribute("code", MessageEnum.SUCCESS.getCode());
			ra.addFlashAttribute("msg", MessageEnum.SUCCESS.getDescription());

		} else {
			ra.addFlashAttribute("code", MessageEnum.FAIL.getCode());
			ra.addFlashAttribute("msg", MessageEnum.FAIL.getDescription());
		}
		// board table update
		boardService.modify(params);

		mv.setViewName("redirect:/forum/notice/readPage.do?boardSeq=" + boardSeq + "&boardTypeSeq=" + boardTypeSeq);

		return mv;
	}

	// 첨부파일 DELETE
	@RequestMapping("/forum/notice/deleteAttach.do")
	public ModelAndView deleteAttach(@RequestParam("attachSeq") int attachSeq, @RequestParam("boardSeq") int boardSeq,
			@RequestParam("boardTypeSeq") int boardTypeSeq) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());

		System.out.println("==============파일삭제 controller==================" + attachSeq);
		boardService.deleteAttach(attachSeq);
		mv.setViewName("redirect:/forum/notice/modifyPage.do?boardSeq=" + boardSeq + "&boardTypeSeq=" + boardTypeSeq);
		return mv;
	}

	// 게시글& 첨부파일 DELETE
	@RequestMapping("/forum/notice/deleteBoard.do")
	public ModelAndView deleteBoardAndAttach(@RequestParam("boardSeq") int boardSeq,
			@RequestParam("boardTypeSeq") int boardTypeSeq,
			RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());

		// board
		int delete = boardService.deleteBoard(boardSeq, boardTypeSeq);
		if (delete == 1) {
			ra.addFlashAttribute("code", MessageEnum.SUCCESS.getCode());
			ra.addFlashAttribute("msg", MessageEnum.SUCCESS.getDescription());

		} else {
			ra.addFlashAttribute("code", MessageEnum.FAIL.getCode());
			ra.addFlashAttribute("msg", MessageEnum.FAIL.getDescription());
		}

		mv.setViewName("redirect:/forum/notice/listPage.do");
		return mv;
	}

}
