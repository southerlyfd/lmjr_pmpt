package com.pmpt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName: Transport
 * @Description: 货车送货信息
 * @Author: 汪洋
 * @Date: 2017年8月30日 下午4:55:17
 */
@Entity
public class Transport implements Serializable {

	private static final long serialVersionUID = 3795581149335578325L;

	// 主键
	private Integer id;

	// 司机姓名
	private String tName;

	// 司机电话
	private String tPhone;

	// 司机身份证号
	private String idNo;

	// 货车车牌号
	private String plateNum;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	@Column
	public String gettPhone() {
		return tPhone;
	}

	public void settPhone(String tPhone) {
		this.tPhone = tPhone;
	}

	@Column
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column
	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idNo == null) ? 0 : idNo.hashCode());
		result = prime * result + ((plateNum == null) ? 0 : plateNum.hashCode());
		result = prime * result + ((tName == null) ? 0 : tName.hashCode());
		result = prime * result + ((tPhone == null) ? 0 : tPhone.hashCode());
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
		Transport other = (Transport) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idNo == null) {
			if (other.idNo != null)
				return false;
		} else if (!idNo.equals(other.idNo))
			return false;
		if (plateNum == null) {
			if (other.plateNum != null)
				return false;
		} else if (!plateNum.equals(other.plateNum))
			return false;
		if (tName == null) {
			if (other.tName != null)
				return false;
		} else if (!tName.equals(other.tName))
			return false;
		if (tPhone == null) {
			if (other.tPhone != null)
				return false;
		} else if (!tPhone.equals(other.tPhone))
			return false;
		return true;
	}

}
