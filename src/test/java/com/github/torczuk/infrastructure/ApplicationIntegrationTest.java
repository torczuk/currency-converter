package com.github.torczuk.infrastructure;

import com.github.torczuk.Application;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationIntegrationTest {

    @Test
    public void shouldCreateApplicationContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        Assertions.assertThat(context).isNotNull();
    }

}