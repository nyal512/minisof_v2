package com.nyal.minisof.service.account;

import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;
    @Override
    public long count() {
        return accountRepository.count();
    }

    @Override
    public void delete(AccountEntity account) {
        accountRepository.delete(account);
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }

    @Override
    public void deleteAll(List<AccountEntity> listAccount) {
        accountRepository.deleteAll(listAccount);
    }

    @Override
    public void deleterAllById(List<Integer> listAccountId) {
        accountRepository.deleteAllById(listAccountId);
    }

    @Override
    public void deleteById(Integer accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public boolean exist(String username) {
        return accountRepository.existsByUsername(username) != 0;
    }

    @Override
    public boolean existById(Integer accountId) {
        return accountRepository.existsById(accountId);
    }

    @Override
    public List<AccountEntity> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<AccountEntity> findAllById(List<Integer> listAccountId) {
        return accountRepository.findAllById(listAccountId);
    }

    @Override
    public Optional<AccountEntity> findById(Integer listAccountId) {
        return accountRepository.findById(listAccountId);
    }

    @Override
    public AccountEntity findByUserId(Integer userId) {
        return accountRepository.getAccountByUserId(userId);
    }

    @Override
    public AccountEntity save(AccountEntity user) {
        return accountRepository.save(user);
    }

    @Override
    public List<AccountEntity> saveAll(List<AccountEntity> listUser) {
        return accountRepository.saveAll(listUser);
    }

    @Override
    public AccountEntity getAccountByUsername(String username) {
        return accountRepository.getAccountByUsername(username);
    }

    @Override
    public boolean authenticateAccount(String username, String password) {
        AccountEntity account = getAccountByUsername(username);
        return account != null && account.getPassword().equals(password);
    }

    @Override
    public List<AccountEntity> findAllByName(String key) {
        return accountRepository.findAllByName(key);
    }
}
