package com.Web.StudentEnquiry.Binding;

import org.springframework.stereotype.Component;

@Component
public class EnquirySearchCriteria {
	private String classMode;
	private String courseName;
	private String enquiryStatus;
	public String getClassMode() {
		return classMode;
	}
	public void setClassMode(String classMode) {
		this.classMode = classMode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getEnquiryStatus() {
		return enquiryStatus;
	}
	public void setEnquiryStatus(String enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}
	public EnquirySearchCriteria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
