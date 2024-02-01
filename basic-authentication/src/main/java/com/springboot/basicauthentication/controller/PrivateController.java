package com.springboot.basicauthentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {

    @GetMapping
    public String helloWorldPrivate(){
        return "Hello world from private";
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String helloWorldPrivateUser(){
        return "Hello world from private-user";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String helloWorldPrivateAdmin(){
        return "Hello world from private-admin";
    }

}
