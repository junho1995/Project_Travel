package net.daum.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.siot.IamportRestClient.IamportClient;

import net.daum.service.MemberService;
import net.daum.service.PlanService;
import net.daum.vo.ChatVO;
import net.daum.vo.MemberVO;
import net.daum.vo.MessageVO;
import net.daum.vo.PlanVO;

@Controller
public class MainController {
//test
	@Autowired
	private MemberService memberService;
	@Autowired
	private PlanService planService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	//홈페이지 창
	@GetMapping("/homepage")
	public ModelAndView homepage() {
		ModelAndView home=new ModelAndView();
		home.setViewName("jsp/homepage");
		return home;
	}
	
	
	//시큐리티와 연결된 로그인
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView home=new ModelAndView();
		home.setViewName("jsp/login");
		return home;
	}
	
	
	//시큐리티와 연결된 로그아웃
	@GetMapping("/logout")
	public ModelAndView logout() {
		ModelAndView home=new ModelAndView();
		home.setViewName("jsp/logout");
		return home;
	}
	
	
	//로그아웃을 위해 시큐리티로 즉시 보내는 용도의 컨트롤러
	@GetMapping("/LogoutHandler")
	public ModelAndView LogoutHandler() {
		ModelAndView home=new ModelAndView();
		home.setViewName("jsp/logoutHandler");
		return home;
	}
	
	
	//비밀번호를 잘못 입력했을 때 다시 비번 수정 페이지로 넘기기 위한 컨트롤러
		@GetMapping("/Edit_Password")
		public ModelAndView Edit_Password() {
			ModelAndView home=new ModelAndView();
			home.setViewName("jsp/edit_Password");
			return home;
		}
		
		
	//비밀번호를 잘못 입력했을 때 다시 회원탈퇴 페이지로 넘기기 위한 컨트롤러
		@GetMapping("/Edit_Exit")
			public ModelAndView Edit_Exit() {
				ModelAndView home=new ModelAndView();
				home.setViewName("jsp/edit_Exit");
				return home;
		}	
		
		
	//회원가입 창
	@GetMapping("/C_Join")
	public ModelAndView C_Join(MemberVO m) {
		
		String[] email= {"gmail.com","naver.com","daum.net","직접입력"};
		
		ModelAndView jm=new ModelAndView();
		jm.setViewName("jsp/Join");
		jm.addObject("email", email);
		return jm;
	}
	
	
	//비밀번호를 찾아봅니다...
	@GetMapping("/Find_Password")
	public ModelAndView Find_Password() {
		
		ModelAndView fp=new ModelAndView("jsp/find_Password");
		return fp;
	}
	
	
	//DB로 가서 해당 아이디와 해당 이메일 주소가 있는지, 동일한지 검사합니다...
	@PostMapping("/Find_Password_ok")
	public String Find_Password_ok(String member_id, String mail, HttpServletResponse response)
		throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		//입력값으로 받은 mail을 @를 기준으로 분리합니다.
		String[] mails=mail.split("@");
		String mail_id=mails[0];
		String mail_domain=mails[1];
		
		//셋 다 동일한 유저가 있으면 그 객체를 가져옵니다...
		MemberVO m=this.memberService.idAndMailCheck(member_id, mail_id, mail_domain);
				
		if(m == null) {
			out.println("<script>");
			out.println("alert('아이디와 이메일에 일치하는 회원이 없습니다.\\n올바른 회원정보를 입력하세요!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
		    Random r=new Random();
		    int pwd_random = r.nextInt(100000);
		    String ran_pwd = Integer.toString(pwd_random);
		    m.setMember_pwd(passwordEncoder.encode(ran_pwd));
		    
    
		    this.memberService.updatePwd(m);
		    
		    out.println("<script>");
		    out.println("alert('수정된 비밀번호 입니다 \\n" + ran_pwd + "');");
		    out.println("window.location.href = '/login';");
		    out.println("</script>");
		}		
		
		return null;
	}
		
	
	//아이디 중복체크
		@PostMapping("/Idcheck")
		public String idcheck(@RequestParam("id") String id,HttpServletResponse response) 
		throws Exception{

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			
			MemberVO db_id = this.memberService.idCheck(id);
			int result = -1;
			
			if(db_id != null) {
				result = 1;
			}
			
			out.println(result);
			
			return null;
		}	
		
		
	//회원가입이 정상적으로 됐을 때 회원을 저장한다
	@PostMapping("/Join_Clear")
	public ModelAndView Join_Clear(MemberVO m) {
		
		ChatVO c=new ChatVO();
		m.setChatVO(c);//회원가입할 때 채팅VO도 같이 집어넣어서 저장한다. 관리자와 채팅할 채팅방을 같이 개설하기 위해서
		m.setMember_pwd(passwordEncoder.encode(m.getMember_pwd()));
		this.memberService.insertMember(m);
		
		ModelAndView jm=new ModelAndView();
		jm.setViewName("redirect:/homepage");
		return jm;
	}
	
	
	//내정보에서 내 정보를 확인할 수 있다. 
		@GetMapping("/Alert")
		public ModelAndView Alert(@AuthenticationPrincipal UserDetails userDetails) {
			
			String username=userDetails.getUsername();
			String[] email= {"gmail.com","naver.com","daum.net","직접입력"};
			MemberVO m= this.memberService.idCheck(username);
			
			ModelAndView home=new ModelAndView();
			home.setViewName("jsp/alert");
			home.addObject("email",email);
			home.addObject("m",m);

			return home;
		}
	
		
	//내 정보에서 회원 수정을 진행.
	@PostMapping("/Alert_All")
	public ModelAndView Alert_Edit(MemberVO m, String alert_A, HttpServletResponse response, HttpServletRequest request,
			@AuthenticationPrincipal UserDetails userDetails,HttpSession session) 
	throws Exception{
			
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		ModelAndView aa=new ModelAndView();
		
		if(alert_A.equals("정보 수정")) {
			
			this.memberService.update_Edit(m);
			
			out.println("<script>");
			out.println("alert('회원 정보 수정이 완료 됐습니다.');");
			out.println("history.back();");
			out.println("</script>");
			
		}else if(alert_A.equals("비밀번호 수정")) {
			
			aa.setViewName("jsp/edit_Password");
			
			return aa;
		}else if(alert_A.equals("변경")) {
		
			if(passwordEncoder.matches(request.getParameter("member_pwd"), userDetails.getPassword())) {
				//비밀번호가 일치한다면
				m.setMember_id(userDetails.getUsername());
				m.setMember_pwd(passwordEncoder.encode(request.getParameter("new_pwd")));
			    this.memberService.updatePwd(m);
				
				out.println("<script>");
				out.println("alert('비밀번호 변경이 완료 됐습니다.\\n다시 로그인 하세요.');");
				out.println("window.location.href = '/LogoutHandler';");
				out.println("</script>");
			}else {
				//일치하지 않는 경우엔 경고창만 띄우고 다시 돌아가기.
				out.println("<script>");
				out.println("alert('기존 비밀번호를 잘못 입력했습니다.');");
				out.println("window.location.href = '/Edit_Password';");
				out.println("</script>");	
			}		
		}else if(alert_A.equals("회원 탈퇴")) {
			
			aa.setViewName("jsp/edit_Exit");
			
			return aa;
			
		}else if(alert_A.equals("탈퇴")) {
			
			if(passwordEncoder.matches(request.getParameter("member_pwd"), userDetails.getPassword())) {
				//비밀번호가 일치한다면
				String userId=userDetails.getUsername();
			    this.memberService.deleteUser(userId);
				
				out.println("<script>");
				out.println("alert('회원탈퇴가 완료되었습니다.');");
				out.println("window.location.href = '/LogoutHandler';");
				out.println("</script>");
			}else {
				//일치하지 않는 경우엔 경고창만 띄우고 다시 돌아가기.
				out.println("<script>");
				out.println("alert('기존 비밀번호를 잘못 입력했습니다.');");
				out.println("window.location.href = '/Edit_Exit';");
				out.println("</script>");
			}		
		}
		return null;
	}
		
	
	
	public class MessageComparator implements Comparator<MessageVO> {
	    @Override
	    public int compare(MessageVO message1, MessageVO message2) {
	        return Long.compare(message1.getMessageNo(), message2.getMessageNo());
	    }
	}
	
	@RequestMapping("/chat")
	public ModelAndView chat(@AuthenticationPrincipal UserDetails userDetails) {

		String member_id=userDetails.getUsername();

		long chatNumber=this.memberService.getChatNumber(member_id);

				List<MessageVO> allMessage=new ArrayList<>();
				allMessage=this.memberService.getAllMessage(chatNumber);
				Collections.sort(allMessage, new MessageComparator());
				//채팅방에 속하는 모든 메시지는 서버에서 가져오기 성공함.
				//System.out.println(allMessage.get(4).getMessageText()); 
				//메시지 하나를 출력하고 싶을 때.
					
				
		ModelAndView mv = new ModelAndView();
		mv.addObject("chatNumber",chatNumber);
		mv.addObject("member_id",member_id);
		mv.addObject("allMessage",allMessage);
		mv.setViewName("jsp/chat");
		return mv;
	}

	
	@PostMapping("/usermessageSend")
	public ModelAndView messageSend(@RequestParam("chatNumber") String chatNo,
									@RequestParam("chatting") String messageText,
									HttpServletResponse response,
									@AuthenticationPrincipal UserDetails userDetails) throws Exception{
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
//		System.out.println(chatNo);
//		System.out.println(messageText);
		
		ModelAndView m=new ModelAndView();
		int result=1;
		String member_id=userDetails.getUsername();
		
		ChatVO c=new ChatVO();
		c.setChatNo(Long.parseLong(chatNo));
		MessageVO msg=new MessageVO();
		msg.setMessageText(member_id+ ": "+messageText);
		msg.setChatVO(c);
		
		this.memberService.setMessage(msg);
		
		out.println(result);
		
		
		return null;
	}
	
	
	@GetMapping("/Wallet")
	public ModelAndView Wallet(@AuthenticationPrincipal UserDetails userDetails) {
		
		String username=userDetails.getUsername();
		MemberVO m= this.memberService.idCheck(username);
		
		String mail=m.getMail_id()+"@"+m.getMail_domain();
		String name=m.getMember_name();
		String phone=m.getMember_phone01()+"-"+m.getMember_phone02()+"-"+m.getMember_phone03();
		String addr=m.getSample6_address()+"/"+m.getSample6_detailAddress()+"/"+m.getSample6_extraAddress();
		String post=m.getSample6_postcode();
		
		ModelAndView home=new ModelAndView();
		home.setViewName("jsp/wallet");
		home.addObject("mail",mail);
		home.addObject("name",name);
		home.addObject("phone",phone);
		home.addObject("addr",addr);
		home.addObject("post",post);

		return home;
	}
	
	
	@GetMapping("successPay")
	public void successPay(@AuthenticationPrincipal UserDetails userDetails,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String username=userDetails.getUsername();
		MemberVO m= this.memberService.idCheck(username);
		m.setRole("PAIDUSER");
		this.memberService.update_Edit(m);
		
//		  HttpSession session = request.getSession(); 
// 		  세션 객체 생성
//        session.setAttribute("id", m.getMember_id());
//        session.setAttribute("name", m.getMember_name());        
//        session.setAttribute("auth", "ROLE_" + m.getRole());
//        위 방법으론 권한 업데이트가 되지 않음.
		
        //System.out.println(m.getMember_id()+m.getMember_name()+m.getRole());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.remove(new SimpleGrantedAuthority("ROLE_NOPAIDUSER"));
        updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_PAIDUSER"));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        //결제 시 권한을 업데이트해서 접근할 수 없는 페이지에 접근을 가능하게 해야해서, 현재 접속중인 유저 정보를 업데이트.
        
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('결제가 완료됐습니다.');");
		out.println("window.location.href = '/addschedule';");
		out.println("</script>");
	}

	@GetMapping("/homepageSearch")
	public ModelAndView homepageSearch(String searchInput) {
		
		//System.out.println(searchInput);
		List<PlanVO> p=new ArrayList();
		p=this.planService.allUserPlan();
		int busanGo=0;
		int seoulGo=0;
		int tokyoGo=0;
		
		for(int i=0; i<p.size(); i++) {
			int a= p.get(i).getCities().size();//한 계획 안에 들어 있는 여러 여행지
			
			for(int j=0; j<a; j++) {//그 여행지마다 갯수 지정.
				if(p.get(i).getCities().get(j).getCityCode().equals("PUS")) busanGo++;
				if(p.get(i).getCities().get(j).getCityCode().equals("SEL")) seoulGo++;
				if(p.get(i).getCities().get(j).getCityCode().equals("TYO")) tokyoGo++;	
				//System.out.println(p.get(i).getCities().get(j).getCityCode());
			}
			
		}
		
		ModelAndView search=new ModelAndView();
		search.addObject("busanGo", busanGo);
		search.addObject("seoulGo", seoulGo);
		search.addObject("tokyoGo", tokyoGo);
		search.setViewName("jsp/searchResult");
		
		return search;
	}
	//병합 전 시큐리티 접근 가능 여부 파악
//	@GetMapping("/Main")
//	public ModelAndView Main() {
//		ModelAndView home=new ModelAndView();
//		home.setViewName("jsp/main");
//		return home;
//	}
//	@GetMapping("/Add_schedule")
//	public ModelAndView Add_schedule() {
//		ModelAndView home=new ModelAndView();
//		home.setViewName("jsp/add_schedule");
//		return home;
//	}
//	@GetMapping("/Share_c")
//	public ModelAndView Share_c() {
//		ModelAndView home=new ModelAndView();
//		home.setViewName("jsp/share_c");
//		return home;
//	}
	//403접근 금지에러가 났을 때 
	@GetMapping("/accessDenied") 
	public void accessDenied() {}
	
}