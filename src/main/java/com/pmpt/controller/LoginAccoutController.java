package com.pmpt.controller;

import static org.mockito.Matchers.booleanThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pmpt.common.ActionLogClass;
import com.pmpt.common.ActionLogCode;
import com.pmpt.common.BCrypt;
import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.MD5Util;
import com.pmpt.common.MainUtilityTools;
import com.pmpt.common.Response;
import com.pmpt.common.ResponseStatusCode;
import com.pmpt.common.Sendsms;
import com.pmpt.easemob.impl.EasemobIMUsers;
import com.pmpt.entities.Interest;
import com.pmpt.entities.LoginAccout;
import com.pmpt.entities.enums.Interests;
import com.pmpt.entities.enums.Valid;
import com.pmpt.interfaces.Service.ActionLogService;
import com.pmpt.interfaces.Service.InterestService;
import com.pmpt.interfaces.Service.LoginAccoutService;
import com.pmpt.interfaces.Service.PublishService;
import com.pmpt.interfaces.Service.VerificationCodeService;

import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;

/**
 * 
 * @ClassName: LoginAccoutController.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月24日下午2:09:19
 */
@RestController
public class LoginAccoutController {
	@Autowired
	private LoginAccoutService service;
	@Autowired
	private VerificationCodeService redisService;
	@Autowired
	private PublishService publishService;
	@Autowired
	private Sendsms sendSMS;
	@Autowired
	private InterestService interestService;
	@Resource
	private LocaleMessageSourceService localeMessageSourceService;
	@Resource
	private ActionLogService actionLogService;

	private EasemobIMUsers easemobIMUsers = new EasemobIMUsers();

