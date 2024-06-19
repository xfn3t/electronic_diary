package com.electronic.diary.repository;

import com.electronic.diary.DTO.UserDTO;
import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    boolean existsById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query(value = "SELECT u FROM UserDTO u WHERE username = ?1")
    UserDTO findByUsername(String username);

    default void updateById(Long id, UserDTO newUser) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        System.out.println("ID FROM UPDATE: " + id);
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

}