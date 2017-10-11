package com.pmpt.entities;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: MyTrack
 * @Description: 我的足迹记录表(MongoDB)
 * @Author: 汪洋
 * @Date: 2017年9月11日 下午4:31:52
 */
public class MyTrack {

	private String id;

	private Integer billId;

	private Integer loginAcId;

	/**
	 * @param id
	 * @param billId
	 * @param loginAcId
	 */
	public MyTrack(String id, Integer billId, Integer loginAcId) {
		super();
		this.id = id;
		this.billId = billId;
		this.loginAcId = loginAcId;
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@NotNull
	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	@NotNull
	public Integer getLoginAcId() {
		return loginAcId;
	}

	public void setLoginAcId(Integer loginAcId) {
		this.loginAcId = loginAcId;
	}

}
