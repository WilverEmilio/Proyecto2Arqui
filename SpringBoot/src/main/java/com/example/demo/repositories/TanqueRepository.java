package com.example.demo.repositories;

import com.example.demo.models.Tanque;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TanqueRepository  extends JpaRepository<Tanque, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Tanque t SET t.capacidad = :capacidad where t.id = :id")
    void updateCapacidadTanque(BigDecimal capacidad, Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Tanque t SET t.nivel_actual = :nivel_actual where t.id = :id")
    void updateNivel_actualTanque(BigDecimal nivel_actual, Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Tanque t SET t.ubicacion = :ubicacion where t.id = :id")
    void updateUbicacionTanque(String ubicacion, Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Tanque t SET t.tipo_gasolina = :tipo_gasolina where t.id = :id")
    void updateTipo_gasolinaTanque(String tipo_gasolina, Integer id);
}
