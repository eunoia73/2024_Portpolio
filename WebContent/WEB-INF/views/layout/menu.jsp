<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- ================================
	    START MENU AREA
	================================= -->
<%
//1. 로그인 되어있으면(session 객체에 id 있으면) 버튼은 logout
String loginout; //버튼 이름 변수

if (session.getAttribute("memberId") != null) {
	loginout = "로그아웃";

} else {
	//2. 로그인 안 되어있으면 버튼은 login
	loginout = "로그인";
}
%>
<%-- <%
//1. 로그인 되어있으면(session 객체에 id 있으면) 버튼은 memberId
String showMemberId; //버튼 이름 변수

if (session.getAttribute("memberId") != null) {
	showMemberId = "Join Now";

} else {
	//2. 로그인 안 되어있으면 버튼은 login
	showMemberId = ${cookie.memberId.value};
}
%> --%>


<!-- start menu-area -->
<div class="menu-area">
	<!-- start .top-menu-area -->
	<div class="top-menu-area">
		<!-- start .container -->
		<div class="container">
			<!-- start .row -->
			<div class="row">
				<!-- start .col-md-3 -->
				<div class="col-lg-3 col-md-3 col-6 v_middle">
					<div class="logo">
						<a href="<c:url value='/index.do'/>"> <img
							src="<c:url value='/assest/template/images/logo.png'/>"
							alt="logo image" class="img-fluid" />
						</a>
					</div>
				</div>
				<!-- end /.col-md-3 -->

				<!-- start .col-md-5 -->
				<div class="col-lg-8 offset-lg-1 col-6 col-md-9 v_middle">
					<!-- start .author-area -->
					<div class="author-area not_logged_in">
						<div class="author__notification_area">
							<%-- <ul>
								<li class="has_dropdown">
									<div class="icon_wrap">
										<span class="lnr lnr-cart"></span> <span
											class="notification_count purch">2</span>
									</div>

									<div class="dropdowns dropdown--cart">
										<div class="cart_area">
											<div class="cart_product">
												<div class="product__info">
													<div class="thumbn">
														<img
															src="<c:url value='/assest/template/images/capro1.jpg'/>"
															alt="cart product thumbnail" />
													</div>

													<div class="info">
														<a class="title" href="single-product.html">Finance
															and Consulting Business Theme</a>
														<div class="cat">
															<a href="#"> <img
																src="<c:url value='/assest/template/images/catword.png'/>"
																alt="" />Wordpress
															</a>
														</div>
													</div>
												</div>

												<div class="product__action">
													<a href="#"> <span class="lnr lnr-trash"></span>
													</a>
													<p>$60</p>
												</div>
											</div>
											<div class="cart_product">
												<div class="product__info">
													<div class="thumbn">
														<img
															src="<c:url value='/assest/template/images/capro2.jpg'/>"
															alt="cart product thumbnail" />
													</div>

													<div class="info">
														<a class="title" href="single-product.html">Flounce -
															Multipurpose OpenCart Theme</a>
														<div class="cat">
															<a href="#"> <img
																src="<c:url value='/assest/template/images/catword.png'/>"
																alt="" />Wordpress
															</a>
														</div>
													</div>
												</div>

												<div class="product__action">
													<a href="#"> <span class="lnr lnr-trash"></span>
													</a>
													<p>$60</p>
												</div>
											</div>
											<div class="total">
												<p>
													<span>Total :</span>$80
												</p>
											</div>
											<div class="cart_action">
												<a class="go_cart" href="cart.html">View Cart</a> <a
													class="go_checkout" href="checkout.html">Checkout</a>
											</div>
										</div>
									</div>
								</li>
							</ul> --%>
						</div>
						<div class="pull-right join desktop-size d-md-block d-none">
							<a href="<c:url value='/auth/joinPage.do'/>"
								class="btn btn--round btn-secondary  btn--xs">회원가입</a>

							<!-- session에 memberId 있으면 로그아웃  -->
							<c:choose>
								<c:when test="${not empty sessionScope.memberId}">
									<a href="<c:url value='/logout.do'/>"
										class="btn btn--round btn--xs">로그아웃</a>

								</c:when>
								<c:otherwise>
									<a href="<c:url value='/auth/loginPage.do'/>"
										class="btn btn--round btn--xs">로그인</a>
								</c:otherwise>
							</c:choose>



						</div>
						<div class="pull-right join mobile-size d-md-none d-flex">
							<a href="#" class="btn btn--round btn-secondary "><span
								class="lnr lnr-user"></span></a> <a href="#" class="btn btn--round"><span
								class="lnr lnr-enter"></span></a>
						</div>
					</div>
					<!-- end .author-area -->
				</div>
				<!-- end /.col-md-5 -->
			</div>
			<!-- end /.row -->
		</div>
		<!-- end /.container -->
	</div>
	<!-- end  -->

	<!-- start .mainmenu_area -->
	<div class="mainmenu">
		<!-- start .container -->
		<div class="container">
			<!-- start .row-->
			<div class="row">
				<!-- start .col-md-12 -->
				<div class="col-md-12">
					<nav class="navbar navbar-expand-md navbar-light mainmenu__menu">
						<button class="navbar-toggler" type="button"
							data-toggle="collapse" data-target="#navbarNav"
							aria-controls="navbarNav" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="navbarNav">
							<ul class="navbar-nav">
								<li class="has_dropdown"><a
									href="<c:url value='/index.do'/>">HOME</a> <!-- 									<div class="dropdowns dropdown--menu">
 --> <!-- <ul>
											<li><a href="index.html">Home Multi Vendor</a></li>
											<li><a href="index-single.html">Home Two Single User</a>
											</li>
											<li><a href="index3.html">Home Three Product</a></li>
										</ul> --> <!-- 									</div>
 --><!-- </li> -->
								<!-- <li class="has_dropdown"><a href="all-products-list.html">all
										product</a>
									<div class="dropdowns dropdown--menu">
										<ul>
											<li><a href="all-products.html">Recent Items</a></li>
											<li><a href="all-products.html">Popular Items</a></li>
											<li><a href="index3.html">Free Templates</a></li>
											<li><a href="#">Follow Feed</a></li>
											<li><a href="#">Top Authors</a></li>
										</ul>
									</div></li> -->
								<!-- <li class="has_dropdown"><a href="#">categories</a>
									<div class="dropdowns dropdown--menu">
										<ul>
											<li><a href="category-grid.html">Popular Items</a></li>
											<li><a href="category-grid.html">Admin Templates</a></li>
											<li><a href="category-grid.html">Blog / Magazine /
													News</a></li>
											<li><a href="category-grid.html">Creative</a></li>
											<li><a href="category-grid.html">Corporate Business</a>
											</li>
											<li><a href="category-grid.html">Resume Portfolio</a></li>
											<li><a href="category-grid.html">eCommerce</a></li>
											<li><a href="category-grid.html">Entertainment</a></li>
											<li><a href="category-grid.html">Landing Pages</a></li>
										</ul>
									</div></li> -->
								<!-- <li class="has_megamenu"><a href="#">Elements</a>
									<div class="dropdown_megamenu contained">
										<div class="megamnu_module">
											<div class="menu_items">
												<div class="menu_column">
													<ul>
														<li><a href="accordion.html">Accordion</a></li>
														<li><a href="alert.html">Alert</a></li>
														<li><a href="brands.html">Brands</a></li>
														<li><a href="buttons.html">Buttons</a></li>
														<li><a href="cards.html">Cards</a></li>
														<li><a href="charts.html">Charts</a></li>
														<li><a href="content-block.html">Content Block</a></li>
														<li><a href="dropdowns.html">Drpdowns</a></li>
													</ul>
												</div>

												<div class="menu_column">
													<ul>
														<li><a href="features.html">Features</a></li>
														<li><a href="footer.html">Footer</a></li>
														<li><a href="info-box.html">Info Box</a></li>
														<li><a href="menu.html">Menu</a></li>
														<li><a href="modal.html">Modal</a></li>
														<li><a href="pagination.html">Pagination</a></li>
														<li><a href="peoples.html">Peoples</a></li>
														<li><a href="products.html">Products</a></li>
													</ul>
												</div>

												<div class="menu_column">
													<ul>
														<li><a href="progressbar.html">Progressbar</a></li>
														<li><a href="social.html">Social</a></li>
														<li><a href="tab.html">Tabs</a></li>
														<li><a href="table.html">Table</a></li>
														<li><a href="testimonials.html">Testimonials</a></li>
														<li><a href="timeline.html">Timeline</a></li>
														<li><a href="typography.html">Typography</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div></li> -->

								<li class="has_dropdown"><a href="<c:url value='/forum/notice/listPage.do'/>">게시판</a>
									<div class="dropdowns dropdown--menu">
										<ul>
											<li><a
												href="<c:url value='/forum/notice/listPage.do'/>">자유게시판</a>
											</li>
											<!-- <li><a href="all-products.html">Popular Items</a></li>
											<li><a href="index3.html">Free Templates</a></li>
											<li><a href="#">Follow Feed</a></li>
											<li><a href="#">Top Authors</a></li> -->
										</ul>
									</div></li>

								 	<li><a
									href="<c:url value='/contact/contactPage.do'/>">Contact Me</a>
								</li> 
							</ul>
						</div>
						<!-- /.navbar-collapse -->
					</nav>
				</div>
				<!-- end /.col-md-12 -->
			</div>
			<!-- end /.row-->
		</div>
		<!-- start .container -->
	</div>
	<!-- end /.mainmenu-->
</div>
<!-- end /.menu-area -->
<!--================================
	    END MENU AREA
	=================================-->

