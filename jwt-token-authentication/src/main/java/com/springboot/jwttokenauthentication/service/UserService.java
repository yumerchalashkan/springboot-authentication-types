package com.springboot.jwttokenauthentication.service;

import com.springboot.jwttokenauthentication.dto.CreateUserRequest;
import com.springboot.jwttokenauthentication.model.User;
import com.springboot.jwttokenauthentication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }
    
    public Optional<User> getByUserName(String userName){
        return userRepository.findByUsername(userName);
    }

    public User createUser(CreateUserRequest createUserRequest){
        User tempUser = User.builder()
                .name(createUserRequest.name())
                .username(createUserRequest.username())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .authorities(createUserRequest.authorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();

        return userRepository.save(tempUser);
    }
    
}
