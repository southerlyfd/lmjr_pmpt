package com.pmpt.entities.enums;

/**
 * @ClassName: DefaultAddr
 * @Description: 是否默认地址
 * @author: 汪洋
 * @date: 2017年8月14日 下午7:02:20
 */
public enum DefaultAddr {

	NOTDEFAULTADDR("0", "非默认地址"), 
	ISDEFAULTADDR("1", "默认地址");

	private String key;

	private String des;

	DefaultAddr(String key, String des) {
		this.key = key;
		this.des = des;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
