package com.Web.StudentEnquiry.Binding;

import org.springframework.stereotype.Component;

@Component
public class UnlockForm {

	private String email;
	private String temPassword;
	private String newPassword;
	private String confirmPassword;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTemPassword() {
		return temPassword;
	}
	public void setTemPassword(String temPassword) {
		this.temPassword = temPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public UnlockForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
