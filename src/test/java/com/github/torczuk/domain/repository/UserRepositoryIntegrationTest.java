package com.github.torczuk.domain.repository;

import com.github.torczuk.Application;
import com.github.torczuk.TestApplicationConfig;
import com.github.torczuk.domain.model.Address;
import com.github.torczuk.domain.model.User;
import com.github.torczuk.testrepository.TestUserRepository;
import com.github.torczuk.util.Fakes;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =  {Application.class, TestApplicationConfig.class})
public class UserRepositoryIntegrationTest {
    private final static User USER = Fakes.USER_1;
    @Rule public ExpectedException expectedException = ExpectedException.none();

    @Autowired private UserRepository userRepository;
    @Autowired private TestUserRepository testUserRepository;

    @Before
    public void setUp() {
        testUserRepository.deleteAll();
    }

    @Test
    public void shouldCreateUser() {
        User user = USER;

        User saved = userRepository.save(user);

        assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    public void shouldThrowExceptionWhenAlreadyExistUserWithTheSamePassword() {
        User user = USER;
        User userWithTheSameEmail = new User("other name", "other surname", user.getEmail(), "sample@email.com", new Address("Cracow", "Main Square 2", "20-001"));

        userRepository.save(user);
        expectedException.expect(DataIntegrityViolationException.class);
        userRepository.save(userWithTheSameEmail);
    }

    @Test
    public void shouldReturnUserByEmail() {
        User user = USER;

        userRepository.save(user);
        User someUser = userRepository.getByEmail(user.getEmail());

        assertThat(user).isEqualTo(someUser);

    }
}