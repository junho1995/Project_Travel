<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/heart.css">
</head>
<body>
	<div class="container">
		<div class="post">
			<button class="like-btn" onclick="likePost(this)">좋아요</button>
			<span class="like-count">0</span>
		</div>
	</div>

	<script>
        function likePost(btn) {
            var likeCount = btn.nextElementSibling; // 좋아요 수를 표시하는 요소
            var currentCount = parseInt(likeCount.textContent); // 현재 좋아요 수
            if (btn.classList.contains('liked')) {
                btn.classList.remove('liked');
                currentCount -= 1;
            } else {
                btn.classList.add('liked');
                currentCount += 1;
            }
            likeCount.textContent = currentCount;
        }
    </script>
</body>
</html>