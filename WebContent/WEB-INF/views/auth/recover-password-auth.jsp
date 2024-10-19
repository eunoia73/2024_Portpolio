<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%
String ctx = request.getContextPath();
%>
<!--================================
            START DASHBOARD AREA
    =================================-->
<script type="text/javascript">
	window.onload = function() {
		var code = '${code}';
		var msg = '${msg}';

		if (code !== '' && code !== '0000') {
			alert(msg);
		}
	}
</script>
<section class="pass_recover_area section--padding2">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 offset-lg-3">
				<form action="<%=ctx%>/auth/searchPw.do" method="post">
					<div class="cardify recover_pass">
						<div class="login--header">
							<p>
								아이디와 이메일을 입력하시면 <br>비밀번호를 변경할 수 있는 링크를 보내드립니다.
							</p>
						</div>
						<!-- end .login_header -->




						<div class="login--form">
							<div class="form-group">
								<label for="email_ad">아이디</label> 
								<input id="user_id"
									name="memberId" type="text" class="text_field"
									placeholder="아이디를 입력해주세요." required>
							</div>
							<div id="id_result"></div>
							<input id="idChecked" value="0" hidden>
							</div>
						<!-- end .login--form -->


						<div class="login--form">
							<div class="form-group">
								<label for="email_ad">이메일</label> <input id="email_ad"
									name="email" type="text" class="text_field"
									placeholder="이메일을 입력해주세요." required>
								<input id="emailChecked" name="emailChecked" class="emailChecked" value="0" hidden>

							</div>
							<div id="email_result"></div>

							<br>
							<button class="btn btn--md btn--round register_btn" type="submit" onclick="return validateForm()">메일보내기</button>
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
<script>
	/* 아이디 확인 */
	// 1. 아이디 입력창 정보 가져오기
	let userId = document.querySelector('#user_id');
	var idResultDiv = document.getElementById('id_result');
	var idChecked = document.querySelector('#idChecked');

	// 2. 아이디 형식 체크 함수 
	function idCheck(str) {
		return /^[A-Za-z0-9]{6,12}$/.test(str);
	}

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
	
	/* 이메일 확인  */
	// 1. 이메일 입력창 정보 가져오기
	let elInputEmail = document.querySelector('#email_ad');
	var resultDiv = document.getElementById('email_result');
	var emailChecked = document.querySelector('.emailChecked');

	
	//이메일 형식 체크 함수 
	function emailCheck(str){
		return /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
		.test(str);
	}
	
	elInputEmail.onkeyup = function(){
		if(emailCheck(elInputEmail.value)){
			resultDiv.innerHTML = '유효한 이메일 주소입니다.';
			resultDiv.style.color = 'blue';
		 	emailChecked.value = 1;


		}else{
			resultDiv.innerHTML = '유효하지 않은 이메일 주소입니다.';
			resultDiv.style.color = 'red';
			emailChecked.value = 0;

		}
	}
	
	//submit
	function validateForm(){
		
		if(idChecked.value == 1 && emailChecked.value == 1){
			return true;
		}else {
			alert("전송 실패했습니다.");
			return false;
		}
	}
</script>

</html>


