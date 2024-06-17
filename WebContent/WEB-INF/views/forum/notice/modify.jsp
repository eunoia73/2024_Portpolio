<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/scripts.jsp"%>
<link rel="stylesheet"
	href="<%=ctx%>/assest/template/css/trumbowyg.min.css">
<script src="<%=ctx%>/assest/template/js/vendor/trumbowyg.min.js"></script>
<script src="<%=ctx%>/assest/template/js/vendor/trumbowyg/ko.js"></script>

<!--================================
            START DASHBOARD AREA
    =================================-->
<section class="support_threads_area section--padding2">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="question-form cardify p-4">
					<form
						action="<%=ctx %>/forum/notice/modify.do?boardSeq=${board.boardSeq }&boardTypeSeq=${board.boardTypeSeq}"
						method="POST" enctype="multipart/form-data">
						<div class="form-group">
							<label>제목</label> <input type="text" name="title"
								value=${board.title } required>
						</div>
						<div class="form-group">
							<label>Description</label>
							<div id="trumbowyg-demo">${board.content }</div>
						</div>


						<c:if test="${attFile !=null}">
							<%-- 	<a href = 'javascript:download(${attFile.attachSeq})'>	${attFile.orgFileNm } (${attFile.fileSize})	</a>
								 --%>

							<!-- 파일다운로드   -->
							<c:forEach items="${attFile}" var="j">



								<a href='<%=ctx%>/forum/download.do?attachSeq=${j.attachSeq}'>
									${j.attachSeq} ${j.orgFileNm} (${j.fileSize}) </a>

								<!-- 파일 삭제 버튼  -->

								<a
									href='<%=ctx%>/forum/notice/deleteAttach.do?attachSeq=${j.attachSeq }&boardSeq=${board.boardSeq }&boardTypeSeq=${board.boardTypeSeq}'>
									&nbsp | &nbsp 삭제 </a>

								<hr>
							</c:forEach>

						</c:if>


						<c:if test="${count != 0}">
							<c:forEach begin="1" end="${count }">

								<div class="attachments">
									<label>Attachments</label> <label> <span
										class="lnr lnr-paperclip"></span> Add File <span>or
											Drop Files Here</span> <input type="file" name="attFile"
										style="display: inline-block;">
									</label>
								</div>
							</c:forEach>
						</c:if>
				</div>
				<div class="form-group">
					<button class="btn btn--md btn-primary" type="submit">
						Submit Request</button>
					<a href="<c:url value='/forum/notice/listPage.do'/>"
						class="btn btn--md btn-light">Cancel</a>
				</div>
				</form>
			</div>
			<!-- ends: .question-form -->
		</div>
		<!-- end .col-md-12 -->
	</div>
	<!-- end .row -->

	<!-- end .container -->
</section>
<!--================================
            END DASHBOARD AREA
    =================================-->
<script type="text/javascript">
	$('#trumbowyg-demo').trumbowyg({
		lang : 'kr'
	});
	
	window.onload = function() {
		var result = '${result}';
		var msg = '${msg}';

		if (result != '') {
			alert(msg)
			window.location.href = '/forum//notice/listPage.do';
		}
	};
	
	<%--  //첨부파일 삭제 
    function deleteFile(attachSeq){
    	console.log(attachSeq);
    	
    	let url = '<%=ctx%>/forum/notice/deleteAttach.do';
    	url += '?attachSeq=' + attachSeq;
    	

    	$.ajax({    
    		// 타입 (get, post, put 등등)    
    		type : 'GET',           
    		// 요청할 서버url
    		url : url,
    		// 비동기화 여부 (default : true)
    		/* async : true, */
			// Http header
    		headers : {
    			"Content-Type" : "application/json"
    			/* "X-HTTP-Method-Override" : "POST" */
    		},
    		dataType : 'text',
			// 결과 성공 콜백함수 - 비동기통신은 콜백이 항상 있어야한다
    		success : function(result) {
    			 console.log(result); 
		/* 	 if(result == 0){//삭제해도1이기에 구분
	    			$('a#cThumbUpAnchor').removeClass('active');
	    				
	    			} else{
	    				$('a#cThumbUpAnchor').addClass('active');
	    			} */
    		},
			// 결과 에러 콜백함수
    		error : function(request, status, error) {
    			alert(error);
                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);

    		}
    	});

    }  --%>
    
	
</script>
