<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%
String ctx = request.getContextPath();
%>

<link rel="stylesheet"
	href="<%=ctx%>/assest/template/css/trumbowyg.min.css">
<script src="<%=ctx%>/assest/template/js/vendor/trumbowyg.min.js"></script>
<script src="<%=ctx%>/assest/template/js/vendor/trumbowyg/ko.js"></script>
<c:if test="${not empty code and not empty msg}">
	<script type="text/javascript">
            window.onload = function() {
                var code = '${code}';
                var msg = '${msg}';

                if (code !== '') {
                    alert(msg);
                }
            }
        </script>
</c:if>
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
									<c:if test ='${liked == 1 }'> class = "active" </c:if>> <span
									class="lnr lnr-thumbs-up"></span>
								</a>

								<!-- 싫어요  -->
								<a href="#" id='cThumbDownAnchor'
									onClick="javascript:thumbDown(${board.boardSeq}, ${board.boardTypeSeq});"
									<c:if test ='${disLiked == 1 }'> class = "active" </c:if>>
									<span class="lnr lnr-thumbs-down"></span>
								</a>
							</div>
							<!-- end .vote -->
						</div>
						<!-- end .title_vote -->
						<div class="suppot_query_tag">
							<%-- <img class="poster_avatar"
								src="<%=ctx%>/assest/template/images/support_avat1.png"
								alt="Support Avatar">  --%>
								
								${board.regMemberId }
								<span>${board.regDtm }</span>
						</div>
						<p style="margin-bottom: 0; margin-top: 19px;">
							${board.content }</p>

						<br> <br> <br>

						<!-- 첨부 파일 다운로드  -->
						<c:forEach items="${attFile}" var="attFile">
							<c:if test="${attFile != null}">
								<a
									href='<%=ctx%>/forum/download.do?attachSeq=${attFile.attachSeq}'>
									<span class="lnr lnr-download"></span> ${attFile.orgFileNm}
									(${attFile.fileSize})
								</a>
							</c:if>
							<br>
						</c:forEach>

						<!-- 로그인된 계정과 비교해서 자신이 쓴 게시물만 수정, 삭제 버튼 나와야 한다! -->
						<c:if test="${board.regMemberSeq eq member.memberSeq}">
						<!-- 게시글 수정버튼  -->
						<a
							href="<%=ctx %>/forum/notice/modifyPage.do?boardSeq=${board.boardSeq}&boardTypeSeq=${board.boardTypeSeq} "
							class="comment-reply-form">
							<button class="btn btn--sm btn--round">수정</button>
						</a>

						<!-- 게시글 삭제 버튼  -->
						<a
							href="<%=ctx %>/forum/notice/deleteBoard.do?boardSeq=${board.boardSeq}&boardTypeSeq=${board.boardTypeSeq} "
							onClick="return confirmDelete();" class="comment-reply-form">
							<button class="btn btn--sm btn--round">삭제</button>
						</a>

						</c:if>
					</div>
					<!-- end .forum_issue -->




					<div class="forum--replays cardify">
						<div class="area_title">
							<h4>${commentCnt }개의 댓글</h4>
						</div>
						<!-- end .area_title -->

						<!-- 댓글  -->
						<c:forEach items="${comments}" var="comment" varStatus="status">

							<div class="forum_single_reply">
								<div class="reply_content">
									<div class="name_vote">
										<div class="pull-left">
											<h4>
												<div id="comment_content">${comment.content }</div>

												<!-- <span>staff</span> -->
											</h4>
											<p>${comment.memberNm}${comment.regDtm}</p>
										</div>
										<!-- end .pull-left -->


									<!-- 로그인 계정 비교해서 자신이 작성한 댓글만 수정, 삭제 버튼 나와야한다 -->
										<!-- 댓글 수정 | 삭제  -->

										<c:if test="${comment.memberSeq eq member.memberSeq}">
											<div style="position: absolute; right: 30px;">
												<button class="modifyButton btn btn--sm btn--round"
													onClick="showPanel(${comment.commentSeq}, '${comment.content}')"
													id="modify${comment.commentSeq }">수정</button>
												| <a href="" class="btn btn--sm btn--round"
													onClick="deleteComment(${comment.commentSeq})">삭제</a>
											</div>


											<!-- 수정버튼 누르면 수정 폼이 나온다.  -->
											<div id="showModify${comment.commentSeq}"></div>
										</c:if>

										<!-- 댓글 좋아요 싫어요  -->
										<!-- <div class="vote">
											<a href="#" class="active"> <span
												class="lnr lnr-thumbs-up"></span>
											</a> <a href="#" class=""> <span class="lnr lnr-thumbs-down"></span>
											</a>
										</div> -->


									</div>
									<!-- end .vote -->

								</div>


								<!-- end .reply_content -->
							</div>
							<!-- end .forum_single_reply -->
						</c:forEach>


						<div class="comment-form-area">
							<h4>댓글 남기기</h4>
							<!-- comment reply -->
							<div class="media comment-form support__comment">
								<div class="media-left">
									<a href="#"> 
									<%-- <img class="media-object"
										src="<%=ctx%>/assest/template/images/m7.png"
										alt="Commentator Avatar"> --%>
									</a>
								</div>
								<div class="media-body">
									<div id="trumbowyg-demo"></div>
									<button class="btn btn--sm btn--round"
										onClick='addComment(${board.boardSeq}, ${board.boardTypeSeq })'>저장하기</button>
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
	
	window.onload = function() {
		var result = '${params.result}';
		var msg = '${params.msg}';

		if (result != '') {
			alert(msg)
/* 			window.location.href = '/11004/loginPage.do';
 */		}
	};
	
	
	
	
	// delete 확인 메시지 
    function confirmDelete() {
        return confirm("정말로 삭제하시겠습니까?");
    }
	
	
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
			 if(result == 0){//삭제해도1이니까  구분
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
    
    
  //댓글 추가 기능
    
    function addComment(boardSeq, boardTypeSeq) {
  		var url = '<%=ctx%>/forum/notice/reply.do';
  		$.ajax({        
  			type : 'POST',
  			url : url,
  			headers : {
  				"Content-Type" : "application/json"
  			},
  			
  			dataType : 'JSON',
  			data : JSON.stringify ({
  				//지정해서 보내기 html로 변환되어서 이동
  				boardSeq : boardSeq,
  				boardTypeSeq : boardTypeSeq,
  				content: $('#trumbowyg-demo').trumbowyg('html')
  			}),
  			success : function(result) {
  				if(result) {
  					window.location.reload();
  				}
  				else {
  	  			alert('실패!');
  				}
  			},
  			error : function(request, status, error) {
  				console.log(error)
  			}
  		});
  	}
  

  
  
  //수정 폼 
 function showPanel(commentSeq, content){
	  var showModifyForm = document.getElementById('showModify'+commentSeq);
	  console.log(commentSeq);
	  console.log(content);
	  //수정 폼 생성 
	  showModifyForm.innerHTML = '<br><textarea id="modifyComment'+commentSeq+'"  style="width :275%;"></textarea><button class="btn btn--sm btn--round" onClick="updateComment('+commentSeq+')">저장하기</button>';
	
	  //기존에 쓰여진 댓글 안 보이게하기 
	  var comment = document.getElementById('comment_content');
	  /* comment.innerHTML = ''; */
	  
	  //textarea의 value로 
	  var modifyComment = document.getElementById('modifyComment'+commentSeq);
	  modifyComment.value = content;
  }
  
  
 //댓글 수정 기능
    
    function updateComment(commentSeq) {
  		var url = '<%=ctx%>/forum/notice/modifyComment.do';
  	    var content = $("#modifyComment"+commentSeq).val();

  		
  		$.ajax({        
  			type : 'POST',
  			url : url,
  			headers : {
  				"Content-Type" : "application/json"
  			},
  			
  			dataType : 'JSON',
  			data : JSON.stringify ({
  				//지정해서 보내기 html로 변환되어서 이동
  				commentSeq : commentSeq,
  				content: content
  			}),
  			success : function(result) {
  				if(result) {
  					window.location.reload();
  					alert('수정되었습니다.');
  				}
  				else {
  	  			alert('실패!');
  				}
  			},
  			error : function(request, status, error) {
  				console.log(error)
  			}
  		});
  	}
   
   
 //댓글 삭제 기능
   
   function deleteComment(commentSeq) {
 		var url = '<%=ctx%>/forum/notice/deleteComment.do';
 		url += '?commentSeq='+commentSeq;
 		$.ajax({        
 			type : 'POST',
 			url : url,
 			headers : {
 				"Content-Type" : "application/json"
 			},
 			
 			dataType : 'JSON',
 			data : JSON.stringify ({
 				//지정해서 보내기 html로 변환되어서 이동
 				commentSeq : commentSeq
 			}),
 			success : function(result) {
 				if(result) {
 					alert('삭제되었습니다.');
 					window.location.reload();
 				}
 				else {
 	  			alert('실패!');
 				}
 			},
 			error : function(request, status, error) {
 				console.log(error)
 			}
 		});
 	}

    
    
</script>

