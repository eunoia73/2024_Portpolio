<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="com.portfolio.www.message.MessageEnum"%>

<%
String ctx = request.getContextPath();
String query = request.getParameter("redirectURL");
//hidden으로 숨겨온 값 null값 처리하기 
if (query == null || query.equals("")) {
	query = "index.do";
}
%>



<!--================================
            START LOGIN AREA
    =================================-->
<section class="login_area section--padding2">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 offset-lg-3">
				<form action="<%=ctx%>/login.do" method="post">
					<div class="cardify login">
						<div class="login--header">
							<!-- board에서 받아온 url을 보내야함.
								form태그와 함께 넘어가야한다.   -->
							<input type="hidden" class="redirectURL" name="redirectURL"
								value=<%=query%>><br> <br>
							<h3>환영합니다</h3>
							<p></p>
						</div>
						<!-- end .login_header -->

						<div class="login--form">
							<div class="form-group">
								<label for="user_name">아이디</label> 
								<input id="user_id"
									name="memberId" type="text" class="text_field"
									<%-- value="${cookie.memberId.value}" --%> value="aaaaaa"
									placeholder="아이디를 입력해주세요." required>
							</div>
							<div id="id_result"></div>
							<input id="idChecked" value="0" hidden>
							<br>


							<div class="form-group">
								<label for="pass">비밀번호</label> <input id="passwd" name="passwd"
									type="text" class="text_field" value="aaaaaa1!"
									placeholder="비밀번호를 입력해주세요." required>
								<div id="pw_result"></div>
								<input id="pwChecked" value="0" hidden>
							</div>
							<div id="pw_result"></div>

							<div class="form-group">
								<div class="custom_checkbox">
									<input type="checkbox" id="ch2" name="rememberId"
										${empty cookie.memberId.value? "":"checked"}> <label
										for="ch2"> <span class="shadow_checkbox"></span> <span
										class="label_text">아이디 저장</span>
									</label>
								</div>
							</div>

							<button class="btn btn--md btn--round" type="submit"
								onclick="return validateForm()">로그인</button>

							<div class="login_assist">
								<p class="recover">
									잊어버리셨나요?
									<!-- <a href="pass-recovery.html">아이디</a> or  -->
									<a href="<c:url value='/auth/recoverPage.do'/>">비밀번호</a>?
								</p>
								<p class="signup">
									계정이 없으신가요? <a href="<c:url value='/auth/joinPage.do'/>">회원가입</a>?
								</p>
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


<script type="text/javascript">
<%-- /*    window.onload=function(){
 */    	var code = '${code}';
    	var msg = '${msg}';
    	
    	if(code!=''&& code!= '<%=MessageEnum.SUCCESS.getCode()%>
	') { //정상처리 - 0000 
		alert(msg)
		window.location.href = '/pf/auth/loginPage.do';
	}
	/* 	};
	 */ --%>

	/* 아이디 확인 */
	// 1. 아이디 입력창 정보 가져오기
	let userId = document.querySelector('#user_id');
	var idResultDiv = document.getElementById('id_result');
	var idChecked = document.querySelector('#idChecked');

	// 2. 아이디 형식 체크 함수 
	function idCheck(str) {
		return /^[A-Za-z0-9]{6,12}$/.test(str);
	}
	
	//로그인 편하도록 
	if(userId.value == "aaaaaa") idChecked.value = 1;

	userId.onkeyup = function() {
		if (idCheck(userId.value)) {
			idResultDiv.innerHTML = '';
			idChecked.value = 1;
		} else {
			idResultDiv.innerHTML = '아이디는 6~12자리 영어 또는 숫자만 가능합니다.';
			idResultDiv.style.color = 'red';
			idChecked.value = 0;

		}
	}
	
	/* 비밀번호 확인 */
	// 1. 비밀번호 입력창 정보 가져오기
	let userpwd = document.querySelector('#passwd');
	var pwResultDiv = document.getElementById('pw_result');
	var pwChecked = document.querySelector('#pwChecked');

	// 2. 비밀번호 형식 체크 함수 
		function pwCheck(str) {
		return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/
				.test(str);
	}
	
		if(userpwd.value == "aaaaaa1!") pwChecked.value = 1;

	userpwd.onkeyup = function() {
		if (pwCheck(userpwd.value)) {
			pwResultDiv.innerHTML = '';
			pwChecked.value = 1;
		} else {
			pwResultDiv.innerHTML = '8~20자리, 영문, 숫자, 특수문자(@$!%*#?&)를 사용하세요.';
			pwResultDiv.style.color = 'red';
			pwChecked.value = 0;

		}
	}
	
	//submit
	function validateForm(){
		
		if(idChecked.value == 1 && pwChecked.value == 1){
			return true;
		}else {
			alert("로그인에 실패했습니다.");
			return false;
		}
	}
	
	
	window.onload=function(){
		var code = '${code}';
		var msg = '${msg}';
		
		if(msg!=''){
			console.log(msg);
			alert(msg);
				
		}
		
	}; 
	
</script>

<!--================================
            END LOGIN AREA
    =================================-->
