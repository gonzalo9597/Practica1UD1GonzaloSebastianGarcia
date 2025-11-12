package com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base;

import java.time.LocalDate;

public class Sandalia extends Calzado{
    private boolean abierta;

    public Sandalia(){}


    public Sandalia(String codigoSKU, String marca, String modelo, LocalDate fechaDeLanzamiento, double talla, boolean abierta) {
        super(codigoSKU, marca, modelo, fechaDeLanzamiento, talla);
        this.abierta = abierta;
    }

    public boolean isAbierta() {
        return abierta;
    }

    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }

    public String getAbierta() {
        return abierta ? "si" : "no";
    }

    @Override
    public String toString() {
        return "Sandalia:" + getCodigoSKU()+" "+getMarca()+" "+getModelo() +" "+getFechaDeLanzamiento()+" "+getTalla()
                + " Abierta: " + (abierta ? "si" : "no");
    }
}
