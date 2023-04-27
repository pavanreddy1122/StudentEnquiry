package com.Web.StudentEnquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.StudentEnquiry.Entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity,Integer> {

}
