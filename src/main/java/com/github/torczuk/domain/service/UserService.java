package com.github.torczuk.domain.service;

import com.github.torczuk.domain.model.Address;
import com.github.torczuk.domain.model.User;
import com.github.torczuk.domain.repository.UserRepository;
import com.github.torczuk.dto.SignUpDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired private UserRepository userRepository;

    public User createUser(SignUpDetails details) {
        Address address = new Address(details.getCity(), details.getAddress(), details.getPostCode());
        User user = new User(details.getFirstName(),
                details.getFirstName(),
                details.getPassword(),
                details.getEmail(),
                address);

        return userRepository.save(user);
    }

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public Long getCurrentUserId() {
        Long userId = null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            userId = user.getId();
        }
        return userId;
    }
}
