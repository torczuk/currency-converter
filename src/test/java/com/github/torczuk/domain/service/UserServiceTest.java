package com.github.torczuk.domain.service;

import com.github.torczuk.domain.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock private UserRepository repository;
    @InjectMocks private UserService service;

    @Test
    public void shouldCreateUserFromSignUpDetails() {
        Assertions.assertThat(true).isFalse();
    }

    @Test
    public void shouldTakeIntoAccountEmailWhenAuthenticateUserByUserName() {
        Assertions.assertThat(true).isFalse();
    }
}