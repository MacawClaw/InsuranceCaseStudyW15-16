package com.genspark.insuranceapp;

import com.genspark.insuranceapp.Dao.ClaimDao;
import com.genspark.insuranceapp.Dao.UserDao;
import com.genspark.insuranceapp.Dao.VehicleDao;
import com.genspark.insuranceapp.Entity.Claim;
import com.genspark.insuranceapp.Entity.Status;
import com.genspark.insuranceapp.Entity.User;
import com.genspark.insuranceapp.Entity.Vehicle;
import com.genspark.insuranceapp.Service.ClaimService;
import com.genspark.insuranceapp.Service.VehicleService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ClaimTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private ClaimDao claimDao;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ClaimService claimService;

    @Autowired
    private EntityManager entityManager;

    /*
    The Data used in these tests were randomly generated.
     */
    @Test
    void correctFindClaimsByUsername() {
        List<Claim> claims = claimService.getClaimsByUsername("ogarcia");
        System.out.println(claims.get(0));
        System.out.println(claims.get(1));
        System.out.println(claims.get(2));
        assertEquals("ogarcia", claims.get(0).getVehicle().getUser().getUsername());
        assertEquals("ogarcia", claims.get(1).getVehicle().getUser().getUsername());
        assertEquals("ogarcia", claims.get(2).getVehicle().getUser().getUsername());
        assertEquals(1, claims.get(0).getClaimId());
        assertEquals(2, claims.get(1).getClaimId());
        assertEquals(3, claims.get(2).getClaimId());
        //assertEquals("Nissan", claims.get(1).getMake());
        //assertEquals("Ford", claims.get(0).getMake());
    }


    @Test
    void correctFindClaimByVehicleVin() {
        String vin = "1N4AL21E68N441282";
        List<Claim> claims = claimService.getClaimsByVehicleVin(vin);
        System.out.println(claims.get(0));
        System.out.println(claims.get(1));
        assertEquals(vin, claims.get(0).getVehicle().getVin());
        assertEquals(vin, claims.get(1).getVehicle().getVin());
        assertEquals("ogarcia", claims.get(0).getUser().getUsername());
    }

    @Test
    void correctFindClaimByStatusId() {
        long statusId = 1L;
        String vin = "1N4AL21E68N441282";
        List<Claim> claims = claimService.getClaimsByClaimStatus(statusId);
        System.out.println(claims.get(0));
        System.out.println(claims.get(1));
        assertEquals(vin, claims.get(0).getVehicle().getVin());
        assertEquals(vin, claims.get(1).getVehicle().getVin());
        assertEquals("ogarcia", claims.get(0).getUser().getUsername());
    }

    @Test
    void correctFindClaimByClaimId() {
        int id = 1;
        String vin = "1N4AL21E68N441282";
        String username = "ogarcia";
        Claim claim = claimService.getClaimByClaimId(id);
        System.out.println(claim);
        assertEquals(id, claim.getClaimId());
        assertEquals(vin, claim.getVehicle().getVin());
        assertEquals(username, claim.getUser().getUsername());
    }

    @Test
    void saveVehicle() {
        User user = new User();
        user.setUsername("ogarcia");
        user.setPassword("$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2");
        user.setEnabled(true);
        user.setEmail("omar@something.com");
        user.setFirstName("Omar");
        user.setLastName("Garcia");
        user.setPhone("202-918-2132");
        user.setAddress("4269 Hello World Rd. Exclamation,KS 67003");

        Vehicle vehicle = new Vehicle();
        String vin = "1FMYU02B32KA63604";
        vehicle.setVin(vin);
        vehicle.setYear(2002);
        vehicle.setMake("Ford");
        vehicle.setModel("Escape");
        vehicle.setUser(user);

        Status status = new Status(1L, "Being Evaluated");

        Claim claim = new Claim();
        claim.setClaimStatus(status);
        claim.setClaimDescription("My car was hit by a deer.");
        claim.setClaimCost(new BigDecimal("1200.00"));
        claim.setAdjusterNotes("Quote needed.");
        claim.setVehicle(vehicle);
        claim.setUser(user);

        Claim claimReturned = claimService.saveClaim(claim);
        //assertEquals(4, claimReturned.getClaimId());
        assertEquals("ogarcia", claimReturned.getUser().getUsername());

    }

    @Test
    void updateClaim() {
        User user = new User();
        user.setUsername("ogarcia");
        user.setPassword("$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2");
        user.setEnabled(true);
        user.setEmail("omar@something.com");
        user.setFirstName("Omar");
        user.setLastName("Garcia");
        user.setPhone("202-918-2132");
        user.setAddress("4269 Hello World Rd. Exclamation,KS 67003");

        Vehicle vehicle = new Vehicle();
        String vin = "1FMYU02B32KA63604";
        vehicle.setVin(vin);
        vehicle.setYear(2002);
        vehicle.setMake("Ford");
        vehicle.setModel("Escape");
        vehicle.setUser(user);

        Status status = new Status(1L, "Being Evaluated");

        Claim claim = new Claim();
        claim.setClaimId(4);
        claim.setClaimStatus(status);
        claim.setClaimDescription("My car was hit by a deer.");
        claim.setClaimCost(new BigDecimal("1300.00"));
        claim.setAdjusterNotes("Quote needed.");
        claim.setVehicle(vehicle);
        claim.setUser(user);

        Claim claimReturned = claimService.updateClaim(claim);
        assertEquals("ogarcia", claimReturned.getUser().getUsername());
        assertEquals(new BigDecimal("1300.00"), claimReturned.getClaimCost());
    }


    @Test
    void deleteClaim() {
        int id = 4;
        claimService.deleteClaim(4);
        Claim claimReturned = claimService.getClaimByClaimId(id);
        assertEquals(0, claimReturned.getClaimId());
    }
}
