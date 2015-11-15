package com.github.torczuk.testrepository;

import com.github.torczuk.domain.model.Conversion;
import org.springframework.data.repository.Repository;

public interface TestConversionRepository extends Repository<Conversion, Long> {
    void deleteAll();
    int count();
}
