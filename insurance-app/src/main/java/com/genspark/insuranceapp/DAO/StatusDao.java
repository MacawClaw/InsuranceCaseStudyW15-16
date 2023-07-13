package com.genspark.insuranceapp.Dao;

import com.genspark.insuranceapp.Entity.Status;

public interface StatusDao {
    public Status findStatusByName(String statusName);
}
