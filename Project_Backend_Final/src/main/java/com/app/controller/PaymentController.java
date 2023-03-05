package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.AdminRepository;
import com.app.dao.BookingRepository;
import com.app.dao.FreelancerRepository;
import com.app.dao.PaymentRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Admin;
import com.app.pojos.Bookings;
import com.app.pojos.Freelancer;
import com.app.pojos.PaymentDetails;
import com.app.pojos.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FreelancerRepository freelancerRepository;
	
	
	@Autowired
	private AdminRepository  adminRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private BookingRepository bookinRepository;
	
	@PostMapping("/savePaymentDetails")
	public String SavePaymentDetails(@RequestBody PaymentDetails paymentDetails) {
		
		System.out.println("SavePaymentDetails  user  "+paymentDetails.getUser());
		System.out.println("SavePaymentDetails  freelancer  "+paymentDetails.getFreelancer());
		 
		User user = userRepository.findByUserId(paymentDetails.getUser().getUserId()); 
		user.setPaymentStatus("done");
		Freelancer freelancer = freelancerRepository.findByFreelancerId(paymentDetails.getFreelancer().getFreelancerId());
		  freelancer.setTotalAmount(freelancer.getTotalAmount()+paymentDetails.getFreelancerAmount());
		  Admin admin = adminRepository.findByAdminId(1);
		  admin.setTotalAmount(admin.getTotalAmount()+paymentDetails.getAdminAmount());
		  userRepository.save(user);
		  adminRepository.save(admin);
		  freelancerRepository.save(freelancer);
		  Bookings bookings = bookinRepository.findByBookingId(paymentDetails.getBookings().getBookingId());
		  
		  bookings.setPaymentStatus("done");
		  bookinRepository.save(bookings);
		paymentRepository.save(paymentDetails);
		
		return "Payment Done successfully";
		
	}
	

}
