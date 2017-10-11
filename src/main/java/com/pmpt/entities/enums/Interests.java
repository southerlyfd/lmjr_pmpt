/**
 * 
 */
package com.pmpt.entities.enums;

/**
 * @ClassName: Like.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月11日下午2:11:37
 */
public enum Interests {
	XYZT("01", "小叶紫檀", "image/interest/xyzt.jpeg"),
	FZHHL("02", "非洲黄花梨", "image/interest/fzhhl.jpg"),
	HHL("03", "黄花梨", "image/interest/hlm.jpg"),
	YNHHL("04", "越南黄花梨", "url"),
	DHSZ("05", "大红酸枝", "url"),
	HZ("06", "花枝", "url"),
	BSZ("07","白酸枝", "url"),
	MZHSZ("08","美洲红酸枝", "url"),
	FZHSZ("09","非洲红酸枝", "url"),
	FZDYZT("10","非洲大叶紫檀", "url");
	
	private String code;
	private String productName;
	private String interestUrl;
	
	Interests(String code, String productName, String interestUrl){
		this.code = code;
		this.productName = productName;
		this.interestUrl = interestUrl;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInterestUrl() {
		return interestUrl;
	}
	public void setInterestUrl(String interestUrl) {
		this.interestUrl = interestUrl;
	}
	
}
