/**
 * 
 */
package com.pmpt.entities.enums;

/**
 * @ClassName: Sex.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月31日上午9:44:51
 */
public enum Sex {
	
	MAN("m", "男"),
	WOMAN("w","女");
	
	private String key;
	private String des;
	
	Sex(String key, String des){
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
