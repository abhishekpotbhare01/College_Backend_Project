package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findUserByUserEmailAndUserPassword(String userEmail,String userPassword);
	
	public User findByUserId(int userId);
	
	public User findByUserEmail(String userEmail);
}
