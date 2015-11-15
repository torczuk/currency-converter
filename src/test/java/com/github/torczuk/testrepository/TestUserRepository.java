package com.github.torczuk.testrepository;

import com.github.torczuk.domain.model.User;
import org.springframework.data.repository.Repository;

public interface TestUserRepository extends Repository<User, Long>{
    void deleteAll();
}
