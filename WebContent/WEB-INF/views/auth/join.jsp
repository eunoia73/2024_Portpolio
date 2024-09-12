<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="com.portfolio.www.message.MessageEnum"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- <style>
#error {
	border-color: #dc3545;
	color: #dc3545;
}
</style> -->


<!--================================
            START SIGNUP AREA
    =================================-->
<section class="signup_area section--padding2">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 offset-lg-3">


				<form action="/pf/auth/join.do">
					<div class="cardify signup_form">
						<div class="login--header">
							<h3>Create Your Account</h3>
							<p>Please fill the following fields with appropriate
								information to register a new MartPlace account.</p>
						</div>
						<!-- end .login_header -->

						<div class="login--form">



							<div class="form-group">
								<label for="user_name">Username</label> 
								<input id="user_name"
									name="memberId" type="text" class="text_field"
									placeholder="Enter your username..." required>
							</div>
							<p>hello</p>
							<div id="error"></div>



							<div class="form-group">
								<label for="email_ad">Email Address</label> <input id="email_ad"
									name="email" type="text" class="text_field"
									placeholder="Enter your email address" required>
							</div>

							<div class="form-group">
								<label for="urname">Your Name</label> <input id="urname"
									name="memberNm" type="text" class="text_field"
									placeholder="Enter your Name" required>
							</div>

							<div class="form-group">
								<label for="password">Password</label> <input id="password"
									name="passwd" type="text" class="text_field"
									placeholder="Enter your password..." required>
							</div>

							<div class="form-group">
								<label for="con_pass">Confirm Password</label> <input
									id="con_pass" type="text" class="text_field"
									placeholder="Confirm password" required>
							</div>

							<button class="btn btn--md btn--round register_btn" type="submit">Register
								Now</button>

							<div class="login_assist">
								<p>
									Already have an account? <a
										href="<c:url value='/auth/loginPage.do'/>">Login</a>
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
<!--================================
            END SIGNUP AREA
    =================================-->
<script type="text/javascript">
    window.onload=function(){
    	var code = '${code}';
    	var msg = '${msg}';
    	//code가 0000이 아니면 회원가입 실패
    	if(code!='' && code!= '<%=MessageEnum.SUCCESS.getCode()%>' ){
    		alert(msg);
    		window.location.href='/pf/auth/joinPage.do';
    	}else if(code!='' && code == '<%=MessageEnum.SUCCESS.getCode()%>
	') { //회원가입 성공일때도 메시지 보여주
			alert(msg);
		}
	};

	/* let input = document.querySelector('#user_name'); */
	let input = document.getElementId('user_name');
	let error = document.querySelector('#error');
	input.addEventListener('blur', function() {
		if (input.value.length < 7) {
			input.classList.add('invalid');
			error.innerHTML = "아이디는 7자리 이상이어야 합니다.";
		}
	});
	input.addEventListener('focus', function() {
		if (this.classList.contains('invalid')) {
			this.classList.remove('invalid');
			error.innerHTML = "";
		}
	})
</script>


</html>