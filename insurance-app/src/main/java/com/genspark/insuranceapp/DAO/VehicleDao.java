package com.genspark.insuranceapp.Dao;

import com.genspark.insuranceapp.Entity.Vehicle;

import java.util.List;

public interface VehicleDao {

    List<Vehicle> findByUsername(String username);

    Vehicle findByVin(String vin);

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    void deleteByVin(String vin);
}
