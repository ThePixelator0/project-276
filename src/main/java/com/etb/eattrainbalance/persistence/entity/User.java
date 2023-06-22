package com.etb.eattrainbalance.persistence.entity;

import com.etb.eattrainbalance.persistence.entity.enam.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Lombok annotations for automatically generating getters, setters, constructors, and builders
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Indicates that this class is an entity, mapped to a database table
@Entity
// Specifies the name of the database table to be used for mapping
@Table(name = "users")
public class User implements UserDetails {
    // Specifies the primary key of the entity
    @Id
    // Defines the primary key generation strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Specifies the mapped column for the primary key
    @Column(name = "id")
    private Long id;

    // Ensures that email cannot be null
    @NotNull
    // Specifies the mapped column for the email field
    @Column(name = "email")
    private String email;

    // Ensures that password cannot be null and has size between 4 and 100
    @NotNull
    @Size(min = 4, max = 100)
    // Specifies the mapped column for the password field
    @Column(name = "password")
    private String password;

    // Specifies the mapped column for the name field
    @Column(name = "name")
    private String name;

    // Specifies the mapped column for the reset_password field
    @Column(name = "reset_password")
    private Boolean resetPassword;

    // Specifies the mapped column for the enable field
    @Column(name = "enable")
    private Boolean enable;

    // Specifies that this field is an Enum, and will be stored as a String
    @Enumerated(EnumType.STRING)
    private Role role;

    // Implementation of the UserDetails.getAuthorities() method
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // Returns the username for Spring Security, in this case, the email
    @Override
    public String getUsername() {
        return email;
    }

    // Indicates that the user account is not expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Indicates that the user account is not locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Indicates that the user credentials are not expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Indicates that the user is enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}