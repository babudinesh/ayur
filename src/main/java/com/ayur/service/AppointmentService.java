package com.ayur.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.controller.dto.AppointmentsDTO;
import com.ayur.model.Appointments;
import com.ayur.model.BranchSettings;
import com.ayur.repository.AppointmentRepository;
import com.ayur.repository.BranchSettingsRepository;

@Service
public class AppointmentService extends AbstractService<Appointments, Long>{

	 @Autowired
	 private AppointmentRepository appointmentRepository;
	 
	 @Autowired
     private BranchSettingsRepository  branchSettingsRepository;
	
	@Override
	protected JpaRepository<Appointments, Long> getRepository() {
		return appointmentRepository;
	}

	public List<Appointments> findAll() {
		return appointmentRepository.findAll();
	}
	
	public Appointments save(Appointments appointments)
	{
	    appointments = appointmentRepository.save(appointments);
	    BranchSettings branchSettings = branchSettingsRepository.findByBranchAndBookingDate(appointments.getBranch(), appointments.getBookingDate());
	    branchSettings.setBookingCount(branchSettings.getBookingCount()+1);
	    branchSettingsRepository.save(branchSettings);
	    return appointments;
	}

	public String generateAppointmentId(Appointments appointments) {
	    LocalDate localDate = LocalDate.now();
	    BranchSettings branchSettings = branchSettingsRepository.findByBranchAndBookingDate(appointments.getBranch(),appointments.getBookingDate());
	    Long bookingCount = branchSettings.getBookingCount()+1;
	    String appointmentId= DateTimeFormatter.ofPattern("yyyyMMdd").format(localDate)
	            .concat(appointments.getBranch().getId().toString()).concat(bookingCount.toString());
	    System.out.println(appointmentId);
	    return appointmentId;
	}
	
}
