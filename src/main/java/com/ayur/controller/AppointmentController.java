package com.ayur.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.ayur.service.SmsService;

@Controller
public class AppointmentController {
	
    @Autowired
    private AppointmentService appointmentService;
    
    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public String list(Model model) {
         List<Appointments> list = appointmentService.findAll();
         model.addAttribute("list", list);
         return "appointments/list";

     }
    
    

}
