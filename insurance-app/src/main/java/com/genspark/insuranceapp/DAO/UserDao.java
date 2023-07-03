package com.genspark.insuranceapp.Dao;

import com.genspark.insuranceapp.Entity.User;

import java.util.List;

public interface UserDao {

    User findByUsername(String username);

    User save(User user);

    List<User> getAllUsers();

    void deleteByUsername(String userName);

    User updatePassword(User user);
}
