<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:include page="../include/header.jsp" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WishList</title>
<link rel="stylesheet" type="text/css" href="../css/latest.css">
</head>

<body>
	<div class="wrap">
		<div class="main"></div>

		<!-- 검색 박스 -->
		<div class="search-container">
			<form action="search_result.jsp" method="GET">
				<input type="text" name="text" placeholder="국가/도시/회원ID 를(을) 입력하세요">
				<button type="submit">검색</button>
			</form>
		</div>

		<div class="line"></div>

		<!-- 버튼 박스 -->
		<div class="button_box">
			<a href="my_trip.jsp" class="btn_1">My Trip</a> <a
				href="add_schedule.jsp" class="btn_2">New Schedule</a> <a
				href="WishList.jsp" class="btn_3">Wish List</a> <a
				href="share_c.jsp" class="btn_4">Share square</a>
		</div>

		<!-- 정렬 버튼 -->
		<div class="sort-box">
			<a href="latest.jsp" class="btn-latest">최신순</a> <a href="Best.jsp"
				class="btn-best">Best</a>
		</div>

		<div class="block">
			<div class="box">
				<a href="#"><p>#01</p></a> <a href="#"><p>#02</p></a> <a href="#"><p>#03</p></a>
				<a href="#"><p>#04</p></a> <a href="#"><p>#05</p></a> <a href="#"><p>#06</p></a>
				<a href="#"><p>#07</p></a> <a href="#"><p>#08</p></a> <a href="#"><p>#09</p></a>
			</div>
		</div>

	</div>

</body>
</html>

<%--일정관리 footer --%>
<jsp:include page="../include/footer.jsp" />