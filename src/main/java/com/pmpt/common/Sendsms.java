package com.pmpt.common;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
//import org.springframework.data.redis.core.RedisTemplate;
/**
 * 
 * @author  Cliff Zhang  
 * @Date    2017/8/14
 */


@Service
public class Sendsms {
	
	private static String Url = "http://121.199.16.178/webservice/sms.php?method=Submit";
	private static String  name ="cf_shlyjr";
	private static String  pwd ="shlyjr@123";
	private static String validCode = "";
	public  static  String getSms(String phone){
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		client.getParams().setContentCharset("UTF-8");
		validCode = DigitGenerator.generateCode(4);
		String content = new String("您的验证码是："+validCode +"。 ");
	    NameValuePair[] data = {
				 new NameValuePair("account", name),
	             new NameValuePair("password", pwd),
	             new NameValuePair("mobile", phone),
	             new NameValuePair("content", content)
		};
		method.setRequestBody(data);
        try {
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
            if("2".equals(code)){
                return validCode;
            }
        } catch (Exception e) {
           MainUtilityTools.catchException(e, Sendsms.class, "");
        }
        return null;
	  }
	
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public String verification(String sid,String phone) {
		//验证SID的合法性；
		String  validCode = "";
		boolean flag = false;
		ValueOperations<String,String> operations;
		String loginame= "";
		try {
		operations = redisTemplate.opsForValue();
		loginame=operations.get(sid);
		}catch(Exception e) {
			System.out.println("请稍等网络延迟中......:"+ e.getMessage());
	    }
		
		if(!loginame.isEmpty()&&!loginame.equals(null)) {
		    	 flag = true;   
		}
		
		if(flag) {
			validCode =Sendsms.getSms(phone);
			return validCode;
		}
		
		return null;
	}
	
	/*
	 * 注册发送短信验证码,返回验证码
	 */
	public String registrationVerification(String phone) {
		if(phone != null) {
			String validCode = ""; // 验证码
			boolean flag = true;
			ValueOperations<String,String> operations;
			String phoneValue = "";
			String phoneKey = MD5Util.setMD5(phone);
			if (phoneKey != null) {
				operations = redisTemplate.opsForValue();
				phoneValue = operations.get(phoneKey);
				if(phoneValue != null && !"".equals(phoneValue)) {
					flag =false;
				}
				if(flag) {
					validCode =Sendsms.getSms(phone);
					return validCode;
				}
			}
		}
		return "";
	}
	
	
	
	
}
