<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<jsp:include page="../include/header.jsp" />
<link rel="stylesheet" href="../css/homepage_css.css">

<body>
	<div class="wrap">
		<div class="video-container">
			<video autoplay loop muted>
				<source src="../video/main_video.mp4" type="video/mp4">
			</video>
		</div>

		<div class="content">
			<main>
				<form id="searchForm" action="#" method="GET">
					<!-- 폼 생성 -->
					<div class="search-container">
						<input type="text" class="search-bar" id="searchInput">
						<button type="submit" class="search-button">To World</button>
						<!-- submit 버튼 추가 -->
					</div>
				</form>
			</main>
		</div>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>






