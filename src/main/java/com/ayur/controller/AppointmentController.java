package com.ayur.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayur.controller.dto.AppointmentsDTO;
import com.ayur.model.Appointments;
import com.ayur.model.Branch;
import com.ayur.repository.AppointmentRepository;
import com.ayur.repository.BranchRepository;
import com.ayur.service.AppointmentService;

@RestController
public class AppointmentController {
	
    @Autowired
    private AppointmentService appointmentService;
    
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
    private BranchRepository branchRepository;
	
	 @RequestMapping(value = "/appointment/save", method = RequestMethod.POST,consumes="application/json")
	 public Appointments save(@RequestBody AppointmentsDTO appointmentDTO) {

	     Branch branch = branchRepository.findOne(appointmentDTO.getBranch());
	     Appointments appointment = new Appointments();
	     appointment.setAddress(appointmentDTO.getAddress());
	     appointment.setBookingDate(appointmentDTO.getBookingDate());
	     appointment.setBranch(branch);
	     appointment.setDescription(appointmentDTO.getDescription());
	     appointment.setMobile(appointmentDTO.getMobile());
	     appointment.setName(appointmentDTO.getName());
		 Appointments save = appointmentRepository.save(appointment);
	       
	        return save ;

	    }
	 
	 

}
