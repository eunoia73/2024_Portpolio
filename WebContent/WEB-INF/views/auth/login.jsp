<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
String ctx = request.getContextPath();
String query = request.getParameter("redirectURL");
//hidden으로 숨겨온 값 null값 처리하기 
if(query==null||query.equals("")){
	query="index.do";
}
%>
    <script type="text/javascript">
<%--     window.onload=function(){
    	var code = '${code}';
    	var msg = '${msg}';
    	
    	if(code!=''&& code!= '<%= MessageEnum.SUCCESS.getCode()%>'){  //정상처리 - 0000 
    		alert(msg)
    		window.location.href='/05/loginPage.do';
    	}
    }; --%>
    
    </script>
    
   
   
    <!--================================
            START LOGIN AREA
    =================================-->
    <section class="login_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <form action="<%= ctx %>/login.do" method="post">
                        <div class="cardify login">
                            <div class="login--header">
                           <!-- board에서 받아온 url을 보내야함.
								form태그와 함께 넘어가야한다.   -->
								<input type="hidden" class="redirectURL" name="redirectURL" value=<%=query%> ><br><br>      
                                <h3>환영합니다</h3>
                                <p></p>
                            </div>
                            <!-- end .login_header -->

                            <div class="login--form">
                                <div class="form-group">
                                    <label for="user_name">아이디</label>
                                    <input id="user_name" name="memberId" type="text" class="text_field" <%-- value="${cookie.memberId.value}" --%> value="aaa" placeholder="Enter your username...">
                                </div>

                                <div class="form-group">
                                    <label for="pass">비밀번호</label>
                                    <input id="pass" name="passwd" type="text" class="text_field" value="1111" placeholder="Enter your password...">
                                </div>

                                <div class="form-group">
                                    <div class="custom_checkbox">
                                        <input type="checkbox" id="ch2" name="rememberId"  ${empty cookie.memberId.value? "":"checked"}>
                                        <label for="ch2">
                                            <span class="shadow_checkbox"></span>
                                            <span class="label_text">아이디 저장</span>
                                        </label>
                                    </div>
                                </div>

                                <button class="btn btn--md btn--round" type="submit">로그인</button>

                                <div class="login_assist">
                                    <p class="recover">잊어버리셨나요?
                                        <a href="pass-recovery.html">아이디</a> or
                                        <a href="<c:url value='/auth/recoverPage.do'/>">비밀번호</a>?</p>
                                    <p class="signup">계정이 없으신가요?
                                        <a href="<c:url value='/auth/joinPage.do'/>">회원가입</a>?</p>
                                </div>
                            </div>
                            <!-- end .login--form -->
                        </div>
                        <!-- end .cardify -->
                    </form>
                </div>
                <!-- end .col-md-6 -->
            </div>
            <!-- end .row -->
        </div>
        <!-- end .container -->
    </section>
    
      
    <!--================================
            END LOGIN AREA
    =================================-->
