package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.pojos.Freelancer;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer, Integer> {
	public Freelancer findFreelancerByFrlEmailAndFrlPassword(String Email, String Password);

	public Freelancer findByFreelancerId(int freelancerId);

	@Query("select f from Freelancer f group by f.frlCity")
	public List<Freelancer> findAllFrlCity();

	public List<Freelancer> findByFrlCity(String city);

	@Query("select f from Freelancer f group by f.frlProfession")
	public List<Freelancer> findAllFrlProfession();

	public List<Freelancer> findByFrlProfession(String profession);

	public Freelancer findByFrlEmail(String email);
}
