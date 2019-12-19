package com.ayur.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
    public String add( Model model,HttpServletRequest request,RedirectAttributes rd) throws ParseException {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setAge(Integer.parseInt(request.getParameter("age")));
        prescriptionDTO.setAppointmentId(Long.parseLong(request.getParameter("appointmentId")));
        prescriptionDTO.setBloodGroup(request.getParameter("bloodGroup"));
        prescriptionDTO.setBloodPressure(request.getParameter("bloodPressure"));
        prescriptionDTO.setCoffee(request.getParameter("coffee"));
        prescriptionDTO.setDateOfBirth(request.getParameter("dateOfBirth"));
        prescriptionDTO.setDescription(request.getParameter("description"));
        prescriptionDTO.setDiagnosis(request.getParameter("diagnosis"));
        prescriptionDTO.setFoodType(request.getParameter("foodType"));
        prescriptionDTO.setGender(request.getParameter("gender"));
        prescriptionDTO.setHeight(request.getParameter("height"));
        prescriptionDTO.setHungry(request.getParameter("hungry"));
        prescriptionDTO.setMotion(request.getParameter("motion"));
        prescriptionDTO.setNextVisitDate(request.getParameter("nextVisitDate"));
        String[] values = request.getParameterValues("productList");
        prescriptionDTO.setProductList(Arrays.asList(values).toString());
        prescriptionDTO.setSleep(request.getParameter("sleep"));
        prescriptionDTO.setSugar(request.getParameter("sugar"));
        prescriptionDTO.setTea(request.getParameter("tea"));
        prescriptionDTO.setThyroid(request.getParameter("thyroid"));
        prescriptionDTO.setUrine(request.getParameter("urine"));
        prescriptionDTO.setWeight(request.getParameter("weight"));
        System.out.println(prescriptionDTO.toString());
        Prescription prescription = prescriptionService.save(prescriptionDTO);
        
        //model.addAttribute("appointment", prescription.getAppointment());
        //model.addAttribute("prescription", new PrescriptionDTO());
        rd.addFlashAttribute("message","Successfully Saved");
        return "redirect:/view-appointment?id="+prescription.getAppointment().getId();

    }
}
