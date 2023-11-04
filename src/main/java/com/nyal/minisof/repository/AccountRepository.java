package com.nyal.minisof.repository;

import com.nyal.minisof.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    @Query(value = "SELECT ae FROM AccountEntity ae WHERE ae.username = :username")
    AccountEntity getAccountByUsername(String username);
    @Query(value = "SELECT COUNT(*) FROM `account_entity` WHERE username = ?1", nativeQuery = true)
    int existsByUsername(String username);
}
