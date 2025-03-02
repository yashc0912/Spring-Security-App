package com.yash.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Principal principal) {
        if (principal != null) {
            return "Hello " + principal.getName() + ", Welcome to Daily Code Buffer!!";
        } else {
            return "Hello Guest, Welcome to Daily Code Buffer!!";
        }
    }
}
