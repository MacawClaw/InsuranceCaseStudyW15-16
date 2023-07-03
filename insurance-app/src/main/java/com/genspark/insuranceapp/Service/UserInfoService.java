package com.genspark.insuranceapp.Service;

import com.genspark.insuranceapp.Entity.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserInfoService extends UserDetailsService {

    List<User> findAll();

    User findByUserName(String username);

    User saveUser(User user);

    User saveAdjuster(User user);

    List<User> deleteByUsername(String username);

    User updatePassword(User user);
}
