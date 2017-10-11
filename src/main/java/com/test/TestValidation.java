package com.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;

import com.pmpt.common.MainUtilityTools;

import sun.misc.BASE64Decoder;


public class TestValidation {

	// UUID
	@org.junit.Test
	public void getUUID() {
		for (int i = 0; i < 10; i++) {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			System.out.println(uuid);
		}
		String uuid2 = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println("uuid:" + uuid2);
	}
	
	// 身份证号码格式验证
	@org.junit.Test
	public void testID() {
		String str = "411520097010183391";
		if (MainUtilityTools.verifyIDNumber(str)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
	
	@org.junit.Test
	public void testImage() {
		String [] args = {"1","2","3"};
		String b= Arrays.toString(args);
		String a = args.toString();
		System.out.println("b" + b);
		System.out.println("a:" + a);
		
	}
	
	/*
	 * base64字符串变图片
	 */
	@org.junit.Test
	public void decodeBase64ToImage() {
		String base64 = "";
		String path = "E:\\base64\\";
		String imgName = "base.jpg";
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			FileOutputStream write = new FileOutputStream(new File(path) + imgName);
			byte[] decoderBytes = decoder.decodeBuffer(base64);
			write.write(decoderBytes);
			write.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
