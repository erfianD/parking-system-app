package com.prkng.svc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prkng.svc.controller.VehicleController;
import com.prkng.svc.dto.ParkingBillDto;
import com.prkng.svc.dto.VehicleDto;
import com.prkng.svc.model.Vehicle;
import com.prkng.svc.service.VehicleService;

@WebMvcTest(VehicleController.class)
@AutoConfigureMockMvc(addFilters = false)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testAddVehicle() throws Exception {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setPlatKendaraan("B3425XYZ");
        vehicleDto.setJenisKendaraan("Bus");

        Vehicle vehicle = new Vehicle();
        vehicle.setId(UUID.fromString("4c0498ff-62d2-4568-abf4-f12f8fbfae9a"));
        vehicle.setJamMasuk(LocalDateTime.parse("2025-07-08T20:20:07.221"));
        vehicle.setJamKeluar(null);
        vehicle.setJenisKendaraan("Bus");
        vehicle.setPlatNomor("B3425XYZ");
        vehicle.setStatus("In");
        vehicle.setTarifParkir(6000);
        vehicle.setTotalWaktu(0);
        vehicle.setTotalTarif(0);

        when(vehicleService.addVehicle(any(VehicleDto.class))).thenReturn(vehicle);

        mockMvc.perform(post("/vehicle/entry")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(vehicleDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("4c0498ff-62d2-4568-abf4-f12f8fbfae9a"))
            .andExpect(jsonPath("$.jamMasuk").value("2025-07-08T20:20:07.221"))
            .andExpect(jsonPath("$.jamKeluar").doesNotExist())
            .andExpect(jsonPath("$.jenisKendaraan").value("Bus"))
            .andExpect(jsonPath("$.platNomor").value("B3425XYZ"))
            .andExpect(jsonPath("$.status").value("In"))
            .andExpect(jsonPath("$.tarifParkir").value(6000))
            .andExpect(jsonPath("$.totalWaktu").value(0))
            .andExpect(jsonPath("$.totalTarif").value(0));

    }

    @Test
    void exitVehicleTest() throws Exception{
        ParkingBillDto parkingBillDto = new ParkingBillDto();
        parkingBillDto.setPlatKendaraan("B3425XYZ");
        parkingBillDto.setJenisKendaraan("Bus");

        Vehicle vehicle = new Vehicle();
        vehicle.setId(UUID.fromString("4c0498ff-62d2-4568-abf4-f12f8fbfae9a"));
        vehicle.setJamMasuk(LocalDateTime.parse("2025-07-08T20:20:07.221"));
        vehicle.setJamKeluar(LocalDateTime.parse("2025-07-09T11:54:44.257"));
        vehicle.setJenisKendaraan("Bus");
        vehicle.setPlatNomor("B3425XYZ");
        vehicle.setStatus("Out");
        vehicle.setTarifParkir(6000);
        vehicle.setTotalWaktu(16);
        vehicle.setTotalTarif(96000);

        when(vehicleService.countParkingBill(any(ParkingBillDto.class))).thenReturn(vehicle);

        mockMvc.perform(patch("/vehicle/exit")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(parkingBillDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("4c0498ff-62d2-4568-abf4-f12f8fbfae9a"))
            .andExpect(jsonPath("$.jamMasuk").value("2025-07-08T20:20:07.221"))
            .andExpect(jsonPath("$.jamKeluar").value("2025-07-09T11:54:44.257"))
            .andExpect(jsonPath("$.jenisKendaraan").value("Bus"))
            .andExpect(jsonPath("$.platNomor").value("B3425XYZ"))
            .andExpect(jsonPath("$.status").value("Out"))
            .andExpect(jsonPath("$.tarifParkir").value(6000))
            .andExpect(jsonPath("$.totalWaktu").value(16))
            .andExpect(jsonPath("$.totalTarif").value(96000));
    }
}
