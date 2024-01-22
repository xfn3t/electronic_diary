package com.electronic.diary.service;


import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.DTO.UserTableDTO;
import com.electronic.diary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final UserTableService userTableService = new UserTableServiceImp();

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Map<Optional<UserDTO>, List<UserTableDTO>>> findAllUsersWithTables() {

        List<Map<Optional<UserDTO>, List<UserTableDTO>>> list = new ArrayList<>();

        for(UserDTO user: userRepository.findAll()) {
            Map<Optional<UserDTO>, List<UserTableDTO>> table = new HashMap<>();
            table.put(userRepository.findById(user.getId()), userTableService.findTableByUserId(user.getId()));
            list.add(table);
        }
        return list;
    }

    @Override
    public Map<Optional<UserDTO>, List<UserTableDTO>> findByIdUsersWithTable(Long user_id) {

        System.out.println(userRepository.findById(user_id));
        if (userRepository.findById(user_id).isEmpty())
            return new HashMap<>();

        Map<Optional<UserDTO>, List<UserTableDTO>> table = new HashMap<>();
        table.put(userRepository.findById(user_id), userTableService.findTableByUserId(user_id));
        return table;
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
