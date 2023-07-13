package com.genspark.insuranceapp.Service;

import com.genspark.insuranceapp.Entity.Claim;

import java.util.List;

public interface ClaimService {

    List<Claim> getClaimsByUsername(String username);

    List<Claim> getClaimsByVehicleVin(String vin);

    List<Claim> getClaimsByClaimStatus(long statusId);

    Claim getClaimByClaimId(int claimId);

    List<Claim> getAllClaims();

    Claim saveClaim(Claim claim);

    Claim updateClaim(Claim claim);

    void deleteClaim(int claimId);
}
