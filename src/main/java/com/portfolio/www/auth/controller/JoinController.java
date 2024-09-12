package com.portfolio.www.auth.controller;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView join(@ModelAttribute MemberDto member, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();

		// memberId 공백이면 예외 발생
		if (!StringUtils.hasText(member.getMemberId()) || (member.getMemberId().length() < 7)) {
//			bindingResult.addError(new FieldError("memberError", "memberId", member.getMemberId(), false, null, null,
//					MessageEnum.VERIFY_ID_LENGTH.getDescription()));
			mv.addObject("code", MessageEnum.VERIFY_ID_LENGTH.getCode());
			mv.addObject("msg", MessageEnum.VERIFY_ID_LENGTH.getDescription());
		}

		System.out.println("========modelAttribute" + member);
		System.out.println(member.getMemberId());
		System.out.println(member.getPasswd());
		System.out.println(member.getMemberNm());
		System.out.println(member.getEmail());

		// 에러가 있으면 다시 회원가입 입력 폼으로 이동
		if (bindingResult.hasErrors()) {

			System.out.println("bindingError" + bindingResult);
			System.out.println("bindingError/n" + bindingResult.getFieldError());
			mv.setViewName("auth/join");
		}

		// 성공로직
		int result = joinService.join(member);
		redirectAttributes.addAttribute("code", MessageEnum.SUCCESS.getCode());
		redirectAttributes.addAttribute("msg", MessageEnum.SUCCESS.getDescription());

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
//		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
//		mv.addObject("code", MessageEnum.SUCCESS.getCode());
//		mv.addObject("msg", MessageEnum.SUCCESS.getDescription());

//		mv.setViewName("auth/login");
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

	
}
