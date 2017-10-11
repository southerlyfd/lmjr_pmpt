/**
 * 
 */
package com.pmpt.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @ClassName: Interests.java
 * @Description: 品名表
 * @author jianghb
 * @date 2017年9月27日下午2:03:09
 */
@Entity
public class Interests implements Serializable {

	private static final long serialVersionUID = -2961462530312844415L;

	// id
	private Integer id;
	
	// 编号
	private Integer code;
	
	// 品名
	private String productName;
	
	// 品名图片
	private String interestUrl;
	
	// 俗名
	private List<Vulgo> vulgo;

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
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column
	public String getInterestUrl() {
		return interestUrl;
	}

	public void setInterestUrl(String interestUrl) {
		this.interestUrl = interestUrl;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "interestsId")
	public List<Vulgo> getVulgo() {
		return vulgo;
	}

	public void setVulgo(List<Vulgo> vulgo) {
		this.vulgo = vulgo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interestUrl == null) ? 0 : interestUrl.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((vulgo == null) ? 0 : vulgo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interests other = (Interests) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (interestUrl == null) {
			if (other.interestUrl != null)
				return false;
		} else if (!interestUrl.equals(other.interestUrl))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (vulgo == null) {
			if (other.vulgo != null)
				return false;
		} else if (!vulgo.equals(other.vulgo))
			return false;
		return true;
	}
	
}
