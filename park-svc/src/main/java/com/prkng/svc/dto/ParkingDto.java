package com.prkng.svc.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ParkingDto {

    private long id;
    private String jenisKendaraan;
    private int tarif;
    
    private int totalKendaraan;
    private int slotMaks;
    private int slotAvailable;
    private LocalDate createDate;
}
