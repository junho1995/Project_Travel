<%@ page contentType="text/html; charset=UTF-8"%>
<script
	src="https://t1.kakaocdn.net/kakao_js_sdk/${VERSION}/kakao.min.js"
	integrity="${INTEGRITY_VALUE}" crossorigin="anonymous"></script>

<link rel="stylesheet" href="../css/talk.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<div class="container">
	<!-- 토글 버튼 추가 -->
	<div class="toggle-btn" id="toggleBtn">
		<i class="fas fa-bars"></i>
	</div>

	<div class="side-panel" id="sidePanel">
		<div class="option">
			<button id="friendsBtn">친구 목록</button>
			<button id="groupBtn">단체방 목록</button>
		</div>
		<div class="friends-list panel" id="friends-panel">
			<h2>친구 목록</h2>
			<ul>
				<li>친구 1</li>
				<li>친구 2</li>
				<li>친구 3</li>
				<li>친구 4</li>
				<li>친구 5</li>
				<li>친구 6</li>
				<li>친구 7</li>
				<li>친구 8</li>
				<li>친구 9</li>
				<li>친구 10</li>
			</ul>
		</div>
		<div class="group-chats panel" id="group-panel" style="display: none;">
			<h2>단체방 목록</h2>
			<ul>
				<li>단체방 1</li>
				<li>단체방 2</li>
				<li>단체방 3</li>
				<li>단체방 4</li>
				<li>단체방 5</li>
				<li>단체방 6</li>
				<li>단체방 7</li>
				<li>단체방 8</li>
				<li>단체방 9</li>
				<li>단체방 10</li>
			</ul>
		</div>
	</div>

	<div class="chat-window">
		<div class="chat-header">
			<h2>
				<i class="fas fa-user"></i> 채팅 상대방 이름
			</h2>
		</div>
		<div class="chat-messages">
			<div class="message">
				<p>상대방의 첫 번째 메시지입니다.</p>
			</div>
			<!-- 여러 채팅 메시지 추가 가능 -->
		</div>
		<div class="chat-input">
			<input type="text" placeholder="메시지 입력">
			<button>
				<i class="fas fa-paper-plane"></i>
			</button>
		</div>
	</div>
</div>

<script src="../script/talk.js"></script>

<script>
    // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해야 합니다.
    Kakao.init('01b686264cda6f2563e41cb2d408b00e');

    // SDK 초기화 여부를 판단합니다.
    console.log(Kakao.isInitialized());
    
    Kakao.API.request({
    	  url: '/v1/api/talk/friends/message/send',
    	  data: {
    	    receiver_uuids: ['${RECEIVER_UUID}'],
    	    template_id: ${YOUR_TEMPLATE_ID},
    	  },
    	})
    	  .then(function(response) {
    	    console.log(response);
    	  })
    	  .catch(function(error) {
    	    console.log(error);
    	  });
  </script>
