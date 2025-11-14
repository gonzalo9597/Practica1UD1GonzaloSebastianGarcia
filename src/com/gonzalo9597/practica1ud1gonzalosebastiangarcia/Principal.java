package com.gonzalo9597.practica1ud1gonzalosebastiangarcia;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui.CalzadosControlador;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui.CalzadosModelo;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui.Ventana;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        //aquí importamos librería de flatlaf del archivo ejecutable

            try {
                UIManager.setLookAndFeel(new FlatMacLightLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }
        Ventana vista = new Ventana();
        CalzadosModelo modelo = new CalzadosModelo();
        CalzadosControlador controlador = new CalzadosControlador(vista,modelo);


    }
}
