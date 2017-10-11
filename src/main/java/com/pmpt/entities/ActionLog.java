/**
 * 
 */
package com.pmpt.entities;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author jitl
 *
 */
@Document
public class ActionLog {

	@Id
	private String id;
	@NotNull
	private String actionLogClass;
	@NotNull
	private String actionLogCode;
	@NotNull
	private String ip;
	@NotNull
	private Date date;
	@NotNull
	private String dateStr;
	@NotNull
	private String actor;
	@NotNull
	private String describe;
	@NotNull
	private String interfaceCode;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActionLogClass() {
		return actionLogClass;
	}
	public void setActionLogClass(String actionLogClass) {
		this.actionLogClass = actionLogClass;
	}
	public String getActionLogCode() {
		return actionLogCode;
	}
	public void setActionLogCode(String actionLogCode) {
		this.actionLogCode = actionLogCode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getInterfaceCode() {
		return interfaceCode;
	}
	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}
	
	
}
