package com.springboot.jwttokenauthentication.dto;

import com.springboot.jwttokenauthentication.model.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateUserRequest(
        String name,
        String username,
        String password,
        Set<Role> authorities
) {
}
