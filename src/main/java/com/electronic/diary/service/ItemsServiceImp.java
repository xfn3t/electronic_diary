package com.electronic.diary.service;

import com.electronic.diary.DTO.ItemsDTO;
import com.electronic.diary.DTO.UserDTO;
import com.electronic.diary.repository.ItemsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImp implements ItemsService {

    @Autowired
    ItemsRepository itemsRepository;

    @Override
    public List<ItemsDTO> findAll() {
        return itemsRepository.findAll();
    }

    @Override
    public List<ItemsDTO> findItemsByUserId(Long user_id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try(Session session = sessionFactory.openSession()) {
            Query<ItemsDTO> query = session.createQuery("FROM ItemsDTO WHERE user.user_id = :user_id", ItemsDTO.class);
            query.setParameter("user_id", user_id);
            session.close();

            return query.list();
        }
    }

    @Override
    public void update(Long id, ItemsDTO newItem) {

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

    @Override
    public void deleteTableByUserId(Long id) {
        findItemsByUserId(id).forEach(x -> itemsRepository.delete(x));
    }

    @Override
    public void deleteItem(Long id) {
        itemsRepository.deleteById(id);
    }

    @Override
    public void save(ItemsDTO item) {
        itemsRepository.save(item);
    }
}
