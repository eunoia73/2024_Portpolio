package com.portfolio.www.forum.notice;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.board.dto.BoardAttachDto;
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
		System.out.println(attFiles[0]);
		System.out.println(attFiles[1]);
		System.out.println(attFiles[2]);
		
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


	//READ 
	@RequestMapping("/forum/notice/readPage.do")
	public ModelAndView readPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("forum/notice/read");
		
		if(!params.containsKey("boardSeq")){
			
		}
		
		//만약 boardTypeSeq가 없으면?
		if(!params.containsKey("boardTypeSeq")) {
			params.put("boardTypeSeq", "3");
		}
		
//		Integer boardSeq = Integer.parseInt(params.get("boardSeq"));
//		Integer boardTypeSeq = Integer.parseInt(params.get("boardTypeSeq"));
//		Integer memberSeq = Integer.parseInt(params.get("memberSeq"));
		
		mv.addObject("board", boardService.getRead(params.get("boardSeq")));
		
		// 첨부파일
		mv.addObject("attFile", boardService.getAllAttFile(Integer.parseInt(params.get("boardSeq")),
				Integer.parseInt(params.get("boardTypeSeq"))));
		
		//좋아요 
		mv.addObject("liked", boardService.getLike(Integer.parseInt(params.get("boardSeq")), Integer.parseInt(params.get("boardTypeSeq")), -1));
//		
//		//싫어요 
		mv.addObject("disLiked", boardService.getDisLike(Integer.parseInt(params.get("boardSeq")), Integer.parseInt(params.get("boardTypeSeq")), -1));

		
		return mv;
	}
	
	//파일 다운로드 
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
	

}
