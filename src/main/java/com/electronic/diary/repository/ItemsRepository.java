package com.electronic.diary.repository;

import com.electronic.diary.DTO.ItemsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsDTO, Long> {
}