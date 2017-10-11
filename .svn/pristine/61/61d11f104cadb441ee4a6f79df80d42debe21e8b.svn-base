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
import javax.persistence.NamedQuery;

/**
 * @ClassName: LoginSession.java
 * @Description: 类说明
 * @author jianghb
 * @date 2017年8月17日上午11:32:16
 */
@Entity
@NamedQueries({
	@NamedQuery(name="LoginSession.update",
			query="UPDATE LoginSession as bean SET bean.token=?1,bean.deviceType=?2 WHERE bean.id=?3"),
	@NamedQuery(name="LoginSession.findByToken",
			query="select bean.token from LoginSession as bean where bean.id=?1")
})
public class LoginSession implements Serializable {
	private static final long serialVersionUID = 3017475672072467492L;
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = true)
	private String token; // 用户缓存key值
	@Column
	private String deviceType; // 设备类型
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		LoginSession other = (LoginSession) obj;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
	
	
}
