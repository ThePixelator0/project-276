package com.etb.eattrainbalance.modal.security;

import com.etb.eattrainbalance.persistence.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// This class implements UserDetails, an interface core to Spring Security Framework.
public class UserPrincipal implements UserDetails {

    // User's unique identifier
    private Long id;

    // User's email (used as username)
    private String email;

    // User's password, should not be exposed in JSON serialization
    @JsonIgnore
    private String password;

    // Authorities granted to the user (roles, rights, etc.)
    private Collection<? extends GrantedAuthority> authorities;

    // User's name
    private String name;

    // Indicator if the user is enabled or not, should not be exposed in JSON serialization
    @JsonIgnore
    private Boolean enabled;

    // Indicates if the user needs to reset the password
    @JsonIgnore
    private Boolean resetPassword;

    // Constructor of UserPrincipal
    public UserPrincipal(Long id,
                         String email,
                         String password,
                         Collection<? extends GrantedAuthority> authorities,
                         String name,
                         Boolean enabled,
                         Boolean resetPassword) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.name = name;
        this.enabled = enabled;
        this.resetPassword = resetPassword;
    }

    // Factory method to create a new instance of UserPrincipal from User object
    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = Stream.of(user.getRole()).map(r -> new SimpleGrantedAuthority(r.name())).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getName(),
                user.getEnable(),
                user.getResetPassword()
        );
    }


    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
