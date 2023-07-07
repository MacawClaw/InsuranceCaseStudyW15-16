package com.genspark.insuranceapp.Dao;

import com.genspark.insuranceapp.Entity.Role;
import com.genspark.insuranceapp.Entity.User;
import com.genspark.insuranceapp.Entity.Vehicle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleDaoImpl implements VehicleDao {

    private EntityManager entityManager;

    public VehicleDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Vehicle> findByUsername(String username) {
        TypedQuery<Vehicle> query = entityManager.createQuery("select v from Vehicle v where v.user.username = :user", Vehicle.class);
        query.setParameter("user", username);
        //List<Vehicle> list = query.getResultList();

        try {
            List<Vehicle> list = query.getResultList();
            System.out.println(list);
            return list;
        } catch (Exception e){
            List<Vehicle> list = new ArrayList<>();
            return list;
        }
    }

    @Override
    public Vehicle findByVin(String vin) {
        TypedQuery<Vehicle> query = entityManager.createQuery("from Vehicle where vin=:vehicle", Vehicle.class);
        query.setParameter("vehicle", vin);

        try {
            Vehicle vehicle =  query.getSingleResult();
            System.out.println(vehicle);
            return vehicle;
        } catch (Exception e){
            return new Vehicle();
        }
    }

    @Override
    @Transactional
    public Vehicle saveVehicle(Vehicle vehicle) {
        entityManager.createNativeQuery("INSERT INTO vehicles (vehicle_vin, vehicle_year, vehicle_make, vehicle_model, username) VALUES (?, ?, ?, ?, ?)")
                .setParameter(1, vehicle.getVin())
                .setParameter(2, vehicle.getYear())
                .setParameter(3,vehicle.getMake())
                .setParameter(4,vehicle.getModel())
                .setParameter(5,vehicle.getUser().getUsername())
                .executeUpdate();
        /*
        for(Role role: user.getRoles()){
            entityManager.createNativeQuery("INSERT INTO users_roles (username, role_id) VALUES (?, ?)")
                    .setParameter(1, user.getUsername())
                    .setParameter(2,role.getId())
                    .executeUpdate();
        }
         */
        return vehicle;
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(Vehicle vehicle) {
        entityManager.createNativeQuery("UPDATE vehicles SET vehicle_make = ?, vehicle_model = ?, vehicle_year = ? WHERE vehicle_vin = ?")
                .setParameter(1, vehicle.getMake())
                .setParameter(2, vehicle.getModel())
                .setParameter(3, vehicle.getYear())
                .setParameter(4, vehicle.getVin())
                .executeUpdate();
        return findByVin(vehicle.getVin());
    }

    @Override
    @Transactional
    public List<Vehicle> getAllVehicles() {
        TypedQuery<Vehicle> query = entityManager.createQuery("from Vehicle", Vehicle.class);
        List<Vehicle> list = query.getResultList();
        return list;
    }

    @Override
    @Transactional
    public void deleteByVin(String vin) {
        entityManager.createNativeQuery("DELETE FROM vehicles WHERE vehicle_vin = ?")
                .setParameter(1, vin)
                .executeUpdate();
    }
}
