package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TanqueResponse {
    //Los mismos nombres de la tabla

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("capacidad")
    private BigDecimal capacidad;

    @JsonProperty("nivel_actual")
    private BigDecimal nivel_actual;

    @JsonProperty("ubicacion")
    private String ubicacion;

    @JsonProperty("tipo_gasolina")
    private String tipo_gasolina;

    @JsonProperty("create_ad")
    private Timestamp create_ad;

    @JsonProperty("update_ad")
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