	/**
	 * 登录
	 * 
	 * @param phone
	 *            手机号
	 * @param pwd
	 *            密码
	 * @param deviceType
	 *            设备信息
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_SIGNIN)
	public Response signIn(String phone, String pwd, String deviceType) {
		Response res = new Response();
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM21005", phone,
				"请求登录");
		if (phone != null && MainUtilityTools.phoneNum(phone) && pwd != null && MainUtilityTools.pwd(pwd)) {
			Map<String, Object> maps = new HashMap<>();
			String pwdMD5= MD5Util.setMD5(pwd);
			maps = service.signIn(phone, pwdMD5, deviceType); // loginAccoutId、phone、userId、loginSeesion
			if (!maps.isEmpty()) {
				String token = (String) maps.get("token");
				res.setStatus(ResponseStatusCode.SUCCESS.getCode());
				res.setMessage(localeMessageSourceService.getMessage("success"));
				res.setObject(maps.get("phone"));
				res.setToken(token);
				actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM21005",
						phone, "登录成功");
				return res;
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("no.result"));
		return res;
	}

	/**
	 * 从缓存中获取用户状态码
	 * 
	 * @param token
	 *            缓存中用户状态信息key值
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_GETSTATUS)
	public Response getStatus(String token) {
		Response res = new Response();
		if (token != null && !"".equals(token)) {
			String[] result = redisService.getRedisStr(token);
			if (result != null) {
				Map<String, String> map = new HashMap<>();
				String phone = result[0];
				map.put("phone", phone);
				if (result.length == 2) {
					String userId = result[1];
					map.put("userId", userId);
				}
				res.setStatus(ResponseStatusCode.SUCCESS.getCode());
				res.setMessage(localeMessageSourceService.getMessage("success"));
				res.setObject(map);
				res.setToken(token);
				return res;
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("no.result"));
		return res;
	}

	/**
	 * 注册
	 * 
	 * @param phone
	 *            手机号
	 * @param pwd
	 *            密码
	 * @param smsNum
	 *            短信验证码
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_REGISTER)
	public Response registerByPhone(String phone, String pwd, String smsNum) {
		Response res = new Response();
		Map<String, Object> loginAcMap = null;
		boolean pwdb = MainUtilityTools.pwd(pwd);
		if (phone != null && pwdb && smsNum != null) {
			boolean p = MainUtilityTools.phoneNum(phone);
			if (p) { // 手机号格式是否正确
				actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.REGIST.getCode(), "LM21001",
						phone, "请求注册");
				String sms = redisService.getRedis(MD5Util.setMD5(phone));
				if ("123456".equals(smsNum)) { // 测试使用--( sms != null && sms.equals(smsNum) )
					LoginAccout loginAccout = new LoginAccout();
					loginAccout.setIsEffect(Valid.VALID.getKey());
					loginAccout.setPhone(phone);
//					loginAccout.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt()));
					loginAccout.setPwd(MD5Util.setMD5(pwd));

					// 组装环信账户密码
					RegisterUsers users = new RegisterUsers();
					loginAccout.setHxLoginName(MD5Util.setMD5(phone));
					// 密码BCrypt加密
					loginAccout.setHxPassword(BCrypt.hashpw(pwd, BCrypt.gensalt()));
					User user = new User().username(loginAccout.getHxLoginName()).password(loginAccout.getHxPassword());
					users.add(user);
					try {
						Object result = easemobIMUsers.createNewIMUserSingle(users);
						if (result != null) {
							loginAcMap = new HashMap<>();
							// 注册人互人平台账号
							service.registerByPhone(loginAccout);
							loginAcMap.put("loginName", loginAccout.getHxLoginName());
							loginAcMap.put("password", loginAccout.getHxPassword());
							actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.REGIST.getCode(),
									"", phone, "环信注册" + result);
						}
					} catch (Exception e) {
						MainUtilityTools.catchException(e, this.getClass(), "");
						res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
						res.setMessage(localeMessageSourceService.getMessage("insertion.exception"));
						return res;
					}
					res.setStatus(ResponseStatusCode.SUCCESS.getCode());
					res.setMessage(localeMessageSourceService.getMessage("success"));
					res.setObject(loginAcMap);
					actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.REGIST.getCode(),
							"LM21001", phone, "注册成功");
					return res;
				}
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("fail"));
		return res;
	}

	/**
	 * 退出接口 手动删除redis数据
	 * 
	 * @param token
	 *            redis缓存中的key
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_LOGOUT)
	public Response logOut(String token) {
		Response res = new Response();
		String sessionValue = "";
		if (token != null) {
			try {
				sessionValue = redisService.getRedis(token);
				if (sessionValue != null) {
					redisService.deleteRedisAccout(token);
					res.setStatus(ResponseStatusCode.SUCCESS.getCode());
					res.setMessage(localeMessageSourceService.getMessage("success"));
					return res;
				} else {
					res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
					res.setMessage(localeMessageSourceService.getMessage("no.result"));
					return res;
				}
			} catch (Exception e) {
				MainUtilityTools.catchException(e, this.getClass(), "");
				res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
				res.setMessage(localeMessageSourceService.getMessage("fail"));
				return res;
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("no.result"));
		return res;
	}

	/**
	 * 修改密码
	 * 
	 * @param token
	 *            用户令牌
	 * @param newPwd
	 *            新密码
	 * @param againPwd
	 *            确认密码
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_MODIFYPWD)
	public Response modifyPwd1(String token, String newPwd, String againPwd) {
		Response res = new Response();
		String phone = ""; // 判断用户权限
		int change = 0;
		if (token != null && !"".equals(token)) {
			phone = redisService.getRedisMap(token, "phone"); // 取出token中的手机号
			boolean result = false;
			if (phone != null) {
				actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM21009",
						phone, "请求修改密码");
				try {
					result = MainUtilityTools.pwd(newPwd);
					if (newPwd != null && !"".equals(newPwd) && againPwd != null && !"".equals(againPwd)
							&& newPwd.equals(againPwd) && result) { // 老密码不为空
						change = service.modifyPwd(phone, newPwd);
						if (change != 0) {
							res.setStatus(ResponseStatusCode.SUCCESS.getCode());
							res.setObject(change);
							res.setMessage(localeMessageSourceService.getMessage("success"));
							actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(),
									"LM21009", phone, "修改密码成功");
							return res;
						} else {
							res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
							res.setObject(change);
							res.setMessage(localeMessageSourceService.getMessage("no.result"));
							return res;
						}
					} else {
						res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
						res.setObject(change);
						res.setMessage(localeMessageSourceService.getMessage("format.error"));
						return res;
					}
				} catch (Exception e) {
					MainUtilityTools.catchException(e, this.getClass(), "");
					res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
					res.setObject(change);
					res.setMessage(localeMessageSourceService.getMessage("fail"));
					return res;
				}
			}
		}
		res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
		res.setObject(change);
		res.setMessage(localeMessageSourceService.getMessage("no.result"));
		return res;
	}

	/**
	 * 验证短信验证码
	 * 
	 * @param phone
	 *            账号
	 * @param smsValue
	 *            用户输入的短信验证码
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_VERIFYCODE)
	public Response verifyCode(String phone, String smsValue) {
		Response res = new Response();
		String redisValue = ""; // 缓存中的短信验证码
		if (MainUtilityTools.phoneNum(phone)) {
			String phoneKey = MD5Util.setMD5(phone);
			if (phoneKey != null) {
				actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM21011",
						phone, "访问验证短信验证码");
				redisValue = redisService.getRedis(phoneKey); // 获取验证码
				if (redisValue != null && !redisValue.isEmpty() && redisValue.equals(smsValue)) { // 短信验证码输入是否正确
					String token = MainUtilityTools.getToken(); // 生成随机码作为key
					redisService.setRedis(token, phone); // 保存到redis中5分钟
					res.setStatus(ResponseStatusCode.SUCCESS.getCode());
					res.setObject(token);
					res.setMessage(localeMessageSourceService.getMessage("success"));
					actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(),
							"LM21011", phone, "验证短信验证码成功");
					return res;
				}
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("format.error"));
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM21011", phone,
				"验证短信验证码失败");
		return res;
	}

	/**
	 * 忘记密码提交修改密码
	 * 
	 * @param token
	 *            验证短信验证码后返回的token
	 * @param newPwd
	 *            新密码
	 * @param againPwd
	 *            重复密码
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_FORGOTPWD)
	public Response forgotPwd(String token, String newPwd, String againPwd) {
		Response res = new Response();
		String phone = "";
		int change = 0; // 修改密码改变的数据条数
		if (token != null && !"".equals(token) && MainUtilityTools.pwd(newPwd) && newPwd.equals(againPwd)) {
			phone = redisService.getRedis(token); // 取出手机号
			redisService.deleteRedisAccout(token); // 销毁token
			actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM21003",
					phone, "访问验证短信验证码");
			if (MainUtilityTools.phoneNum(phone)) {
				try {
					change = service.modifyPwd(phone, newPwd); // 根据账号重设密码
					if (change != 0) {
						res.setStatus(ResponseStatusCode.SUCCESS.getCode());
						res.setObject(change);
						res.setMessage(localeMessageSourceService.getMessage("success"));
						actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(),
								"LM21003", phone, "修改密码成功");
						return res;
					} else {
						res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
						res.setObject(change);
						res.setMessage(localeMessageSourceService.getMessage("no.result"));
						return res;
					}
				} catch (Exception e) {
					MainUtilityTools.catchException(e, this.getClass(), "");
					res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
					res.setObject(change);
					res.setMessage(localeMessageSourceService.getMessage("fail"));
					return res;
				}
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("format.error"));
		actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.LOGIN.getCode(), "LM21003", phone,
				"忘记密码改密失败");
		return res;
	}

	/**
	 * 验证账户是否已经注册过
	 * 
	 * @param phone
	 *            手机号
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_FINDACCOUT)
	public Response findAccout(String phone) {
		Response res = new Response();
		boolean flag = false;
		if (phone != null && MainUtilityTools.phoneNum(phone)) {
			try {
				flag = service.findAccout(phone);
				if (flag) {
					res.setStatus(ResponseStatusCode.SUCCESS.getCode());
					res.setObject(flag);
					res.setMessage(localeMessageSourceService.getMessage("user.already.exists"));
					return res;
				} else {
					res.setStatus(ResponseStatusCode.SUCCESS.getCode());
					res.setObject(flag);
					res.setMessage(localeMessageSourceService.getMessage("user.is.not.found"));
					return res;
				}
			} catch (Exception e) {
				MainUtilityTools.catchException(e, this.getClass(), "");
				res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
				res.setMessage(localeMessageSourceService.getMessage("query.exception"));
				res.setObject(flag);
				return res;
			}
		}
		res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
		res.setMessage(localeMessageSourceService.getMessage("format.error"));
		res.setObject(flag);
		return res;
	}

	/**
	 * 发送注册短信验证码
	 * 
	 * @param phone
	 *            手机号
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_GETSMS)
	public Response getSMS(String phone) {
	/*@RequestMapping(value = WebURIConstant.LOGINACCOUT_GETSMS, method = RequestMethod.POST)
	public Response getSMS(@RequestParam(value = "phone", required = true) String phone) {*/
		Response res = new Response();
		boolean flag = false;
		String redisValue = "";
		if (phone != null && MainUtilityTools.phoneNum(phone)) {
			actionLogService.addLog(null, ActionLogClass.INFO.getCode(), ActionLogCode.REGIST.getCode(), "LM21008",
					phone, "注册发送短信验证码");
			redisValue = redisService.getRedis(phone); // 查询1分钟之内该用户是否发送过短信
			if (redisValue == null || "".equals(redisValue)) { // 1分钟内不接受短信请求
				try {
					flag = service.findAccout(phone);
					if (!flag) { // 该用户未注册且未发送过验证码
						String smsCode = sendSMS.registrationVerification(phone); // 调用短信接口,以短信方式发送验证码到用户手机上
						// 发送成功后
						if (smsCode != null && !"".equals(smsCode)) {
							String phoneKey = MD5Util.setMD5(phone);
							redisService.setRedis(phoneKey, smsCode); // 以phone值经过MD5加密后做为key，验证码作为value，保存5分钟
							redisService.setRedisSMS(phone, localeMessageSourceService.getMessage("success")); // 以phone为key值，保存1分钟
							res.setStatus(ResponseStatusCode.SUCCESS.getCode());
							res.setMessage(localeMessageSourceService.getMessage("success"));
							res.setToken(phoneKey);
							return res;
						}
					}
				} catch (Exception e) {
					MainUtilityTools.catchException(e, this.getClass(), "");
					res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
					res.setMessage(localeMessageSourceService.getMessage("query.exception"));
					return res;
				}
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("format.error"));
		return res;
	}

