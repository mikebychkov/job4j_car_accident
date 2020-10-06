package com.accident.service;

import com.accident.model.Accident;
import com.accident.model.AccidentType;
import com.accident.model.Rule;
import com.accident.store.AccidentMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccidentService {

    private AccidentMem store;

    @Autowired
    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public List<Accident> getDataList() {
        return store.getDataList();
    }

    public void save(Accident accident, String[] ruleIDs) {
        if (accident.getId() == 0) {
            Random rnd = new Random();
            accident.setId(rnd.nextInt());
        }
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
        return store.findById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return store.getAccidentTypeList();
    }

    public AccidentType findTypeById(int id) {
        return store.findTypeById(id);
    }

    public List<Rule> getAccidentRules() {
        return store.getAccidentRuleList();
    }

    public Rule findRuleById(int id) {
        return store.findRuleById(id);
    }
}
