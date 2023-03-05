package com.app.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ComplaintRepository;
import com.app.dao.FreelancerRepository;
import com.app.dao.UserRepository;
import com.app.pojos.Complaint;
import com.app.pojos.Freelancer;
import com.app.pojos.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ComplaintController {
	
	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FreelancerRepository freelancerRepository;
	
	
	@GetMapping("/getAllComplaint")
	public List<Complaint> getAllComplaint(){
		return complaintRepository.findAll();
	}

	@PostMapping("/addComplaint")
	public Complaint addComplaint(@RequestBody Complaint complaint) {
		User usr=complaint.getUser();
		usr=userRepository.findByUserId(usr.getUserId());
		

		Freelancer frl = complaint.getFreelancer();
		frl =  freelancerRepository.findByFreelancerId(frl.getFreelancerId());
		

		
		complaint.setUser(usr);
		complaint.setFreelancer(frl);
		
		Date date = new Date();
		String strDateFormat = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate = dateFormat.format(date);
		complaint.setDate(formattedDate);
		complaint.setStatus("pending");
				
		return complaintRepository.save(complaint);
	}
	
	
	
	@PostMapping("/getCompByUser")
	public List<Complaint> getHire(@RequestBody Complaint complaint){
		
		  return complaintRepository.getCompUser(complaint.getUser().getUserId());
	}
	
	@PostMapping("/resolveCompStatus")
	public String resoveCompStatus(@RequestBody Complaint comp) {
		System.out.println("in resolve status");
		comp=complaintRepository.findByCompId(comp.getCompId());
		comp.setStatus("resolved");
		complaintRepository.save(comp);
		return "resolved";
		
	}
	
	@PostMapping("/rejectCompStatus")
	public String rejectCompStatus(@RequestBody Complaint comp) {
		comp=complaintRepository.findByCompId(comp.getCompId());
		comp.setStatus("rejected");
		complaintRepository.save(comp);
		return "rejected";
		
	}
}
