package com.electronic.diary.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MappingController {



    @GetMapping("/")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        String username = authentication.getName();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok("Authenticated user: " + username + ", Roles: " + userDetails.getAuthorities());
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
