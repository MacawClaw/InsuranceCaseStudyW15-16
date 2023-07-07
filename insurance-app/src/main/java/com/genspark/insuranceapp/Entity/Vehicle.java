package com.genspark.insuranceapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @Column(name="vehicle_vin")
    private String vin;

    @Column(name = "vehicle_year")
    private int year;

    @Column(name = "vehicle_make")
    private String make;

    @Column(name = "vehicle_model")
    private String model;

    @ManyToOne
    @JoinColumn(name="username", referencedColumnName="username")
    private User user;

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin=" + vin +
                ", year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", username=" + user.getUsername() +
                '}';
    }

}
