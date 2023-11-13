package com.nyal.minisof.service.account;

import com.nyal.minisof.model.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    long count();
    void delete(AccountEntity account);
    void deleteAll();
    void deleteAll(List<AccountEntity> listUser);
    void deleterAllById(List<Integer> ids);
    void deleteById(Integer id);
    boolean exist(String username);
    boolean existById(Integer id);
    List<AccountEntity> findAll();
    List<AccountEntity> findAllById(List<Integer> ids);
    List<AccountEntity> findAllByName(String key);
    Optional<AccountEntity> findById(Integer id);
    AccountEntity findByUserId(Integer userId);
    AccountEntity save(AccountEntity user);
    List<AccountEntity> saveAll(List<AccountEntity> listUser);
    AccountEntity getAccountByUsername(String username);
    boolean authenticateAccount(String username, String password);
}
