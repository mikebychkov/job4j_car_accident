package com.accident.service;

import com.accident.model.Accident;
import com.accident.model.AccidentType;
import com.accident.model.Rule;
import com.accident.store.AccidentMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            Random rnd = new Random();
            accident.setId(rnd.nextInt());
        }
        store.save(accident);
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
