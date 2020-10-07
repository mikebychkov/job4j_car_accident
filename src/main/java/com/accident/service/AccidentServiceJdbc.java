package com.accident.service;

import com.accident.model.Accident;
import com.accident.model.AccidentType;
import com.accident.model.Rule;
import com.accident.store.AccidentJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentServiceJdbc {

    private AccidentJdbcTemplate store;

    @Autowired
    public AccidentServiceJdbc(AccidentJdbcTemplate store) {
        this.store = store;
    }

    public List<Accident> getDataList() {
        return store.getAccidents();
    }

    public void save(Accident accident, String[] ruleIDs) {
        accident.setType(getType(accident));
        accident.setRules(getRules(ruleIDs));
        store.save(accident);
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
        return store.getAccidentById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return store.getAccidentTypes();
    }

    public AccidentType findTypeById(int id) {
        return store.getAccidentTypeById(id);
    }

    public List<Rule> getAccidentRules() {
        return store.getRules();
    }

    public Rule findRuleById(int id) {
        return store.getRuleById(id);
    }
}
