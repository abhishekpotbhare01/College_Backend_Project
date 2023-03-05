package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payment_details")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentId;
	@OneToOne
	private User user;
	@OneToOne
	private Freelancer freelancer;
	private double freelancerAmount;
	private double adminAmount;
	@OneToOne
	private Bookings bookings;

}
