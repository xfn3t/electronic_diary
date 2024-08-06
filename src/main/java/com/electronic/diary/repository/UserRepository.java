package com.electronic.diary.repository;

import com.electronic.diary.DTO.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    //@Query(value = "SELECT u FROM User u WHERE username = ?1")
    Optional<User> findByUsername(String username);

    default void updateById(Long id, User newUser) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        System.out.println("ID FROM UPDATE: " + id);
        User user = session.get(User.class, id);

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