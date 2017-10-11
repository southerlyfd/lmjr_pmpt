/**
 * 
 */
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
import com.pmpt.common.PageBean;
import com.pmpt.entities.LoginSession;
import com.pmpt.entities.Module;
import com.pmpt.entities.Person;
import com.pmpt.interfaces.Dao.LoginSessionDAO;
import com.pmpt.interfaces.Dao.LoginSessionDAOCus;
import com.pmpt.interfaces.Dao.PersonDAO;
import com.pmpt.interfaces.Dao.PersonDAOCus;

/**
 * @ClassName: PersonService.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月30日下午2:58:56
 */
@Service
@ComponentScan("com.pmpt.domain.person.dao")
public class PersonService {

	@Autowired
	private PersonDAO dao;
	@Autowired
	private PersonDAOCus dao2;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private LoginSessionDAO sessionDao;
	@Autowired
	private LoginSessionDAOCus sessionDaoCus;
	
	// 新增人员信息,更新人员信息
	public void save(Person person) {
		try {
			dao.save(person);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
		}
	}
	// 查询人员信息
	public PageBean<Object[]> findAll(int pageSize, int currentPage) {
		return dao2.findAll(pageSize, currentPage);
	}
	// 删除管理员信息
	public void delete(Integer personId) {
		dao.delete(personId);
	}
	// 查询单个人员所有信息
	public Person findById(Integer personId){
		return dao.findOne(personId);
	}
	// 修改管理员密码
	public int updatePwd(String loginName, String newPwd) {
		return dao2.updatePwd(loginName, newPwd);
	}
	// 管理员登录
	public Map<String, Object> signIn(String loginName, String pwd, String deviceType){
		Map<String, Object> map = new HashMap<>();
		List<Object[]> person = null; 
		// 从DB中获取账户信息
		person = dao2.signIn(loginName, pwd);
		Module module = null;
		if (person != null && !person.isEmpty()) { // 如果查询到管理员信息
			StringBuffer sb = new StringBuffer();
			map.put("loginName", loginName); // 登录有效，返回登录名
			sb.append(loginName).append(",");
			String token = MainUtilityTools.getToken(); // 生成新的key
			map.put("token", token);
			Object[] obj = null; // 接受登录信息
			for (int i = 0; i < person.size(); i++) {
				obj = person.get(i);
				if (1 < obj.length) {
					module = (Module) obj[1];
					sb.append(module.getName());
				}
			}
			map.put("module", sb);
			LoginSession ls = new LoginSession();
			ls.setDeviceType(deviceType);
			ls.setToken(token);
			// 判断是否是第一次登录
			Object[] tokenObj = person.get(0);
			if (tokenObj[2] == null) { // 第一次登录
				// 对缓存进行记录（loginSession表插入token/设备信息）
				sessionDao.save(ls); // 存入DB缓存数据表
				// 修改账户信息loginSession
				int result = dao2.updatePersonSession(ls, loginName);
				if (result != 0) {
					getOperations().set(token, sb.toString()); // 插入redis缓存
					return map;
				}
			} else { // 用户不是初次登录，缓存有记录
				Integer loginSession = (Integer)tokenObj[2]; // 记录
				// 查出loginSession的token值
				List<Object[]> result = new ArrayList<>();
				result = sessionDaoCus.findByToken(loginSession); // 查出数据库中loginSession表中token
				Object resultObj = result.get(0);
				String dbToken = (String) resultObj; // 当前redis缓存数据库中用户信息key值
				sessionDaoCus.memoryCache(loginSession, token, deviceType); // 修改数据库中loginSession缓存表中数据
				// redis中数据删除
				redisTemplate.delete(dbToken); // 删除redis缓存中上次登录时保存的用户信息
			}
			getOperations().set(token, sb.toString()); // 插入redis缓存
		}
		return map;
	}
	
	public ValueOperations<String, String> getOperations(){ // 获取redis服务
		MainUtilityTools.setRedisTemplate(redisTemplate);
		return redisTemplate.opsForValue();
	}
}
