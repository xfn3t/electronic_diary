package com.electronic.diary.service;

import com.electronic.diary.DTO.UserTableDTO;
import com.electronic.diary.repository.UserRepository;
import com.electronic.diary.repository.UserTableRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTableServiceImp implements UserTableService {

    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public List<UserTableDTO> findTableByUserId(Long user_id) {

        try(Session session = sessionFactory.openSession()) {
            Query<UserTableDTO> query = session.createQuery("from UserTableDTO where user_id = :user_id", UserTableDTO.class);
            query.setParameter("user_id", user_id);
            return query.list();
        }
    }
}
