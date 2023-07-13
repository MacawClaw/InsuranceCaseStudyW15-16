package com.genspark.insuranceapp.Dao;

import com.genspark.insuranceapp.Entity.Claim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClaimDaoImpl implements ClaimDao{

    private EntityManager entityManager;

    public ClaimDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Claim> findByUsername(String username) {
        TypedQuery<Claim> query = entityManager.createQuery("select c from Claim c where c.vehicle.user.username = :user", Claim.class);
        query.setParameter("user", username);
        //List<Vehicle> list = query.getResultList();

        try {
            List<Claim> list = query.getResultList();
            System.out.println(list);
            return list;
        } catch (Exception e){
            List<Claim> list = new ArrayList<>();
            return list;
        }
    }

    @Override
    public List<Claim> findByVehicleVin(String vin) {
        TypedQuery<Claim> query = entityManager.createQuery("select c from Claim c where c.vehicle.vin = :vehicle", Claim.class);
        query.setParameter("vehicle", vin);
        //List<Vehicle> list = query.getResultList();

        try {
            List<Claim> list = query.getResultList();
            System.out.println(list);
            return list;
        } catch (Exception e){
            List<Claim> list = new ArrayList<>();
            return list;
        }
    }

    @Override
    public List<Claim> findByClaimStatus(long statusId) {
        TypedQuery<Claim> query = entityManager.createQuery("select c from Claim c where c.claimStatus.id = :status", Claim.class);
        query.setParameter("status", statusId);
        //List<Vehicle> list = query.getResultList();

        try {
            List<Claim> list = query.getResultList();
            System.out.println(list);
            return list;
        } catch (Exception e){
            List<Claim> list = new ArrayList<>();
            return list;
        }
    }

    @Override
    public Claim findByClaimId(int claimId) {
        TypedQuery<Claim> query = entityManager.createQuery("from Claim where id=:claim", Claim.class);
        query.setParameter("claim", claimId);

        try {
            Claim claim =  query.getSingleResult();
            System.out.println(claim);
            return claim;
        } catch (Exception e){
            return new Claim();
        }
    }

    @Override
    @Transactional
    public Claim saveClaim(Claim claim) {
        entityManager.createNativeQuery("INSERT INTO claims (status_id, claim_description, claim_cost, adjuster_notes, vehicle_vin, username) VALUES (?, ?, ?, ?, ?, ?)")
                .setParameter(1, claim.getClaimStatus().getId())
                .setParameter(2, claim.getClaimDescription())
                .setParameter(3, claim.getClaimCost())
                .setParameter(4, claim.getAdjusterNotes())
                .setParameter(5, claim.getVehicle().getVin())
                .setParameter(6, claim.getUser().getUsername())
                .executeUpdate();
        /*
        for(Role role: user.getRoles()){
            entityManager.createNativeQuery("INSERT INTO users_roles (username, role_id) VALUES (?, ?)")
                    .setParameter(1, user.getUsername())
                    .setParameter(2,role.getId())
                    .executeUpdate();
        }
         */
        return claim;
    }

    @Override
    @Transactional
    public Claim updateClaim(Claim claim) {
        entityManager.createNativeQuery("UPDATE claims SET status_id = ?, claim_description = ?, claim_cost = ?, adjuster_notes = ? WHERE claim_id = ?")
                .setParameter(1, claim.getClaimStatus().getId())
                .setParameter(2, claim.getClaimDescription())
                .setParameter(3, claim.getClaimCost())
                .setParameter(4, claim.getAdjusterNotes())
                .setParameter(5, claim.getClaimId())
                .executeUpdate();
        return findByClaimId(claim.getClaimId());
    }

    @Override
    @Transactional
    public List<Claim> getAllClaims() {
        TypedQuery<Claim> query = entityManager.createQuery("from Claim", Claim.class);
        List<Claim> list = query.getResultList();
        return list;
    }

    @Override
    @Transactional
    public void deleteByClaimId(int claimId) {
        entityManager.createNativeQuery("DELETE FROM claims WHERE claim_id = ?")
                .setParameter(1, claimId)
                .executeUpdate();
    }
}
