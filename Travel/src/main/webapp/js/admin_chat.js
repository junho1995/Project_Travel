/**
 * 
 */
function scrollToBottom() {
        var chatWindow = document.getElementById("chating");
        chatWindow.scrollTop = chatWindow.scrollHeight;
    }
    
$(document).ready(function(){
			wsOpen();
			$("#yourMsg").show();
			scrollToBottom();
			var checkw="ws://" + location.host + "/chating/"+$("#chatNumber").val();
			console.log(checkw);
});

	var ws;
	function wsOpen(){
		
		ws = new WebSocket("ws://" + location.host + "/chating/"+$("#chatNumber").val());
		wsEvt();
	}
		
	function wsEvt() {
		ws.onopen = function(data){
			//소켓이 열리면 동작
			var mesg="소켓이 성공적으로 열렸습니다.";
			console.log(mesg);
		}
		
		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			var msg = data.data;
			if(msg != null && msg.trim() != ''){
				var d = JSON.parse(msg);
				if(d.type == "getId"){
					var si = d.sessionId != null ? d.sessionId : "";
					if(si != ''){
						$("#sessionId").val(si); 
					}
				}else if(d.type == "message") {
    					// 현재 시간을 가져옵니다.
    					var currentTime = new Date();
    					var year = currentTime.getFullYear();
    					var month = currentTime.getMonth() + 1; 
    					var date = currentTime.getDate();
    					var hours = currentTime.getHours();
    					var minutes = currentTime.getMinutes();
    					var formattedTime = year + "-" + 
                        (month < 10 ? '0' + month : month) + "-" + 
                        (date < 10 ? '0' + date : date) + " " + 
                        (hours < 10 ? '0' + hours : hours) + ":" + 
                        (minutes < 10 ? '0' + minutes : minutes); // 시간을 YYYY-MM-DD HH:MM 형식으로 포맷팅

    if(d.sessionId == $("#sessionId").val()) {
        $("#chating").append
        ("<p class='me'>관리자: " + d.msg + "<br> <span class='time'>" + formattedTime + "</span></p><br>");
        
    } else {
        $("#chating").append
        ("<p class='others'>"+$("#user").val()+ " : " + d.msg + "<br> <span class='time'>" + formattedTime + "</span></p><br>");
    }
}else{
		console.warn("unknown type!")
				}
			}
			scrollToBottom();
		}

		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){
				send();
			}
		});
	}
	

	function send() {
		var option ={
			type: "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : $("#chatting").val(),
			room : $("#chatNumber").val()
		}
		ws.send(JSON.stringify(option))
		
		
	var header = $("meta[name='_csrf_header']").attr('content');
    var token = $("meta[name='_csrf']").attr('content');  
    
    $.ajax({
        type:"POST",   
        url:"messageSend", 
        beforeSend: function(xhr){
         xhr.setRequestHeader(header, token);
        },
        data: {"chatNumber":$("#chatNumber").val(),
        	   "chatting":$("#chatting").val()
        },  
        datatype:"int",
        success: function (data) {
      	  if(data==1){
				alert("(테스트)성공적으로 문의 사항이 전송되었습니다.");
				scrollToBottom()
      	  }else{
				alert("실패");
      	  }  	    	  
        },
    	  error:function(){
    		  alert("이거 참 곤란합니다...");
    		  
    	  }
      });
      scrollToBottom()
      $('#chatting').val("");
	}