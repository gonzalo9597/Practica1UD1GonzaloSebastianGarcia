package com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base;

import java.time.LocalDate;

public class Calzado {
    private String codigoSKU;
    private String marca;
    private String modelo;
    private LocalDate fechaDeLanzamiento;
    private double talla;

    public Calzado(){}

    public Calzado(String codigoSKU, String marca, String modelo, LocalDate fechaDeLanzamiento, double talla) {
        this.codigoSKU = codigoSKU;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
        this.talla = talla;
    }

    public String getCodigoSKU() {
        return codigoSKU;
    }

    public void setCodigoSKU(String codigoSKU) {
        this.codigoSKU = codigoSKU;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }
}
