package com.elctronic.diary.controller;

import com.elctronic.diary.UserData;
import com.elctronic.diary.repo.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<UserData> getAll() {
        return user.findAll();
    }

    @GetMapping("data/{id}")
    public UserData getUserData(@PathVariable String id) {
        return getUser(id);
    }


    @DeleteMapping("{id}")
    public void delUser(@PathVariable String id) {
        user.delete(getUser(id));
    }
}
