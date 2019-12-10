package com.ayur.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.controller.dto.PrescriptionDTO;
import com.ayur.model.Appointments;
import com.ayur.model.BranchSettings;
import com.ayur.model.Customers;
import com.ayur.model.Prescription;
import com.ayur.repository.AppointmentRepository;
import com.ayur.repository.CustomersRepository;
import com.ayur.repository.PrescriptionRepository;

@Service
public class PrescriptionService extends AbstractService<Prescription, Long>{

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private CustomersRepository customersRepository;
    
    @Override
    protected JpaRepository<Prescription, Long> getRepository() {
        // TODO Auto-generated method stub
        return null;
    }

    public Prescription save(PrescriptionDTO prescriptionDTO) {
        Appointments appointments = appointmentRepository.findOne(prescriptionDTO.getAppointmentId());
        Customers customer  = customersRepository.findByMobile(appointments.getMobile());
        if(customer==null) {
            customer = new Customers();
            customer.setFirstname(appointments.getName());
            customer.setAddress(appointments.getAddress());
            customer.setMobile(appointments.getMobile());
            customer = customersRepository.save(customer);
        }
        Prescription prescription = new Prescription();
        prescription.setAppointment(appointments);
        prescription.setCustomer(customer);
        prescription.setDiagnosis(prescriptionDTO.getDiagnosis());
        prescription.setDescription(prescriptionDTO.getDiagnosis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            prescription.setNextVisitDate(formatter.parse(prescriptionDTO.getNextVisitDate()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return prescriptionRepository.save(prescription);
    }
    
    public String generateCustomerId() {
        LocalDate localDate = LocalDate.now();
        String appointmentId = DateTimeFormatter.ofPattern("yyyyMMdd").format(localDate)
                .concat(getRandomIntegerBetweenRange(1,1000));
        System.out.println(appointmentId);
        return appointmentId;
    }
    
    public static String getRandomIntegerBetweenRange(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return String.valueOf(x);
    }
}
