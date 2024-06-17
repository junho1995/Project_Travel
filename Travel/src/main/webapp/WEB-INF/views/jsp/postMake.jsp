<%@ page contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" href="../css/postMake.css">
<jsp:include page="../include/header.jsp" />

<header class="header">
	<h1 class="header-title">게시글 작성하기</h1>
</header>

<div class="container">
	<div class="input-container">
		<input type="text" id="post-title" placeholder="제목을 입력하세요...">
		<textarea id="post-content" placeholder="내용을 입력하세요..."></textarea>
		<input type="file" id="post-image" accept="image/*">
		<!-- 이미지 업로드를 위한 input 추가 -->
		<input type="text" id="post-tags" placeholder="태그를 입력하세요... (쉼표로 구분)">
		<select id="limit-select">
			<!-- 제한된 인원을 선택할 수 있는 드롭다운 -->
			<option value="1">1명</option>
			<option value="2">2명</option>
			<option value="3">3명</option>
			<!-- 필요한 만큼 인원 제한을 추가할 수 있습니다. -->
		</select>
		<button id="post-button">게시물 작성</button>
	</div>
</div>

<script src="../script/postMake.js"></script>

