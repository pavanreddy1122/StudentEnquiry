package com.Web.StudentEnquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.StudentEnquiry.Entity.EnqStatusEntity;

public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity,Integer>{

}
