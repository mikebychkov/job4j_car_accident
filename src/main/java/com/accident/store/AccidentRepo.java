package com.accident.store;

import org.springframework.data.repository.CrudRepository;
import com.accident.model.Accident;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentRepo extends CrudRepository<Accident, Integer> {
}
