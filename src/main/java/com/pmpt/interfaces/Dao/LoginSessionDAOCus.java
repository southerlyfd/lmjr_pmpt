/**
 * 
 */
package com.pmpt.interfaces.Dao;

import java.util.List;

/**
 * @ClassName: LoginSessionDAOCus.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月17日下午2:22:45
 */
public interface LoginSessionDAOCus {
	// 修改loginSession缓存表中数据
	int memoryCache(Integer loginSession, String token, String deviceType);
	// 查询loginSession缓存表中token
	List<Object[]> findByToken(Integer id);
}
