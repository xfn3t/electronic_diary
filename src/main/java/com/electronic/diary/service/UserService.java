package com.electronic.diary.service;

import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.DTO.UserTableDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAllUsers();
    Optional<UserDTO> findById(Long id);
    void save(UserDTO user);
    void delete(UserDTO user);
    void deleteById(Long id);
    Boolean existEntity(UserDTO user);

    Map<Optional<UserDTO>, List<UserTableDTO>> findByIdUsersWithTable(Long user_id);
    List<Map<Optional<UserDTO>, List<UserTableDTO>>> findAllUsersWithTables();

}
