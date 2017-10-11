/**
 * 
 */
package com.pmpt.interfaces.Dao;

import java.util.List;

import com.pmpt.entities.Interest;


/**
 * @ClassName: InterestRepository.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月11日下午4:25:37
 */
public interface InterestRepository {
	
	void addInterest(Interest interest);
	
	List<Interest> queryInterest(String loginAccoutId);

}
