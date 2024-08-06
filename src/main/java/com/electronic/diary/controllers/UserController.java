package com.electronic.diary.controllers;

import com.electronic.diary.DTO.User;
import com.electronic.diary.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j // for logs in console --> variable "log"
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        log.info("Get user by id: " + id);
        return userRepository.findById(id);
    }

    @GetMapping("/existUser")
    public Boolean findByEntity(@RequestBody User user) {
        return userRepository.existsByUsername(user.getUsername()) && userRepository.existsById(user.getUserId());
    }

    @GetMapping("/username")
    public Optional<User> findByUsername() {
        return userRepository.findByUsername("xfn3t");
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
                log.error("Empty user object");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty user object");
            }
            if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
                log.error("User exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User exist");
            }
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Success saved \n" + user);

        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage());
            log.info("User details: {}", user);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not saved " + user);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        System.out.println("ID: " + id);
        User u =  user;
        if(userRepository.existsById(id) && id != null) {
            userRepository.updateById(id, user);
            Optional<User> uu = userRepository.findById(id);
            log.info("Entity: " + u + " updated to: " + uu);
            return ResponseEntity.status(HttpStatus.OK).body("Entity: " + u + " updated to: " + uu);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not updated:  " + user);

    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable Long id) {
        if (id == null) return HttpStatus.BAD_REQUEST;
        userRepository.deleteById(id);
        //userTableService.deleteTableByUserId(id);
        return HttpStatus.OK;
    }

    @DeleteMapping
    public HttpStatus deleteUser(@RequestBody User user) {
        if (user == null) return HttpStatus.BAD_REQUEST;
        if (!userRepository.existsById(user.getUserId())) return HttpStatus.BAD_REQUEST;

        user = userRepository.findByUsername(user.getUsername()).get();
        userRepository.delete(user);
        log.info("\n DELETED: " + user);
        return HttpStatus.OK;
    }
}
