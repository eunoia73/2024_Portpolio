<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String ctx = request.getContextPath();
%>
<section class="section--padding2">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="">
					<div class="modules__content">
						<div class="withdraw_module withdraw_history">

							<div class="withdraw_table_header">
								<h3>공지사항</h3>
							</div>
							<div class="table-responsive">
								<table class="table withdraw__table">
									<thead>
										<tr>
											<th>No</th>

											<th>제목</th>

											<th>Date</th>
											<th>작성자</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach items="${list }" var="i">
											<tr>
												<td>${i.boardSeq}</td>


												<td>
														<a href="<c:url value='/forum/notice/readPage.do?boardSeq=${i.boardSeq }&boardTypeSeq=${i.boardTypeSeq }'/>" style="color: black">
															${i.title }</a>
															
															<!--첨부파일 여부  -->
															 <c:if test="${i.attachCnt > 0}"><span class="lnr lnr-paperclip" style="color:#0674ec"></span>${i.attachCnt}</c:if>
															 <!-- 댓글 여부  -->
															 <c:if test="${i.commentCnt > 0}" > &nbsp | &nbsp<span class="lnr lnr-bubble" style="color:#7347c1"></span>&nbsp${i.commentCnt}</c:if>
															  
												</td>





												<td>${i.regDtm }</td>
												<td>${i.regMemberId }</td>
											</tr>
										</c:forEach>
									</tbody>


								</table>
								<div
									style="display: inline-block; margin: 0 5px; float: right; padding-right: 10px;">
									<a href="<c:url value='/forum/notice/writePage.do'/>">
										<button
											class="btn btn--round btn--bordered btn-sm btn-secondary">작성</button>
									</a>
								</div>
								<div class="pagination-area" style="padding-top: 45px;">
									<nav class="navigation pagination" role="navigation">
										<div class="nav-links">
											<c:if test="${showPrev}">
												<a class="prev page-numbers"
													href="<c:url value='/forum/notice/listPage.do?page=${beginPage-1}&size=10'/> ">
													<span class="lnr lnr-arrow-left"></span>
												</a>
											</c:if>


											<c:forEach var="i" begin="${beginPage}" end="${endPage}">
												<a class="page-numbers"
													href="<c:url value='/forum/notice/listPage.do?page=${i-1}&size=10'/>">
													${i } </a>
											</c:forEach>

											<%--  <a class="page-numbers current" href="<c:url value='/forum/notice/listPage.do?page=0&size=10'/>"> 1 </a>
				                                <a class="page-numbers" href="<c:url value='/forum/notice/listPage.do?page=1&size=10'/>">2</a>
				                                <a class="page-numbers" href="<c:url value='/forum/notice/listPage.do?page=2&size=10'/>">3</a>
				                                <a class="page-numbers" href="<c:url value='/forum/notice/listPage.do?page=3&size=10'/>">4</a>
				                                <a class="page-numbers" href="<c:url value='/forum/notice/listPage.do?page=4&size=10'/>">5</a>
				                                <a class="page-numbers" href="<c:url value='/forum/notice/listPage.do?page=5&size=10'/>">6</a>
				                                <a class="page-numbers" href="<c:url value='/forum/notice/listPage.do?page=6&size=10'/>">7</a>
				                                <a class="page-numbers" href="<c:url value='/forum/notice/listPage.do?page=7&size=10'/>">8</a>
				                                <a class="page-numbers" href="<c:url value='/forum/notice/listPage.do?page=8&size=10'/>">9</a>
				                                <a class="page-numbers" href="<c:url value='/forum/notice/listPage.do?page=9&size=10'/>">10</a>
				                                --%>

											<c:if test="${showNext}">

												<a class="next page-numbers"
													href="<c:url value='/forum/notice/listPage.do?page=${endPage+1}&size=10'/> ">
													<span class="lnr lnr-arrow-right"></span>
												</a>
											</c:if>



										</div>
									</nav>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end .col-md-6 -->
		</div>
		<!-- end .row -->
	</div>
	<!-- end .container -->
</section>
<script type="text/javascript">
	window.onload = function() {
		var result = '${result}';
		var msg = '${msg}';

		if (result != '') {
			alert(msg)
			/* 			window.location.href = '/11004/loginPage.do';
			 */
		}
	};
</script>
