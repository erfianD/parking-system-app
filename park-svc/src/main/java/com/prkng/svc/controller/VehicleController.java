package com.prkng.svc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prkng.svc.dto.ParkingBillDto;
import com.prkng.svc.dto.VehicleDto;
import com.prkng.svc.model.Vehicle;
import com.prkng.svc.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/entry")
    public Vehicle addVehicle(@RequestBody VehicleDto vehicleDto) {
        
        return vehicleService.addVehicle(vehicleDto);
    }

    @PatchMapping("/exit")
    public Vehicle updateVehicle(@RequestBody ParkingBillDto parking) {
        
        return vehicleService.countParkingBill(parking);
    }
    

}
