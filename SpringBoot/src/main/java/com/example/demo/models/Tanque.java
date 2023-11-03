package com.example.demo.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "tanques")
public class Tanque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private  Integer id;
    private BigDecimal capacidad;
    private BigDecimal  nivel_actual;

    private String ubicacion;

    private String tipo_gasolina;

    private Timestamp create_ad;
    private Timestamp update_ad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(BigDecimal capacidad) {
        this.capacidad = capacidad;
    }

    public BigDecimal getNivel_actual() {
        return nivel_actual;
    }

    public void setNivel_actual(BigDecimal nivel_actual) {
        this.nivel_actual = nivel_actual;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo_gasolina() {
        return tipo_gasolina;
    }

    public void setTipo_gasolina(String tipo_gasolina) {
        this.tipo_gasolina = tipo_gasolina;
    }

    public Timestamp getCreate_ad() {
        return create_ad;
    }

    public void setCreate_ad(Timestamp create_ad) {
        this.create_ad = create_ad;
    }

    public Timestamp getUpdate_ad() {
        return update_ad;
    }

    public void setUpdate_ad(Timestamp update_ad) {
        this.update_ad = update_ad;
    }
}
