package com.pmpt.interfaces.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.pmpt.common.MainUtilityTools;
import com.pmpt.entities.LoginAccout;
import com.pmpt.entities.LoginSession;
import com.pmpt.interfaces.Dao.LoginAccoutDAO;
import com.pmpt.interfaces.Dao.LoginAccoutDAOCus;
import com.pmpt.interfaces.Dao.LoginSessionDAO;
import com.pmpt.interfaces.Dao.LoginSessionDAOCus;

/**
 * @author jianghb
 */
@Service
//@ComponentScan("com.pmpt.domain.loginAccout.dao")
public class LoginAccoutService {
	@Autowired
	private LoginAccoutDAO dao;
	@Autowired
	private LoginAccoutDAOCus dao2;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private LoginSessionDAO sessionDao;
	@Autowired
	private LoginSessionDAOCus sessionDaoCus;

	/**
	 * 登录
	 * 
	 * @param Phone
	 *            手机号
	 * @param pwd
	 *            密码
	 * @param deviceType
	 *            设备信息
	 * @return phone/userId/loginSession
	 */
	public Map<String, Object> signIn(String phone, String pwd, String deviceType) {
		Map<String, Object> map = new HashMap<>();
		List<Object[]> accout = null;
		accout = dao2.signIn(phone, pwd); // 从DB获取账户信息
		if (accout != null && !accout.isEmpty()) {
			StringBuffer sb = new StringBuffer(); // 账户信息和用户实名认证后的userId组成字符串
			String token = MainUtilityTools.getToken(); // 生成新的key
			map.put("token", token);
			Object[] accoutObj = accout.get(0);
			// 获取loginAccoutId
			if (3 < accoutObj.length) {
				Integer loginAccoutId = (Integer) accoutObj[3];
				sb.append(loginAccoutId).append(",");
			}
			String phoneStr = (String) accoutObj[0]; // 手机号
			map.put("phone", phoneStr);
			sb.append(phoneStr).append(",");
			if (accoutObj[1] != null) { // 实名认证userId
				Integer userId = (Integer) accoutObj[1];
				map.put("userId", userId);
				sb.append(userId);
			}
			LoginSession ls = new LoginSession();
			ls.setDeviceType(deviceType);
			ls.setToken(token);
			if (accoutObj[2] == null) { // 用户第一次登录，缓存为空
				// 对缓存进行记录（loginSession表插入token/设备信息）
				sessionDao.save(ls); // 存入DB缓存数据表
				// 修改账户信息loginSession
				int result = dao2.updateLogSession(ls, phoneStr);
				if (result != 0) {
					// 插入redis缓存
					getOperations().set(token, sb.toString());
					return map;
				}
			} else { // 用户不是初次登录，缓存有记录
				Integer loginSession = (Integer) accoutObj[2]; // 记录
				// 查出loginSession的token值
				List<Object[]> result = new ArrayList<>();
				result = sessionDaoCus.findByToken(loginSession); // 查出数据库中loginSession表中token
				Object resultObj = result.get(0);
				String dbToken = (String) resultObj; // 当前redis缓存数据库中用户信息key值
				sessionDaoCus.memoryCache(loginSession, token, deviceType); // 修改数据库中loginSession缓存表中数据
				// redis中数据删除
				redisTemplate.delete(dbToken); // 删除redis缓存中上次登录时保存的用户信息
			}
			// 插入redis缓存
			getOperations().set(token, sb.toString());
		}
		return map;
	}

	public ValueOperations<String, String> getOperations() { // 获取redis服务
		MainUtilityTools.setRedisTemplate(redisTemplate);
		return redisTemplate.opsForValue();
	}

	/**
	 * 账户注册
	 * 
	 * @param phone
	 *            手机号
	 * @param pwd
	 *            密码
	 * @return
	 */
	public void registerByPhone(LoginAccout loginAccout) {
		dao.save(loginAccout);
	}

	/**
	 * 查询密码
	 * 
	 * @param phone
	 * @return
	 */
	public String queryPwd(String phone) {
		String pwd = "";
		pwd = dao2.queryPwd(phone);
		return pwd;
	}

	/**
	 * 修改密码
	 * 
	 * @param phone
	 *            手机号
	 * @param pwd
	 *            要修改成的密码
	 * @return 修改记录数
	 */
	public int modifyPwd(String phone, String pwd) {
		return dao2.modifyPwd(phone, pwd);
	}

	/**
	 * 查询账户是否存在
	 * 
	 * @param phone
	 * @return
	 */
	public boolean findAccout(String phone) {
		return dao2.findAccout(phone);
	}

}
