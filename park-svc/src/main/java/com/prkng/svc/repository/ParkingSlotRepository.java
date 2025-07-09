package com.prkng.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prkng.svc.model.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, String> {

}
