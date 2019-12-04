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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ayur.model.Appointments;
import com.ayur.model.Branch;
import com.ayur.repository.BranchRepository;
import com.ayur.service.AppointmentService;
import com.ayur.service.BranchService;


@Controller
public class BranchController {

	 private BranchService branchService;
	 
	 @Autowired
	 private BranchRepository branchRepository;
	 
	 @Autowired
	    private AppointmentService appointmentService;
	
	@RequestMapping("/branch")
    public String add(Model model) {

	    List<Branch> list = branchRepository.findAll();
        model.addAttribute("list", list);
        model.addAttribute("branch", new Branch());
        return "branch/form";

    }
	
	
	 @RequestMapping(value = "/branch/save", method = RequestMethod.POST)
	    public String save(Branch branch, final RedirectAttributes ra) {

		 Branch save = branchRepository.save(branch);
	        ra.addFlashAttribute("successFlash", "Branc Saved successfully");
	        return "redirect:/branch";

	    }
	 
	 @RequestMapping(value = "/branch/send", method = RequestMethod.POST,consumes="application/json")
	 @CrossOrigin(origins = "http://localhost:9093")
	    public String save(@RequestBody Branch branch) {

		 Branch save = branchRepository.save(branch);
	       
	        return "welcome";

	    }
	 
	 @RequestMapping(value = "/branch/getList", method = RequestMethod.GET)
	   public String lists(Model model) {
	        List<Branch> list = branchRepository.findAll();
	        model.addAttribute("list", list);
	        return "branch/list";

	    }

	 @RequestMapping(value = "/appointments", method = RequestMethod.GET)
     public String list(Model model) {
          List<Appointments> list = appointmentService.findAll();
          model.addAttribute("list", list);
          return "appointments/list";

      }
	 
	
}
