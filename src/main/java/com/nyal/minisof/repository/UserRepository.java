package com.nyal.minisof.repository;

import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT * FROM `user_entity`  WHERE name = ?1", nativeQuery = true)
    UserEntity getUserByName(String username);
    @Query(value = "SELECT COUNT(*) FROM `user_entity` WHERE name = ?1", nativeQuery = true)
    int existsByUsername(String username);
    @Query(value = "SELECT * FROM `user_entity` WHERE phone = ?1", nativeQuery = true)
    Optional<UserEntity> findByNumberPhone(String numberPhone);
}
