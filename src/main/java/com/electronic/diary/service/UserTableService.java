package com.electronic.diary.service;

import com.electronic.diary.DTO.UserTableDTO;

import java.util.List;

public interface UserTableService {
    List<UserTableDTO> findTableByUserId(Long user_id);
}
