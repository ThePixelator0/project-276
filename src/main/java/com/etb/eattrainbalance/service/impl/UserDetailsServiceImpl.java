package com.etb.eattrainbalance.service.impl;



import com.etb.eattrainbalance.exception.ResourceNotFoundException;
import com.etb.eattrainbalance.modal.security.UserPrincipal;
import com.etb.eattrainbalance.persistence.entity.User;
import com.etb.eattrainbalance.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Method to load user by username (in this case, email)
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Finding user by email, throwing exception if not found
        User user = userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username : " + username)
                );
        
        // Creating a UserPrincipal object from the User entity
        return UserPrincipal.create(user);
    }

    // Method to load user by ID
    @Transactional
    public UserDetails loadUserById(Long id) {
        // Finding user by ID, throwing exception if not found
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        // Creating a UserPrincipal object from the User entity
        return UserPrincipal.create(user);
    }
}