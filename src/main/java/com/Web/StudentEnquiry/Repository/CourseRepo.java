package com.Web.StudentEnquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.StudentEnquiry.Entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer> {

}
