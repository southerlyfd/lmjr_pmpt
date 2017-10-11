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
 * @ClassName: Region.java
 * @Description: 国家(产地)表
 * @author jianghb
 * @date 2017年9月27日下午1:52:22
 */
@Entity
public class Country implements Serializable {
	
	private static final long serialVersionUID = 3913636970927551825L;

	// ID
	private Integer id;
	
	// 国家编号
	private Integer code;
	
	// 国家名称
	private String name;
	
	// 国家标志建筑图片
	private String LogoBuildPic;
	
	// 该国家所产的品名
	private List<Interests> interests;

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
	public String getLogoBuildPic() {
		return LogoBuildPic;
	}

	public void setLogoBuildPic(String logoBuildPic) {
		LogoBuildPic = logoBuildPic;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "countryId")
	public List<Interests> getInterests() {
		return interests;
	}

	public void setInterests(List<Interests> interests) {
		this.interests = interests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((LogoBuildPic == null) ? 0 : LogoBuildPic.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((interests == null) ? 0 : interests.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Country other = (Country) obj;
		if (LogoBuildPic == null) {
			if (other.LogoBuildPic != null)
				return false;
		} else if (!LogoBuildPic.equals(other.LogoBuildPic))
			return false;
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
		if (interests == null) {
			if (other.interests != null)
				return false;
		} else if (!interests.equals(other.interests))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
