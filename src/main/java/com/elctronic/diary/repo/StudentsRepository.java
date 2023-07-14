package com.elctronic.diary.repo;

import com.elctronic.diary.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Integer> {}
