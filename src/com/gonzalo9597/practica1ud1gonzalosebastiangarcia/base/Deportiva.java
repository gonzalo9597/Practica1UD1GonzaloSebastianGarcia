package com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base;

import java.time.LocalDate;

public class Deportiva extends Calzado {

    private String deporte;

    public Deportiva() {
    }


    public Deportiva(String codigoSKU, String marca, String modelo, LocalDate fechaMatriculacion, double talla, String deporte) {
        super(codigoSKU, marca, modelo, fechaMatriculacion, talla);
        this.deporte = deporte;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    @Override
    public String toString() {
        return "Deportiva con código SKU: " + getCodigoSKU() + ", marca: " + getMarca() + ", modelo: " + getModelo()
                + ", fecha de lanzamiento: " + getFechaDeLanzamiento()
                + ", talla: " + getTalla() + ", deporte: " + getDeporte();
    }

}
