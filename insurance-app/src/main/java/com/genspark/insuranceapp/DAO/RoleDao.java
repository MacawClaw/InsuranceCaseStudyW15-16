package com.genspark.insuranceapp.Dao;

import com.genspark.insuranceapp.Entity.Role;

public interface RoleDao {

    public Role findRoleByName(String roleName);
}
