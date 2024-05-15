package com.electronic.diary.service;

import com.electronic.diary.DTO.ItemsDTO;

import java.util.List;

public interface ItemsService {

    List<ItemsDTO> findAll();
    List<ItemsDTO> findItemsByUserId(Long user_id);
    void save(ItemsDTO item);

    void update(Long id, ItemsDTO newItem);

    void deleteTableByUserId(Long id);
    void deleteItem(Long id);

}
