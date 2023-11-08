package com.nyal.minisof.service.token;

import com.nyal.minisof.model.ProductCartEntity;
import com.nyal.minisof.model.TokenEntity;
import com.nyal.minisof.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenImpl implements TokenService{
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public long count() {
        return tokenRepository.count();
    }

    @Override
    public void delete(TokenEntity token) {
        tokenRepository.delete(token);
    }

    @Override
    public boolean existsByDeviceTokenAndAccountId(String deviceToken, Integer accountId) {
        return tokenRepository.existsByDeviceTokenAndAccountId(deviceToken, accountId)!= 0;
    }

    @Override
    public List<TokenEntity> findAll() {
        return tokenRepository.findAll();
    }

    @Override
    public Optional<TokenEntity> findById(Integer tokenId) {
        return tokenRepository.findById(tokenId);
    }

    @Override
    public TokenEntity save(TokenEntity token) {
        return tokenRepository.save(token);
    }

    @Override
    public List<TokenEntity> saveAll(List<TokenEntity> listToken) {
        return tokenRepository.saveAll(listToken);
    }

    @Override
    public TokenEntity findByDeviceToken(String deviceToken) {
        return tokenRepository.findByDeviceToken(deviceToken);
    }

    @Override
    public TokenEntity findByDeviceTokenAndAccountId(String deviceToken, Integer accountId) {
        return tokenRepository.findByDeviceTokenAndAccountId(deviceToken, accountId);
    }
}
