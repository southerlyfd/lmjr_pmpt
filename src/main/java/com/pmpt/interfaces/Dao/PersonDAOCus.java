/**
 * 
 */
package com.pmpt.interfaces.Dao;

import java.util.List;

import com.pmpt.common.PageBean;
import com.pmpt.entities.LoginSession;

/**
 * @ClassName: PersonDAOCus.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月30日下午2:53:27
 */
public interface PersonDAOCus {
	// 分页查询管理员信息
	PageBean<Object[]> findAll(int pageSize, int currentPage);
	// 管理员登录
	List<Object[]> signIn(String loginName, String pwd);
	// 修改管理员登录的缓存信息
	int updatePersonSession(LoginSession loginSession, String loginName);
	// 修改管理员密码
	int updatePwd(String loginName, String newPwd);
}
