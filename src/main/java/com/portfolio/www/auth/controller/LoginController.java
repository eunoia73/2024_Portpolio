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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.www.auth.dto.MemberDto;
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

	// 로그인
	@RequestMapping("/login.do")
	public ModelAndView login(@RequestParam String redirectURL, HttpServletRequest request, HttpServletResponse response,
			@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		MemberDto member = null;

		// 체크박스 체크 여부
		String rememberId = request.getParameter("rememberId");

		try {
			member = joinService.login(params);

			if (!ObjectUtils.isEmpty(member)) {
				// 세션처리
				HttpSession session = request.getSession();
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberSeq", member.getMemberSeq());
				int memberSeq = (int) session.getAttribute("memberSeq");
				System.out.println("=========session안에 있는memberSeq=======" + memberSeq);
				System.out.println("=========params=========" + params);
				System.out.println("=========request========" + request);
				System.out.println("=========request" + request.getParameter("rememberId"));

				// rememberMe 체크 되어 있으면(on이면) cookie 생성
				if (rememberId != null) {

					// 쿠키 생성
					Cookie cookie = new Cookie("memberId", member.getMemberId());
					response.addCookie(cookie);

				} else {
					// 2-3. checkbox 체크 안 했으면
					// 2-4. 쿠키 삭제
					Cookie cookie = new Cookie("memberId", member.getMemberId());
					cookie.setMaxAge(0);
					response.addCookie(cookie);

				}
				System.out.println("======loginController======");
				System.out.println("redirectURL"+redirectURL);
				mv.setViewName("redirect:/"+redirectURL);

				
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

		// 세션 무효화
		req.getSession().invalidate();
		System.out.println("session========있나??" + req.getSession().getAttribute("memberId"));
		mv.setViewName("/auth/login");

		return mv;
	}

	@RequestMapping("/auth/recoverPage.do")
	public ModelAndView recoverPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("auth/recover-password-auth");

		return mv;
	}

	// memberSeq 찾기, 비밀번호 변경 위한 인증메일 보내기
	@RequestMapping("/auth/searchPw.do")
	public ModelAndView searchPw(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());

		System.out.println(params);
		joinService.searchPasswd(params.get("memberId"), params.get("email"));

		mv.setViewName("auth/login");

		return mv;
	}

	// 비밀번호 변경페이지
	@RequestMapping("/auth/changePwPage.do")
	public ModelAndView changePwPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());

		System.out.println("=========memberSeq+++++++++" + params.get("memberSeq"));

		mv.addObject("memberSeq", params.get("memberSeq"));
		mv.setViewName("auth/recover-password-form");

		return mv;
	}

	// 비밀번호 변경하기
	@RequestMapping("/auth/changePw.do")
	public ModelAndView changePw(@RequestParam HashMap<String, String> params,
			@RequestParam("memberSeq") int memberSeq) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());

		System.out.println("==============memberSeq++++++++++" + memberSeq);
		System.out.println("비번 변경===============" + params);
		int result = joinService.changePasswd(memberSeq, params.get("memberId"), params.get("passwd"));

		if (result == 1) {
//			mv.addObject("code", MessageEnum.SUCCESS.getCode());
			mv.addObject("code", "0000");
			mv.addObject("msg", "성공");
			mv.setViewName("auth/login");

		} else {
			mv.addObject("code", MessageEnum.FAIL.getCode());
			mv.addObject("msg", MessageEnum.FAIL.getDescription());
//			mv.addObject("code", "9999");
//			mv.addObject("msg", "실패..");
			mv.setViewName("redirect:/auth/changePwPage.do?memberSeq=" + memberSeq);
		}

		return mv;
	}

}
