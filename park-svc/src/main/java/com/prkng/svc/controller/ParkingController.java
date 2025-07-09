package com.prkng.svc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prkng.svc.dto.ParkingDto;
import com.prkng.svc.model.ParkingRates;
import com.prkng.svc.model.ParkingSlot;
import com.prkng.svc.service.ParkingService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/addRates")
    public ParkingRates addParkingRates(@RequestBody ParkingDto parkingDto) {
        
        return parkingService.addParkingInfo(parkingDto);
    }
    
    @GetMapping("/getSlotParking")
    public Optional<ParkingSlot> getSlotParking(@RequestParam LocalDate today, String jenisKendaraan) {
        return parkingService.getParkingByToday(today, jenisKendaraan);
    }
    
    @GetMapping("/getAllSlot")
    public List<ParkingSlot> getAllSlot() {
        return parkingService.getAllParkingSlots();
    }
    

}
