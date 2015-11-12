package com.github.torczuk.infrastructure.repository;

import com.github.torczuk.Application;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationIntegrationTest {

    @Test
    public void shouldCreateApplicationContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        Assertions.assertThat(context).isNotNull();
    }

}