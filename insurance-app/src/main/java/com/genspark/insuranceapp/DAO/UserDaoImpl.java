package com.genspark.insuranceapp.Dao;

import com.genspark.insuranceapp.Entity.Role;
import com.genspark.insuranceapp.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("from User where username=:user", User.class);
        query.setParameter("user", username);

        try {
            User user =  query.getSingleResult();
            System.out.println(user);
            return user;
        } catch (Exception e){
            return new User();
        }
    }


    @Override
    @Transactional
    public User save(User user) {
        entityManager.createNativeQuery("INSERT INTO users (username, user_password, enabled, user_email, user_fname, user_lname, user_phone, user_address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, user.getUsername())
                .setParameter(2, user.getPassword())
                .setParameter(3,user.isEnabled())
                .setParameter(4,user.getEmail())
                .setParameter(5,user.getFirstName())
                .setParameter(6,user.getLastName())
                .setParameter(7,user.getPhone())
                .setParameter(8,user.getAddress())
                .executeUpdate();
        for(Role role: user.getRoles()){
            entityManager.createNativeQuery("INSERT INTO users_roles (username, role_id) VALUES (?, ?)")
                    .setParameter(1, user.getUsername())
                    .setParameter(2,role.getId())
                    .executeUpdate();
        }
        return user;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        List<User> list = query.getResultList();
        return list;
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        entityManager.createNativeQuery("DELETE FROM users_roles WHERE username = ?")
                .setParameter(1, username)
                .executeUpdate();
        entityManager.createNativeQuery("DELETE FROM users WHERE username = ?")
                .setParameter(1, username)
                .executeUpdate();

    }

    @Override
    @Transactional
    public User updatePassword(User user) {
        entityManager.createNativeQuery("UPDATE users SET password = ? WHERE username = ?")
                .setParameter(1, user.getPassword())
                .setParameter(2, user.getUsername())
                .executeUpdate();
        return findByUsername(user.getUsername());
    }
}
