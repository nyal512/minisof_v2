package com.nyal.minisof.repository;

import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    @Query(value = "SELECT * FROM `account_entity`  WHERE username = ?1", nativeQuery = true)
    AccountEntity getAccountByUsername(String username);
    @Query(value = "SELECT COUNT(*) FROM `account_entity` WHERE username = ?1", nativeQuery = true)
    int existsByUsername(String username);
    @Query(value = "SELECT * FROM `account_entity` WHERE username LIKE %?1%", nativeQuery = true)
    public List<AccountEntity> findAllByName(String key);
    @Query(value = "SELECT * FROM `account_entity`  WHERE user_id = ?1", nativeQuery = true)
    AccountEntity getAccountByUserId(Integer userId);
}
