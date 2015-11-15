package com.github.torczuk.domain.repository;

import com.github.torczuk.domain.model.Conversion;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ConversionRepository extends Repository<Conversion, Long>{

    Conversion save(Conversion conversion);

    List<Conversion> findAllByUserId(Long userId);
}
