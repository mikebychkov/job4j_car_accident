package com.accident.store;

import com.accident.model.Rule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepo extends CrudRepository<Rule, Integer> {
}
