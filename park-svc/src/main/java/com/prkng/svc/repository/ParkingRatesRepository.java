package com.prkng.svc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prkng.svc.model.ParkingRates;

public interface ParkingRatesRepository extends JpaRepository<ParkingRates, Long>{

}
