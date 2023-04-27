package com.Web.StudentEnquiry.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="AIT_ENQURIRY_STATUS")
public class EnqStatusEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer statusId;
	private String statusName;
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public EnqStatusEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
