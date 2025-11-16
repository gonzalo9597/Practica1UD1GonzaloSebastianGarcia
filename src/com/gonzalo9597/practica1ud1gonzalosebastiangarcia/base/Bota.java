package com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base;

import java.time.LocalDate;

public class Bota extends Calzado {


    private boolean waterProof;

    public Bota(){}


    public Bota(String codigoSKU, String marca, String modelo, LocalDate fechaDeLanzamiento, double talla, boolean waterProof) {
        super(codigoSKU, marca, modelo, fechaDeLanzamiento, talla);
        this.waterProof = waterProof;
    }

    public boolean isWaterProof() {
        return waterProof;
    }

    public void setWaterProof(boolean waterProof) {
        this.waterProof = waterProof;
    }

    public String getWaterProof() {
        return waterProof ? "si" : "no";
    }
//si waterProof es true, se convierte return "si", si es false "no" (lo mismo con abierta en Sandalia)
    @Override
    public String toString() {
        return "BOTA con código SKU: " + getCodigoSKU()+", marca: "+getMarca()+", modelo: "+getModelo()
                +", fecha de lanzamiento: "+getFechaDeLanzamiento()+", talla: "+getTalla()
                + ", impermeable: " + (waterProof ? "si" : "no");
    }
}
