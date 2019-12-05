package com.ayur.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayur.model.Branch;
import com.ayur.model.BranchSettings;

@Repository
public interface BranchSettingsRepository extends JpaRepository<BranchSettings, Long>{

    BranchSettings findByBranchAndBookingDate(Branch branch,Date bookingDate);
}
