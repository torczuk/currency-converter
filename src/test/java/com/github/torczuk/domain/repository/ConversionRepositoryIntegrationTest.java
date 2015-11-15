package com.github.torczuk.domain.repository;


import com.github.torczuk.Application;
import com.github.torczuk.TestApplicationConfig;
import com.github.torczuk.domain.model.Conversion;
import com.github.torczuk.testrepository.TestConversionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =  {Application.class, TestApplicationConfig.class})
public class ConversionRepositoryIntegrationTest {

    @Autowired private ConversionRepository conversionRepository;
    @Autowired private TestConversionRepository conversionTestRepository;

    @Before
    public void setUp() {
        conversionTestRepository.deleteAll();
    }

    @Test
    public void shouldSaveConversion(){
        Conversion conversion = new Conversion(10L, 1L, "10.2", "USD", "PLN");

        Conversion saved = conversionRepository.save(conversion);

        assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldFindAllConversionsByUserId(){
        Conversion user1Conversion = new Conversion(10L, 1L, "10.2", "USD", "PLN");
        Conversion user2Conversion = new Conversion(9L, 2L, "1.2", "USD", "YEN");

        Conversion user1ConversionSaved = conversionRepository.save(user1Conversion);
        conversionRepository.save(user2Conversion);

        assertThat(conversionRepository.findAllByUserId(1L)).containsExactly(user1ConversionSaved);
    }
}
