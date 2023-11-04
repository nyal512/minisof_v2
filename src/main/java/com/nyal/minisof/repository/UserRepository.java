package com.nyal.minisof.repository;

import com.nyal.minisof.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT COUNT(*) FROM `user_entity` WHERE name = ?1", nativeQuery = true)
    int existsByUsername(String username);
}
