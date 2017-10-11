/**
 * 
 */
package com.pmpt.interfaces.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jitl
 * 操作日志记录
 */
public interface ActionLogService {

	/**
	 * @param request HttpServletRequest
	 * @param actionLogClass 操作日志分类
	 * @param actionLogCode 操作日志类型代码
	 * @param interfaceCode 接口编码
	 * @param actor loginAccuoutID
	 * @param describe 描述信息
	 * @return
	 */
	String addLog(HttpServletRequest request,String actionLogClass,String actionLogCode,String interfaceCode,String actor,String describe);
}
