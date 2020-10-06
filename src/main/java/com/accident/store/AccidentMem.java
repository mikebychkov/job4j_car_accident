package com.accident.store;

import com.accident.model.Accident;
import com.accident.model.AccidentType;
import com.accident.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> data = new HashMap<>();

    public AccidentMem() {
        init();
    }

    public void init() {
        Accident ac1 = new Accident();
        ac1.setId(1);
        ac1.setName("Accident #1");
        ac1.setText("Some text about accident ...");
        ac1.setAddress("Baker street, 17");

        Accident ac2 = new Accident();
        ac2.setId(2);
        ac2.setName("Accident #2");
        ac2.setText("Accident about some text ...");
        ac2.setAddress("Korzhova, 23");

        data.put(1, ac1);
        data.put(2, ac2);
    }

    public void save(Accident accident) {
        data.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return data.get(id);
    }

    public Map<Integer, Accident> getData() {
        return data;
    }

    public List<Accident> getDataList() {
        return new ArrayList<>(getData().values());
    }

    public Map<Integer, AccidentType> getAccidentTypes() {
        Map<Integer, AccidentType> types = new HashMap<>();
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
        return types;
    }

    public List<AccidentType> getAccidentTypeList() {
        return new ArrayList<>(getAccidentTypes().values());
    }

    public AccidentType findTypeById(int id) {
        return getAccidentTypes().get(id);
    }

    public Map<Integer, Rule> getAccidentRules() {
        Map<Integer, Rule> rules = new HashMap<>();
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
        return rules;
    }

    public List<Rule> getAccidentRuleList() {
        return new ArrayList<>(getAccidentRules().values());
    }

    public Rule findRuleById(int id) {
        return getAccidentRules().get(id);
    }
}
