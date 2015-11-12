package com.github.torczuk.infrastructure.service;

import com.github.torczuk.domain.model.Dictionary;
import com.github.torczuk.domain.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.sort;

@Service
public class DictionaryService {
    @Autowired private DictionaryRepository dictionaryRepository;

    public List<Dictionary> cities() {
        List<Dictionary> cities = dictionaryRepository.findAllByName("city");
        sort(cities);
        return cities;
    }

    public List<Dictionary> currencies() {
        List<Dictionary> currencies = dictionaryRepository.findAllByName("currency");
        sort(currencies);
        return currencies;
    }
}
