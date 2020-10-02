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
        return new ArrayList<>(store.getData().values());
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            Random rnd = new Random();
            accident.setId(rnd.nextInt());
        }
        store.getData().put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return store.getData().get(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return new ArrayList<>(store.getAccidentTypes().values());
    }

    public AccidentType findTypeById(int id) {
        return store.getAccidentTypes().get(id);
    }

    public List<Rule> getAccidentRules() {
        return new ArrayList<>(store.getAccidentRules().values());
    }

    public Rule findRuleById(int id) {
        return store.getAccidentRules().get(id);
    }
}
