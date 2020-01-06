package com.ayur.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ayur.model.Branch;
import com.ayur.service.AppointmentService;
import com.ayur.service.SmsService;

@Controller
public class SmsController {

    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private SmsService smsService;
    
    @RequestMapping("/message")
    public String add(Model model) {

        
        return "sms/form";

    }
    
    @RequestMapping(value = "/sms-details", method = RequestMethod.POST)
    public String getDetails(HttpServletRequest request,Model model) {
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        List<Object[]> list= appointmentService.findByAppointmentDates( fromDate, toDate);
        model.addAttribute("list",list);
        return "sms/form";

    }
    @RequestMapping(value = "/send-message", method = RequestMethod.POST)
    public String sendMessage(HttpServletRequest request,Model model) {
        String mobileNumbers[] = request.getParameterValues("mobile");
        String message = request.getParameter("message");
        smsService.sendMessage(mobileNumbers, message);
        model.addAttribute("successMessage","Message Sent Successfully");
        return "sms/form";

    }
}
