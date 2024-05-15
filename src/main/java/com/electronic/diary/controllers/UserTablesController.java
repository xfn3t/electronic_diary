package com.electronic.diary.controllers;

import com.electronic.diary.DTO.ItemsDTO;
import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.service.ItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // for logs in console --> variable "log"
@RestController
@RequestMapping("/api/{id}/table")
public class UserTablesController {

    @Autowired
    private ItemsService itemsService;


    @GetMapping
    public List<ItemsDTO> getAllItems(@PathVariable Long id) {
        return itemsService.findAll();
    }


    @PostMapping
    public void addItem(@RequestBody List<ItemsDTO> items, @PathVariable Long id) {

        if (!items.isEmpty())
            for (ItemsDTO item: items) {
                UserDTO u = new UserDTO();
                u.setUser_id(id);
                item.setUser(u);
                itemsService.save(item);
            }
    }

    @PutMapping("/item")
    public void updateItem(@PathVariable Long id, @RequestBody ItemsDTO item) {
        itemsService.update(id, item);
    }

    @DeleteMapping
    public void deleteAllItems(@PathVariable Long id) {
        itemsService.deleteTableByUserId(id);
    }

    @DeleteMapping("/item")
    public void deleteItem(@PathVariable Long id) {
        itemsService.deleteItem(id);
    }
}
