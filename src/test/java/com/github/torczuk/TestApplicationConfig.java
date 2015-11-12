package com.github.torczuk;

import com.github.torczuk.testrepository.TestDictionaryRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = TestDictionaryRepository.class)
public class TestApplicationConfig {
}
