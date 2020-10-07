package com.accident.control;

import com.accident.model.Accident;
import com.accident.service.AccidentService;
import com.accident.service.AccidentServiceJdbc;
import com.accident.store.AccidentMem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {

    private static final Logger logger = LogManager.getLogger(IndexControl.class);

    private AccidentServiceJdbc service;

    @Autowired
    public IndexControl(AccidentServiceJdbc service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> list = service.getDataList();
        model.addAttribute("list", list);
        return "index";
    }
}