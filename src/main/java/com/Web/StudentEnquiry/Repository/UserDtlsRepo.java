package com.Web.StudentEnquiry.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Web.StudentEnquiry.Entity.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity,Integer>{
	
	public UserDtlsEntity findByEmail(String email);
	public UserDtlsEntity findByEmailAndPassword(String email,String password);

}
