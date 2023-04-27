package com.Web.StudentEnquiry.Runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.Web.StudentEnquiry.Entity.CourseEntity;
import com.Web.StudentEnquiry.Entity.EnqStatusEntity;
import com.Web.StudentEnquiry.Repository.CourseRepo;
import com.Web.StudentEnquiry.Repository.EnqStatusRepo;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	private CourseRepo courseRepo;
	@Autowired
	private EnqStatusRepo enqStatusRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		courseRepo.deleteAll();
		enqStatusRepo.deleteAll();
		CourseEntity c1=new CourseEntity();
		c1.setCourseName("Java");
		CourseEntity c2=new CourseEntity();
		c2.setCourseName("Python");
		
		CourseEntity c3=new CourseEntity();
		c3.setCourseName("c#");
		
		CourseEntity c4=new CourseEntity();
		c4.setCourseName("Dev Ops");
		
		List<CourseEntity> list=Arrays.asList(c1,c2,c3,c4);
		courseRepo.saveAll(list);
		
		EnqStatusEntity e1=new EnqStatusEntity();
		e1.setStatusName("New");
		EnqStatusEntity e2=new EnqStatusEntity();
		e2.setStatusName("Enrolled");
		EnqStatusEntity e3=new EnqStatusEntity();
		e3.setStatusName("Lost");
		
		List<EnqStatusEntity> list1=Arrays.asList(e1,e2,e3);
		enqStatusRepo.saveAll(list1);
		
		
		
	}
	

}
