package com.electronic.diary.controllers;

import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.service.UserService;
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
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/existEntity")
    public Boolean findByEntity(@RequestBody UserDTO user) {
        return userService.existEntity(user);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserDTO user) {
        try {
            if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
                log.error("Empty user object");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty user object");
            }
            if (userService.existEntity(user)) {
                log.error("User exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User exist");
            }
            userService.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Success saved \n" + user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not saved");
        }
    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable Long id) {
        if (id == null) return HttpStatus.BAD_REQUEST;
        userService.deleteById(id);
        return HttpStatus.OK;
    }

    @DeleteMapping
    public HttpStatus deleteUser(@RequestBody UserDTO user) {
        if (user == null) return HttpStatus.BAD_REQUEST;
        if (!userService.existEntity(user)) return HttpStatus.BAD_REQUEST;

        userService.delete(user);
        log.info("\n DELETED: " + user);
        return HttpStatus.OK;
    }
}
