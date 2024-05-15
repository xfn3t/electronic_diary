package com.electronic.diary.service;


import com.electronic.diary.DTO.ItemsDTO;
import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
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
    public void update(Long id, UserDTO newUser) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        UserDTO user = session.get(UserDTO.class, id);

        if (user != null) {

            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());

            session.update(user);

            transaction.commit();
        } else {
            System.out.println("User with id: " + id + " not found.");
        }

        session.close();
    }

    @Override
    public void delete(UserDTO user) {
        userRepository.delete(user);
    }

    @Override
    public Boolean existEntity(UserDTO user) {

        return findAllUsers()
                .stream()
                .anyMatch(
                    x -> x.getUsername().equals(user.getUsername()) ||
                         x.getEmail().equals(user.getEmail())
                );
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return findAllUsers()
                .stream()
                .filter(
                        x -> x.getUsername().equals(username)
                )
                .findFirst();
    }
}
