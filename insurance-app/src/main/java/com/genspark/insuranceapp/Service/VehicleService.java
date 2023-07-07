package com.genspark.insuranceapp.Service;

import com.genspark.insuranceapp.Entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getVehiclesByUsername(String username);

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle);

    void deleteVehicle(String vin);

    List<Vehicle> getAllVehicles();

    Vehicle getVehicleByVin(String vin);
}
