package com.elctronic.diary.repo;

import com.elctronic.diary.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
