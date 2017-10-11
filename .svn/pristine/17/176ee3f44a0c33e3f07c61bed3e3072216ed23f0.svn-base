package com.pmpt.entities.enums;

/**
 * @ClassName: Status
 * @Description: 发布商品购买商品状态
 * @Author: 汪洋
 * @Date: 2017年8月17日 下午1:19:01
 */
public enum Status {

	/**
	 * 发布状态
	 */
	//肯定点过发布下一步按钮后的状态，不一定支付了，可编辑
	SUCCPUB("1", "待发布"),
	//卖家付款后到真正上架的这段时间的状态
	READYSELL("2","预售中"),
	SELLING("3", "热拍中"), 
	RDELIVERY("4","拍中待发货"), 
	DELIVERY("5", "已发货"),
	NODELIVERY("6", "拍中未发货"), 
	NOSELLING("7", "已下架"), 
	PASSIN("8","流拍"),

	/**
	 * 购买状态
	 */
	PAY("00", "拍中已付款"), 
	NOPAY("01", "拍中未付款"), 
	TAKEDELIVERY("02", "已付款已收货"),
	REFUNDING("03","未拍中待退款"),
	REFUNDED("04","未拍中已退款");
	
	private String key;

	private String des;

	Status(String key, String des) {
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
