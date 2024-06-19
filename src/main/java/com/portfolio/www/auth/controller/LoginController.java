package com.portfolio.www.auth.controller;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.auth.service.JoinService;
import com.portfolio.www.message.MessageEnum;

@Controller
public class LoginController {
	
	@Autowired
	private JoinService joinService;
	
	@RequestMapping("/auth/loginPage.do")
	public ModelAndView loginPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/login");
		
		return mv;
	}
	
	@RequestMapping("/auth/recoverPage.do")
	public ModelAndView recoverPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/recover-password-auth");
		
		return mv;
	}

	
	//memberSeq 찾기, 비밀번호 변경 위한 인증메일 보내기 
	@RequestMapping("/auth/searchPw.do")
	public ModelAndView searchPw(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		
		System.out.println(params);
		joinService.searchPasswd(params.get("memberId"), params.get("email"));
		
		mv.setViewName("auth/login");
		
		return mv;
	}
	
	//비밀번호 변경페이지 
	@RequestMapping("/auth/changePwPage.do")
	public ModelAndView changePwPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		System.out.println("=========memberSeq+++++++++"+params.get("memberSeq"));
		
		mv.addObject("memberSeq", params.get("memberSeq"));
		mv.setViewName("auth/recover-password-form");
		
		return mv;
	}
	
	
	
	
	
	//비밀번호 변경하기 
	@RequestMapping("/auth/changePw.do")
	public ModelAndView changePw(@RequestParam HashMap<String, String> params, @RequestParam("memberSeq") int memberSeq) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		System.out.println("==============memberSeq++++++++++"+memberSeq);
		System.out.println("비번 변경==============="+params);
		int result = joinService.changePasswd(memberSeq, params.get("memberId"), params.get("passwd"));
		
		if(result == 1) {
//			mv.addObject("code", MessageEnum.SUCCESS.getCode());
			mv.addObject("code", "0000");
			mv.addObject("msg", "성공");
			mv.setViewName("auth/login");

		}else {
			mv.addObject("code", MessageEnum.FAIL.getCode());
			mv.addObject("msg", MessageEnum.FAIL.getDescription());
//			mv.addObject("code", "9999");
//			mv.addObject("msg", "실패..");
			mv.setViewName("redirect:/auth/changePwPage.do?memberSeq="+memberSeq);
		}
		
		return mv;
	}
	
	
}
