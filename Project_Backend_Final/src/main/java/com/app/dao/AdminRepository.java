package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findAdminByAdminEmailAndAdminPassword(String adminEmail,String adminPassword);
	
	public Admin findByAdminId(int adminId);
}
