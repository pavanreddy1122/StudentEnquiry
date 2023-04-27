package com.Web.StudentEnquiry.Binding;

import org.springframework.stereotype.Component;

@Component
public class DashboardResponse {
	
	private Integer totalEnquires;
	private Integer enrolled;
	private Integer lost;
	public Integer getTotalEnquires() {
		return totalEnquires;
	}
	public void setTotalEnquires(Integer totalEnquires) {
		this.totalEnquires = totalEnquires;
	}
	public Integer getEnrolled() {
		return enrolled;
	}
	public void setEnrolled(Integer enrolled) {
		this.enrolled = enrolled;
	}
	public Integer getLost() {
		return lost;
	}
	public void setLost(Integer lost) {
		this.lost = lost;
	}
	public DashboardResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
