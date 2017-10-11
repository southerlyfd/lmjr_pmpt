package com.pmpt.interfaces.Dao;

import java.util.List;

import com.pmpt.entities.LoginSession;



public interface LoginAccoutDAOCus {
	// 登录
	List<Object[]> signIn(String phone, String pwd);
	// 修改账户的缓存信息
	int updateLogSession(LoginSession loginSession, String phone);
	// 查询密码
	String queryPwd(String phone);
	// 修改密码
	int modifyPwd(String phone, String pwd);
	// 查询账户是否存在
	boolean findAccout(String phone);

}
