package com.ayur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayur.model.Customers;
import com.ayur.model.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{

    List<Prescription> findByCustomer(Customers customer);
}
