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

    @Override
    public String toString() {
        return "Bota:" + getCodigoSKU()+" "+getMarca()+" "+getModelo() +" "+getFechaDeLanzamiento()+" "+getTalla()
                + " Impermeable: " + (waterProof ? "si" : "no");
    }
}
