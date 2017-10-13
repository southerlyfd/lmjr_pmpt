/**
 * 
 */
package com.pmpt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @ClassName: ProductName.java
 * @Description: 产品名称表
 * @author jianghb
 * @date 2017年10月13日上午10:43:33
 */
@Entity
public class ProductName implements Serializable{

	private static final long serialVersionUID = -2881298249972298714L;

	// id
	private Integer id;
	
	// 编号
	private Integer code;
	
	// 品名或俗名
	private String name;
	
	// （品名或俗名）图片url
	private String productNameUrl;
	
	// 性质（1-品名，2-俗名）
	private Integer type;
	
	// 上一级
	private ProductName productName;
	
	// 产地（国家）
	private Region region;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getProductNameUrl() {
		return productNameUrl;
	}

	public void setProductNameUrl(String productNameUrl) {
		this.productNameUrl = productNameUrl;
	}

	@Column(nullable = false)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@ManyToOne
	public ProductName getProductName() {
		return productName;
	}

	public void setProductName(ProductName productName) {
		this.productName = productName;
	}

	@ManyToOne
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
}
