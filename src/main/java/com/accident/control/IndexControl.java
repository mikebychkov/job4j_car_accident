package com.accident.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControl {

    private static final Logger logger = LogManager.getLogger(IndexControl.class);

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}