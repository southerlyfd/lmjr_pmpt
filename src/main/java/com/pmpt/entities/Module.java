/**
 * 
 */
package com.pmpt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.pmpt.entities.enums.Valid;


/**
 * @ClassName: Module.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月30日上午11:43:24
 */
@Entity
public class Module implements Serializable{
	
	private static final long serialVersionUID = 4892535325394926046L;
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private String parentModule; // 模块分类
	@Column(nullable = false)
	private String name; // 节点名称
	@Column(nullable = false)
	private String url; // 功能模块地址
	@Column(nullable = false)
	private String iconUrl; // 功能头标地址
	@Column
	private String remark; // 备注
	@Column(nullable = false)
	private String valid = Valid.ISVALID.getKey(); // 是否有效，1-有效，0-无效
	@Column(nullable = false)
	private String showNumber; // 常用功能排序
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParentModule() {
		return parentModule;
	}
	public void setParentModule(String parentModule) {
		this.parentModule = parentModule;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(String showNumber) {
		this.showNumber = showNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iconUrl == null) ? 0 : iconUrl.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentModule == null) ? 0 : parentModule.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((showNumber == null) ? 0 : showNumber.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((valid == null) ? 0 : valid.hashCode());
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
		Module other = (Module) obj;
		if (iconUrl == null) {
			if (other.iconUrl != null)
				return false;
		} else if (!iconUrl.equals(other.iconUrl))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentModule == null) {
			if (other.parentModule != null)
				return false;
		} else if (!parentModule.equals(other.parentModule))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (showNumber == null) {
			if (other.showNumber != null)
				return false;
		} else if (!showNumber.equals(other.showNumber))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (valid == null) {
			if (other.valid != null)
				return false;
		} else if (!valid.equals(other.valid))
			return false;
		return true;
	}
	
}
