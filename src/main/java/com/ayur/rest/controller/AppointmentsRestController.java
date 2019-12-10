package com.ayur.rest.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ayur.controller.dto.AppointmentsDTO;
import com.ayur.model.Appointments;
import com.ayur.model.Branch;
import com.ayur.model.BranchSettings;
import com.ayur.repository.BranchRepository;
import com.ayur.repository.BranchSettingsRepository;
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
    
    @Autowired
    private BranchSettingsRepository branchSettingsRepository;
    
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
         try {
            appointmentService.generatePaymentLink();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            return save ;

        }
     
     @RequestMapping(value = "/appointment/check-availability", method = RequestMethod.GET)
     public String checkAvailability(@RequestParam("bookingDate") Date bookingDate,@RequestParam("branchId") Long branchId) {

         Branch branch = branchRepository.findOne(branchId);
         BranchSettings branchSettings= branchSettingsRepository.findByBranchAndBookingDate(branch,(bookingDate));
         String availability = "Not Available";
         if(branchSettings!=null) {
              if(branchSettings.getAppointmentCount() - branchSettings.getBookingCount()> 0) {
                  availability = "Available";
              }
              
         }
         return availability;
        }
     
     
}
