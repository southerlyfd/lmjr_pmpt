/**
 * 
 */
package com.pmpt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName: Vulgo.java
 * @Description: 俗名表
 * @author jianghb
 * @date 2017年9月27日下午2:35:53
 */
@Entity
public class Vulgo implements Serializable {

	private static final long serialVersionUID = 725686255178854293L;

	// id
	private Integer id;
	
	// 编号
	private Integer code;
	
	// 俗名
	private String vulgoName;
	
	// 俗称照片url
	private String vulgoUrl;

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
	public String getVulgoName() {
		return vulgoName;
	}

	public void setVulgoName(String vulgoName) {
		this.vulgoName = vulgoName;
	}

	@Column(nullable = false)
	public String getVulgoUrl() {
		return vulgoUrl;
	}

	public void setVulgoUrl(String vulgoUrl) {
		this.vulgoUrl = vulgoUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((vulgoName == null) ? 0 : vulgoName.hashCode());
		result = prime * result + ((vulgoUrl == null) ? 0 : vulgoUrl.hashCode());
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
		Vulgo other = (Vulgo) obj;
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
		if (vulgoName == null) {
			if (other.vulgoName != null)
				return false;
		} else if (!vulgoName.equals(other.vulgoName))
			return false;
		if (vulgoUrl == null) {
			if (other.vulgoUrl != null)
				return false;
		} else if (!vulgoUrl.equals(other.vulgoUrl))
			return false;
		return true;
	}
	
}
