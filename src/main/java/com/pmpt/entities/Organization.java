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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * @author jitl
 *
 */
@Entity
public class Organization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3945122732960680107L;
	private Integer id;
	private String name;
	private Organization parentOrg;
	private List<Organization> subBeans;
	
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	@Column(nullable = false)
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the subBeans
	 */
	@OneToMany(cascade = CascadeType.REFRESH)
	public List<Organization> getSubBeans() {
		return subBeans;
	}
	/**
	 * @param subBeans the subBeans to set
	 */
	public void setSubBeans(List<Organization> subBeans) {
		this.subBeans = subBeans;
	}
	/**
	 * @return the parentOrg
	 */
	@OneToOne(optional = true, cascade = CascadeType.REFRESH)
	public Organization getParentOrg() {
		return parentOrg;
	}
	/**
	 * @param parentOrg the parentOrg to set
	 */
	public void setParentOrg(Organization parentOrg) {
		this.parentOrg = parentOrg;
	}

}
