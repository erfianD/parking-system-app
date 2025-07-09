package com.prkng.svc.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID incidentId;
    private String platNomor;
    private String jenisKendaraan;
    private String incidentRemark;
    private LocalDateTime incidentTime;
    private String parkingOperator;
    private String customer;
    private String customerContact;
}
