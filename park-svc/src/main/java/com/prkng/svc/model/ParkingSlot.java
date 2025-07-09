package com.prkng.svc.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "slot_parking")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String jenisKendaraan;
    private int totalKendaraan;
    private int slotMaks;
    private int slotAvailable;
    private LocalDate createDate;

}
