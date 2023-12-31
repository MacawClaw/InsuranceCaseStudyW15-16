package com.genspark.insuranceapp.Service;

import com.genspark.insuranceapp.Dao.*;
import com.genspark.insuranceapp.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserDao userDao;

    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserInfoServiceImpl(UserDao userDao, RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.getAllUsers();
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setId(2L);
        role.setName("ROLE_CLIENT");
        ArrayList<Role> roleArrayList = new ArrayList<>();
        roleArrayList.add(role);
        user.setRoles(roleArrayList);
        return userDao.save(user);
    }

    @Override
    public List<User> deleteByUsername(String username) {
        this.userDao.deleteByUsername(username);
        return this.userDao.getAllUsers();
    }

    @Override
    public User saveAdjuster(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role1 = new Role();
        Role role2 = new Role();
        ArrayList<Role> roleArrayList = new ArrayList<>();
        role1.setId(1L);
        role1.setName("ROLE_ADJUSTER");
        role2.setId(2L);
        role2.setName("ROLE_CLIENT");
        roleArrayList.add(role1);
        roleArrayList.add(role2);
        user.setRoles(roleArrayList);
        return userDao.save(user);
    }

    //code was gotten and adapted from tutorials in https://www.udemy.com/course/spring-hibernate-tutorial
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userDao.findByUsername(username);
//        System.out.println(user.toString());
//        if (user == null){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for(Role role: user.getRoles()){
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                authorities);
//    }

    //code was gotten from tutorials in https://www.udemy.com/course/spring-hibernate-tutorial

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userName);
        System.out.println(user.toString());
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }


    //code was gotten from tutorials in https://www.udemy.com/course/spring-hibernate-tutorial
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User updatePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userDao.updatePassword(user);
    }
}
