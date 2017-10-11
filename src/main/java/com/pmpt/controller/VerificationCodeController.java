package com.pmpt.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmpt.common.LocaleMessageSourceService;
import com.pmpt.common.MainUtilityTools;
import com.pmpt.common.Response;
import com.pmpt.common.ResponseStatusCode;
import com.pmpt.interfaces.Service.VerificationCodeService;

import sun.misc.BASE64Encoder;

@RestController
public class VerificationCodeController {
	@Autowired
	private VerificationCodeService redisService;
	@Resource
	private LocaleMessageSourceService localeMessageSourceService;
	
	@RequestMapping(WebURIConstant.VERIFICATIONCODE_IMAGE)
	public Response getVerificationCode() {
		ByteArrayOutputStream outputStream = null;
		Map<String, Object> map = new HashMap<>();
		map = MainUtilityTools.getVerificationCode();
		BufferedImage bi = (BufferedImage) map.get("image");
		String num = (String) map.get("num");
		String numKey = MainUtilityTools.getToken(); // 获取随机数作为key
		String base = "";
		try {
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", outputStream);
			redisService.setRedis(numKey, num);
		}catch (IOException e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			Response res = new Response();
			res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
			res.setMessage(localeMessageSourceService.getMessage("no.result"));
			return res;
		} 
		BASE64Encoder encoder = new BASE64Encoder(); // 对字节数组Base64编码
		base = encoder.encode(outputStream.toByteArray()); // 返回Base64编码过的字节数组字符串
		Response res = new Response();
		Map<String, String> maps = new HashMap<>();
		maps.put("baseKey", numKey); // 验证码key值
		maps.put("base", base);
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setObject(maps); // BASE64Encoder
		res.setMessage(localeMessageSourceService.getMessage("success"));
		return res;
	}
	
	/**
	 * 携带请求合法的唯一标识(备用)
	 * @param request
	 * @return
	 */
	@RequestMapping("/imagesec")
	public Response getVerificationCode1(HttpServletRequest request) {
		String requestSessionId = request.getSession().getId();// 获取session中的sessionId作为请求合法唯一性的标识
		ByteArrayOutputStream outputStream = null;
		Map<String, Object> map = new HashMap<>();
		map = MainUtilityTools.getVerificationCode();
		BufferedImage bi = (BufferedImage) map.get("image");
		String num = (String) map.get("num");
		StringBuffer sb = new StringBuffer();
		sb.append(num).append(",").append(requestSessionId); // 将验证码与请求携带的sessionId编辑成字符串存入缓存
		String numKey = MainUtilityTools.getToken(); // 获取随机数作为key
		String base = "";
		try {
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", outputStream);
			redisService.setRedis(numKey, sb.toString());
		}catch (IOException e) {
			MainUtilityTools.catchException(e, this.getClass(), "");
			Response res = new Response();
			res.setStatus(ResponseStatusCode.EXCEPTION.getCode());
			res.setMessage(localeMessageSourceService.getMessage("no.result"));
			return res;
		} 
		BASE64Encoder encoder = new BASE64Encoder(); // 对字节数组Base64编码
		base = encoder.encode(outputStream.toByteArray()); // 返回Base64编码过的字节数组字符串
		Response res = new Response();
		Map<String, String> maps = new HashMap<>();
		maps.put("baseKey", numKey); // 验证码key值
		maps.put("base", base);
		maps.put("requestSessionId", requestSessionId);
		res.setStatus(ResponseStatusCode.SUCCESS.getCode());
		res.setObject(maps); // BASE64Encoder
		res.setMessage(localeMessageSourceService.getMessage("success"));
		return res;
	}
}
