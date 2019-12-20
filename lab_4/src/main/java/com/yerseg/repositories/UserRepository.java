package com.yerseg.repositories;

import com.yerseg.model.User_;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User_, Long> {
    boolean existsByEmail(String email);
    User_ findByEmail(String email);
}
