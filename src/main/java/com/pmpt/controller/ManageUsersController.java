package com.pmpt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmpt.common.ActionLogClass;
import com.pmpt.common.ActionLogCode;
import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.MainUtilityTools;
import com.pmpt.common.PageBean;
import com.pmpt.common.Response;
import com.pmpt.common.ResponseStatusCode;
import com.pmpt.interfaces.Service.ActionLogService;
import com.pmpt.interfaces.Service.ManageUsersService;

/**
 * @ClassName: ManageUsersController.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月23日上午10:48:39
 */
@RestController
public class ManageUsersController {

	@Autowired
	private ManageUsersService manageService;
	@Resource
	private LocaleMessageSourceService localeMessageSourceService;
	@Resource
	private ActionLogService actionLogService;
	/**
	 * 分页显示用户信息
	 * @param token 用户令牌
	 * @param pageSize 页面大小
	 * @param currentPage 当前页
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGEUSERS_FINDALL)
	public Response findAll(String token, int pageSize, int currentPage) {
		Response res = new Response();
		// 判断是否有访问权限
	
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41001", "", "请求展示用户信息");
		// 调用service层访问数据库
		PageBean<Object[]> pageBean = new PageBean<>();
		if(pageSize <= 0) {
			pageSize = 1;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		try {
			pageBean = manageService.findAll(pageSize, currentPage);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
			res.setMessage(localeMessageSourceService.getMessage("query.exception"));
			return res;
		}
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setMessage(localeMessageSourceService.getMessage("success"));
		res.setObject(pageBean);
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41001", "", "展示用户信息成功");
		return res;
	}
	/**
	 * 模糊查询用户信息接口
	 * @param token 用户令牌
	 * @param condition 查询信息
	 * @param pageSize 页面大小
	 * @param currentPage 当前页
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGEUSERS_CONDITIONALQUERY)
	public Response conditionalQuery(String token, String condition,  int pageSize, int currentPage) {
		Response res = new Response();
		// 判断是否有访问权限
		
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41002", "", "请求模糊查询用户信息");
		// 调用service层访问数据库
		PageBean<Object[]> pageBean = new PageBean<>();
		if(condition != null && !"".equals(condition)) {
			try {
				pageBean = manageService.conditionalQuery(condition, pageSize, currentPage);
			} catch (Exception e) {
				MainUtilityTools.catchException(e, this.getClass(), "");
				res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
				res.setMessage(localeMessageSourceService.getMessage("query.exception"));
				return res;
			}
			res.setStatus(ResponseStatusCode.SUCCESS.getCode());
			res.setMessage(localeMessageSourceService.getMessage("success"));
			res.setObject(pageBean);
			actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41002", "", "模糊查询用户信息成功");
			return res;
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("fail"));
		res.setObject(pageBean);
		return res;
	}
	/**
	 * 单个用户所有信息展示接口
	 * @param token 用户令牌
	 * @param loginAccoutId 账号id
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGEUSERS_QUERYUSER)
	public Response queryUserInformation(String token, String loginAccoutId) {
		Response res = new Response();
		// 判断是否有访问权限
		
		// 调用service层访问数据库
		List<Object[]> list = new ArrayList<>();
		if (loginAccoutId != null && !"".equals(loginAccoutId)) {
			actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41003", "", "访问展示单个用户所有信息");
			try {
				list = manageService.queryUserInformation(loginAccoutId);
			} catch (Exception e) {
				MainUtilityTools.catchException(e, this.getClass(), "");
				res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
				res.setMessage(localeMessageSourceService.getMessage("query.exception"));
				return res;
			}
			res.setStatus(ResponseStatusCode.SUCCESS.getCode());
			res.setMessage(localeMessageSourceService.getMessage("success"));
			res.setObject(list);
			actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41003", "", "展示单个用户所有信息成功");
			return res;
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("fail"));
		res.setObject(list);
		return res;
	}
	
}
