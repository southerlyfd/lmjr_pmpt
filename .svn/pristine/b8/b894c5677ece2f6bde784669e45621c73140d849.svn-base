/**
 * 
 */
package com.pmpt.interfaces.Dao;

import java.util.List;

import com.pmpt.common.PageBean;

/**
 * @ClassName: ManageUsersDAOCus.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月23日上午11:05:58
 */
public interface ManageUsersDAOCus {
	
	// 分页查询所有用户信息
	PageBean<Object[]> findAll(int pageSize, int currentPage);
	// 条件查询
	PageBean<Object[]> conditionalQuery(String condition, int pageSize, int currentPage);
	// 用户详细信息展示
	List<Object[]> queryUserInformation(String loginAccoutId);
}
