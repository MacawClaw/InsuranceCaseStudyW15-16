package com.genspark.insuranceapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="username")
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name="user_email")
    private String email;

    @Column(name="user_fname")
    private String firstName;

    @Column(name="user_lname")
    private String lastName;

    @Column(name="user_phone")
    private String phone;

    @Column(name="user_address")
    private String address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first name='" + firstName + '\'' +
                ", last name='" + lastName + '\'' +
                ", phone=" + phone + '\'' +
                ", address=" + address + '\'' +
                ", roles=" + roles +
                '}';
    }

}
