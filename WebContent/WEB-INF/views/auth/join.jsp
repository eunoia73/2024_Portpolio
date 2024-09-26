<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="com.portfolio.www.message.MessageEnum"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%
String ctx = request.getContextPath();
%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


<!-- <style>
#error {
	border-color: #dc3545;
	color: #dc3545;
}
</style> -->

<style>
.hide {
	display: none;
}
</style>


<!--================================
            START SIGNUP AREA
    =================================-->
<section class="signup_area section--padding2">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 offset-lg-3">


				<form action="<%=ctx%>/auth/join.do">
					<div class="cardify signup_form">
						<div class="login--header">
							<h3>회원가입</h3>
							<p>새로운 계정을 만들기 위해 아래의 정보를 입력해주세요.</p>
						</div>
						<!-- end .login_header -->

						<div class="login--form">



							<div class="form-group">
								<label for="user_name">아이디</label> 
								<input id="idChecked" name="idChecked" class="idChecked" value="0" hidden>
								<a href="#" class="idCheckTag hide" onClick="javascript:idCheck();" style="inline-block; color:blue; float:right;" >중복체크<span class="alert_icon lnr lnr-checkmark-circle hide"></span></a>
								<input id="user_name"
									name="memberId" type="text" class="text_field"
									placeholder="아이디를 입력해주세요." required>
								<spring:hasBindErrors name="memberDto">
									<c:if test="${errors.hasFieldErrors('memberId') }">
										<strong style="color: red">${errors.getFieldError('memberId').defaultMessage }</strong>
									</c:if>
								</spring:hasBindErrors>
							</div>
							
							
							<div class="success-message hide" style="color: red">아이디 중복체크를 해주세요.</div>
							<div class="failure-message hide" style="color: red">아이디는
								6~12자리로 입력해주세요.</div>
							<div class="failure-message2 hide" style="color: red">영어 또는
								숫자만 가능합니다.</div>
							<br>


							<div class="form-group">
								<label for="email_ad">이메일</label> <input id="email_ad"
									name="email" type="text" class="text_field"
									placeholder="이메일을 입력해주세요." required>
								<input id="emailChecked" name="emailChecked" class="emailChecked" value="0" hidden>

							</div>
							<div id="email_result"></div>

							<br>

							<div class="form-group">
								<label for="urname">이름</label> <input id="urname"
									name="memberNm" type="text" class="text_field"
									placeholder="이름을 입력해주세요." required>
								<input id="nameChecked" name="nameChecked" class="nameChecked" value="0" hidden>
									
							</div>
							<div id="urname_result"></div>
							<br>

							<div class="form-group">
								<label for="password">비밀번호</label> <input id="password"
									name="passwd" type="text" class="text_field"
									placeholder="비밀번호를 입력해주세요." required>
							</div>
							<div class="strongPassword-message hide" style="color: red">8글자 이상, 영문, 숫자,
								특수문자(@$!%*#?&)를 사용하세요.</div>

							<br>

							<div class="form-group">
								<label for="con_pass">비밀번호 확인</label> <input
									id="con_pass" type="text" class="text_field"
									placeholder="비밀번호를 입력해주세요." required>
								<input id="pwChecked" name="pwChecked" class="pwChecked" value="0" hidden>
									
							</div>
							<div class="mismatch-message hide" style="color: red">비밀번호가 일치하지 않습니다.</div>

							<br>

							<button class="btn btn--md btn--round register_btn" type="submit" onclick="return validateForm()">가입하기</button>

							<div class="login_assist">
								<p>
									사용 중인 계정이 있으신가요? <a
										href="<c:url value='/auth/loginPage.do'/>">로그인</a>
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

<script>


//아이디 중복 체크
function idCheck(){
	var memberId = $('#user_name').val();
	console.log(memberId);
	
	var idChecked = document.querySelector('.idChecked');
 	console.log(idChecked.value);

	 // 클래스명으로 요소 선택
    var alertIcon = document.querySelector('.alert_icon');
    alertIcon.classList.add('hide');

	 
	let url = '<%=ctx%>/auth/idCheck.do?';
	url += 'memberId=' + encodeURIComponent(memberId);

	

	$.ajax({    
		// 타입 (get, post, put 등등)    
		type : 'get',           
		// 요청할 서버url
		url : url,
		// Http header
		headers : {
			"Content-Type" : "application/json"
			/* "X-HTTP-Method-Override" : "POST" */
		},
		dataType : 'text',
		// 결과 성공 콜백함수 - 비동기통신은 콜백이 항상 있어야한다
		success : function(result) {
			console.log(result); 
		 if(result == 0){
			 	alert('사용 가능한 아이디입니다.');
			 	idCheckTag.classList.remove('hide');
			 	alertIcon.classList.remove('hide');
			 	idChecked.value = 1;
			 	console.log(idCheck.value);
			 	
			    // hidden 속성 제거
			    /* alertIcon.removeAttribute('hidden'); */
			    
			    
				let elSuccessMessage = document.querySelector('.success-message'); // div.success-message.hide
				elSuccessMessage.classList.add('hide'); // 성공 메시지가 가려져야 함

    				
    			} else{
    				/* $('a#cThumbUpAnchor').addClass('active'); */
    				alert('중복된 아이디입니다.');
    			 	idChecked.value = 0;

    			 	console.log(idChecked.value);

    				 // hidden 속성 추
    			    alertIcon.classList.add('hide');
    			}
		},
		// 결과 에러 콜백함수
		error : function(request, status, error) {
			console.log(error)
		}
	});

}



	/* 유효성 검증  */
	/* 정보 가져오기 */
	//memberId
	// 1. 아이디 입력창 정보 가져오기
	let elInputUsername = document.querySelector('#user_name'); // input#username
	console.log(elInputUsername);
	// 2. 성공 메시지 정보 가져오기
	let elSuccessMessage = document.querySelector('.success-message'); // div.success-message.hide
	// 3. 실패 메시지 정보 가져오기 (글자수 제한 6~12글자)
	let elFailureMessage = document.querySelector('.failure-message'); // div.failure-message.hide
	// 4. 실패 메시지2 정보 가져오기 (영어 또는 숫자)
	let elFailureMessageTwo = document.querySelector('.failure-message2'); // div.failure-message2.hide
	//중복체크 태그 정보 가져오기 
	let idCheckTag = document.querySelector('.idCheckTag');
	//아이콘 가져오기 
    var alertIcon = document.querySelector('.alert_icon');

	//아이디 길이체크 함수 
	function idLength(value) {
		return value.length >= 6 && value.length <= 12
	}
	//아이디가 숫자, 영어로 구성되어있는지 
	function onlyNumberAndEnglish(str) {
		return /^[A-Za-z0-9][A-Za-z0-9]*$/.test(str);
	}

	//이벤트 
	elInputUsername.onkeyup = function() {
		// 값을 입력한 경우
		if (elInputUsername.value.length !== 0) {
			// 영어 또는 숫자 외의 값을 입력했을 경우
			if (onlyNumberAndEnglish(elInputUsername.value) === false) {
				elSuccessMessage.classList.add('hide');
				elFailureMessage.classList.add('hide');
				elFailureMessageTwo.classList.remove('hide'); // 영어 또는 숫자만 가능합니다
				idCheckTag.classList.add('hide');
			}
			// 글자 수가 4~12글자가 아닐 경우
			else if (idLength(elInputUsername.value) === false) {
				elSuccessMessage.classList.add('hide'); // 성공 메시지가 가려져야 함
				elFailureMessage.classList.remove('hide'); // 아이디는 6~12글자이어야 합니다
				elFailureMessageTwo.classList.add('hide'); // 실패 메시지2가 가려져야 함
				idCheckTag.classList.add('hide');

			}
			// 조건을 모두 만족할 경우
			else if (idLength(elInputUsername.value)
					|| onlyNumberAndEnglish(elInputUsername.value)) {
				elSuccessMessage.classList.remove('hide'); // 사용할 수 있는 아이디입니다
				elFailureMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
				elFailureMessageTwo.classList.add('hide'); // 실패 메시지2가 가려져야 함
				idCheckTag.classList.remove('hide');
			 	alertIcon.classList.add('hide');

			}
		}
		// 값을 입력하지 않은 경우 (지웠을 때)
		// 모든 메시지를 가린다.
		else {
			elSuccessMessage.classList.add('hide');
			elFailureMessage.classList.add('hide');
			elFailureMessageTwo.classList.add('hide');
			idCheckTag.classList.add('hide');

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
	
	
	/* 이름 확인 */
	let urname = document.querySelector('#urname');
	var urname_result = document.getElementById('urname_result');
	var nameChecked = document.querySelector('.nameChecked');

	
	//이름 형식 체크 함수 
	function urnameCheck(str){
		return /^[가-힣a-zA-Z]{2,15}$/
		.test(str);
	}
	
	urname.onkeyup = function(){
		if(urnameCheck(urname.value)){
			urname_result.innerHTML = '유효한 이름입니다. ';
			urname_result.style.color = 'blue';
			nameChecked.value = 1;


		}else{
			urname_result.innerHTML = '이름은 한글 또는 영어 2~15자리로 입력해주세요.';
			urname_result.style.color = 'red';
			nameChecked.value = 0;

		}
	}
	
	
	/* 비밀번호 확인 */

	// 1. 비밀번호 입력창 정보 가져오기
	let elInputPassword = document.querySelector('#password'); // input#password
	// 2. 비밀번호 확인 입력창 정보 가져오기
	let elInputPasswordRetype = document.querySelector('#con_pass'); // input#password-retype
	// 3. 실패 메시지 정보 가져오기 (비밀번호 불일치)
	let elMismatchMessage = document.querySelector('.mismatch-message'); // div.mismatch-message.hide
	// 4. 실패 메시지 정보 가져오기 (8글자 이상, 영문, 숫자, 특수문자 미사용)
	let elStrongPasswordMessage = document
			.querySelector('.strongPassword-message'); // div.strongPassword-message.hide
	var pwChecked = document.querySelector('.pwChecked');


	//아이디 형식 체크 
	function strongPassword(str) {
		return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/
				.test(str);
	}
	//비밀번호 확인 일치 체크 
	function isMatch(password1, password2) {
		return password1 === password2;
	}

	elInputPassword.onkeyup = function() {

		// console.log(elInputPassword.value);
		// 값을 입력한 경우
		if (elInputPassword.value.length !== 0) {
			if (strongPassword(elInputPassword.value)) {
				elStrongPasswordMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
			} else {
				elStrongPasswordMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
				pwChecked.value = 0;

			}
		}
		// 값을 입력하지 않은 경우 (지웠을 때)
		// 모든 메시지를 가린다.
		else {
			elStrongPasswordMessage.classList.add('hide');
		}
	};

	//비밀번호 일치 확인 
	elInputPasswordRetype.onkeyup = function() {

		// console.log(elInputPasswordRetype.value);
		if (elInputPasswordRetype.value.length !== 0) {
			if (isMatch(elInputPassword.value, elInputPasswordRetype.value)) {
				elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
				pwChecked.value = 1;
				console.log(pwChecked.value);
			} else {
				elMismatchMessage.classList.remove('hide'); // 실패 메시지가 보여야 함
				pwChecked.value = 0;

			}
		} else {
			elMismatchMessage.classList.add('hide'); // 실패 메시지가 가려져야 함
			pwChecked.value = 0;

		}
	};
	
	//submit
	function validateForm(){
		var idChecked = document.querySelector('.idChecked');
		var emailChecked = document.querySelector('.emailChecked');
		var nameChecked = document.querySelector('.nameChecked');
		var pwChecked = document.querySelector('.pwChecked');
		
		console.log(idChecked.value);
		console.log(emailChecked.value);
		console.log(nameChecked.value);
		console.log(pwChecked.value);
		if(idChecked.value == 1 && emailChecked.value == 1
				&& nameChecked.value == 1 && pwChecked.value == 1){
			
			alert("회원가입을 완료하기 위해 이메일 인증을 해주세요.");
			return true;
		}else {
			alert("회원가입에 실패했습니다.");
			return false;
		}
	}
	
	//
	window.onload=function(){
		var code = '${code}';
		var msg = '${msg}';
		
		if(code!=''&& code!= '<%= MessageEnum.SUCCESS.getCode()%>'){  //정상처리 - 0000 
			alert(msg)
			/* window.location.href='/05/loginPage.do'; */
		}
	}; 
	
	
</script>


</html>