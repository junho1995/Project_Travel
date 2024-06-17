<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/header_css.css">
<%-- <link rel="stylesheet" href="../css/calendar_css.css">--%>


<nav class="navbar">
	<%-- <div class="circle" id="draggableCircle"></div> --%>
	<a href="../jsp/homepage.jsp" class="logo-link"> <img
		src="../images/main_logo.png" alt="HTML Logo">
	</a>
	<li><a href="../jsp/add_schedule.jsp">여행 계획</a></li>
	<li><a href="../jsp/share_c.jsp">여행 후기</a></li>
	<li><a href="../jsp/main.jsp">커뮤니티</a></li>
	<li><a href="../jsp/alert.jsp">내 정보</a></li>

	<!-- Login 버튼 -->
	<div class="login-button">
		<a href="#" onclick="openLoginModal()"
			style="text-decoration: none; color: pink;">로그인</a>
	</div>
</nav>
<hr>

<!-- 로그인 모달 -->
<div id="loginModal" class="modal">
	<!-- 모달 내용 -->
	<div class="modal-content">
		<span class="close" onclick="closeModal()">&times;</span>
		<!-- 로그인 폼 -->
		<div class="login-form">
			<h2>Login to Your Account</h2>
			<form action="/login" method="post">
				<input type="text" id="username" name="username" placeholder="ID"><br>
				<input type="password" id="password" name="password"
					placeholder="PW"><br> <input type="submit"
					value="Login">
			</form>
			<p>
				더 많은 기능을 이용하고 싶으신가요? <a href="#" onclick="openSignupModal()">Sign
					up</a>
			</p>
		</div>
	</div>
</div>

<!-- 회원가입 모달 -->
<div id="signupModal" class="modal">
	<!-- 모달 내용 -->
	<div class="modal-content">
		<!-- 닫기 버튼 -->
		<span class="close" onclick="closeModal()">&times;</span>
		<!-- 회원가입 폼 -->
		<div class="signup-form">
			<h2>Create Account</h2>
			<form action="/signup" method="post">
				<!-- 사용자 이름 입력 -->
				<input type="text" id="signup-username" name="username"
					placeholder="ID"><br>
				<!-- 비밀번호 입력 -->
				<input type="password" id="signup-password" name="password"
					placeholder="PW"><br>
				<!-- 주민등록번호 입력 -->
				<input type="text" id="signup-resident-id" name="resident-id"
					placeholder="Resident ID"><br>
				<!-- 휴대폰번호 입력 -->
				<input type="tel" id="signup-phone-number" name="phone-number"
					placeholder="phone number"><br>
				<!-- 이메일 입력 -->
				<input type="email" id="signup-email" name="email"
					placeholder="Email"><br>
				<!-- 닉네임 입력 -->
				<input type="text" id="signup-nickname" name="nickname"
					placeholder="Nickname"><br>
				<!-- 회원가입 버튼 -->
				<input type="submit" value="Sign up">
			</form>
		</div>
	</div>
</div>

<!-- 외부 JavaScript 파일 연결 -->
<script src="../script/header_script.js"></script>
<script src="../script/calendar_script.js"></script>










