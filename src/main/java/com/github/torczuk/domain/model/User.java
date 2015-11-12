package com.github.torczuk.domain.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User extends AbstractEntity implements UserDetails {

    private String firstName;
    private String lastName;
    private String password;
    @Column(unique = true) private String email;
    @Embedded private Address address;

    public User() {
    }

    public User(String firstName, String lastName, String password, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override public String getUsername() {
        return email;
    }

    @Override public boolean isAccountNonExpired() {
        return true;
    }

    @Override public boolean isAccountNonLocked() {
        return true;
    }

    @Override public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override public boolean isEnabled() {
        return true;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("USER");
    }
}
