package com.nyal.minisof.service.user;

import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    long count();
    void delete(UserEntity user);
    void deleteAll();
    void deleteAll(List<UserEntity> listUser);
    void deleterAllById(List<Integer> ids);
    void deleteById(Integer id);
    boolean exist(String username);
    boolean existById(Integer id);
    List<UserEntity> findAll();
    List<UserEntity> findAllById(List<Integer> ids);
    Optional<UserEntity> findById(Integer id);
    Optional<UserEntity> findByNumberPhone(String numberPhone);
    UserEntity save(UserEntity user);
    List<UserEntity> saveAll(List<UserEntity> listUser);
    UserEntity getUserByName(String username);
}
