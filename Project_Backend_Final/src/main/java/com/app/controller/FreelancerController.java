package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.FreelancerRepository;
import com.app.pojos.Freelancer;

@RestController
@CrossOrigin
public class FreelancerController {

	@Autowired
	private FreelancerRepository freelancerRepository;
	
	@GetMapping("/FreelancerList")
	public List<Freelancer> flist(){
		return freelancerRepository.findAll();
	}
	
	@PostMapping("/AddFreelancer")
	public ResponseEntity<?> addFreelancer(@RequestBody @Valid Freelancer freelancer) {
		try {
			System.out.println("In add freelancer");
			return new ResponseEntity<>(freelancerRepository.save(freelancer), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Duplicate User",HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping("/CheckFreelancer")
	public Freelancer freelancerLogin(@RequestBody Freelancer freelancer) {
		System.out.println("In Check Freelancer");
		return freelancerRepository.findFreelancerByFrlEmailAndFrlPassword(freelancer.getFrlEmail(), freelancer.getFrlPassword());
	}
	
	@PostMapping("/updateFreelancerStatus")
	public String updateFreelancerStatus(@RequestBody Freelancer f) {
	Freelancer freelancer = freelancerRepository.findByFreelancerId(f.getFreelancerId());
	if(freelancer.getStatus().equals("active")) {
		freelancer.setStatus("inactive");
	}else {
		freelancer.setStatus("active");
	}
	freelancerRepository.save(freelancer);
	return null;
	}
	
	@GetMapping("/GetAllCity")
	public List<Freelancer> findAllFrlCity(){
		System.out.println("in get all cities");
		return freelancerRepository.findAllFrlCity();
	}
	
	@PostMapping("/GetByCity")
	public List<Freelancer> getByFrlCity(@RequestBody Freelancer f){
		System.out.println("In get By City");
		return freelancerRepository.findByFrlCity(f.getFrlCity());
	}
	
	@GetMapping("/GetAllProfession")
	public List<Freelancer> findAllFrlProfession(){
		return freelancerRepository.findAllFrlProfession();
	}
	
	@PostMapping("/GetByProfession")
	public List<Freelancer> findByFrlProfession(@RequestBody Freelancer f){
		return freelancerRepository.findByFrlProfession(f.getFrlProfession());
	}
	
	@PostMapping("/FindByFreelancerId")
	public Freelancer findByFreelancerId(@RequestBody Freelancer freelancer){
		System.out.println("Find by freelancer Id");
		return freelancerRepository.findByFreelancerId(freelancer.getFreelancerId());
	}
	
	@PutMapping("/editFreelancer/{freelancerId}")
	public ResponseEntity<?> EditFreelancer(@PathVariable Integer freelancerId,@RequestBody Freelancer freelancerDeatils) {
		
		 System.out.println(" in EditFreelancer   id "+freelancerId + "  freelancerDeatils  "+freelancerDeatils);
		

		Freelancer save = freelancerRepository.save(freelancerDeatils);
		
		 return  ResponseEntity.ok(save);
	}
}








