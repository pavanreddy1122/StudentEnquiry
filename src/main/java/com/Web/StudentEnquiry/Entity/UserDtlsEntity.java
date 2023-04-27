package com.Web.StudentEnquiry.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="AIT_USER_DTLS")
public class UserDtlsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String email;
	private Long phonumber;
	private String password;
	private String accStatus;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private List<StudentEnqEntity> student;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	
	public List<StudentEnqEntity> getStudent() {
		return student;
	}
	public void setStudent(List<StudentEnqEntity> student) {
		this.student = student;
	}
	public UserDtlsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
