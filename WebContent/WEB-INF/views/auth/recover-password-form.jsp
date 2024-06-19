<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%
String ctx = request.getContextPath();
%>
<script type="text/javascript">
	
		var code = '${code}';
		var msg = '${msg}';

		if (code !== '' && code !== '0000') {
			alert(msg);
		}
	
</script>

<!--================================
            START DASHBOARD AREA
    =================================-->
<section class="pass_recover_area section--padding2">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 offset-lg-3">
				<form action="<%= ctx %>/auth/changePw.do?memberSeq=${memberSeq}" method="post">
					<div class="cardify recover_pass">
						<div class="login--header">
							<p>Please enter new password for your account. </p>
						</div>
						<!-- end .login_header -->

						<div class="login--form">
							<div class="form-group">
								<label for="email_ad">Your Id</label>
								 <input id="id_ad" name="memberId"
									type="text" class="text_field"
									placeholder="Enter your id">
							</div>
						</div>
						<!-- end .login--form -->


						<div class="login--form">
							<div class="form-group">
								<label for="email_ad">new password</label> 
								<input id="email_ad" name="passwd"
									type="text" class="text_field"
									placeholder="Enter your new password">
							</div>

							<button class="btn btn--md btn--round register_btn" type="submit">Register
								Now</button>
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