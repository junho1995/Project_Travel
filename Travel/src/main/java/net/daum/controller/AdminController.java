package net.daum.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.MemberService;
import net.daum.vo.ChatVO;
import net.daum.vo.MessageVO;



@RequestMapping("/admin/*")
@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/homepage")
	public ModelAndView homepage() {
		
		List<String> users=new ArrayList<>();
		users=this.memberService.getAllUserId();		
		
		ModelAndView home=new ModelAndView();
		home.setViewName("admin/adminHomepage");
		home.addObject("users",users);
		return home;
	}
	
	public class MessageComparator implements Comparator<MessageVO> {
	    @Override
	    public int compare(MessageVO message1, MessageVO message2) {
	        return Long.compare(message1.getMessageNo(), message2.getMessageNo());
	    }
	}
	
	@GetMapping("/chat")
    public String showChat(@RequestParam("user") String user, Model model) {

		long chatNumber=this.memberService.getChatNumber(user);
		//유저의 채팅넘버

		List<MessageVO> allMessage=new ArrayList<>();
		allMessage=this.memberService.getAllMessage(chatNumber);
		//채팅방에 속하는 모든 메시지는 서버에서 가져오기 성공함.
		Collections.sort(allMessage, new MessageComparator());
		
        model.addAttribute("user", user);
        model.addAttribute("chatNumber", chatNumber);
        model.addAttribute("allMessage",allMessage);
        
        return "admin/admin_chat";
    }
    
	
	
	@PostMapping("/messageSend")
	public ModelAndView messageSend(@RequestParam("chatNumber") String chatNo,
									@RequestParam("chatting") String messageText,
									HttpServletResponse response) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
//		System.out.println(chatNo);
//		System.out.println(messageText);
		
		ModelAndView m=new ModelAndView();
		int result=1;
		
		ChatVO c=new ChatVO();
		c.setChatNo(Long.parseLong(chatNo));
		MessageVO msg=new MessageVO();
		msg.setMessageText("관리자: "+messageText);
		msg.setChatVO(c);
		
		this.memberService.setMessage(msg);
		
		out.println(result);
		
		
		return null;
	}

	
}
