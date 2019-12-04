package com.ayur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.model.Branch;
import com.ayur.repository.BranchRepository;

@Service
public class BranchService extends AbstractService<Branch, Long>{

	 @Autowired
	 private BranchRepository branchRepository;
	
	@Override
	protected JpaRepository<Branch, Long> getRepository() {
		return branchRepository;
	}

	public List<Branch> findAll() {
		return branchRepository.findAll();
	}

}
