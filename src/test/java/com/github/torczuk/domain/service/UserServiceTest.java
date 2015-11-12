package com.github.torczuk.domain.service;

import com.github.torczuk.domain.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Rule public ExpectedException expectedException = ExpectedException.none();
    @Mock private UserRepository repository;
    @InjectMocks private UserService service;

    @Test
    public void shouldThrowUsernameNotFoundExceptionWhenUserCannotBeFound() {
        String email = "email@gmail.com";
        given(repository.getByEmail(email)).willReturn(null);

        expectedException.expect(UsernameNotFoundException.class);
        service.loadUserByUsername(email);
    }
}