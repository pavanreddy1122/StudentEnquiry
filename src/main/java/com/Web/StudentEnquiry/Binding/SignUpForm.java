package com.Web.StudentEnquiry.Binding;

import org.springframework.stereotype.Component;

@Component
public class SignUpForm {
	
	private String userName;
	private String email;
	private Long phonumber;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhonumber() {
		return phonumber;
	}
	public void setPhonumber(Long phonumber) {
		this.phonumber = phonumber;
	}
	public SignUpForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
