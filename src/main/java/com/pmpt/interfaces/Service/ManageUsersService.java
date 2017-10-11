package com.pmpt.interfaces.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.pmpt.common.PageBean;
import com.pmpt.interfaces.Dao.ManageUsersDAOCus;

/**
 * @ClassName: ManageUsersService.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月23日上午11:14:38
 */
@Service
@ComponentScan("com.pmpt.domain.manage.dao")
public class ManageUsersService {
	@Autowired
	private ManageUsersDAOCus dao2;
	
	// 分页查询所有用户信息
	public PageBean<Object[]> findAll(int pageSize, int currentPage){
		return dao2.findAll(pageSize, currentPage);
	}
	// 条件模糊查询用户信息
	public PageBean<Object[]> conditionalQuery(String condition, int pageSize, int currentPage){
		return dao2.conditionalQuery(condition, pageSize, currentPage);
	}
	// 用户详细信息展示
	public List<Object[]> queryUserInformation(String loginAccoutId){
		return dao2.queryUserInformation(loginAccoutId);
	}
}
