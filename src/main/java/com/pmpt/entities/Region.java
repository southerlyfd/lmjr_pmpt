/**
 * 
 */
package com.pmpt.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @ClassName: Region.java
 * @Description: 地域表
 * @author jianghb
 * @date 2017年10月13日上午9:47:51
 */
@Entity
public class Region implements Serializable{
	
	private static final long serialVersionUID = -4544543037152385216L;

	// 主键
	private Integer id;
	
	// 编号
	private Integer code;
	
	// (洲、国家)名称
	private String regionName;
	
	// 性质（1-洲，2-国家）
	private Integer regionType;
	
	// 上一级
	private Region grandRegion;
	
	// 国家标志性建筑图片
	private String loginBuildPic;
	
	// 该国家所产的品名
	private List<ProductName> productName;

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
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Column(nullable = false)
	public Integer getRegionType() {
		return regionType;
	}

	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}

	@ManyToOne
	public Region getGrandRegion() {
		return grandRegion;
	}

	public void setGrandRegion(Region grandRegion) {
		this.grandRegion = grandRegion;
	}

	@Column(nullable = false)
	public String getLoginBuildPic() {
		return loginBuildPic;
	}

	public void setLoginBuildPic(String loginBuildPic) {
		this.loginBuildPic = loginBuildPic;
	}

	@OneToMany
	public List<ProductName> getProductName() {
		return productName;
	}

	public void setProductName(List<ProductName> productName) {
		this.productName = productName;
	}
	
}
