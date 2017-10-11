package com.pmpt.common;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	// 字符串MD5加密
	public static String setMD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算MD5函数
			md.update(str.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			return null;
		}
	}

	// 加密后的字符串是否相同
	public static boolean checkLoginName(String newLoginName, String oldLoginName) {
		if (setMD5(newLoginName).equals(oldLoginName)) {
			return true;
		} else {
			return false;
		}
	}
}
