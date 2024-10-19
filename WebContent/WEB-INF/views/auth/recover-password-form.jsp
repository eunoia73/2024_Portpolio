<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%
String ctx = request.getContextPath();
%>
<script type="text/javascript">
	window.onload = function() {
		var code = '${code}';
		var msg = '${msg}';

		if (msg != '') {
			console.log(msg);
			alert(msg);

		}

	};
</script>

<!--================================
            START DASHBOARD AREA
    =================================-->
<section class="pass_recover_area section--padding2">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 offset-lg-3">
				<form action="<%= ctx %>/auth/changePw.do?memberSeq=${memberSeq}"
					method="post">
					<div class="cardify recover_pass">
						<div class="login--header">
							<p>새로운 비밀번호를 입력해주세요.</p>
						</div>
						<!-- end .login_header -->

						<div class="login--form">
							<div class="form-group">
								<label for="email_ad">아이디</label> <input id="id_ad"
									name="memberId" type="text" class="text_field"
									placeholder="아이디를 입력해주세요." value="${memberId}" readonly >
							</div>
						</div>
						<!-- end .login--form -->


						<div class="login--form">
							<div class="form-group">
								<label for="email_ad">새로운 비밀번호</label> <input id="passwd"
									name="passwd" type="text" class="text_field"
									placeholder="새로운 비밀번호를 입력해주세요.">
							</div>
							<div id="pw_result"></div>
							<input id="pwChecked" value="0" hidden>
							
							<br>

							<button class="btn btn--md btn--round register_btn" type="submit" onClick="return validateForm()">등록하기</button>
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
            END DASHBOARD AREA
    =================================-->
<script type="text/javascript">
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
		
		if(pwChecked.value == 1){
			return true;
		}else {
			alert("비밀번호 변경을 실패했습니다.");
			return false;
		}
	}
</script>

</html>