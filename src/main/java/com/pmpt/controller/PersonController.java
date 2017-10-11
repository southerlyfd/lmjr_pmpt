/**
 * 
 */
package com.pmpt.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmpt.common.ActionLogClass;
import com.pmpt.common.ActionLogCode;
import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.MainUtilityTools;
import com.pmpt.common.PageBean;
import com.pmpt.common.Response;
import com.pmpt.common.ResponseStatusCode;
import com.pmpt.entities.Person;
import com.pmpt.interfaces.Service.ActionLogService;
import com.pmpt.interfaces.Service.PersonService;
import com.pmpt.interfaces.Service.VerificationCodeService;

/**
 * @ClassName: PersonController.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月30日下午3:07:59
 */
@RestController
public class PersonController {
	@Autowired
	private PersonService personService;
	@Autowired
	private VerificationCodeService redisService;
	@Resource
	private LocaleMessageSourceService localeService;
	@Resource
	private ActionLogService actionLogService;
	
	/**
	 * 管理员信息新增接口
	 * @param person 
	 * @param token 用户令牌
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGE_PERSON_SAVE)
	public Response savePerson(@RequestBody Person person, String token) {
		Response res = new Response();
		// 判断是否有访问权限
		
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41004", "", "新增管理员信息");
		try {
			personService.save(person);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
			res.setMessage(localeService.getMessage("insertion.exception"));
			return res;
		}
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setMessage(localeService.getMessage("success"));
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41004", "", "新增管理员信息成功");
		return res;
	}
	/**
	 * 后台管理员分页查询接口
	 * @param token
	 * @param pageSize 每页显示信息数
	 * @param currentPage 当前页
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGE_PERSON_FINDALL)
	public Response query(String token, int pageSize, int currentPage) {
		Response res = new Response();
		// 判断是否有访问权限
		
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41007", "", "请求展示管理员信息");
		PageBean<Object[]> pageBean = new PageBean<>();
		if (pageSize <= 0) {
			pageSize = 1;
		}
		if (currentPage <= 0) {
			currentPage = 1;
		}
		try {
			pageBean = personService.findAll(pageSize, currentPage);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
			res.setMessage(localeService.getMessage("query.exception"));
			return res;
		}
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setMessage(localeService.getMessage("success"));
		res.setObject(pageBean);
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41007", "", "展示管理员信息成功");
		return res;
	}
	/**
	 * 后台管理员信息删除接口
	 * @param token 用户令牌
	 * @param personId 管理员id
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGE_PERSON_DELETE)
	public Response delete(String token, Integer personId) {
		Response res = new Response();
		// 判断是否有访问权限
		
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41005", "", "访问删除管理员信息");
		try {
			personService.delete(personId);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
			res.setMessage(localeService.getMessage("delete.exception"));
			return res;
		}
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setMessage(localeService.getMessage("success"));
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41005", "", "删除管理员信息成功");
		return res;
	}
	/**
	 * 修改管理员信息接口
	 * @param token
	 * @param person
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGE_PERSON_UPDATE)
	public Response update(String token, @RequestBody Person person) {
		Response res = new Response();
		// 判断是否有访问权限
		
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41006", "", "访问修改管理员信息");
		try {
			personService.save(person);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
			res.setMessage(localeService.getMessage("update.exception"));
			return res;
		}
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setMessage(localeService.getMessage("success"));
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41006", "", "修改管理员信息成功");
		return res;
	}
	/**
	 * 查询单个管理员所有信息
	 * @param token
	 * @param personId
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGE_PERSON_FINDBYID)
	public Response findByPerson(String token, Integer personId) {
		Response res = new Response();
		// 判断是否有访问权限
		
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41009", "", "请求查看管理员信息");
		Person person = new Person();
		try {
			person = personService.findById(personId);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
			res.setMessage(localeService.getMessage("query.exception"));
			return res;
		}
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setMessage(localeService.getMessage("success"));
		res.setObject(person);
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41009", "", "请求查看管理员信息");
		return res;
	}
	/**
	 * 管理员登录
	 * @param loginName 登录名
	 * @param pwd 密码
	 * @param verificationCodeKey 验证码redis中的key值
	 * @param verificationCode 输入的验证码
	 * @param deviceType 设备信息
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGE_PERSON_SIGNIN)
	public Response signIn(String loginName, String pwd,String verificationCodeKey, String verificationCode, String deviceType) {
		Response res = new Response();
		Map<String, Object> maps = new HashMap<>();
		if(verificationCodeKey != null) { // 验证码不为空
			String verCode = redisService.getRedis(verificationCodeKey); // 获取验证码
			redisService.deleteRedisAccout(verificationCodeKey); // 销毁验证码
			if (verCode != null && !"".equals(verCode) && verCode.equalsIgnoreCase(verificationCode)) { // 输入验证码是否正确，不用区分大小写
				if (loginName != null && !"".equals(loginName) && pwd != null && MainUtilityTools.pwd(pwd)) {
					actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41008", loginName, "请求登录");
					// 访问数据库
					try {
						maps = personService.signIn(loginName, pwd, deviceType);
						if (!maps.isEmpty()) {
							res.setStatus(ResponseStatusCode.SUCCESS.getCode());
							res.setMessage(localeService.getMessage("success"));
							res.setObject(maps);
							actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41008", loginName, "登录成功");
							return res;
						}
					} catch (Exception e) {
						MainUtilityTools.catchException(e, this.getClass(), "");
						res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
						res.setMessage(localeService.getMessage("query.exception"));
						return res;
					}
				}
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeService.getMessage("fail"));
		res.setObject(maps);
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41008", loginName, "请求登录失败");
		return res;
	}
	
	/**
	 * 修改管理员密码
	 * @param token 用户令牌
	 * @param loginName 登录名
	 * @param newPwd 新密码
	 * @return
	 */
	@RequestMapping(WebURIConstant.MANAGE_PERSON_UPDATEPWD)
	public Response updatePwd(String token, String loginName, String newPwd) {
		Response res = new Response();
		// 判断是否有访问权限,token中的值与loginName比对是否一致
		
		int change = 0; // 修改密码改变的数据条数
		if (loginName != null && !"".equals(loginName) && newPwd != null && MainUtilityTools.pwd(newPwd)) {
			actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM41010", loginName, "请求修改管理员密码");
			try {
				change = personService.updatePwd(loginName, newPwd); // 根据账号重设密码
				if (change != 0) {
					res.setStatus(ResponseStatusCode.SUCCESS.getCode());
					res.setObject(change);
					res.setMessage(localeService.getMessage("success"));
					actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM21010", loginName, "修改管理员密码成功");
					return res;
				} else {
					res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
					res.setObject(change);
					res.setMessage(localeService.getMessage("no.result"));
					return res;
				}
			} catch (Exception e) {
				MainUtilityTools.catchException(e, this.getClass(), "");
				res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
				res.setObject(change);
				res.setMessage(localeService.getMessage("fail"));
				return res;
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setObject(change);
		res.setMessage(localeService.getMessage("format.error"));
		return res;
	}
}
