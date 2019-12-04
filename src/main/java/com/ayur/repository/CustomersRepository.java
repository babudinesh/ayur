package com.ayur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayur.model.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {

}
