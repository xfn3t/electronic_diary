package com.elctronic.diary.controller;

import com.elctronic.diary.UserTable;
import com.elctronic.diary.repo.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class DiaryController {
    // xfn3t
    @Autowired
    private UserTableRepository studentRepo;

    @GetMapping
    public List<UserTable> listAll(Model model) {
        
        List<UserTable> listStudents = studentRepo.findAll();
        model.addAttribute("listStudents", listStudents);

        return listStudents;
    }

    private UserTable getMessage(Long id) {

        int i = 0;

        List<UserTable> listStudents = studentRepo.findAll();

        // Rewrite to lambda
        for (UserTable x : listStudents) {
            if (x.getId().equals(id)) {
                break;
            }
            i++;
        }
        return listStudents.get(i);
    }

    @GetMapping("{id}")
    public UserTable getUOne(@PathVariable Long id) {
        return getMessage(id);
    }

    @PostMapping
    public UserTable create(@RequestBody UserTable message) {

        List<UserTable> listStudents = studentRepo.findAll();

        if ( listStudents.size() > 0 ) {
            if ( listStudents.get( listStudents.size()-1).getId() > 0 )
                message.setId( listStudents.get(listStudents.size()-1).getId() + 1 );
        } else {
            message.setId(1L);
        }

        return studentRepo.save(message);
    }

    @PutMapping("{id}")
    public UserTable update(@PathVariable Long id, @RequestBody UserTable message) {

        UserTable messageFromDB = getMessage(id);

        messageFromDB.setId(id);
        messageFromDB.setItem(message.getItem());
        messageFromDB.setGrade(message.getGrade());

        studentRepo.save(messageFromDB);

        return messageFromDB;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        studentRepo.delete(getMessage(id));
    }
}
