package com.electronic.diary.repository;

import com.electronic.diary.DTO.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {

    @Query("FROM Item WHERE user.userId = ?1")
    List<Item> findItemsByUserId(Long userId);

    void deleteById(Long id);

    default void update(Long id, Item newItem) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Item item = session.get(Item.class, id);

        if (item != null) {

            item.setItem_name(newItem.getItem_name());
            item.setItem_content(newItem.getItem_content());

            session.update(item);

            transaction.commit();
        } else {
            System.out.println("Item with id: " + id + " not found.");
        }

        session.close();
    }
}