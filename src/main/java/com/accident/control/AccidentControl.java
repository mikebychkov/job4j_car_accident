package com.accident.control;

import com.accident.config.WebConfig;
import com.accident.service.AccidentService;
import com.accident.service.AccidentServiceHbm;
import com.accident.service.AccidentServiceJdbc;
import com.accident.service.AccidentServiceJpa;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.accident.model.Accident;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private static final Logger logger = LogManager.getLogger(AccidentControl.class);

    private AccidentServiceJpa service;

    @Autowired
    public AccidentControl(AccidentServiceJpa service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getAccidentTypes());
        model.addAttribute("rules", service.getAccidentRules());
        return "create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.findById(id));
        model.addAttribute("types", service.getAccidentTypes());
        model.addAttribute("rules", service.getAccidentRules());
        return "update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        service.save(accident, ids);
        return "redirect:/";
    }
}