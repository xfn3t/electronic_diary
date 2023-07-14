package com.elctronic.diary.controller;

import com.elctronic.diary.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class DiaryController {

    private int counter = 4;
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Three message"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    private Map<String, String> getMessage(String id) {
        return messages.stream()
                .filter(message -> message.get("id")
                        .equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("{id}")
    public Map<String, String> getUOne(@PathVariable String id) {
        return getMessage(id);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {

        message.put("id", String.valueOf(counter++));

        messages.add(message);

        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDB = getMessage(id);

        messageFromDB.putAll(message);
        messageFromDB.put("id", id);

        return messageFromDB;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);

        messages.remove(message);
    }
}
