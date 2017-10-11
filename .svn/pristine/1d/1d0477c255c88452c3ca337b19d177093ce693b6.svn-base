/**
 * 
 */
package com.pmpt.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.pmpt.entities.enums.Sex;
import com.pmpt.entities.enums.Valid;


/**
 * @ClassName: Person.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月30日上午11:20:19
 */
@Entity
public class Person implements Serializable{
	
	private static final long serialVersionUID = 7019981449846373055L;
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private String name; // 用户名
	@Column
	private String englishName; // 英文名
	@Column(unique = true, nullable = false)
	private String loginName; // 登录名
	@Column(nullable = false)
	private String phone; // 手机号
	@Column(nullable = false)
	private String address; // 家庭住址
	@Column(nullable = false)
	private String sex = Sex.MAN.getKey(); // 性别 m-男，w-女
	@Column
	private String age; // 年龄
	@Column(nullable = false)
	private Date entryDate; // 入职日期
	@Column
	private Date quitDate; // 离职日期
	@Column(nullable = false)
	private String code; // 员工编号
	@Column
	private String qq; // QQ联系方式
	@Column(nullable = false)
	private String  showNumber; // 排序
	@Column(nullable = false)
	private String valid = Valid.ISVALID.getKey(); // 是否有效
	@Column(nullable = false)
	private String idNumber; // 身份证号
	@ManyToMany
	private List<Module> listModule; // 权限
	@Column(nullable = false)
	private String email; // 邮箱
	@Column
	private String position; // 岗位
	@Column
	private String department; // 部门
	@Column
	private String remark; // 备注
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getShowNumber() {
		return showNumber;
	}
	public void setShowNumber(String showNumber) {
		this.showNumber = showNumber;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public List<Module> getListModule() {
		return listModule;
	}
	public void setListModule(List<Module> listModule) {
		this.listModule = listModule;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", englishName=" + englishName + ", loginName=" + loginName
				+ ", phone=" + phone + ", address=" + address + ", sex=" + sex + ", age=" + age + ", entryDate="
				+ entryDate + ", quitDate=" + quitDate + ", code=" + code + ", qq=" + qq + ", showNumber=" + showNumber
				+ ", valid=" + valid + ", idNumber=" + idNumber + ", listModule=" + listModule + ", email=" + email
				+ ", position=" + position + ", department=" + department + ", remark=" + remark + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((englishName == null) ? 0 : englishName.hashCode());
		result = prime * result + ((entryDate == null) ? 0 : entryDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
		result = prime * result + ((listModule == null) ? 0 : listModule.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((qq == null) ? 0 : qq.hashCode());
		result = prime * result + ((quitDate == null) ? 0 : quitDate.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((showNumber == null) ? 0 : showNumber.hashCode());
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
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (englishName == null) {
			if (other.englishName != null)
				return false;
		} else if (!englishName.equals(other.englishName))
			return false;
		if (entryDate == null) {
			if (other.entryDate != null)
				return false;
		} else if (!entryDate.equals(other.entryDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idNumber == null) {
			if (other.idNumber != null)
				return false;
		} else if (!idNumber.equals(other.idNumber))
			return false;
		if (listModule == null) {
			if (other.listModule != null)
				return false;
		} else if (!listModule.equals(other.listModule))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (qq == null) {
			if (other.qq != null)
				return false;
		} else if (!qq.equals(other.qq))
			return false;
		if (quitDate == null) {
			if (other.quitDate != null)
				return false;
		} else if (!quitDate.equals(other.quitDate))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (showNumber == null) {
			if (other.showNumber != null)
				return false;
		} else if (!showNumber.equals(other.showNumber))
			return false;
		if (valid == null) {
			if (other.valid != null)
				return false;
		} else if (!valid.equals(other.valid))
			return false;
		return true;
	}
}
