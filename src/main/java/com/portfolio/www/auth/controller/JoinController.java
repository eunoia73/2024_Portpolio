package com.portfolio.www.auth.controller;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.auth.dto.MemberDto;
import com.portfolio.www.auth.service.JoinService;
import com.portfolio.www.message.MessageEnum;

@Controller
public class JoinController {

	@Autowired
	private JoinService joinService;

	@RequestMapping("/auth/joinPage.do")
	public ModelAndView joinPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/join");

		return mv;
	}

	@RequestMapping("/auth/join.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();

		System.out.println("========params" + params);
//
//		MemberDto memberDto = new MemberDto();
//		memberDto.setMemberId(params.get("memberId"));
//		memberDto.setPasswd(params.get("passwd"));
//		memberDto.setMemberNm(params.get("memberNm"));
//		memberDto.setEmail(params.get("email"));
//		
		
		int result = joinService.join(params);

		// result가 "0000"이면 회원가입 성공!
//		if (result == MessageEnum.SUCCESS.getCode()) {
//			mv.addObject("code", MessageEnum.SUCCESS.getCode());
//			mv.addObject("msg", MessageEnum.SUCCESS.getDescription());
//			mv.setViewName("auth/login");
//
//		} else if (result == MessageEnum.EXISTS_LOGIN_NM.getCode()) {
//			mv.addObject("code", MessageEnum.EXISTS_LOGIN_NM.getCode());
//			mv.addObject("msg", MessageEnum.EXISTS_LOGIN_NM.getDescription());
//			mv.setViewName("auth/join");
//		}
//
//		else if (result == MessageEnum.VERIFY_ID_LENGTH.getCode()) {
//
//			mv.addObject("code", MessageEnum.VERIFY_ID_LENGTH.getCode());
//			mv.addObject("msg", MessageEnum.VERIFY_ID_LENGTH.getDescription());
//			mv.setViewName("auth/join");
//
//		} else if (result == MessageEnum.VERIFY_EMAIL_FORM.getCode()) {
//			mv.addObject("code", MessageEnum.VERIFY_EMAIL_FORM.getCode());
//			mv.addObject("msg", MessageEnum.VERIFY_EMAIL_FORM.getDescription());
//			mv.setViewName("auth/join");
//
//		}
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.addObject("code", MessageEnum.SUCCESS.getCode());
		mv.addObject("msg", MessageEnum.SUCCESS.getDescription());

		mv.setViewName("auth/login");
		return mv;
	}

	@RequestMapping("/emailAuth.do")
	public ModelAndView emailAuth(@RequestParam("uri") String uri) {
		ModelAndView mv = new ModelAndView();

		System.out.println("controllerUri" + uri);
		boolean result = joinService.mailAuth(uri);
		if (result) {
			mv.addObject("code", MessageEnum.SUCCESS.getCode());
			mv.addObject("msg", MessageEnum.SUCCESS.getDescription());

		}

		mv.setViewName("auth/login");

		return mv;
	}

	// 로그인
	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request, @RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		MemberDto member = null;
		try {
			member = joinService.login(params);

			if (!ObjectUtils.isEmpty(member)) {
				// 세션처리 
				HttpSession session = request.getSession();
				session.setAttribute("memberId", member.getMemberId());
				mv.setViewName("index");
			} else {
				mv.setViewName("login"); // 로그인 안 되면 다시 로그인 페이지로
				mv.addObject("code", MessageEnum.PASSWD_NOT_EQUAL.getCode());
				mv.addObject("msg", MessageEnum.PASSWD_NOT_EQUAL.getDescription());
			}
		} catch (EmptyResultDataAccessException e) { // 사용자 없을
			// 여기에 Exception넣는게 가장 무책임한 코드.. 예외를 식별하는게 좋다!
			mv.setViewName("login"); // 로그인 안 되면 다시 로그인 페이지로
			mv.addObject("code", MessageEnum.USER_NOT_FOUND.getCode());
			mv.addObject("msg", MessageEnum.USER_NOT_FOUND.getDescription());
		}

		return mv;
	}

	// 로그아웃
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");

		// 세션 무효화
		req.getSession().invalidate();

		return mv;
	}

}
