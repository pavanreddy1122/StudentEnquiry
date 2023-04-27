package com.Web.StudentEnquiry.Binding;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EnquiryForm {
	private String studentName;
	private Integer phoNumber;
	private String classMode;
	private String courseName;
	private String enquiryStatus;
	

}
