package com.github.torczuk.testrepository;

import com.github.torczuk.domain.model.Dictionary;
import org.springframework.data.repository.Repository;

public interface TestDictionaryRepository extends Repository<Dictionary, Long>{
    void deleteAll();
}
