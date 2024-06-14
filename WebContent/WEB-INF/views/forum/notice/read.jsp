<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String ctx = request.getContextPath();
%>
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
				<div class="forum_detail_area ">
					<div class="cardify forum--issue">
						<div class="title_vote clearfix">
							<h3>${board.title }</h3>

							<div class="vote">
								<!-- 좋아요  -->
								<a href="#" id='cThumbUpAnchor'
									onClick="javascript:thumbUp(${board.boardSeq}, ${board.boardTypeSeq });"
									<c:if test ='${liked == 1 }'> class = "active" </c:if>>
									<span class="lnr lnr-thumbs-up"></span>
								</a>

								<!-- 싫어요  -->
								<a href="#" id='cThumbDownAnchor' 
								onClick="javascript:thumbDown(${board.boardSeq}, ${board.boardTypeSeq});"
								<c:if test ='${disLiked == 1 }'> class = "active" </c:if>
								>
                                        <span class="lnr lnr-thumbs-down"></span>
                                    </a>
							</div>
							<!-- end .vote -->
						</div>
						<!-- end .title_vote -->
						<div class="suppot_query_tag">
							<img class="poster_avatar"
								src="<%=ctx%>/assest/template/images/support_avat1.png"
								alt="Support Avatar"> ${board.regMemberId } <span>${board.regDtm }</span>
						</div>
						<p style="margin-bottom: 0; margin-top: 19px;">
							${board.content }</p>

						<br> <br> <br>

						<!-- 첨부 파일 다운로드  -->
						<c:forEach items="${attFile}" var="attFile">
							<c:if test="${attFile != null}">
								<a
									href='<%=ctx%>/forum/download.do?attachSeq=${attFile.attachSeq}'>
									${attFile.attachSeq} ${attFile.orgFileNm} (${attFile.fileSize})
								</a>
							</c:if>
							<br>
						</c:forEach>


					</div>
					<!-- end .forum_issue -->




					<div class="forum--replays cardify">
						<div class="area_title">
							<h4>1 Replies</h4>
						</div>
						<!-- end .area_title -->

						<div class="forum_single_reply">
							<div class="reply_content">
								<div class="name_vote">
									<div class="pull-left">
										<h4>
											AazzTech <span>staff</span>
										</h4>
										<p>Answered 3 days ago</p>
									</div>
									<!-- end .pull-left -->

									<div class="vote">
										<a href="#" class="active"> <span
											class="lnr lnr-thumbs-up"></span>
										</a> <a href="#" class=""> <span class="lnr lnr-thumbs-down"></span>
										</a>
									</div>
								</div>
								<!-- end .vote -->
								<p>Nunc placerat mi id nisi interdum mollis. Praesent
									pharetra, justo ut sceleris que the mattis, leo quam aliquet
									congue placerat mi id nisi interdum mollis.</p>
							</div>
							<!-- end .reply_content -->
						</div>
						<!-- end .forum_single_reply -->

						<div class="comment-form-area">
							<h4>Leave a comment</h4>
							<!-- comment reply -->
							<div class="media comment-form support__comment">
								<div class="media-left">
									<a href="#"> <img class="media-object"
										src="<%=ctx%>/assest/template/images/m7.png"
										alt="Commentator Avatar">
									</a>
								</div>
								<div class="media-body">
									<form action="#" class="comment-reply-form">
										<div id="trumbowyg-demo"></div>
										<button class="btn btn--sm btn--round">Post Comment</button>
									</form>
								</div>
							</div>
							<!-- comment reply -->
						</div>
					</div>
					<!-- end .forum_replays -->
				</div>
				<!-- end .forum_detail_area -->
			</div>
			<!-- end .col-md-12 -->
		</div>
		<!-- end .row -->
	</div>
	<!-- end .container -->
</section>
<!--================================
            END DASHBOARD AREA
    =================================-->

<script type="text/javascript">

	$('#trumbowyg-demo').trumbowyg({
		lang : 'kr'
	});
	
    //다운로드 기능
    function download(attachSeq){
    	let url = '<%=ctx%>/form/notice/attach.do';
		url += '?attachSeq=' + attachSeq;
	}
    
    //좋아요 
    function thumbUp(boardSeq, boardTypeSeq){
    	console.log(boardSeq);
    	console.log(boardTypeSeq);
    	
    	let url = '<%=ctx%>/forum//notice/thumb-up.do?';
    	url += 'boardSeq=' + boardSeq;
    	url += '&boardTypeSeq=' + boardTypeSeq;
    	

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
    			/* console.log(result); */
			 if(result == 0){//삭제해도1이기에 구분
	    			$('a#cThumbUpAnchor').removeClass('active');
	    				
	    			} else{
	    				$('a#cThumbUpAnchor').addClass('active');
	    			}
    		},
			// 결과 에러 콜백함수
    		error : function(request, status, error) {
    			console.log(error)
    		}
    	});

    }
    


    //싫어요 
    function thumbDown(boardSeq, boardTypeSeq){
    	console.log(boardSeq);
    	console.log(boardTypeSeq);
    	
    	let url = '<%=ctx%>/forum/notice';
    	url += '/' + boardTypeSeq;
    	url += '/' + boardSeq;
    	url += '/thumb-down.do';
    	
    	

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
    			/* console.log(result); */
			 if(result == 0){//삭제해도1이기에 구분
	    			$('a#cThumbDownAnchor').removeClass('active');
	    				
	    			} else{
	    				$('a#cThumbDownAnchor').addClass('active');
	    			}
    		},
			// 결과 에러 콜백함수
    		error : function(request, status, error) {
    			console.log(error)
    		}
    	});

    }
    
    
    
    
</script>

