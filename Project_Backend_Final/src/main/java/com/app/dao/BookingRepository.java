package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.Bookings;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Integer> {
	
	@Query("select b from Bookings b where user_user_id =:id")
	public List<Bookings> getBookingsUser(@Param("id") int id);
	
	public Bookings findByBookingId(int id);
	
	@Query("select b from Bookings b where freelancer_freelancer_id =:id")
	public List<Bookings> getBookingsFreelancer(@Param("id") int id);
}
