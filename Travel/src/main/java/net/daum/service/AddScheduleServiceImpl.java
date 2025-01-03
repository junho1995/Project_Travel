package net.daum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.AddScheduleDAO;
import net.daum.vo.CityVO;
import net.daum.vo.NationalVO;

@Service
public class AddScheduleServiceImpl implements AddScheduleService {

	@Autowired
	private AddScheduleDAO addshceduleDao;

	@Override
	public List<NationalVO> findNname() {
		
		return this.addshceduleDao.findNname();
	}



}
