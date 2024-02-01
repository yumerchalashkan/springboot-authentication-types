package com.springboot.basicauthentication.service;

import com.springboot.basicauthentication.dto.CreateUserRequest;
import com.springboot.basicauthentication.model.User;
import com.springboot.basicauthentication.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Optional<User> getByUserName(String userName){
        return userRepository.findByUsername(userName);
    }

    public User CreateUser(CreateUserRequest createUserRequest){
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
