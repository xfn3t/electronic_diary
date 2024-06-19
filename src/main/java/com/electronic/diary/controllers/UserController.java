package com.electronic.diary.controllers;

import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public List<UserDTO> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> getUserById(@PathVariable Long id) {
        log.info("Get user by id: " + id);
        return userRepository.findById(id);
    }

    @GetMapping("/existUser")
    public Boolean findByEntity(@RequestBody UserDTO user) {
        return userRepository.existsByUsername(user.getUsername()) && userRepository.existsById(user.getUser_id());
    }

    @GetMapping("/username")
    public Optional<UserDTO> findByUsername() {
        return Optional.ofNullable(userRepository.findByUsername("xfn3t"));
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserDTO user) {
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
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        System.out.println("ID: " + id);
        UserDTO u =  user;
        if(userRepository.existsById(id) && id != null) {
            userRepository.updateById(id, user);
            Optional<UserDTO> uu = userRepository.findById(id);
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
    public HttpStatus deleteUser(@RequestBody UserDTO user) {
        if (user == null) return HttpStatus.BAD_REQUEST;
        if (!userRepository.existsById(user.getUser_id())) return HttpStatus.BAD_REQUEST;

        user = userRepository.findByUsername(user.getUsername());
        userRepository.delete(user);
        log.info("\n DELETED: " + user);
        return HttpStatus.OK;
    }
}
