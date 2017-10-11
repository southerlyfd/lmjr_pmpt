/**
 * 
 */
package com.pmpt.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.UUID;
import javax.naming.InitialContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jitl
 *
 */
public class MainUtilityTools {

	// logger
	public static Logger logger = LoggerFactory.getLogger(MainUtilityTools.class);

	@SuppressWarnings("unused")
	private static int errorCode;

	/**
	 * 捕获异常
	 * 
	 * @param e
	 * @param object
	 * @param eInfo
	 * @return
	 */
	public static String catchException(Exception e, Object object, String eInfo) {

		if (e != null) {
			System.out.println(e.toString());
			logger.error(e.toString(), e);
		} else {
			System.out.println(eInfo);
			logger.error(eInfo);
		}

		// return MainUtilityTools.getString("erp.fail");
		return "fail";
	}

	public static Logger getLogger() {
		return logger;
	}

	// 查找DAOBean ctx
	public static InitialContext ctx = null;

	@SuppressWarnings("deprecation")
	public static Object execute(Parameters parameters) {
		final String uriConnection = "http://web1.jinianen.com/erpWeb/" + parameters.getUrl();
		HttpPost httpRequest = new HttpPost(uriConnection);
		HttpResponse httpResponse = null;

		try {

			// UrlEncodedFormEntity serializableEntity = new
			// UrlEncodedFormEntity(parameters,true);
			httpRequest.setEntity(new UrlEncodedFormEntity(parameters.getList(), HTTP.UTF_8));

			// 创建HttpClientBuilder
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			// HttpClient
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
			// httpResponse = new DefaultHttpClient().execute(httpRequest);
			httpResponse = closeableHttpClient.execute(httpRequest);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		} catch (ClientProtocolException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		if (httpResponse == null) {
			errorCode = 3;
			return null;
		}
		int sCode = httpResponse.getStatusLine().getStatusCode();
		if (sCode == 200) {

			HttpEntity httpEntity = httpResponse.getEntity();
			String str = null;

			if (httpEntity != null) {
				try {
					str = EntityUtils.toString(httpEntity);
					if (str != null && str.length() > 0) {

						return str;
					} else {
						errorCode = 1;
						return null;
					}
				} catch (Exception e) {
					logger.error(e.toString());
				}
			} else {
				errorCode = 1;
				return null;
			}
		} else {
			errorCode = 2;
			return null;
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String execute2(String url, Map<String, String> map, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String getToken() {
		String token = "";
		token = UUID.randomUUID().toString().replaceAll("-", "");
		return token;
	}

	// 图片高度
	private static final int IMG_HEIGHT = 100;
	// 图片宽度
	private static final int IMG_WIDTH = 30;
	// 验证码长度
	private static final int CODE_LEN = 4;

	public static Map<String, Object> getVerificationCode() {
		Map<String, Object> map = new HashMap<>();
		// 用于绘制图片，设置图片的长度和图片类型（RGB）
		BufferedImage bi = new BufferedImage(IMG_HEIGHT, IMG_WIDTH, BufferedImage.TYPE_INT_RGB);
		// 获取绘图工具
		Graphics graphics = bi.getGraphics();
		graphics.setColor(new Color(0xDCDCDC));
		graphics.fillRect(0, 0, 100, 30);
		// 验证码中所使用的字符
		char[] codeChar = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjklmnpqrstuvwxyz2345689".toCharArray();
		String captcha = ""; // 存放生成的验证码
		Random random = new Random();
		for (int i = 0; i < CODE_LEN; i++) { // 循环将每个验证码字符绘制到图片上
			int index = random.nextInt(codeChar.length);
			// 随机生成验证码颜色
			graphics.setColor(new Color(random.nextInt(150), random.nextInt(200), random.nextInt(255)));
			// 将一个字符绘制到图片上，并制定位置（设置x，y坐标）
			graphics.drawString(codeChar[index] + "", (i * 20) + 15, 20);
			captcha += codeChar[index];
		}
		// num：验证码,image：验证码BufferedImage对象
		map.put("num", captcha);
		map.put("image", bi);
		return map;
	}

	// 密码校验
	public static boolean pwd(String pwd) {
		// 密码验证规则（由6-20个数字和字母组成，字母区分大小写）
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
		// 编译正则表达式
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pwd);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	// 手机号校验
	public static boolean phoneNum(String phone) {
		String regex = "^1[3|4|5|7|8][0-9]\\d{8}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phone);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	// 身份证号码校验
	public static boolean verifyIDNumber(String idNumber) {
		String regex = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(idNumber);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Autowired(required = false)
	public static void setRedisTemplate(RedisTemplate redisTemplate) {
		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setValueSerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		redisTemplate.setHashValueSerializer(stringSerializer);
		// this.redisTemplate = redisTemplate;
	}

	public static String dateToString() {
		Date time = new Date();
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = formatter.format(time);

		return ctime;
	}

	public static String dateToStringForFileURL() {
		Date time = new Date();
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ctime = formatter.format(time);

		return ctime;
	}

	/**
	 * @param 文件的inputStream
	 * @return 文件相对路径:保存正确; "":异常; "file size tai da la":文件太大.
	 */
	public static String saveFile(MultipartFile fileM) {
		InputStream inputStream = null;
		try {
			inputStream = fileM.getInputStream();
		} catch (IOException e) {
			MainUtilityTools.catchException(e, MainUtilityTools.class, "");
		}
		int size = (int) fileM.getSize();

		if (size > 1024 * 50000) {
			MainUtilityTools.catchException(null, MainUtilityTools.class, "file size tai da la");
			return "file size tai da la";
		}

		String fileDir2 = Constants.imgSrc;

		String dateToStringForFileURL = dateToStringForFileURL();

		fileDir2 = fileDir2 + dateToStringForFileURL;

		String fileName = UUID.randomUUID().toString();

		String fileLoc = fileDir2 + "/" + fileName;

		// return

		String fileURL = dateToStringForFileURL + "/" + fileName;

		File file = new File(fileDir2);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 文件大小限制 预读计算大小
		byte bTemp[] = new byte[(int) size];
		int len = 0;
		int temp = 0; // 所有读取的内容都使用temp接收
		try {
			while ((temp = inputStream.read()) != -1) { // 当没有读取完时，继续读取
				bTemp[len] = (byte) temp;
				len++;
			}
			inputStream.close();
		} catch (IOException e) {

			return "";
		}

		// long l = mFile.getSize();

		// 实际大小数组
		/*
		 * byte b[] = new byte[(int) size]; for (int i = 0; i < len; i++) { b[i] =
		 * bTemp[i]; }
		 */

		try {
			FileOutputStream out = new FileOutputStream(fileLoc);
			out.write((byte[]) bTemp);
			out.close();
			return fileURL;
		} catch (Exception e) {
			MainUtilityTools.catchException(e, MainUtilityTools.class, "");
			return "";
		}
	}
}
