package com.accident.service;

import com.accident.model.Accident;
import com.accident.model.AccidentType;
import com.accident.model.Rule;
import com.accident.store.AccidentRepo;
import com.accident.store.AccidentTypeRepo;
import com.accident.store.RuleRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccidentServiceJpa {

    private static final Logger logger = LogManager.getLogger(AccidentServiceJpa.class);

    private AccidentRepo accidentRepo;
    private AccidentTypeRepo accidentTypeRepo;
    private RuleRepo ruleRepo;

    public AccidentServiceJpa() {
    }

    @Autowired
    public void setAccidentRepo(AccidentRepo accidentRepo) {
        this.accidentRepo = accidentRepo;
    }

    @Autowired
    public void setAccidentTypeRepo(AccidentTypeRepo accidentTypeRepo) {
        this.accidentTypeRepo = accidentTypeRepo;
    }

    @Autowired
    public void setRuleRepo(RuleRepo ruleRepo) {
        this.ruleRepo = ruleRepo;
    }

    public List<Accident> getDataList() {
        List<Accident> rsl = new ArrayList<>();
        accidentRepo.findAll().forEach(rsl::add);
        return rsl;
    }

    public void save(Accident accident, String[] ruleIDs) {
        accident.setType(getType(accident));
        accident.setRules(getRules(ruleIDs));
        accidentRepo.save(accident);
    }

    private AccidentType getType(Accident accident) {
        if (accident.getType() == null) {
            return null;
        }
        int typeId = accident.getType().getId();
        if (typeId == 0) {
            return null;
        }
        return findTypeById(typeId);
    }

    private Set<Rule> getRules(String[] ruleIDs) {
        if (ruleIDs == null) {
            return null;
        }
        Set<Rule> rsl = new HashSet<>();
        for(int i = 0; i < ruleIDs.length; i++) {
            Rule rule = findRuleById(Integer.parseInt(ruleIDs[i]));
            if (rule != null) {
                rsl.add(rule);
            }
        }
        return rsl;
    }

    public Accident findById(int id) {
        return accidentRepo.findById(id).get();
    }

    public List<AccidentType> getAccidentTypes() {
        List<AccidentType> rsl = new ArrayList<>();
        accidentTypeRepo.findAll().forEach(rsl::add);
        return rsl;
    }

    public AccidentType findTypeById(int id) {
        return accidentTypeRepo.findById(id).get();
    }

    public List<Rule> getAccidentRules() {
        List<Rule> rsl = new ArrayList<>();
        ruleRepo.findAll().forEach(rsl::add);
        return rsl;
    }

    public Rule findRuleById(int id) {
        return ruleRepo.findById(id).get();
    }
}
