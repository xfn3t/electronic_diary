package com.elctronic.diary.controller;

import com.elctronic.diary.UserTable;
import com.elctronic.diary.repo.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/table")
public class DiaryController {
    // xfn3t
    @Autowired
    private UserTableRepository table;

    /*
    @GetMapping
    public List<UserTable> listAll(Model model) {
        
        List<UserTable> AllTable = table.findAll();
        model.addAttribute("AllTable", AllTable);

        return AllTable;
    }
    */
    @RequestMapping(path = {"/",""}, method = {RequestMethod.GET})

    public ModelAndView welcome() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page.html");
        return modelAndView;
    }

    private UserTable getMessage(Long id) {

        int i = 0;

        List<UserTable> AllTable = table.findAll();

        // Rewrite to lambda
        for (UserTable x : AllTable) {
            if (x.getId().equals(id)) {
                break;
            }
            i++;
        }
        return AllTable.get(i);
    }

    @GetMapping("{id}")
    public UserTable getUOne(@PathVariable Long id) {
        return getMessage(id);
    }

    @PostMapping
    public UserTable create (@RequestBody UserTable message) throws IllegalAccessException {

        List<UserTable> AllTable = table.findAll();

        if ( AllTable.size() > 0 ) {
            if ( AllTable.get( AllTable.size()-1 ).getId() > 0 )
                message.setId( AllTable.get( AllTable.size()-1 ).getId() + 1 );
        } else {
            message.setId(1L);
        }

        return table.save(message);
    }

    @PutMapping("{id}")
    public UserTable update(@PathVariable Long id, @RequestBody UserTable message) {

        UserTable messageFromDB = getMessage(id);

        messageFromDB.setId(id);
        messageFromDB.setItem(message.getItem());
        messageFromDB.setGrade(message.getGrade());

        table.save(messageFromDB);

        return messageFromDB;
    }

    @DeleteMapping("{id}")
        public void delete(@PathVariable Long id) {
        table.delete(getMessage(id));
    }
}
