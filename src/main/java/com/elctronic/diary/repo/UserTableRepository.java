package com.elctronic.diary.repo;

import com.elctronic.diary.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTableRepository extends JpaRepository<UserTable, Integer> {}
