package com.elctronic.diary.controller;

import com.elctronic.diary.UserData;
import com.elctronic.diary.repo.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserDataController {

    @Autowired
    private UserDataRepository user;

    private UserData getUser(String id) {

        int i = 0;

        List<UserData> userData = user.findAll();

        // Rewrite to lambda
        for (UserData x : userData) {
            if (x.getId().equals(id)) {
                break;
            }
            i++;
        }
        return userData.get(i);
    }


    @GetMapping("data/{id}")
    public UserData getUserData(@PathVariable String id) {
        return getUser(id);
    }

    @PostMapping
    public UserData addUser(@RequestBody UserData users) {

        List<UserData> userData = user.findAll();

        users.setId(UUID.randomUUID().toString());

        return user.save(users);
    }

    @DeleteMapping("{id}")
    public void delUser(@PathVariable String id) {
        user.delete(getUser(id));
    }
}
