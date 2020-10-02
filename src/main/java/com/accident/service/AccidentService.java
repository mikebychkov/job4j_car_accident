package com.accident.service;

import com.accident.model.Accident;
import com.accident.store.AccidentMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
