package com.Web.StudentEnquiry.Service;

import java.util.List;

import com.Web.StudentEnquiry.Binding.DashboardResponse;
import com.Web.StudentEnquiry.Binding.EnquiryForm;
import com.Web.StudentEnquiry.Binding.EnquirySearchCriteria;
import com.Web.StudentEnquiry.Entity.StudentEnqEntity;

public interface EnquiryService {

	public DashboardResponse getDashboardData(Integer userId);

	public List<String> getCourseName();

	public List<String> getEnquryStatus();
	
	public boolean saveEnquiry(EnquiryForm form);

	
	public List<StudentEnqEntity> getEnquries();
	
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria,Integer userId);

	
}
