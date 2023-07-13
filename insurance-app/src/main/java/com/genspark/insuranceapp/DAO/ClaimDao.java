package com.genspark.insuranceapp.Dao;

import com.genspark.insuranceapp.Entity.Claim;

import java.util.List;

public interface ClaimDao {

    List<Claim> findByUsername(String username);

    List<Claim> findByVehicleVin(String vin);

    List<Claim> findByClaimStatus(long statusId);

    Claim findByClaimId(int claimId);

    Claim saveClaim(Claim claim);

    Claim updateClaim(Claim claim);

    List<Claim> getAllClaims();

    void deleteByClaimId(int claimId);
}
