package com.accident.store;

import com.accident.model.AccidentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccidentTypeRepo extends CrudRepository<AccidentType, Integer> {
}
