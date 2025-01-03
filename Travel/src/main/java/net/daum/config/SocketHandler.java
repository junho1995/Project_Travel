package net.daum.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {
	
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); 
	
	//소켓 연결
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionId", session.getId());
		
//		System.out.println("소켓 연결 확인");
//		System.out.println(session.getId());
//		System.out.println(session);
//		System.out.println(obj);
		session.sendMessage(new TextMessage(obj.toJSONString()));
	}
	
	//메시지 받고 발송
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		
		String msg = message.getPayload();
		//System.out.println(msg);
		JSONObject obj = jsonToObjectParser(msg);
		
		//String rN = (String)obj.get("room");
		//System.out.println(rN);
		//System.out.println(session.getUri());
		
		//같은 방한테만 보내기 성공..... 웹소켓을 통해 세션에 들어간 유저는 많겠지만, 그 중 방번호가 동일해야지만 채팅할 수 있겠지...
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			if(wss.getUri().equals(session.getUri())) {
			try {
				wss.sendMessage(new TextMessage(obj.toJSONString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
			}
		}
	}
	
	//소켓 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
	
	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
}