package com.prkng.svc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "parking_rates")
public class ParkingRates {

    @Id
    private Long id;
    private String jenisKendaraan;
    private int tarif;
    private int slotMaks;
}
