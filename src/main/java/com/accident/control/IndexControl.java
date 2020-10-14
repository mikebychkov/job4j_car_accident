package com.accident.control;

import com.accident.model.Accident;
import com.accident.service.AccidentService;
import com.accident.service.AccidentServiceHbm;
import com.accident.service.AccidentServiceJdbc;
import com.accident.service.AccidentServiceJpa;
import com.accident.store.AccidentMem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControl {

    private static final Logger logger = LogManager.getLogger(IndexControl.class);

    private AccidentServiceJpa service;

    @Autowired
    public IndexControl(AccidentServiceJpa service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("list", service.getDataList());
        return "index";
    }
}