	/**
	 * 备用 请求发送短信需携带发送验证码时请求回调的sessionId
	 * 
	 * @param phone
	 *            手机号
	 * @param verificationCodeKey
	 *            验证码key值
	 * @param verificationCode
	 *            验证码
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/getsmssec")
	public Response getSMSSec(String phone, String verificationCodeKey, String verificationCode,
			HttpServletRequest request) {
		Response res = new Response();
		boolean flag = false;
		String redisValue = "";
		String[] strs = null; // 验证码和requestSessionId的字符串组合
		String verCode = "";
		String requestSessionId = "";
		String newResSessionId = request.getSession().getId(); // 发送短信验证码请求的sessionId
		if (verificationCodeKey != null && !"".equals(verificationCodeKey)) {
			strs = redisService.getRedisStr(verificationCodeKey); // 取出缓存中验证码与requestSessionId的字符串组合
			redisService.deleteRedisAccout(verificationCodeKey); // 删除缓存中验证码与requestSessionId的字符串组合
			if (strs != null && !"".equals(strs)) {
				verCode = strs[0]; // 获取缓存中的验证码
				requestSessionId = strs[1]; // 获取缓存中的请求sessionId
				// 用户输入的验证码不为空，且正确，且requestSessionId相同
				if (verificationCode != null && verCode.equals(verificationCode)
						&& newResSessionId.equals(requestSessionId)) {
					if (phone != null && MainUtilityTools.phoneNum(phone)) {
						redisValue = redisService.getRedis(newResSessionId); // 查询1分钟之内该用户是否发送过短信
						if (redisValue == null || "".equals(redisValue)) { // 1分钟内不接受短信请求
							try {
								flag = service.findAccout(phone);
								if (!flag) { // 该用户未注册且未发送过验证码
									String smsCode = sendSMS.registrationVerification(phone); // 调用短信接口,以短信方式发送验证码到用户手机上
									// 发送成功后
									if (smsCode != null && !"".equals(smsCode)) {
										String phoneKey = MD5Util.setMD5(phone);
										redisService.setRedis(phoneKey, smsCode); // 以phone值经过MD5加密后做为key，验证码作为value，保存5分钟
										redisService.setRedisSMS(newResSessionId, "2"); // 以phone为key值，保存1分钟
										res.setStatus(ResponseStatusCode.SUCCESS.getCode());
										res.setMessage(localeMessageSourceService.getMessage("success"));
										res.setToken(phoneKey);
										return res;
									}
								}
							} catch (Exception e) {
								MainUtilityTools.catchException(e, this.getClass(), "");
								res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
								res.setMessage(localeMessageSourceService.getMessage("fail"));
								return res;
							}
						}
					}

				}
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("format.error"));
		return res;
	}

	/**
	 * 验证验证码是否输入正确
	 * 
	 * @param verificationCodeKey
	 *            redis缓存中验证码的key值
	 * @param verificationCode
	 *            输入的验证码
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_GETVERIFICATIONCODE)
	public Response getverificationCode(String verificationCodeKey, String verificationCode) {
		Response res = new Response();
		String redisValue = "";
		boolean flag = false;
		if (verificationCodeKey != null && !"".equals(verificationCodeKey)) {
			redisValue = redisService.getRedis(verificationCodeKey); // 缓存中验证码的值
			if (redisValue != null && !"".equals(redisValue) && verificationCode != null
					&& redisValue.equals(verificationCode)) {
				flag = true;
				res.setObject(flag);
				res.setStatus(ResponseStatusCode.SUCCESS.getCode());
				return res;
			}
		}
		res.setObject(flag);
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		return res;
	}

	/**
	 * 添加个人喜好设置
	 * 
	 * @param token
	 *            用户令牌
	 * @param interests
	 *            喜好标签集合
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_ADD_INTEREST)
	public Response addLike(String token, @RequestBody List<Interest> interests) {
		Response res = new Response();
		String loginAccoutId = "";
		if (token != null && !"".equals(token) && interests != null && !interests.isEmpty()) {
			loginAccoutId = redisService.getRedisMap(token, "loginAccoutId"); // 获取账号id
			if (loginAccoutId != null && !"".equals(loginAccoutId)) {
				String commodity = ""; // 品名
				String code = ""; // 编号
				StringBuffer sb = null; // 生成Interest主键
				for (int i = 0; i < interests.size(); i++) {
					sb = new StringBuffer();
					code = interests.get(i).getCode(); // 编号
					commodity = interests.get(i).getCommodity(); // 品名
					sb.append(loginAccoutId).append(code);
					// 主键id,用户ID，品名，编号
					interestService.addInterest(sb.toString(), loginAccoutId, commodity, code);
				}
				res.setStatus(ResponseStatusCode.SUCCESS.getCode());
				res.setMessage(localeMessageSourceService.getMessage("success"));
				return res;
			}
		}
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setMessage(localeMessageSourceService.getMessage("format.error"));
		return res;
	}

	/**
	 * 展示喜好
	 * @return
	 */
	@RequestMapping(WebURIConstant.LOGINACCOUT_INTERESTS)
	public Response ExhibitLikes() {
		Response res = new Response();
		Map<String, String> map = null;
		List<Map<String, String>> list = new ArrayList<>();
		for (Interests interest : Interests.values()) {
			map =  new HashMap<>();
			map.put("code", interest.getCode());
			map.put("productName", interest.getProductName());
			map.put("interestUrl", interest.getInterestUrl());
			list.add(map);
		}
		if (!map.isEmpty()) {
			res.setMessage(localeMessageSourceService.getMessage("success"));
			res.setStatus(ResponseStatusCode.SUCCESS.getCode());
			res.setObject(list);
			return res;
		}
		res.setMessage(localeMessageSourceService.getMessage("fail"));
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setObject(list);
		return res;
	}
	
