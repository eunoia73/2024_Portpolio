<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/scripts.jsp" %>
	<link rel="stylesheet" href="<%=ctx%>/assest/template/css/trumbowyg.min.css">
    <script src="<%=ctx%>/assest/template/js/vendor/trumbowyg.min.js"></script>
    <script src="<%=ctx%>/assest/template/js/vendor/trumbowyg/ko.js"></script>
    <script type="text/javascript">
	    $('#trumbowyg-demo').trumbowyg({
	        lang: 'kr'
	    });
	    
	    
	</script>
	
    <!--================================
            START DASHBOARD AREA
    =================================-->
    <section class="support_threads_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="question-form cardify p-4">
                        <form action = "/pf/forum/notice/write.do" method ="POST" enctype="multipart/form-data" >
                            <div class="form-group">
                                <label>제목</label>
                                <input type="text" name="title" placeholder="제목을 입력해주세요." required>
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <div id="trumbowyg-demo"></div>
                            </div>
                            <div class="form-group">
                                <div class="attachments">
                                    <label>첨부파일</label>
                                    <label>
                                        <span class="lnr lnr-paperclip"></span> 파일을 선택해주세요.
                                        <span></span>
                                        <input type="file" name = "attFile" style="display:inline-block;">
                                    </label>
                                </div>
                                
                                <div class="attachments">
                                    <label>첨부파일</label>
                                    <label>
                                        <span class="lnr lnr-paperclip"></span> 파일을 선택해주세요.
                                        <span></span>
                                        <input type="file" name = "attFile" style="display:inline-block;">
                                    </label>
                                </div>
                                
                                <div class="attachments">
                                    <label>첨부파일</label>
                                    <label>
                                        <span class="lnr lnr-paperclip"></span> 파일을 선택해주세요.
                                        <span></span>
                                        <input type="file" name = "attFile" style="display:inline-block;">
                                    </label>
                                </div>  
                                
                            </div>
                            <div class="form-group">
                                <button class="btn btn--md btn-primary" type="submit"> 저장하기</button>
                            	<a href="<c:url value='/forum/notice/listPage.do'/>" class="btn btn--md btn-light">취소하기</a>
                            </div>
                        </form>
                    </div><!-- ends: .question-form -->
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
