package net.daum.dao;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.CityVO;
import net.daum.vo.DestinationVO;
import net.daum.vo.NationalVO;
import net.daum.vo.PlanVO;

@Repository
public class PlanDAOImpl implements PlanDAO {

	@Autowired
	private NationalRepository nationalRepo;
	
	@Autowired
	private PlanRepository planRepo;
	
	@Autowired
	private CityRepository cityRepo; 
	
	@Autowired
	private DestinationRepository DestinationRepo;

	@Override
	public NationalVO findNational(String nationalCode) {
		return this.nationalRepo.findById(nationalCode).orElse(null);
	}

	@Override
	public void insertPlan(PlanVO p) {
		this.planRepo.save(p);
	}

	@Override
	public void insertDestination(DestinationVO d) {
		this.DestinationRepo.save(d);
	}

	@Override
	public CityVO getCityCode(String cityCode) {
		return this.cityRepo.findByCityCode(cityCode);
	}

	@Override
	public List<PlanVO> allUserPlan() {
		return this.planRepo.allUserPlan();
	}

	@Override
	public PlanVO getPlan(int planNo) {
		return this.planRepo.findById(planNo).orElse(null);
	}

	@Override
	@Transactional
	public void deletePlanByPlanNo(int planNo) {
        PlanVO plan = this.planRepo.findById(planNo).orElse(null);
        System.out.println(plan);
        if (plan != null) {
            // cities 리스트에서 모든 CityVO를 제거
            plan.getCities().clear();
        }
        this.planRepo.deleteByplanNo(planNo);
	}
}