	// 展示喜好——MySQL版
	@RequestMapping("/like")
	public Response exhibitLike() {
		Response res = new Response();
		List<Object[]> list = null;
		list = interestService.exhibitLike();
		if (!list.isEmpty()) {
			res.setMessage(localeMessageSourceService.getMessage("success"));
			res.setStatus(ResponseStatusCode.SUCCESS.getCode());
			res.setObject(list);
			return res;
		}
		res.setMessage(localeMessageSourceService.getMessage("fail"));
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setObject(list);
		return res;
	}
	
	// 展示洲接口——MySQL版
	@RequestMapping("/continent")
	public Response exhibitContinent() {
		Response res = new Response();
		List<Object[]> list = null;
		list = publishService.exhibitContinent();
		if (!list.isEmpty()) {
			res.setMessage(localeMessageSourceService.getMessage("success"));
			res.setStatus(ResponseStatusCode.SUCCESS.getCode());
			res.setObject(list);
			return res;
		}
		res.setMessage(localeMessageSourceService.getMessage("fail"));
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setObject(list);
		return res;
	}

	// 展示国家接口——MySQL版
	@RequestMapping("/countrys")
	public Response countrys(Integer continentId) {
		Response res = new Response();
		List<Object[]> list = null;
		list = publishService.countrys(continentId);
		if (!list.isEmpty()) {
			res.setMessage(localeMessageSourceService.getMessage("success"));
			res.setStatus(ResponseStatusCode.SUCCESS.getCode());
			res.setObject(list);
			return res;
		}
		res.setMessage(localeMessageSourceService.getMessage("fail"));
		res.setStatus(ResponseStatusCode.PARAMETER_ERROR.getCode());
		res.setObject(list);
		return res;
	}
	
}
