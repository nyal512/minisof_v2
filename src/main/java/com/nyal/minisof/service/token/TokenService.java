package com.nyal.minisof.service.token;

import com.nyal.minisof.model.ProductCartEntity;
import com.nyal.minisof.model.TokenEntity;

import java.util.List;
import java.util.Optional;

public interface TokenService {
    long count();
    void delete(TokenEntity token);
    boolean existsByDeviceTokenAndAccountId(String deviceToken, Integer accountId);
    List<TokenEntity> findAll();
    Optional<TokenEntity> findById(Integer tokenId);
    TokenEntity save(TokenEntity token);
    List<TokenEntity> saveAll(List<TokenEntity> listToken);
    TokenEntity findByDeviceToken(String deviceToken);
    TokenEntity findByDeviceTokenAndAccountId(String deviceToken, Integer accountId);
}
