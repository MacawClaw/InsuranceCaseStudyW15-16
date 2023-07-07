package com.genspark.insuranceapp.Service;

import com.genspark.insuranceapp.Dao.UserDao;
import com.genspark.insuranceapp.Dao.VehicleDao;
import com.genspark.insuranceapp.Dao.VehicleDaoImpl;
import com.genspark.insuranceapp.Entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    private VehicleDao vehicleDao;

    private UserDao userDao;

    @Autowired
    public VehicleServiceImpl(VehicleDaoImpl vehicleDao, UserDao userDao){
        this.vehicleDao = vehicleDao;
        this.userDao = userDao;
    }

    @Override
    public List<Vehicle> getVehiclesByUsername(String username) {
        return vehicleDao.findByUsername(username);
    };

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleDao.saveVehicle(vehicle);
    };

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleDao.updateVehicle(vehicle);
    };

    @Override
    public void deleteVehicle(String vin) {
        vehicleDao.deleteByVin(vin);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    };

    @Override
    public Vehicle getVehicleByVin(String vin) {
        return vehicleDao.findByVin(vin);
    };
}
