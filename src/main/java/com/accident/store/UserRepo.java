package com.accident.store;

import org.springframework.data.repository.CrudRepository;
import com.accident.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
}
