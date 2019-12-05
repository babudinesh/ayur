package com.ayur.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ayur.model.Branch;
import com.ayur.model.BranchSettings;
import com.ayur.repository.BranchRepository;
import com.ayur.repository.BranchSettingsRepository;

@Controller
public class BranchSettingsController {

    @Autowired
    private BranchRepository branchRepository;
    
    @Autowired
    private BranchSettingsRepository branchSettingsRepository;
    
    
    @RequestMapping("/branch-settings")
    public String add(Model model) {

        List<Branch> list = branchRepository.findAll();
        model.addAttribute("list", list);
        model.addAttribute("branchSettings", new BranchSettings());
        List<BranchSettings> branchSettingsList = branchSettingsRepository.findAll();
        model.addAttribute("branchSettingsList", branchSettingsList);
        return "branch/settings";

    }
    
    @RequestMapping(value = "/branch-settings/save" , method = RequestMethod.POST)
    public String save(BranchSettings branchSettings, final RedirectAttributes ra) {

        branchSettings.setBookingCount(Long.valueOf("0"));
        branchSettingsRepository.save(branchSettings);
        return "redirect:/branch-settings";

    }
}
