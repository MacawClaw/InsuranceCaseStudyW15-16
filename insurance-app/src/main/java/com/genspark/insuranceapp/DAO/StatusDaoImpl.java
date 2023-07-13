package com.genspark.insuranceapp.Dao;

import com.genspark.insuranceapp.Entity.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class StatusDaoImpl implements StatusDao{

    private EntityManager entityManager;

    public StatusDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Status findStatusByName(String statusName) {
        TypedQuery<Status> query = entityManager.createQuery("from Status where name=:statusName", Status.class);
        query.setParameter("statusName", statusName);

        try {
            Status status =  query.getSingleResult();
            return status;
        } catch (Exception e){
            return null;
        }
    }
}
