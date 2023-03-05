package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojos.PaymentDetails;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails, Integer> {

}
