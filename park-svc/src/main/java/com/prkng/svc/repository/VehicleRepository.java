package com.prkng.svc.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prkng.svc.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

}
