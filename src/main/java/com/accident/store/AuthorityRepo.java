package com.accident.store;

import org.springframework.data.repository.CrudRepository;
import com.accident.model.Authority;

public interface AuthorityRepo extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}
