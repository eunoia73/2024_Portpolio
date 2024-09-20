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
							<p>아이디와 이메일을 입력하시면 <br>비밀번호를 변경할 수 있는 링크를 보내드립니다.</p>
						</div>
						<!-- end .login_header -->




						<div class="login--form">
							<div class="form-group">
								<label for="email_ad">아이디</label> <input id="id_ad"
									name="memberId" type="text" class="text_field"
									placeholder="아이디를 입력하세요.">
							</div>
						</div>
						<!-- end .login--form -->


						<div class="login--form">
							<div class="form-group">
								<label for="email_ad">이메일</label> <input id="email_ad"
									name="email" type="text" class="text_field"
									placeholder="이메일을 입력하세요.">
							</div>

							<button class="btn btn--md btn--round register_btn" type="submit">등록하기</button>
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

</html>


