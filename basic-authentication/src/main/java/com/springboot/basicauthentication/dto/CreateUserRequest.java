package com.springboot.basicauthentication.dto;

import com.springboot.basicauthentication.model.Role;
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
