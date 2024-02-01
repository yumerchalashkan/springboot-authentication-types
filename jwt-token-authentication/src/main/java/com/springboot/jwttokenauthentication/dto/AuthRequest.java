package com.springboot.jwttokenauthentication.dto;

public record AuthRequest(
        String username,
        String password
) {
}
