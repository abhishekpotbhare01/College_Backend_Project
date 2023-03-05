package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojos.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
	
	@Query("select c from Complaint c where user_user_id=:id")
	public List<Complaint> getCompUser(@Param("id") int id);
	
	public Complaint findByCompId(int id);
	
}
