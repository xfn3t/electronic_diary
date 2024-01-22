package com.electronic.diary.repository;

import com.electronic.diary.DTO.UserTableDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTableRepository extends JpaRepository<UserTableDTO, Long> {
}