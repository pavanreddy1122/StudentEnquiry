package com.Web.StudentEnquiry.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Web.StudentEnquiry.Binding.DashboardResponse;
import com.Web.StudentEnquiry.Binding.EnquiryForm;
import com.Web.StudentEnquiry.Binding.EnquirySearchCriteria;
import com.Web.StudentEnquiry.Entity.StudentEnqEntity;
import com.Web.StudentEnquiry.Service.EnquiryService;

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
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model)
	{
		Integer id=(Integer) session.getAttribute("userId");
		DashboardResponse response=enquiryService.getDashboardData(id);
		model.addAttribute("dashboard",response);
		
		return "dashboard";
	}

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model)
	{
		List<String> course= enquiryService.getCourseName();
		List<String> enq= enquiryService.getEnquryStatus();
		EnquiryForm enqForm=new EnquiryForm();
		model.addAttribute("form",enqForm);
		model.addAttribute("course",course);
		model.addAttribute("enq", enq);
		return "add-enquiry";
	}
	
	@PostMapping("/enquiry")
	public String addEnquiry(@ModelAttribute("form")EnquiryForm form, Model model)
	{
		
		boolean status=enquiryService.saveEnquiry(form);
		if(status)
		{
			model.addAttribute("success","Enquiry added");
		}
		else
		{
			model.addAttribute("error","Problem occured");
		}
	return "add-enquiry";
	}
	
	@GetMapping("/enquires")
	public String viewEnquiresPage(Model model)
	{
		initForm(model);
		List<StudentEnqEntity> stu=enquiryService.getEnquries();
		model.addAttribute("enquiries", stu);
		return "view-enquiries";
	}
	
	@GetMapping("/filtered-enquires")
	public String getFilteredEnq(@RequestParam String cname, @RequestParam String status,@RequestParam String mode,Model model)
	{
		
		EnquirySearchCriteria enq=new EnquirySearchCriteria();
		enq.setClassMode(mode);
		enq.setCourseName(cname);
		enq.setEnquiryStatus(status);
		System.out.println(enq);
		Integer id=(Integer) session.getAttribute("userId");
		List<StudentEnqEntity> stu=enquiryService.getFilteredEnqs(enq, id);
		model.addAttribute("enquiries", stu);
		return "filter-enq";
	}
	
	private void initForm(Model model)
	{
		List<String> courses=enquiryService.getCourseName();
		List<String> enqStatus=enquiryService.getEnquryStatus();
		EnquiryForm form=new EnquiryForm();
		
		model.addAttribute("courses",courses);
		model.addAttribute("form", form);
		model.addAttribute("enqStatus",enqStatus);
	}
	
}
