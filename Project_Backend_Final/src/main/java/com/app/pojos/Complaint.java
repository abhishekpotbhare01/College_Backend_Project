package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

 

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Complaint {
	@Id
	@GeneratedValue
	private int compId;
	private String complainer;
	@OneToOne
	private User user;
	@OneToOne
	private Freelancer freelancer;
	@Column(nullable = false)
	private String issue;
	private String discription;
	private String date;
	
	private String status;
	private String remark;
	
	
	
	
}
