package com.ayur.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ayur.controller.dto.AppointmentsDTO;
import com.ayur.controller.dto.PrescriptionDTO;
import com.ayur.model.Appointments;
import com.ayur.model.Branch;
import com.ayur.model.BranchSettings;
import com.ayur.model.PaymentStatus;
import com.ayur.model.Prescription;
import com.ayur.repository.AppointmentRepository;
import com.ayur.repository.BranchRepository;
import com.ayur.service.AppointmentService;
import com.ayur.service.SmsService;

@Controller
public class AppointmentController {
	
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private BranchRepository branchRepository;
    
    @Autowired
    private SmsService smsService;
    
    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public String list(Model model) {
         List<Appointments> list = appointmentService.findAll();
         model.addAttribute("list", list);
         return "appointments/list";

     }
    
    @RequestMapping(value = "/view-appointment", method = RequestMethod.GET)
    public String getAppointmentDetails(@RequestParam(value = "id", required = true)  Long id,Model model) {
         Appointments appointment = appointmentService.findOne(id);
         model.addAttribute("appointment", appointment);
         model.addAttribute("prescription", new PrescriptionDTO());
         return "appointments/view";

     }
    
    @RequestMapping(value = "/save-appointment" , method = RequestMethod.POST)
    public String save(AppointmentsDTO  appointmentDTO, final RedirectAttributes ra) throws ParseException {

        Branch branch = branchRepository.findOne(appointmentDTO.getBranch());
        Appointments appointment = new Appointments();
        appointment.setAddress(appointmentDTO.getAddress());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        appointment.setBookingDate(formatter.parse(appointmentDTO.getBookingDate()));
        appointment.setBranch(branch);
        appointment.setDescription(appointmentDTO.getDescription());
        appointment.setMobile(appointmentDTO.getMobile());
        appointment.setName(appointmentDTO.getName());
        appointment.setAppointmentId(appointmentService.generateAppointmentId(appointment));
        appointment.setPaymentStatus(PaymentStatus.Pending);
        Appointments save = appointmentService.save(appointment);
        smsService.sendSms(appointment);
        ra.addAttribute("message", "Successfully created Appointment");
        try {
           appointmentService.generatePaymentLink(appointment);
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
        return "redirect:/add-appointment";
        
    }
    
    @RequestMapping(value = "/add-appointment", method = RequestMethod.GET)
    public String add(Model model) {
         
        List<Branch> list = branchRepository.findAll();
        model.addAttribute("list", list);
         model.addAttribute("appointment", new AppointmentsDTO());
         return "appointments/create";

     }

}
