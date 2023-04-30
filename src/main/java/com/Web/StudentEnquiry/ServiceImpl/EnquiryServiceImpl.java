package com.Web.StudentEnquiry.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Web.StudentEnquiry.Binding.DashboardResponse;
import com.Web.StudentEnquiry.Binding.EnquiryForm;
import com.Web.StudentEnquiry.Binding.EnquirySearchCriteria;
import com.Web.StudentEnquiry.Entity.CourseEntity;
import com.Web.StudentEnquiry.Entity.EnqStatusEntity;
import com.Web.StudentEnquiry.Entity.StudentEnqEntity;
import com.Web.StudentEnquiry.Entity.UserDtlsEntity;
import com.Web.StudentEnquiry.Repository.CourseRepo;
import com.Web.StudentEnquiry.Repository.EnqStatusRepo;
import com.Web.StudentEnquiry.Repository.StudentEnqRepo;
import com.Web.StudentEnquiry.Repository.UserDtlsRepo;
import com.Web.StudentEnquiry.Service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService{
	
	@Autowired
	private UserDtlsRepo userDtlsRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private EnqStatusRepo enqStatusRepo;
	
	@Autowired
	private StudentEnqRepo studentEnqRepo;
	
	@Autowired
	private HttpSession session;
	

	@Override
	public DashboardResponse getDashboardData(Integer userId) {
		DashboardResponse dash=new DashboardResponse();
		Optional<UserDtlsEntity> findById=userDtlsRepo.findById(userId);
		if(findById.isPresent())
		{
			UserDtlsEntity user=findById.get();
			List<StudentEnqEntity> enq=user.getStudent();
			Integer total=enq.size();
			Integer enrolled=enq.stream().filter(e->e.getEnquiryStatus().equals("Enrolled")).collect(Collectors.toList()).size();
			Integer lost=enq.stream().filter(e->e.getEnquiryStatus().equals("Lost")).collect(Collectors.toList()).size();
			dash.setTotalEnquires(total);
			dash.setEnrolled(enrolled);
			dash.setLost(lost);
		}
		
		return dash;
	}
	
	@Override
	public List<String> getCourseName() {
		List<CourseEntity> findall=courseRepo.findAll();
		List<String> find=new ArrayList<>();
		for(CourseEntity course:findall)
		{
			find.add(course.getCourseName());
		}
		
		return find;
	}

	@Override
	public List<String> getEnquryStatus() {
		List<EnqStatusEntity> findall=enqStatusRepo.findAll();
		List<String> find=new ArrayList<>();
		for(EnqStatusEntity course:findall)
		{
			find.add(course.getStatusName());
		}
		
		return find;
	}

	


	@Override
	public boolean saveEnquiry(EnquiryForm form) {
		
		if(form!=null)
		{
		StudentEnqEntity entity=new StudentEnqEntity();
		BeanUtils.copyProperties(form, entity);
		UserDtlsEntity useEntity=userDtlsRepo.findById((Integer) session.getAttribute("userId")).get();
		entity.setUser(useEntity);
		studentEnqRepo.save(entity);
		return true;
		}else {
		return false;
		}
	}

	@Override
	public List<StudentEnqEntity> getEnquries() {
		 Integer id=(Integer) session.getAttribute("userId");
		 Optional<UserDtlsEntity> useEntity=userDtlsRepo.findById(id);
		 if(useEntity.isPresent())
		 {
			 UserDtlsEntity entity=useEntity.get();
			 List<StudentEnqEntity> stu=entity.getStudent();
			 return stu;
		 }
		 
		return null;
	}

	@Override
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {
		 Optional<UserDtlsEntity> useEntity=userDtlsRepo.findById(userId);
		 if(useEntity.isPresent())
		 {
			 UserDtlsEntity entity=useEntity.get();
			 List<StudentEnqEntity> stu=entity.getStudent();
			 
			 if(null!=criteria.getCourseName()&&!"".equals(criteria.getCourseName()))
			 {
				 stu=stu.stream().filter(e->e.getCourseName().equals(criteria.getCourseName())).collect(Collectors.toList());
			 }
			 if(null!=criteria.getEnquiryStatus()&&!"".equals(criteria.getEnquiryStatus()))
			 {
				 stu=stu.stream().filter(e->e.getEnquiryStatus().equals(criteria.getEnquiryStatus())).collect(Collectors.toList());
			 }
			 if(null!=criteria.getClassMode()&&!"".equals(criteria.getClassMode()))
			 {
				 stu=stu.stream().filter(e->e.getClassMode().equals(criteria.getClassMode())).collect(Collectors.toList());
			 }
			 return stu;
		 }
		return null;
	}

	

	@Override
	public StudentEnqEntity getEnq(int enqId) {
		
		
		return studentEnqRepo.findById(enqId).get();
	}

	@Override
	public boolean update(Integer enqId, EnquiryForm form) {
		StudentEnqEntity stu=getEnq(enqId);
		if(stu==null)
		{
		return false;
		}
		stu.setCourseName(form.getCourseName());
		stu.setClassMode(form.getClassMode());
		stu.setEnquiryStatus(form.getEnquiryStatus());
		stu.setPhoNumber(form.getPhoNumber());
		stu.setStudentName(form.getStudentName());
		studentEnqRepo.save(stu);
		return true;
		
		
		
	}

	@Override
	public String editEnquiry(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
