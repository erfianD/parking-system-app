package com.prkng.svc.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.prkng.svc.model.ParkingRates;
import com.prkng.svc.model.ParkingSlot;
import com.prkng.svc.model.Users;
import com.prkng.svc.model.Vehicle;

public interface CustomRepository {

    Vehicle findByPlatNo(String platNomor, String jenisKendaraan);
    int updateVehicle(LocalDateTime jamKeluar, int totalTarif, int totalWaktu, String platNomor, String jenisKendaraan, String status);
    ParkingRates getParkingRates(String jenisKendaraan);
    Optional<ParkingSlot> getParkingByToday(LocalDate today, String jenisKendaraan);
    int updateSlotToday(ParkingSlot parkingSlot);
    Users getUserByUsername(String username);
}
