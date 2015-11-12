package com.github.torczuk.domain.repository;

import com.github.torczuk.domain.model.Dictionary;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DictionaryRepository extends Repository<Dictionary, Long> {

    Dictionary save(Dictionary dictionary);

    List<Dictionary> findAllByName(String name);

}
