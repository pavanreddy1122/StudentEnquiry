package com.Web.StudentEnquiry.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Web.StudentEnquiry.Binding.DashboardResponse;
import com.Web.StudentEnquiry.Binding.EnquiryForm;
import com.Web.StudentEnquiry.Binding.EnquirySearchCriteria;
import com.Web.StudentEnquiry.Entity.StudentEnqEntity;
import com.Web.StudentEnquiry.Service.EnquiryService;
import com.Web.StudentEnquiry.constants.AppConstants;

@Controller
public class EnquiryController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EnquiryService enquiryService;
	
	
	@GetMapping("/logout")
	public String logout()
	{
		session.invalidate();
		return AppConstants.INDEX;
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model)
	{
		Integer id=(Integer) session.getAttribute(AppConstants.USER_ID);
		DashboardResponse response=enquiryService.getDashboardData(id);
		model.addAttribute(AppConstants.DASHBOARD,response);
		
		return AppConstants.DASHBOARD;
	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model)
	{
		initForm(model);
		return AppConstants.ADD_ENQUIRY;
	}
	
	@PostMapping("/enquiry")
	public String addEnquiry(@ModelAttribute(AppConstants.FORM)EnquiryForm form, Model model)
	{
		boolean status=false;
		if(session.getAttribute(AppConstants.ENQ_ID)!=null)
		{
			Integer enqId=(int)session.getAttribute(AppConstants.ENQ_ID);
			status=enquiryService.update(enqId,form);
			session.removeAttribute(AppConstants.ENQ_ID);
		}
		else
		{
			status=enquiryService.saveEnquiry(form);
		}
		
		if(status)
		{
			model.addAttribute(AppConstants.SUCCESS,"Enquiry added");
		}
		else
		{
			model.addAttribute(AppConstants.ERROR,"Problem occured");
		}
		return AppConstants.ADD_ENQUIRY;
	}
	
	@GetMapping("/enquires")
	public String viewEnquiresPage(Model model)
	{
		initForm(model);
		List<StudentEnqEntity> stu=enquiryService.getEnquries();
		model.addAttribute(AppConstants.ENQUIRIES, stu);
		return AppConstants.VIEW_ENQUIRIES;
	}
	

	@GetMapping("/filtered-enquires")
	public String getFilteredEnq(@RequestParam String cname, @RequestParam String status,@RequestParam String mode,Model model)
	{
		
		EnquirySearchCriteria enq=new EnquirySearchCriteria();
		enq.setClassMode(mode);
		enq.setCourseName(cname);
		enq.setEnquiryStatus(status);
		System.out.println(enq);
		Integer id=(Integer) session.getAttribute(AppConstants.USER_ID);
		List<StudentEnqEntity> stu=enquiryService.getFilteredEnqs(enq, id);
		model.addAttribute(AppConstants.ENQUIRIES, stu);
		return AppConstants.FILTER_ENQUIRIES;
	}
	
	private void initForm(Model model)
	{
		List<String> courses=enquiryService.getCourseName();
		List<String> enqStatus=enquiryService.getEnquryStatus();
		EnquiryForm form=new EnquiryForm();
		model.addAttribute(AppConstants.FORM,form );
		model.addAttribute(AppConstants.COURSES,courses);
		model.addAttribute(AppConstants.STATUS,enqStatus);
	}
	
	@GetMapping("/edit")
	public String editEnquiresPage(@RequestParam("enquiryId") Integer enquiryId,  Model model)
	{
		StudentEnqEntity stu=enquiryService.getEnq(enquiryId);
		if(stu!=null)
		{
			List<String> courseNames =enquiryService.getCourseName();
			//List<String> courseNames =enquiryService.getCourseName();
			//get enq status for drop down
			List<String> enqStatus = enquiryService.getEnquryStatus();
			//create binding class object
			EnquiryForm form=new EnquiryForm();
			
			BeanUtils.copyProperties(stu, form);
			
			//set data in model object
			model.addAttribute(AppConstants.COURSES,courseNames );
			model.addAttribute(AppConstants.STATUS,enqStatus );
			model.addAttribute(AppConstants.FORM,form );
			session.setAttribute(AppConstants.ENQ_ID,stu.getEnquiryId());
			
		}
		return AppConstants.ADD_ENQUIRY;
	}
	
}
