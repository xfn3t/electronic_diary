package com.electronic.diary.controllers;

import com.electronic.diary.DTO.ItemsDTO;
import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.repository.ItemsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // for logs in console --> variable "log"
@RestController
@RequestMapping("/api/users/{id}/table")
public class UserTablesController {

    @Autowired
    private ItemsRepository itemsRepository;

    @GetMapping
    public List<ItemsDTO> findAllByUserId(@PathVariable Long id) {
        log.info("Get all user tables");
        return itemsRepository.findItemsByUserId(id);
    }

    @PostMapping
    public void addItem(@RequestBody List<ItemsDTO> items, @PathVariable Long id) {

        if (!items.isEmpty())
            for (ItemsDTO item: items) {
                UserDTO u = new UserDTO();
                u.setUser_id(id);
                item.setUser(u);
                itemsRepository.save(item);
            }
    }

    @PutMapping("/item")
    public void updateItem(@PathVariable Long id, @RequestBody ItemsDTO item) {
        itemsRepository.update(id, item);
    }

    @DeleteMapping("/item")
    public void deleteItem(@PathVariable Long id) {
        itemsRepository.deleteById(id);
    }
}
