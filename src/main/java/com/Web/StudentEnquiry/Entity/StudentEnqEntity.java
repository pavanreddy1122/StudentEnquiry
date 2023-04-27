package com.Web.StudentEnquiry.Entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Table(name="AIT_STUDENT_ENQURIES")
@Data
public class StudentEnqEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiryId;
	private String studentName;
	private Integer phoNumber;
	private String classMode;
	private String courseName;
	private String enquiryStatus;
	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="userId")
	private UserDtlsEntity user;
	
	
	
}
