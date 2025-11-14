package com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui;

import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Calzado;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.SpinnerNumberModel;

public class Ventana {
    private JPanel panel1;
    JRadioButton deportivaRadioButton;
    JRadioButton botaRadioButton;
    JRadioButton sandaliaRadioButton;
    ButtonGroup buttonGroup1;
    JTextField codigoSKUTxt;
    JTextField marcaTxt;
    JTextField modeloTxt;
    JButton nuevoButton;
    JButton exportarButton;
    JButton importarButton;
    JList<Calzado> list1;
    DatePicker fechaDeLanzamientoDPicker;
    JComboBox deporteBox;
    JSpinner tallaSpinner;
    JCheckBox siCheckBox;
    JLabel WaterProofAbierta;
    JButton eliminarButton;
    JButton borrarButton;
    JLabel deporteLabel;
    JRadioButton darkRadioButton;

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
        frame.setIconImage(new ImageIcon("src/jordan.png").getImage());

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
