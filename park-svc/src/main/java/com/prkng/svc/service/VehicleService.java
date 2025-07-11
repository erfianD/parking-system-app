package com.prkng.svc.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prkng.svc.dto.ParkingBillDto;
import com.prkng.svc.dto.ParkingDto;
import com.prkng.svc.dto.VehicleDto;
import com.prkng.svc.model.ParkingRates;
import com.prkng.svc.model.Vehicle;
import com.prkng.svc.repository.CustomRepository;
import com.prkng.svc.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomRepository customRepo;

    @Autowired
    private ParkingService parkingService;

    private LocalDateTime waktuMasuk;

    public Vehicle addVehicle(VehicleDto vehicleDto) {
        
        ParkingRates parkingRates = customRepo.getParkingRates(vehicleDto.getJenisKendaraan());

        Vehicle vehicle = new Vehicle();
        vehicle.setPlatNomor(vehicleDto.getPlatKendaraan());
        vehicle.setJamMasuk(waktuMasuk.now());
        vehicle.setJenisKendaraan(vehicleDto.getJenisKendaraan());
        vehicle.setTarifParkir(parkingRates.getTarif());
        vehicle.setStatus("In");

        vehicleRepository.save(vehicle);
        
        ParkingDto parkingDto = new ParkingDto();
        parkingDto.setCreateDate(LocalDate.now());
        parkingDto.setJenisKendaraan(vehicleDto.getJenisKendaraan());
        parkingService.addParkingSlotPerDay(parkingDto);

        return vehicle;
    }

    public Vehicle countParkingBill(ParkingBillDto parking) {

        Vehicle vehicleUpdate = new Vehicle();

        Vehicle vehicleData = customRepo.findByPlatNo(parking.getPlatKendaraan(), parking.getJenisKendaraan());
                
        int totalTarif = 0;
        
        int hours = 0;
        
        int minuteLeft = 0;
        
        LocalDateTime currTime = LocalDateTime.now();
        
        long currEpochSecond = currTime.toEpochSecond(ZoneOffset.UTC);

        long entryEpochTime = vehicleData.getJamMasuk().toEpochSecond(ZoneOffset.UTC);

        hours = (int) (currEpochSecond - entryEpochTime) / 3600;

        minuteLeft = (int) ((currEpochSecond - entryEpochTime) % 3600) / 60;

        if (minuteLeft > 10) {
            hours += 1;
        }
        
        totalTarif = hours * vehicleData.getTarifParkir();
        vehicleUpdate.setJamKeluar(currTime);
        vehicleUpdate.setTotalTarif(totalTarif);
        vehicleUpdate.setStatus("Out");
        vehicleUpdate.setPlatNomor(parking.getPlatKendaraan());
        vehicleUpdate.setJenisKendaraan(parking.getJenisKendaraan());

        int status = customRepo.updateVehicle(vehicleUpdate.getJamKeluar(), vehicleUpdate.getTotalTarif(), hours, vehicleUpdate.getPlatNomor(), 
            vehicleUpdate.getJenisKendaraan(), vehicleUpdate.getStatus());

        ParkingDto parkingDto = new ParkingDto();
        parkingDto.setCreateDate(LocalDate.now());
        parkingDto.setJenisKendaraan(parking.getJenisKendaraan());

        if (status == 200) {
            // if (parkingDto != null) parkingService.vehicleOutSlotAvail(parkingDto);
            vehicleData = customRepo.findByPlatNo(parking.getPlatKendaraan(), parking.getJenisKendaraan());
        }
        
        return vehicleData;
    }

}
