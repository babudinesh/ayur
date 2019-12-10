package com.ayur.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ayur.controller.dto.PrescriptionDTO;
import com.ayur.model.Appointments;
import com.ayur.model.Prescription;
import com.ayur.service.PrescriptionService;

@Controller
public class PrescriptionController {
    
    @Autowired
    private PrescriptionService prescriptionService;

    @RequestMapping(value = "/savePrescription" , method = RequestMethod.POST)
    public String add(PrescriptionDTO prescriptionDTO, Model model) {

        
        Prescription prescription = prescriptionService.save(prescriptionDTO);
        
        model.addAttribute("appointment", prescription.getAppointment());
        model.addAttribute("prescription", new PrescriptionDTO());
        model.addAttribute("message","Successfully Saved");
        return "appointments/view";

    }
}
