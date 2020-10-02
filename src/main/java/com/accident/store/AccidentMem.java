package com.accident.store;

import com.accident.model.Accident;
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

    public Map<Integer, Accident> getData() {
        return data;
    }
}
