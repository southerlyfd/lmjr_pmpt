package com.pmpt.common;

import java.util.Random;

public class DigitGenerator {
	 //创建Random对象
	 static Random random=new Random();
	 //随机生成包含验证码字符串
	 public static String generateCode(int num){
	  //初始化种子
	  String[] str={"0","1","2","3","4","5","6","7","8","9"};
	  int number=str.length;
	  //接收随机字符
	  String digit = "";
	  //随机产生4个字符的字符串
	  for(int i=0;i<num;i++){
		  digit+=str[random.nextInt(number)];
	  }
	  return digit;
	 }
}
