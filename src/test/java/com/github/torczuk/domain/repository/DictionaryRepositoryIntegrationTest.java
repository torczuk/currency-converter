package com.github.torczuk.domain.repository;


import com.github.torczuk.Application;
import com.github.torczuk.TestApplicationConfig;
import com.github.torczuk.domain.model.Dictionary;
import com.github.torczuk.testrepository.TestDictionaryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =  {Application.class, TestApplicationConfig.class})
public class DictionaryRepositoryIntegrationTest {

    @Autowired private DictionaryRepository dictionaryRepository;
    @Autowired private TestDictionaryRepository dictionaryTestRepository;

    @Before
    public void setUp() {
        dictionaryTestRepository.deleteAll();
    }

    @Test
    public void shouldSaveDictionary(){
        Dictionary dictionary = new Dictionary("city", "KRK", "Cracow");

        Dictionary saved = dictionaryRepository.save(dictionary);

        assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldFindAllDictionariesByName(){
        Dictionary city = new Dictionary("city", "KRK", "Cracow");
        Dictionary currency = new Dictionary("currency", "PLN", "Polish Zloty");

        dictionaryRepository.save(city);
        Dictionary persisted = dictionaryRepository.save(currency);
        List<Dictionary> currencies = dictionaryRepository.findAllByName("currency");

        assertThat(currencies).containsExactly(persisted);
    }
}