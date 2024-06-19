package com.electronic.diary.repository;

import com.electronic.diary.DTO.ItemsDTO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsDTO, Long> {

    @Query("FROM ItemsDTO WHERE user.user_id = ?1")
    List<ItemsDTO> findItemsByUserId(Long user_id);

    void deleteById(Long id);

    default void update(Long id, ItemsDTO newItem) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        ItemsDTO item = session.get(ItemsDTO.class, id);

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