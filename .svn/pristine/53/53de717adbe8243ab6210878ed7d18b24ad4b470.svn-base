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
 * @author jitl
 *
 */
@Entity
public class Enterprice implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 2827823000327050077L;

	// 主键
	private Integer id;

	// 
	private String iconURL;


	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(nullable = true)
	public String getIconURL() {
		return iconURL;
	}


	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.id != null ? this.id.hashCode() : super
				.hashCode());
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Enterprice)) {
			return false;
		}
		Enterprice other = (Enterprice) object;
		if (this.id != other.id
				&& (this.id == null || !this.id.equals(other.id)))
			return false;
		return true;
	}

	@Override
	public String toString() {

		return id + "";
	}

}
