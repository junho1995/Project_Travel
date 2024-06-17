package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.daum.service.AddScheduleService;
import net.daum.vo.CityVO;
import net.daum.vo.NationalVO;

@Controller
public class AddScheduleController {

	@Autowired
	private AddScheduleService addscheduleservice;
	
	// 계획추가 메인 페이지
	@GetMapping("/addschedule")
	public String getNlist(Model model){
		List<NationalVO> nList= this.addscheduleservice.findNname();

		model.addAttribute("Nlist", nList);
		return "jsp/add_schedule";
	}
}