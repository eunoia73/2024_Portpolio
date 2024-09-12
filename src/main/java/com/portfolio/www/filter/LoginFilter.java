package com.portfolio.www.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.ObjectUtils;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, servletNames = { "pf" })
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        System.out.println("loginFilter Constructor");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	//로그인 하지 않으면 진입하지 못하는 uri 모두 배열에 담기 
	private final String[] LOGIN_REQUIRED_URI = {
			"/forum//notice/listPage.do",
			"/forum/notice/writePage.do",
			"/forum/notice/readPage.do",
			"/forum/notice/deleteBoard.do",
			"/forum/notice/modify.do"
			
	};
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		System.out.println("======filter======");
		System.out.println("==========URI"+uri);
		// pass the request along the filter chain
		

		//Arrays.asList 배열을 List로 바꾼다.
		if(Arrays.asList(LOGIN_REQUIRED_URI).contains(uri.replace("/pf", ""))) {
			HttpSession session = req.getSession();
			if(ObjectUtils.isEmpty(session.getAttribute("memberId"))){
				resp.sendRedirect(req.getContextPath()+"/auth/loginPage.do?redirectURL="+uri.replace("/pf",""));
				return;
			}
		}
		
		chain.doFilter(request, response);  //이 메서드 없으면 더이상 필터를 진행시키지 않는다.
											//request타지 않고 하얀 화면 나옴 
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("loginFilter init");
	}

}
