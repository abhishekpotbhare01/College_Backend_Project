package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.AdminRepository;
import com.app.pojos.Admin;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminRepository adminRepossitory;
	
	@PostMapping("/addAdmin")
	public String addAdmin(@RequestBody Admin admin) {
		adminRepossitory.save(admin);
		return "Admin is Added Successfully";
	}
	
	@PostMapping("/checkAdmin")
	public Admin adminLogin(@RequestBody Admin admin) {
		Admin adm=adminRepossitory.findAdminByAdminEmailAndAdminPassword(admin.getAdminEmail(), admin.getAdminPassword());
		return adm;
	}
	
	@GetMapping("/getAdmin")
	public String getAdmin() {
		return "In Get Admin";
	}
	
	@PostMapping("/findAdmin")
	public Admin findAdmin(@RequestBody Admin admin) {
		return adminRepossitory.findByAdminId(admin.getAdminId());
	}
}
