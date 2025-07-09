package com.prkng.svc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prkng.svc.dto.ParkingDto;
import com.prkng.svc.model.ParkingRates;
import com.prkng.svc.model.ParkingSlot;
import com.prkng.svc.repository.CustomRepository;
import com.prkng.svc.repository.ParkingRatesRepository;
import com.prkng.svc.repository.ParkingSlotRepository;

@Service
public class ParkingService {

    @Autowired
    private ParkingRatesRepository parkingRatesRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private CustomRepository customRepository;

    public ParkingRates addParkingInfo(ParkingDto parkingDto) {
        
        ParkingRates parkingRates = new ParkingRates();

        parkingRates.setId(parkingDto.getId());
        parkingRates.setJenisKendaraan(parkingDto.getJenisKendaraan());
        parkingRates.setTarif(parkingDto.getTarif());
        parkingRates.setSlotMaks(parkingDto.getSlotMaks());
        parkingRatesRepository.save(parkingRates);

        return parkingRates;

    }

    public Optional<ParkingSlot> addParkingSlotPerDay(ParkingDto parkingDto) {
        List<ParkingRates> parkingInfo = getAllParkingInfo();
        ParkingSlot parkingSlot = new ParkingSlot();
        Optional<ParkingSlot> parkingSlotToday = getParkingByToday(parkingDto.getCreateDate(), parkingDto.getJenisKendaraan());
    
        if (parkingSlotToday.isEmpty() || parkingSlotToday == null) {

            int totalKendaraan = 0;
            parkingSlot.setCreateDate(parkingDto.getCreateDate());
            parkingSlot.setJenisKendaraan(parkingDto.getJenisKendaraan());
            Optional<ParkingRates> vehicleSlotInfo = parkingInfo.stream().filter(p -> p.getJenisKendaraan().equals(parkingDto.getJenisKendaraan()))
                .findFirst();
            
            if (vehicleSlotInfo.isPresent()) {

                parkingSlot.setSlotMaks(vehicleSlotInfo.get().getSlotMaks());
                parkingSlot.setSlotAvailable(vehicleSlotInfo.get().getSlotMaks());
            }

            parkingSlot.setTotalKendaraan(totalKendaraan + 1);

            parkingSlotRepository.save(parkingSlot);

        } else if (!parkingSlotToday.isEmpty() || parkingSlotToday != null) {
            parkingSlot.setCreateDate(LocalDate.now());
            parkingSlot.setJenisKendaraan(parkingDto.getJenisKendaraan());
            Optional<ParkingRates> vehicleSlotInfo = parkingInfo.stream().filter(p -> p.getJenisKendaraan().equals(parkingDto.getClass()))
                .findFirst();
            
            if (vehicleSlotInfo.isPresent()) {

                parkingSlot.setSlotMaks(vehicleSlotInfo.get().getSlotMaks());
                parkingSlot.setSlotAvailable(parkingSlotToday.get().getSlotAvailable() - 1);
            }

            parkingSlot.setTotalKendaraan(parkingSlotToday.get().getTotalKendaraan() + 1);
            customRepository.updateSlotToday(parkingSlot);
        }
        return parkingSlotToday;
    }

    public Optional<ParkingSlot> vehicleOutSlotAvail(ParkingDto parkingDto) {
        List<ParkingRates> parkingInfo = getAllParkingInfo();
        Optional<ParkingSlot> parkingSlotToday = getParkingByToday(parkingDto.getCreateDate(), parkingDto.getJenisKendaraan());
        ParkingSlot parkingSlot = new ParkingSlot();
        Optional<ParkingRates> vehicleSlotInfo = parkingInfo.stream().filter(p -> p.getJenisKendaraan().equals(parkingDto.getClass()))
                .findFirst();
            
            if (vehicleSlotInfo.isPresent()) {

                parkingSlot.setSlotAvailable(parkingSlotToday.get().getSlotAvailable() + 1);

            }
        parkingSlot.setTotalKendaraan(parkingSlotToday.get().getTotalKendaraan());
        customRepository.updateSlotToday(parkingSlot);

        return parkingSlotToday;
           
    }

    public List<ParkingRates> getAllParkingInfo() {
        return parkingRatesRepository.findAll();
    }

    public Optional<ParkingSlot> getParkingByToday(LocalDate today, String jenisKendaraan) {
        Optional<ParkingSlot> parkingSlotToday = customRepository.getParkingByToday(today, jenisKendaraan);
        
        return parkingSlotToday;
    }

    public List<ParkingSlot> getAllParkingSlots(){
        return parkingSlotRepository.findAll();
    }

}
