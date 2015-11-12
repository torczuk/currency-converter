package com.github.torczuk.infrastructure.configuration;

import com.github.torczuk.domain.model.AbstractEntity;
import com.github.torczuk.domain.model.Dictionary;
import com.github.torczuk.domain.repository.DictionaryRepository;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = DictionaryRepository.class)
@Import(DBConfiguration.class)
@EntityScan(basePackageClasses=AbstractEntity.class)
public class JpaConfiguration {}
