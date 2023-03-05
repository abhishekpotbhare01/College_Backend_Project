package com.app.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.BookingRepository;
import com.app.dao.FreelancerRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Bookings;
import com.app.pojos.Freelancer;
import com.app.pojos.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FreelancerRepository freelancerRepository;
	
	@PostMapping("/AddBooking")
	public String addBooking(@RequestBody Bookings booking) {
		System.out.println("in add booking");
		User usr = booking.getUser();
		usr = userRepository.findByUserId(usr.getUserId());
		Freelancer frl = booking.getFreelancer();
		frl = freelancerRepository.findByFreelancerId(frl.getFreelancerId());
		
		booking.setUser(usr);
		booking.setFreelancer(frl);
		bookingRepository.save(booking);
		return "Booking request sent";
	}
	
	@PostMapping("/GetBookingByUser")
	public List<Bookings> getBookings(@RequestBody Bookings booking){
		return bookingRepository.getBookingsUser(booking.getUser().getUserId());
	}
	
	@PostMapping("/GetBookings")
	public List<Bookings> getAllBookings(){
		return bookingRepository.findAll();
	}
	
	@PostMapping("/CancelBooking")
	public String cancelBooking(@RequestBody Bookings booking) {
		booking = bookingRepository.findByBookingId(booking.getBookingId());
		if(booking.getBookingStatus().equals("pending")) {
			booking.setBookingStatus("Cancelled");
			bookingRepository.save(booking);
			return "Your Booking is Cancelled";
		} else {
			return "Booking was already cancelled";
		}
	}
	
	@PostMapping("/getBookingsByFreelancer")
	public List<Bookings> getBookingList(@RequestBody Bookings booking){
		System.out.println("In Get Bookings By Freelancer");
		return bookingRepository.getBookingsFreelancer(booking.getFreelancer().getFreelancerId());
	}
	
	@PostMapping("/acceptBooking")
	public String acceptBooking(@RequestBody Bookings bookings) {

		Bookings bookings2 = bookingRepository.findByBookingId(bookings.getBookingId());
		System.out.println("in the fun");

		if (bookings2.getBookingStatus().equals("pending")) {
			bookings2.setBookingStatus("accepted");
			bookings2.setPaymentAmount(bookings.getPaymentAmount());
			bookingRepository.save(bookings2);
			return "You Accept this Booking request ";
		} else {
			return "You Already Accept this booking request";
		}

	}
	
	@PostMapping("/rejectBooking")
	private String rejectBooking(@RequestBody Bookings booking) {
		booking=bookingRepository.findByBookingId(booking.getBookingId());
		if (booking.getBookingStatus().equals("Rejected")) {
			return "Booking request already rejected";
		} else {
			booking.setBookingStatus("Rejected");
			bookingRepository.save(booking);
			return "Booking Request Rejected";
		}
	}
	
	@PostMapping("/GetBookingDetails")
	public Bookings GetBookingDetails(@RequestBody Bookings bookings ) {
		
		Bookings bookings2 = bookingRepository.findByBookingId(bookings.getBookingId());
		
		return bookings2;
		
	}
}
















