package com.genspark.insuranceapp;

import com.genspark.insuranceapp.Dao.*;
import com.genspark.insuranceapp.Entity.*;
import com.genspark.insuranceapp.Service.VehicleService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VehicleTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private EntityManager entityManager;

    /*
    The Data used in these tests were randomly generated.
     */
    @Test
    void correctFindVehiclesByUsername() {
        List<Vehicle> vehicles = vehicleService.getVehiclesByUsername("ogarcia");
        System.out.println(vehicles);
        assertEquals("ogarcia", vehicles.get(0).getUser().getUsername());
        assertEquals("ogarcia", vehicles.get(1).getUser().getUsername());
        assertEquals("Nissan", vehicles.get(1).getMake());
        assertEquals("Ford", vehicles.get(0).getMake());
    }


    @Test
    void correctFindVehicleByVin() {
        String vin = "1N4AL21E68N441282";
        Vehicle vehicle = vehicleService.getVehicleByVin(vin);
        System.out.println(vehicle);
        assertEquals(vehicle.getVin(), vin);
        assertEquals("ogarcia", vehicle.getUser().getUsername());
    }

    @Test
    void saveVehicle() {
        User user = new User();
        user.setUsername("jsmith");
        user.setPassword("$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2");
        user.setEnabled(true);
        user.setEmail("john@something.com");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPhone("402-388-7090");
        user.setAddress("4300 Compliment Ave. Byte,NE 68801");

        Vehicle vehicle = new Vehicle();
        String vin = "2GNALCEK4G6101834";
        vehicle.setVin(vin);
        vehicle.setYear(2015);
        vehicle.setMake("Chevrolet");
        vehicle.setModel("Equinox");
        vehicle.setUser(user);

        Vehicle vehicleReturned = vehicleService.saveVehicle(vehicle);
        assertEquals(vin, vehicleReturned.getVin());
        assertEquals("jsmith", vehicleReturned.getUser().getUsername());

    }


    @Test
    void updateVehicle() {
        User user = new User();
        user.setUsername("jsmith");
        user.setPassword("$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2");
        user.setEnabled(true);
        user.setEmail("john@something.com");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPhone("402-388-7090");
        user.setAddress("4300 Compliment Ave. Byte,NE 68801");

        Vehicle vehicle = new Vehicle();
        String vin = "2GNALCEK4G6101834";
        vehicle.setVin(vin);
        vehicle.setYear(2016);
        vehicle.setMake("Chevrolet");
        vehicle.setModel("Equinox");
        vehicle.setUser(user);

        Vehicle vehicleReturned = vehicleService.updateVehicle(vehicle);
        assertEquals(vin, vehicleReturned.getVin());
        assertEquals(2016, vehicleReturned.getYear());
    }

    @Test
    void deleteVehicle() {
        String vin = "2GNALCEK4G6101834";
        vehicleService.deleteVehicle(vin);
        Vehicle vehicleReturned = vehicleService.getVehicleByVin(vin);
        assertNull(vehicleReturned.getVin());
    }

}
