package com.accident.control;

import com.accident.model.Accident;
import com.accident.store.AccidentMem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {

    private static final Logger logger = LogManager.getLogger(IndexControl.class);

    @GetMapping("/")
    public String index(Model model) {
        AccidentMem store = new AccidentMem();
        store.init();
        List<Accident> list = store.getDataList();
        model.addAttribute("list", list);
        return "index";
    }
}