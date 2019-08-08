package com.complaint.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "complaint")
public class Complaint implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Transient
    public static final String SEQUENCE_NAME = "complaint_sequence";

	@Id
	private long id;
	
	private String subject;
	
	private String comType;
	
	private String comDesc;
	
	private String department;
	
	private String status;
	
	private String userName;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getComDesc() {
		return comDesc;
	}

	public void setComDesc(String comDesc) {
		this.comDesc = comDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", userName=" + userName + ", subject=" + subject + ", comType=" + comType
				+ ", department=" + department + ", comDesc=" + comDesc + ", status=" + status + "]";
	}
	
}
