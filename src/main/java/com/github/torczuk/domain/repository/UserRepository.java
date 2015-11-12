package com.github.torczuk.domain.repository;

import com.github.torczuk.domain.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long>{

    User save(User user);

    User getByEmail(String email);
}
