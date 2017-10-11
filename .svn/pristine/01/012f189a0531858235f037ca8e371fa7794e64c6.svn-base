/**
 * 
 */
package com.pmpt.entities;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @ClassName: Like.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年9月11日下午3:49:25
 */
@Document
public class Interest {
	@Id
	private String id;
	@NotNull
	private String loginAccoutId; // 账户id
	@NotNull
	private String commodity; // 品名
	@NotNull
	private String code; // 编号

	/**
	 * 
	 */
	public Interest() {
		super();
	}

	/**
	 * @param loginAccoutId
	 */
	public Interest(String loginAccoutId) {
		super();
		this.loginAccoutId = loginAccoutId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginAccoutId() {
		return loginAccoutId;
	}

	public void setLoginAccoutId(String loginAccoutId) {
		this.loginAccoutId = loginAccoutId;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
