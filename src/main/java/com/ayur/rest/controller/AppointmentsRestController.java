package com.ayur.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayur.controller.dto.AppointmentsDTO;
import com.ayur.model.Appointments;
import com.ayur.model.Branch;
import com.ayur.repository.BranchRepository;
import com.ayur.service.AppointmentService;
import com.ayur.service.SmsService;

@RestController
public class AppointmentsRestController {

    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private BranchRepository branchRepository;
    
    @Autowired
    private SmsService smsService;
    
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
         appointment.setAppointmentId(appointmentService.generateAppointmentId(appointment));
         Appointments save = appointmentService.save(appointment);
         smsService.sendSms(appointment);
            return save ;

        }
     
     
}
