package com.ayur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.model.Appointments;
import com.ayur.repository.AppointmentRepository;

@Service
public class AppointmentService extends AbstractService<Appointments, Long>{

	 @Autowired
	 private AppointmentRepository appointmentRepository;
	
	@Override
	protected JpaRepository<Appointments, Long> getRepository() {
		return appointmentRepository;
	}

	public List<Appointments> findAll() {
		return appointmentRepository.findAll();
	}

}