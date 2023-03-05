package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="freelancers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Freelancer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int freelancerId;
	@Column(length = 20)
	private String frlName;
	@Column(nullable = false)
	private String frlProfession;
	private String frlAddress;
	private String frlContact;
	@Column(unique = true)
	private String frlEmail;
	@Column(nullable = false)
	private String frlPassword;
	@Column(nullable = false)
	private String frlCity;
	private double frlExperience;
	private double frlRatePerHr;
	private String status;
	private double totalAmount;

}
