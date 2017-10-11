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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pmpt.entities.enums.Sex;
import com.pmpt.entities.enums.Valid;


/**
 * @ClassName: Person.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月30日上午11:20:19
 */
@Entity
@Table(name="Person")
@NamedQueries({
	@NamedQuery(name="PersonAvatar.signIn", 
			query="select bean.loginName,bean2,bean.loginSession.id from PersonAvatar as bean left join bean.listModule bean2 where bean.loginName=:loginName and bean.pwd=:pwd"),
	@NamedQuery(name="PersonAvatar.updatePersonSession",
			query="UPDATE PersonAvatar as bean SET bean.loginSession=:loginSession WHERE bean.loginName=:loginName"),
	@NamedQuery(name="PersonAvatar.updatePwd",
			query="UPDATE PersonAvatar as bean SET bean.pwd=:newPwd WHERE bean.loginName=:loginName")
})
public class PersonAvatar implements Serializable{
	
	private static final long serialVersionUID = -6514466410546715531L;
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
	private String pwd; // 密码(+)
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
	@OneToOne
	private LoginSession loginSession; // 缓存信息（+）
	
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public LoginSession getLoginSession() {
		return loginSession;
	}
	public void setLoginSession(LoginSession loginSession) {
		this.loginSession = loginSession;
	}
	
}
