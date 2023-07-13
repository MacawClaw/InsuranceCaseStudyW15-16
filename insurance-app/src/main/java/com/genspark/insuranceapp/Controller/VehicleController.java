package com.genspark.insuranceapp.Controller;

import com.genspark.insuranceapp.Entity.*;
import com.genspark.insuranceapp.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/vehicles")
    public Vehicle saveVehicle(@RequestBody Vehicle vehicle) {

        return vehicleService.saveVehicle(vehicle);
    }

    @PutMapping("/vehicles/{vin}")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle, @PathVariable String vin) {
        System.out.println(vehicle.getVin().equals(vin));
        return vehicleService.updateVehicle(vehicle);
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/vehicles/users/{username}")
    public List<Vehicle> getVehiclesByUsername(@PathVariable String username) {
        return vehicleService.getVehiclesByUsername(username);
    }

    @GetMapping("/vehicles/{vin}")
    public Vehicle getVehicleByVin(@PathVariable String vin) {
        return vehicleService.getVehicleByVin(vin);
    }

    @DeleteMapping("/vehicles/{vin}")
    public void deleteVehicleByVin(@PathVariable String vin) {
        vehicleService.deleteVehicle(vin);
    }

}
