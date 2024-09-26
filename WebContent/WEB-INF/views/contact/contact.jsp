<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.portfolio.www.message.MessageEnum"%>

<%
String ctx = request.getContextPath();
%>
<link rel="stylesheet"
	href="<%=ctx%>/assest/template/css/trumbowyg.min.css">
<script src="<%=ctx%>/assest/template/js/vendor/trumbowyg.min.js"></script>
<script src="<%=ctx%>/assest/template/js/vendor/trumbowyg/ko.js"></script>
<script type="text/javascript">
	$('#trumbowyg-demo').trumbowyg({
		lang : 'kr'
	});
</script>
<%-- <c:if test="${not empty code and not empty msg}">
	<script type="text/javascript">
		window.onload = function() {
			var code = '${code}';
			var msg = '${msg}';

			if (code !== '') {
				alert(msg);
			}
		}
	</script>
</c:if> --%>
<!--================================
        START AFFILIATE AREA
    =================================-->
<section class="contact-area section--padding">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<!-- start col-md-12 -->
					<div class="col-md-12">
						<div class="section-title">
							<!--   <h1>How can We
                                    <span class="highlighted">Help?</span>
                                </h1>
                                <p>Laborum dolo rumes fugats untras. Etharums ser quidem rerum facilis dolores nemis omnis fugats.
                                    Lid est laborum dolo rumes fugats untras.</p> -->
						</div>
					</div>
					<!-- end /.col-md-12 -->
				</div>
				<!-- end /.row -->



				<div class="col-md-12">
					<div class="contact_form cardify">
						<div class="contact_form__title">
							<h3>회사명과 이메일을 남겨주시면 이력서를 보내드립니다.</h3>
						</div>

						<div class="row">
							<div class="col-md-8 offset-md-2">
								<div class="contact_form--wrapper">
									<form action="<%=ctx%>/contact/contact.do">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<input type="text" id="contactName" name="contactName"
														placeholder="회사명" onBlur="nameCheck();" >
												</div>
											</div>


											<div class="col-md-6">
												<div class="form-group">
													<input type="text" id="contactEmail" name="contactEmail"
														placeholder="이메일" onBlur="emailCheck();" >
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group" id="result_message"></div>
												<input id="nameChecked" name="nameChecked"
													class="nameChecked" value="0" hidden>

											</div>
											<div class="col-md-6">
												<div class="form-group" id="result_message2"></div>
												<input id="emailChecked" name="emailChecked"
													class="emailChecked" value="0" hidden>

											</div>
										</div>



		
										<div class="sub_btn">
											<button type="submit" class="btn btn--round btn--default"
												onclick="return validateForm()">이메일 보내기</button>
										</div>
									</form>
								</div>
							</div>
							<!-- end /.col-md-8 -->
						</div>
						<!-- end /.row -->
					</div>
					<!-- end /.contact_form -->
				</div>
				<!-- end /.col-md-12 -->
			</div>
			<!-- end /.row -->
		</div>
		<!-- end /.col-md-12 -->
	</div>
	<!-- end /.row -->
	</div>
	<!-- end /.container -->
</section>
<!--================================
        END BREADCRUMB AREA
    =================================-->

<script>
 
	function nameCheck() {
		
		var contactName = document.getElementById('contactName').value;
		var resultDiv = document.getElementById('result_message');
		var nameChecked = document.querySelector('.nameChecked');
	 	console.log(nameChecked.value);
		
		let url = '<%=ctx%>/contact/nameCheck.do?';
		url += 'name=' + contactName;

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
				if (result == "0000") {
					resultDiv.innerHTML = '유효한 이름입니다. ';
					resultDiv.style.color = 'blue';
					nameChecked.value = 1;

				} else {
					
						resultDiv.innerHTML = '이름은 2~12자리여야 합니다.';
						resultDiv.style.color = 'red';
						nameChecked.value = 0;
				}
			},
			// 결과 에러 콜백함수
			error : function(request, status, error) {
				console.log(error)
			}
		});
	}
	
function emailCheck() {
		
		var contactEmail = document.getElementById('contactEmail').value;
		var resultDiv = document.getElementById('result_message2');
		var emailChecked = document.querySelector('.emailChecked');
	 	console.log(emailChecked.value);
		
		let url = '<%=ctx%>/contact/emailCheck.do?';
		url += 'email=' + contactEmail;

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
				if (result == "9010") {
					resultDiv.innerHTML = '유효한 이메일입니다. ';
					resultDiv.style.color = 'blue';
					emailChecked.value = 1;

				} else {

						resultDiv.innerHTML = '이메일 형식에 맞게 입력해주세요.';
						resultDiv.style.color = 'red';
						emailChecked.value = 0;
				}
			},
			// 결과 에러 콜백함수
			error : function(request, status, error) {
				console.log(error)
			}
		});
	}

	
	//submit 버튼 
	function validateForm() {
		var emailChecked = document.querySelector('.emailChecked');
		var nameChecked = document.querySelector('.nameChecked');
		if (nameChecked.value == 1 && emailChecked.value == 1) {
			alert("이메일 발송중입니다.");
			return true;
		} else {
			alert("이메일 발송에 실패하였습니다.");
			return false;
		}
	}
	
	let msg = '${resultMsg}'
	let code = '${resultCode}'
	if(msg!=''){
		console.log(msg);
		alert(msg);
		
	}
	
</script>
</html>