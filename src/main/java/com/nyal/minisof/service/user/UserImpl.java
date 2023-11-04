package com.nyal.minisof.service.user;

import com.nyal.minisof.model.UserEntity;
import com.nyal.minisof.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void delete(UserEntity user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteAll(List<UserEntity> listUser) {
        userRepository.deleteAll(listUser);
    }

    @Override
    public void deleterAllById(List<Integer> listUserId) {
        userRepository.deleteAllById(listUserId);
    }

    @Override
    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean exist(String username) {
        return userRepository.existsByUsername(username)!=0;
    }

    @Override
    public boolean existById(Integer userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> findAllById(List<Integer> listUserId) {
        return userRepository.findAllById(listUserId);
    }

    @Override
    public Optional<UserEntity> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> saveAll(List<UserEntity> listUser) {
        return userRepository.saveAll(listUser);
    }
}
