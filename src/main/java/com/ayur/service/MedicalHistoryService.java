package com.ayur.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.model.Branch;
import com.ayur.model.Customers;
import com.ayur.model.MedicalHistory;
import com.ayur.repository.MedicalHistoryRepository;

@Service
public class MedicalHistoryService extends AbstractService<MedicalHistory, Long>{

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;
    
    @Override
    protected JpaRepository<MedicalHistory, Long> getRepository() {
        // TODO Auto-generated method stub
        return null;
    }

    public MedicalHistory findByCustomer(Customers customer) {
        return medicalHistoryRepository.findByCustomer(customer);
    }
    
    public MedicalHistory save(MedicalHistory medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }
}
