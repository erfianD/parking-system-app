package com.prkng.svc.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String platNomor;
    private String jenisKendaraan;
    private int tarifParkir;
    private LocalDateTime jamMasuk;
    private LocalDateTime jamKeluar;
    private int totalWaktu;
    private int totalTarif;
    private String status;

}
