package com.pmpt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.pmpt.entities.enums.DefaultAddr;


/**
 * @ClassName: Address
 * @Description: 地址信息表
 * @author: 汪洋
 * @date: 2017年8月10日 下午4:44:57
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "updateAddr", query = "update Address addr set addr.aName=:name,addr.mobile=:mobile,addr.province=:province,addr.detail=:detail where addr.id=:id"),
		@NamedQuery(name = "delAddr", query = "delete from Address addr where addr.id=:id"),
		@NamedQuery(name = "findAddrById", query = "select addr from Address addr where addr.id=:id")

})
public class Address implements Serializable {

	private static final long serialVersionUID = 7809801803821204191L;

	// 主键
	private Integer id;

	// 用户姓名
	private String aName;

	// 手机号
	private String mobile;

	// 省市
	private String province;

	// 详细地址
	private String detail;

	// 是否为默认地址
	private String defaultAddr = DefaultAddr.NOTDEFAULTADDR.getKey();

	// 收发货详细地址
	private String detailAddr;

	// 买卖家留言
	private String levMsg;

	public Address() {
		super();
	}

	/**
	 * @param name
	 * @param mobile
	 * @param province
	 * @param detail
	 * @param defaultAddr
	 */
	public Address(String aName, String mobile, String province, String detail, String defaultAddr) {
		super();
		this.aName = aName;
		this.mobile = mobile;
		this.province = province;
		this.detail = detail;
		this.defaultAddr = defaultAddr;
	}

	/**
	 * @param id
	 * @param name
	 * @param mobile
	 * @param province
	 * @param address
	 * @param user
	 */
	public Address(Integer id, String aName, String mobile, String province, String detail) {
		super();
		this.id = id;
		this.aName = aName;
		this.mobile = mobile;
		this.province = province;
		this.detail = detail;
	}

	/**
	 * @param id
	 */
	public Address(Integer id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	@Column(nullable = false)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(nullable = false)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(nullable = false)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(nullable = false)
	public String getDefaultAddr() {
		return defaultAddr;
	}

	public void setDefaultAddr(String defaultAddr) {
		this.defaultAddr = defaultAddr;
	}

	@Column
	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	@Column
	public String getLevMsg() {
		return levMsg;
	}

	public void setLevMsg(String levMsg) {
		this.levMsg = levMsg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aName == null) ? 0 : aName.hashCode());
		result = prime * result + ((defaultAddr == null) ? 0 : defaultAddr.hashCode());
		result = prime * result + ((detail == null) ? 0 : detail.hashCode());
		result = prime * result + ((detailAddr == null) ? 0 : detailAddr.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((levMsg == null) ? 0 : levMsg.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((province == null) ? 0 : province.hashCode());
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
		Address other = (Address) obj;
		if (aName == null) {
			if (other.aName != null)
				return false;
		} else if (!aName.equals(other.aName))
			return false;
		if (defaultAddr == null) {
			if (other.defaultAddr != null)
				return false;
		} else if (!defaultAddr.equals(other.defaultAddr))
			return false;
		if (detail == null) {
			if (other.detail != null)
				return false;
		} else if (!detail.equals(other.detail))
			return false;
		if (detailAddr == null) {
			if (other.detailAddr != null)
				return false;
		} else if (!detailAddr.equals(other.detailAddr))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (levMsg == null) {
			if (other.levMsg != null)
				return false;
		} else if (!levMsg.equals(other.levMsg))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		return true;
	}

}
