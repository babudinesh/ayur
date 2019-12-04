package com.hendisantika.adminlte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hendisantika.adminlte.controller.dto.AppointmentsDTO;
import com.hendisantika.adminlte.model.Appointments;
import com.hendisantika.adminlte.model.Branch;
import com.hendisantika.adminlte.repository.AppointmentRepository;
import com.hendisantika.adminlte.repository.BranchRepository;
import com.hendisantika.adminlte.service.AppointmentService;

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
