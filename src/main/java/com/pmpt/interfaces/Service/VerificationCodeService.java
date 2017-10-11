package com.pmpt.interfaces.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.pmpt.common.MainUtilityTools;

@Service
public class VerificationCodeService {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	// 验证码存入redis缓存5分钟
	public void setRedis(String key, String value) {
		getOperations().set(key, value, 300, TimeUnit.SECONDS);
	}
	// 用户发送短信后，phone存入redis中1分钟
	public void setRedisSMS(String key, String value) {
		getOperations().set(key, value, 60, TimeUnit.SECONDS);
	}
	// 取出redis中值
	public String getRedis(String key) {
		String redisValue = "";
		redisValue = getOperations().get(key);
		return redisValue;
	}
	// 删除redis中缓存内容
	public void deleteRedisAccout(String key) {
		try {
			redisTemplate.delete(key);
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
		}
	}
	// 存入redis值为String类型
	public void setRedisStr(String key, String str, Integer num) {
		StringBuffer sb = new StringBuffer();
		sb.append(str).append(",").append(num);
		getOperations().set(key, sb.toString(), 300, TimeUnit.SECONDS);
	}
	/*
	 * 获取并拆分redis缓存用户状态码
	 */
	public String[] getRedisStr(String key) {
		String[] arr = null;
		String sessValue = "";
		try {
			sessValue = getOperations().get(key);
			if(sessValue != null && !"".equals(sessValue)) {
				arr = sessValue.split(",");
			}
		} catch (Exception e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			return arr;
		}
		return arr;
	}
	/**
	 * 
	 * @param key token值
	 * @param value  phone + userId + loginAccoutId
	 * @return
	 */
	public String getRedisMap(String key, String value){
		Map<String, String> map = new HashMap<>();
		String[] arr = null;
		String sessValue = "";
		sessValue = getOperations().get(key);
		if (sessValue != null && !"".equals(sessValue)) {
			arr = sessValue.split(",");
			if (0 < arr.length) {
				map.put("loginAccoutId", arr[0]);
			}
			if (1 < arr.length) {
				map.put("phone", arr[1]);
			}
			if (2 < arr.length) {
				map.put("userId", arr[2]);
			}
		}
		return map.get(value);
	}
	/**
	 * 
	 * @param key token值
	 * @param value loginName/module
	 * @return
	 */
	public String getManageRedisMap(String key, String value){
		Map<String, String> map = new HashMap<>();
		String[] arr = null;
		String sessValue = "";
		sessValue = getOperations().get(key);
		if (sessValue != null && !"".equals(sessValue)) {
			arr = sessValue.split(",");
			if (0 < arr.length) {
				map.put("loginName", arr[0]);
			}
			if (1 < arr.length) {
				map.put("module", arr[1]);
			}
		}
		return map.get(value);
	}
	
	public ValueOperations<String, String> getOperations(){
		MainUtilityTools.setRedisTemplate(redisTemplate);
		return redisTemplate.opsForValue();
	}
}
