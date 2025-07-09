package com.prkng.svc.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.prkng.svc.model.ParkingRates;
import com.prkng.svc.model.ParkingSlot;
import com.prkng.svc.model.Users;
import com.prkng.svc.model.Vehicle;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Repository
public class CustomRepositoryImpl implements CustomRepository{

    private final EntityManager entityManager;

    public CustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Vehicle findByPlatNo(String platNomor, String jenisKendaraan) {
        String query = "SELECT v FROM Vehicle v WHERE v.platNomor = :platNomor AND v.jenisKendaraan = :jenisKendaraan";

        return entityManager.createQuery(query, Vehicle.class)
            .setParameter("platNomor", platNomor)
            .setParameter("jenisKendaraan", jenisKendaraan)
            .getSingleResult();
    }

    @Override
    @Transactional
    public int updateVehicle(LocalDateTime jamKeluar, int totalTarif, int totalWaktu, String platNomor,
        String jenisKendaraan, String status) {
        String query = "UPDATE Vehicle v SET v.jamKeluar = :jamKeluar, v.totalWaktu = :totalWaktu, v.totalTarif = :totalTarif, v.status = :status  "
            + "WHERE v.platNomor = :platNomor AND v.jenisKendaraan = :jenisKendaraan";

            entityManager.createQuery(query)
            .setParameter("jamKeluar", jamKeluar).setParameter("totalWaktu", totalWaktu)
            .setParameter("totalTarif", totalTarif).setParameter("platNomor", platNomor)
            .setParameter("status", status)
            .setParameter("jenisKendaraan", jenisKendaraan).executeUpdate();

        return 200;
    }

    @Override
    public ParkingRates getParkingRates(String jenisKendaraan) {
        String query = "SELECT p FROM ParkingRates p WHERE p.jenisKendaraan = :jenisKendaraan";

        return entityManager.createQuery(query, ParkingRates.class)
            .setParameter("jenisKendaraan", jenisKendaraan)
            .getSingleResult();
    }

    @Override
    public Optional<ParkingSlot> getParkingByToday(LocalDate today, String jenisKendaraan) {
        String query = "SELECT p FROM ParkingSlot p WHERE p.createDate = :createDate AND p.jenisKendaraan = :jenisKendaraan";
        
        try {

            ParkingSlot parkingSlotResult = entityManager.createQuery(query, ParkingSlot.class)
            .setParameter("createDate", today)
            .setParameter("jenisKendaraan", jenisKendaraan)
            .getSingleResult();

            return Optional.ofNullable(parkingSlotResult);

        } catch (NoResultException e) {

            return Optional.empty();
        }

    }

    @Override
    @Transactional
    public int updateSlotToday(ParkingSlot parkingSlot) {
        String query = "UPDATE ParkingSlot p SET p.totalKendaraan = :totalKendaraan, p.slotAvailable = :slotAvailable "
            + "WHERE p.createDate = :createDate AND p.jenisKendaraan = :jenisKendaraan";

        return entityManager.createQuery(query)
            .setParameter("totalKendaraan", parkingSlot.getTotalKendaraan())
            .setParameter("slotAvailable", parkingSlot.getSlotAvailable())
            .setParameter("createDate", parkingSlot.getCreateDate())
            .setParameter("jenisKendaraan", parkingSlot.getJenisKendaraan())
            .executeUpdate();
    }

    @Override
    public Users getUserByUsername(String username) {
       String query = "SELECT u FROM USERS u WHERE u.username = :username";

       return entityManager.createQuery(query, Users.class)
            .setParameter("username", username)
            .getSingleResult();
    }

}
