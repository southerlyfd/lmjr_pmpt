/**
 * 
 */
package com.pmpt.Impl.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.entities.Interest;
import com.pmpt.interfaces.Dao.BillDAOCus;
import com.pmpt.interfaces.Dao.InterestRepository;
import com.pmpt.interfaces.Service.InterestService;

/**
 * @ClassName: InterestServiceImpl.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月11日下午4:22:35
 */
@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	InterestRepository interestRepository;
	
	@Autowired
	BillDAOCus billDao;
	
	@Resource
	LocaleMessageSourceService localeMess;
	/* (non-Javadoc)
	 * @see com.pmpt.service.InterestService#addInterest(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addInterest(String id, String loginAccoutId, String commodity, String code) {
		Interest interest = new Interest();
		interest.setId(id);
		interest.setLoginAccoutId(loginAccoutId);
		interest.setCommodity(commodity);
		interest.setCode(code);
		interestRepository.addInterest(interest);
		return localeMess.getMessage("success");
	}
	/* (non-Javadoc)
	 * @see com.pmpt.service.InterestService#saveInterest(java.lang.String)
	 */
	@Override
	public List<Interest> queryInterest(String loginAccoutId) {
		return interestRepository.queryInterest(loginAccoutId);
	}


	@Override
	public List<Object[]> exhibitLike() {
		return billDao.exhibitLike();
	}

}
