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
 * @ClassName: Continent.java
 * @Description: 洲（产地）表
 * @author jianghb
 * @date 2017年9月27日下午2:45:55
 */
@Entity
public class Continent implements Serializable {

	private static final long serialVersionUID = 2966418737997263080L;

	// id
	private Integer id;
	
	// 洲编号
	private Integer code;
	
	// 洲名称
	private String continentName;
	
	// 国家
	private List<Country> country;

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
	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "continentId")
	public List<Country> getCountry() {
		return country;
	}

	public void setCountry(List<Country> country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((continentName == null) ? 0 : continentName.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Continent other = (Continent) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (continentName == null) {
			if (other.continentName != null)
				return false;
		} else if (!continentName.equals(other.continentName))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
