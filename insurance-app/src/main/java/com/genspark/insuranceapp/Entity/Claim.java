package com.genspark.insuranceapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "claims")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="claim_id")
    private int claimId;

    @Column(name = "claim_description")
    private String claimDescription;

    @Column(name = "claim_cost")
    private BigDecimal claimCost;

    @Column(name = "adjuster_notes")
    private String adjusterNotes;

    @ManyToOne
    @JoinColumn(name = "vehicle_vin", referencedColumnName="vehicle_vin")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name="status_id", referencedColumnName="status_id")
    private Status claimStatus;

    @ManyToOne
    @JoinColumn(name="username", referencedColumnName="username")
    private User user;

    @Override
    public String toString() {
        return "Claim{" +
                "claimId=" + claimId +
                ", claimDescription='" + claimDescription + '\'' +
                ", claimCost='" + claimCost + '\'' +
                ", adjusterNotes='" + adjusterNotes + '\'' +
                ", vehicleVin='" + vehicle.getVin() + '\'' +
                ", username='" + user.getUsername() + '\'' +
                ", claimStatus=" + claimStatus +
                '}';
    }

}
