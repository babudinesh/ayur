package com.ayur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayur.model.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{

}
