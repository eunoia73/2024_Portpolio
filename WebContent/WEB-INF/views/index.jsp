<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%--  <c:if test="${not empty code and not empty msg}">
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

<script type="text/javascript">
	window.onload = function() {
		var msg = "${param.msg}";
		if (msg) {
			alert(decodeURIComponent(msg));
		}
	}
</script>
<!--================================
            START SIGNUP AREA
    =================================-->
<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="testimonial-slider">


				<div class="testimonial">
					<div class="testimonial__about">
						<div class="name-designation v_middle">
							<h6>에러 1</h6>
							<h4 class="name">SQLIntegrityConstraintViolationException</h4>
						</div>
					</div>
					<div class="testimonial__text">
						<p>게시글 삭제 시 발생한 에러. board 테이블의 board_seq, board_type_seq가 다른
							테이블에서 FK로 참조된다. board 테이블에서 컬럼을 삭제하기 위해서는 board_attach,
							board_comment, board_like, board_dislike 테이블의 해당 컬럼을 모두 삭제한 다음에
							진행해야 한다.</p>
					</div>
					<br>
					<div style="text-align: center;">

						<a
							href="<c:url value='https://velog.io/@eunoia73/Spring-java.sql.SQLIntegrityConstraintViolationException'/>"
							class="btn btn--round btn--xs">이동하기</a>
					</div>
				</div>
				<!-- end /.testimonial -->

				<div class="testimonial">
					<div class="testimonial__about">
						<div class="name-designation v_middle">
							<h6>에러 2</h6>
							<h4 class="name">MyBatisSystemException(BindingException)</h4>
						</div>
					</div>
					<div class="testimonial__text">
						<p>게시판 좋아요 기능 구현 중 발생한 에러. MyBatis에서 @Param 어노테이션을 사용하지 않으면,
							MyBatis는 기본 이름(arg0, arg1, arg2)을 사용하여 파라미터를 참조한다. @Param 어노테이션을
							사용하면, 명시적으로 파라미터 이름을 지정할 수 있어 SQL 쿼리에서 가독성이 좋아지고 유지보수가 용이하다.</p>
					</div>
					<br>
					<div style="text-align: center;">

						<a
							href="<c:url value='https://velog.io/@eunoia73/Spring-java.sql.SQLIntegrityConstraintViolationException'/>"
							class="btn btn--round btn--xs">이동하기</a>
					</div>
				</div>
				<!-- end /.testimonial -->

				<div class="testimonial">
					<div class="testimonial__about">
						<div class="name-designation v_middle">
							<h6>에러 3</h6>
							<h4 class="name">UnsatisfiedDependencyException</h4>
						</div>
					</div>
					<div class="testimonial__text">
						<p>새로운 기능 구현을 위해 새로운 controller, service, repository, dto,
							sql.xml 생성 후 발생. sqlSessionFactory 빈을 생성하는 과정에서 SQL.Contact.xml
							파일을 읽는 중 문제가 발생했다. MyBatis에서 SQL 매핑 파일을 파싱할 때 발생하는 오류. 결과적으로,
							mybatis-config.xml 파일에 ContacDto를 추가하지 않아서 발생했다.</p>
					</div>
					<br>
					<div style="text-align: center;">

						<a
							href="<c:url value='https://velog.io/@eunoia73/springorg.springframework.beans.factory.UnsatisfiedDependencyException'/>"
							class="btn btn--round btn--xs">이동하기</a>
					</div>
				</div>
				<!-- end /.testimonial -->


				<div class="testimonial">
					<div class="testimonial__about">
						<div class="name-designation v_middle">
							<h6>에러 4</h6>
							<h4 class="name">MailSendException(SMTPSendFailedException)</h4>
						</div>
					</div>
					<div class="testimonial__text">
						<p>
							메일 기능 bugfix중 발생한 에러. The sender address is unauthorized. 임의로
							email.getReceiver()에 'IMAP/SMTP 사용 안 함'으로 되어있는 이메일을 입력해서 메일이 보내지지
							않았다. 네이버 메일 환경설정에서 '사용함'으로 바꿔주었더니 해결되었다.<br> <br>
						</p>
					</div>
					<br>
					<div style="text-align: center;">

						<a
							href="<c:url value='https://velog.io/@eunoia73/springorg.springframework.mail.MailSendException-Failed-messages-com.sun.mail.smtp.SMTPSendFailedException-554-5.7.1-The-sender-address-is-unauthorized'/>"
							class="btn btn--round btn--xs">이동하기</a>
					</div>
				</div>
				<!-- end /.testimonial -->
			</div>
			<!-- end /.testimonial_slider -->
		</div>
	</div>
	<!-- end .row -->
</div>
<!-- end .container -->
<!--================================
            END SIGNUP AREA
    =================================-->

<!--================================
	    START FEATURE AREA
	=================================-->
<section class="features section--padding">
	<!-- start container -->
	<div class="container">
		<!-- start row -->
		<div class="row">
			<!-- start search-area -->
			<div class="col-lg-4 col-md-6">

				<div class="feature" style="text-align: left">
					<a
						href="<c:url value='https://velog.io/@eunoia73/AWS-%EC%88%98%EB%8F%99%EB%B0%B0%ED%8F%AC-Squid-Shell-FileZilla'/>">

						<div class="feature__title">
							<h3>[AWS] 수동배포</h3>
							<br>
						</div>
						<div class="feature__desc">
							<p>Squid Shell, FileZilla를 이용한 수동 배포 방식. war파일 생성 후 배포하기</p>
						</div>
				</div>
				<!-- end /.feature -->
				</a>
			</div>
			<!-- end /.col-lg-4 col-md-6 -->

			<!-- start search-area -->
			<div class="col-lg-4 col-md-6">
				<div class="feature" style="text-align: left">
					<a
						href="<c:url value='https://velog.io/@eunoia73/AWS-EC2%EC%97%90-Nginx-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0-Proxy-%EC%84%A4%EC%A0%95'/>">

						<div class="feature__title">
							<h3>[AWS] EC2에 Nginx 설치, Proxy 설정</h3>
						</div>
						<div class="feature__desc">
							<p>Squid Shell을 이용하여 EC2에 Nginx 서버를 구축하고 Proxy를 설정한다.</p>
						</div>
				</div>
				<!-- end /.feature -->
				</a>
			</div>
			<!-- end /.col-lg-4 col-md-6 -->

			<!-- start search-area -->
			<div class="col-lg-4 col-md-6">
				<div class="feature" style="text-align: left">
					<%-- <div class="feature__img">
						<img src="<c:url value='/assest/template/images/feature3.png'/>"
							alt="feature" />
					</div> --%>
					<a
						href="<c:url value='https://velog.io/@eunoia73/Squid-Shell%EB%A1%9C-EC2-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-2'/>">
					
					<div class="feature__title">
						<h3>[AWS] Squid Shell로 <br> EC2 사용하기</h3>
					</div>
					<div class="feature__desc">
						<p>Squid Shell을 이용하여 EC2 서버에 Java, Maven, Tomcat을 설치한다.</p>
					</div>
					</a>
				</div>
				<!-- end /.feature -->
			</div>
			<!-- end /.col-lg-4 col-md-6 -->
		</div>
		<!-- end /.row -->
	</div>
	<!-- end /.container -->
</section>
<!--================================
	    END FEATURE AREA
	=================================-->