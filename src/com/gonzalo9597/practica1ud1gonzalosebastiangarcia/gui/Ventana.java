package com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui;

import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Calzado;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.SpinnerNumberModel;

public class Ventana {
    private JPanel panel1;
    public JRadioButton deportivaRadioButton;
    public JRadioButton botaRadioButton;
    public JRadioButton sandaliaRadioButton;
    public ButtonGroup buttonGroup1;
    public JTextField codigoSKUTxt;
    public JTextField marcaTxt;
    public JTextField modeloTxt;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JList<Calzado> list1;
    public DatePicker fechaDeLanzamientoDPicker;
    public JComboBox deporteBox;
    public JSpinner tallaSpinner;
    public JCheckBox siCheckBox;
    public JLabel WaterProofAbierta;
    public JButton editarButton;
    public JButton borrarButton;

    //declarado por mi
    public JFrame frame;
    //para poner los datos de calzados en la lista
    public DefaultListModel<Calzado> dlmCalzados;

    public Ventana() {
        frame = new JFrame("CalzadosMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initComponents();
    }
    public void initComponents() {
        dlmCalzados =new DefaultListModel<Calzado>();
        list1.setModel(dlmCalzados);

        // Configurar ButtonGroup para los radio buttons
        buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(deportivaRadioButton);
        buttonGroup1.add(botaRadioButton);
        buttonGroup1.add(sandaliaRadioButton);

        //Configuración del JSpinner para tallas
        SpinnerNumberModel modeloTallas = new SpinnerNumberModel(
                36, // valor inicial
                36, // valor mínimo
                46, // valor máximo
                0.5  // incremento
        );
        tallaSpinner.setModel(modeloTallas);
    }
}
