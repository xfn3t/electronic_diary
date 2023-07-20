package com.elctronic.diary.repo;

import com.elctronic.diary.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {}
