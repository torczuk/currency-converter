package com.github.torczuk.infrastructure.service;

import com.github.torczuk.domain.model.Dictionary;
import com.github.torczuk.domain.repository.DictionaryRepository;
import com.github.torczuk.domain.service.DictionaryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DictionaryServiceTest {
    private @Mock DictionaryRepository dictionaryRepository;
    private @InjectMocks DictionaryService dictionaryService;

    @Test
    public void shouldReturnSortedCitiesByName() {
        Dictionary city1 = new Dictionary("city", "KRK", "Cracow");
        Dictionary city2 = new Dictionary("city", "BER", "Berlin");
        given(dictionaryRepository.findAllByName("city")).willReturn(asList(city1, city2));

        List<Dictionary> cities = dictionaryService.cities();

        assertThat(cities).containsExactly(city2, city1);
    }

    @Test
    public void shouldReturnSortedCurrenciesByName() {
        Dictionary currency1 = new Dictionary("currency", "PLN", "Polish Zloty");
        Dictionary currency2 = new Dictionary("currency", "EUR", "Euro");
        given(dictionaryRepository.findAllByName("currency")).willReturn(asList(currency1, currency2));

        List<Dictionary> currencies = dictionaryService.currencies();

        assertThat(currencies).containsExactly(currency2, currency1);
    }

}