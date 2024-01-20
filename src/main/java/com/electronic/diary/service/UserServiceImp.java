package com.electronic.diary.service;


import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        if(findById(id).isEmpty()) return;

        userRepository.delete(findById(id).get());
    }

    @Override
    public void save(UserDTO user) {
        Base64.Encoder enc = Base64.getEncoder();
        String password = user.getPassword();

        user.setPassword(enc.encodeToString(password.getBytes()));
        userRepository.save(user);
    }

    @Override
    public void delete(UserDTO user) {
        userRepository.delete(user);
    }

    @Override
    public Boolean existEntity(UserDTO user) {
        return !findAllUsers()
                .stream()
                .filter(
                    x -> x.getUsername().equals(user.getUsername()) &&
                         x.getEmail().equals(user.getEmail())
                )
                .toList()
                .isEmpty();
    }
}
