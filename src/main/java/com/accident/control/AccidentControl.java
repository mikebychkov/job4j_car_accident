package com.accident.control;

import com.accident.model.AccidentType;
import com.accident.model.Rule;
import com.accident.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.accident.model.Accident;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AccidentControl {

    private AccidentService service;

    @Autowired
    public AccidentControl(AccidentService service) {
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
        return "update";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setType(getType(accident));
        //
        String[] ids = req.getParameterValues("rIds");
        accident.setRules(getRules(ids));
        //
        service.save(accident);
        //
        return "redirect:/";
    }

    private AccidentType getType(Accident accident) {
        if (accident.getType() == null) {
            return null;
        }
        int typeId = accident.getType().getId();
        if (typeId == 0) {
            return null;
        }
        return service.findTypeById(typeId);
    }

    private Set<Rule> getRules(String[] ruleIDs) {
        if (ruleIDs == null) {
            return null;
        }
        Set<Rule> rsl = new HashSet<>();
        for(int i = 0; i < ruleIDs.length; i++) {
            Rule rule = service.findRuleById(Integer.parseInt(ruleIDs[i]));
            if (rule != null) {
                rsl.add(rule);
            }
        }
        return rsl;
    }
}