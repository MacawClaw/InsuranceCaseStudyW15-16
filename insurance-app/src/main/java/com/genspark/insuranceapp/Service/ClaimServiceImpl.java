package com.genspark.insuranceapp.Service;

import com.genspark.insuranceapp.Dao.ClaimDao;
import com.genspark.insuranceapp.Dao.UserDao;
import com.genspark.insuranceapp.Dao.VehicleDao;
import com.genspark.insuranceapp.Dao.ClaimDaoImpl;
import com.genspark.insuranceapp.Entity.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService{

    private ClaimDao claimDao;

    private VehicleDao vehicleDao;

    private UserDao userDao;

    @Autowired
    public ClaimServiceImpl(ClaimDaoImpl claimDao, UserDao userDao, VehicleDao vehicleDao){
        this.claimDao = claimDao;
        this.vehicleDao = vehicleDao;
        this.userDao = userDao;
    }

    @Override
    public List<Claim> getClaimsByUsername(String username) {
        return claimDao.findByUsername(username);
    };

    @Override
    public List<Claim> getClaimsByVehicleVin(String vin) {
        return claimDao.findByVehicleVin(vin);
    };

    @Override
    public List<Claim> getClaimsByClaimStatus(long statusId) {
        return claimDao.findByClaimStatus(statusId);
    };

    @Override
    public Claim getClaimByClaimId(int claimId) {
        return claimDao.findByClaimId(claimId);
    };

    @Override
    public List<Claim> getAllClaims() {
        return claimDao.getAllClaims();
    };

    @Override
    public Claim saveClaim(Claim claim) {
        return claimDao.saveClaim(claim);
    };

    @Override
    public Claim updateClaim(Claim claim) {
        return claimDao.updateClaim(claim);
    };

    @Override
    public void deleteClaim(int claimId) {
        claimDao.deleteByClaimId(claimId);
    }
}
