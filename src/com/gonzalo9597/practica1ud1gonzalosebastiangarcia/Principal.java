package com.gonzalo9597.practica1ud1gonzalosebastiangarcia;

import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui.CalzadosControlador;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui.CalzadosModelo;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        CalzadosModelo modelo = new CalzadosModelo();
        CalzadosControlador controlador = new CalzadosControlador(vista,modelo);
    }
}
