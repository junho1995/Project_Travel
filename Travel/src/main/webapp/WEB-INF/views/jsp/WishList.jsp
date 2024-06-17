<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:include page="../include/header.jsp" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WishList</title>
<link rel="stylesheet" type="text/css" href="../css/WishList.css">
</head>

<body>
	<div class="wrap">
		<div class="main"></div>

		<div class="line">
			<p>♡ Wish List ♡</p>
		</div>

		<!-- 검색 박스 -->
		<div class="search-container">
			<form action="search_result.jsp" method="GET">
				<input type="text" name="text" placeholder="국가/도시/회원ID 를(을) 입력하세요">
				<button type="submit">검색</button>
			</form>
		</div>

		<!-- 버튼 박스 -->
		<div class="button_box">
			<a href="add_schedule.jsp" class="btn_1">New Schedule</a> <a
				href="my_trip.jsp" class="btn_2">My Trip</a> <a href="share_c.jsp"
				class="btn_3">Share Square</a>
		</div>

		<!-- 정렬 버튼 -->
		<div class="sort-box">
			<a href="WishList.jsp" class="btn_wish">Wish List</a> <a
				href="Best.jsp" class="btn-best">Best</a>
		</div>

		<div class="block" id="box">
			<img src="../images/map.jpg" alt="대표이미지"
				onclick="location.href='plan_share.jsp'"> <img src="#"
				alt="대표이미지" onclick="location.href='plan_share.jsp'"> <img
				src="#" alt="대표이미지" onclick="location.href='plan_share.jsp'">
			<img src="#" alt="대표이미지" onclick="location.href='plan_share.jsp'">
			<img src="#" alt="대표이미지" onclick="location.href='plan_share.jsp'">
			<img src="#" alt="대표이미지" onclick="location.href='plan_share.jsp'">
			<img src="#" alt="대표이미지" onclick="location.href='plan_share.jsp'">
		</div>

	</div>
</body>
</html>

<%--일정관리 footer --%>
<jsp:include page="../include/footer.jsp" />