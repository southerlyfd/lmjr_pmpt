/**
 * 
 */
package com.pmpt.Impl.DaoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.MainUtilityTools;
import com.pmpt.entities.Interest;
import com.pmpt.interfaces.Dao.InterestRepository;

/**
 * @ClassName: InterestRepositoryImpl.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月11日下午4:28:32
 */
@Repository
public class InterestRepositoryImpl implements InterestRepository {
	
	@Autowired
    private MongoTemplate mongoTemplate;
	@Resource
	private LocaleMessageSourceService localeMess;

	@Override
	public void addInterest(Interest interest) {
		try {
			mongoTemplate.insert(interest);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), localeMess.getMessage("insertion.exception"));
		}
	}
	/* (non-Javadoc)
	 * @see com.pmpt.domain.mongo.dao.InterestRepository#queryInterest(java.lang.String)
	 */
	@Override
	public List<Interest> queryInterest(String loginAccoutId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("loginAccoutId").is(loginAccoutId));
		return mongoTemplate.find(query, Interest.class);
	}
	
}
