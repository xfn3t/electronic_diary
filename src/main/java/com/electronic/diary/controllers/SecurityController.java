package com.electronic.diary.controllers;

import com.electronic.diary.DTO.AuthenticationResponse;
import com.electronic.diary.DTO.SignIn;
import com.electronic.diary.DTO.SignUp;
import com.electronic.diary.DTO.User;
import com.electronic.diary.config.security.JwtUtil;
import com.electronic.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignIn signIn) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signIn.username(), signIn.password()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUp signUp) {
        if (userRepository.existsByUsername(signUp.username()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");

        if (userRepository.existsByEmail(signUp.email()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");

        User user = new User();
        user.setUsername(signUp.username());
        user.setEmail(signUp.email());
        user.setPassword(passwordEncoder.encode(signUp.password()));  // Encode password

        userRepository.save(user);

        return ResponseEntity.ok("Registration successful");
    }
}
