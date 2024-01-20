package com.electronic.diary.service;

import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAllUsers();
    Optional<UserDTO> findById(Long id);
    void save(UserDTO user);
    void delete(UserDTO user);
    void deleteById(Long id);
    Boolean existEntity(UserDTO user);

}
