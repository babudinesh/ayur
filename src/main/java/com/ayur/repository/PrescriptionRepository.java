package com.ayur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayur.model.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{

}
