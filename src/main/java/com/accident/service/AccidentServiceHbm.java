package com.accident.service;

import com.accident.config.WebConfig;
import com.accident.model.Accident;
import com.accident.model.AccidentType;
import com.accident.model.Rule;
import com.accident.store.AccidentHibernate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentServiceHbm {

    private static final Logger logger = LogManager.getLogger(AccidentServiceHbm.class);

    private AccidentHibernate store;

    @Autowired
    public AccidentServiceHbm(AccidentHibernate store) {
        this.store = store;
    }

    public List<Accident> getDataList() {
        return store.getAccidents();
    }

    public void save(Accident accident, String[] ruleIDs) {

        logger.debug("\n\n\n" + Arrays.toString(ruleIDs));
        logger.debug("\n\n\n");

        accident.setType(getType(accident));
        accident.setRules(getRules(ruleIDs));

        logger.debug("\n\n\n" + accident.getRules());
        logger.debug("\n\n\n");

        if (accident.getId() == 0) {
            store.save(accident);
        } else {
            store.update(accident);
        }
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
