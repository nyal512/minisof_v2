package com.nyal.minisof.repository;

import com.nyal.minisof.model.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {
    @Query(value = "SELECT * FROM `token_entity` WHERE device_token = ?1", nativeQuery = true)
    TokenEntity findByDeviceToken(String deviceToken);
    @Query(value = "SELECT COUNT(*) FROM `token_entity` WHERE device_token = ?1 and account_id = ?2", nativeQuery = true)
    int existsByDeviceTokenAndAccountId(String deviceToken, Integer accountId);
    @Query(value = "SELECT * FROM `token_entity` WHERE device_token = ?1 and account_id = ?2", nativeQuery = true)
    TokenEntity findByDeviceTokenAndAccountId(String deviceToken, Integer accountId);
}
