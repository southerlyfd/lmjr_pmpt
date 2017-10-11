/**
 * 
 */
package com.pmpt.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import com.pmpt.entities.enums.Valid;


/**
 * @author jitl
 *
 */
@Entity
@Table(name = "User")
@NamedQueries({

})
public class User implements Serializable {

	private static final long serialVersionUID = -4383754263141550769L;

	// 主键
	private Integer id;

	// 认证后的姓名
	private String name;

	// 性别
	private String sex;// m-男人，w-女人

	// 电话
	private String tel;

	// 是否有效
	private String valid = Valid.NOVALID.getKey();

	// 身份证ID
	private String idNo;

	// 身份证正面photo
	private String frontPic;

	// 身份证反面photo
	private String backPic;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(nullable = false)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(nullable = false)
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Column
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column
	public String getFrontPic() {
		return frontPic;
	}

	public void setFrontPic(String frontPic) {
		this.frontPic = frontPic;
	}

	@Column
	public String getBackPic() {
		return backPic;
	}

	public void setBackPic(String backPic) {
		this.backPic = backPic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backPic == null) ? 0 : backPic.hashCode());
		result = prime * result + ((frontPic == null) ? 0 : frontPic.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idNo == null) ? 0 : idNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		User other = (User) obj;
		if (backPic == null) {
			if (other.backPic != null)
				return false;
		} else if (!backPic.equals(other.backPic))
			return false;
		if (frontPic == null) {
			if (other.frontPic != null)
				return false;
		} else if (!frontPic.equals(other.frontPic))
			return false;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (valid == null) {
			if (other.valid != null)
				return false;
		} else if (!valid.equals(other.valid))
			return false;
		return true;
	}

}
