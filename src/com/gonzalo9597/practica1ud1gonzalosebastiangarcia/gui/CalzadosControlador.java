package com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Bota;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Calzado;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Deportiva;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Sandalia;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class CalzadosControlador implements ActionListener, ListSelectionListener, WindowListener {

    private Ventana vista;
    private CalzadosModelo modelo;
    private File ultimaRutaExportada;

    public CalzadosControlador(Ventana vista, CalzadosModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe fichero de configuracion");
        }

        //listener al arrancar el controlador
        addActionListener(this);
        addWindowListener(this);
        addListSelectionListener(this);
    }

    private void limpiarCampos() {
        vista.buttonGroup1.clearSelection();
        //como los tres botones pertenecen a un grupo, borrar seleccción del botón del grupo y se resetean todos
        vista.codigoSKUTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.fechaDeLanzamientoDPicker.setText(null);
        vista.tallaSpinner.setValue(36);
        //dejamos la talla predeterminada la 36 que es la más baja
        vista.deporteBox.setSelectedIndex(0);
        //en el comboBox dejamos Casual como predeterminada
        vista.siCheckBox.setSelected(false);
        //dejamos sin marcar el checkBox
    }

    //listener botones
    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.eliminarButton.addActionListener(listener);
        vista.borrarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.deportivaRadioButton.addActionListener(listener);
        vista.botaRadioButton.addActionListener(listener);
        vista.sandaliaRadioButton.addActionListener(listener);
        vista.darkRadioButton.addActionListener(listener);
    }

    //listener ventana (boton cerrar)
    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    //listener de la lista
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    public void refrescar() {
        //borramos todos los calzados que hay en el dlm
        vista.dlmCalzados.clear();
        //modelo.obtenerCalzados -> contiene la lista de calzados
        for (Calzado unCalzado : modelo.obtenerCalzados()) {
            vista.dlmCalzados.addElement(unCalzado);
            //añadimos todos los calzados al dlm (el nuevo incluido)
        }
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("calzados.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada"
                , ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("calzados.conf")
                , "Datos configuracion calzados");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (!vista.deportivaRadioButton.isSelected() && !vista.botaRadioButton.isSelected() &&
                        !vista.sandaliaRadioButton.isSelected()) {
                    Util.mensajeError("Debes seleccionar el tipo de calzado deseado");
                    break;
                } else if (modelo.existeCodigoSKU(vista.codigoSKUTxt.getText())) {
                    Util.mensajeError("Ya existe un calzado con el código SKU " +
                            "\n" + vista.codigoSKUTxt.getText());
                    break;
                } else if (vista.codigoSKUTxt.getText().isEmpty()) {
                    Util.mensajeError("Debes escribir el código SKU del calzado");
                    break;
                } else if (vista.marcaTxt.getText().isEmpty()) {
                    Util.mensajeError("Debes escribir la marca del calzado");
                    break;
                } else if (vista.modeloTxt.getText().isEmpty()) {
                    Util.mensajeError("Debes escribir el modelo del calzado");
                    break;
                } else if (vista.fechaDeLanzamientoDPicker.getText().isEmpty()) {
                    Util.mensajeError("Debes escribir la fecha de lanzamiento del calzado");
                    break;
                }
                if (vista.deportivaRadioButton.isSelected()) {
                    modelo.altaDeportiva(vista.codigoSKUTxt.getText(), vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(), vista.fechaDeLanzamientoDPicker.getDate(),
                            //como del Spinner solo se puede sacar el valor a String, hay que castear a Double
                            Double.parseDouble(vista.tallaSpinner.getValue().toString()),
                            vista.deporteBox.getSelectedItem().toString());
                    //para sacar el valor del ComboBox, getSelectedItem
                } else if (vista.botaRadioButton.isSelected()) {
                    modelo.altaBota(vista.codigoSKUTxt.getText(), vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(), vista.fechaDeLanzamientoDPicker.getDate(),
                            Double.parseDouble(vista.tallaSpinner.getValue().toString()), vista.siCheckBox.isSelected());
                } else if (vista.sandaliaRadioButton.isSelected()) {
                    modelo.altaSandalia(vista.codigoSKUTxt.getText(), vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(), vista.fechaDeLanzamientoDPicker.getDate(),
                            Double.parseDouble(vista.tallaSpinner.getValue().toString()), vista.siCheckBox.isSelected());
                }
                limpiarCampos();
                refrescar();
                System.out.println(modelo.obtenerCalzados());
                break;

            case "Eliminar calzado":
                Calzado calzadoAEliminar = vista.list1.getSelectedValue();
                modelo.eliminarCalzado(calzadoAEliminar);
                limpiarCampos();
                refrescar();
                break;

            case "Borrar campos":
                limpiarCampos();
                break;

            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada
                        , "Archivos XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;

            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada
                        , "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;

            case "Deportiva":
                vista.WaterProofAbierta.setVisible(false);
                vista.siCheckBox.setVisible(false);
                vista.deporteBox.setVisible(true);
                vista.deporteLabel.setVisible(true);
                break;

            case "Bota":
                vista.deporteBox.setVisible(false);
                vista.deporteLabel.setVisible(false);
                vista.WaterProofAbierta.setText("WaterProof");
                vista.WaterProofAbierta.setVisible(true);
                vista.siCheckBox.setVisible(true);
                vista.siCheckBox.setSelected(false);
                break;

            case "Sandalia":
                vista.deporteBox.setVisible(false);
                vista.deporteLabel.setVisible(false);
                vista.WaterProofAbierta.setText("Abierta");
                vista.WaterProofAbierta.setVisible(true);
                vista.siCheckBox.setVisible(true);
                vista.siCheckBox.setSelected(false);
                break;

            case "Dark Mode":
                try {
                    if (vista.darkRadioButton.isSelected()) {
                        UIManager.setLookAndFeel(new FlatMacDarkLaf());
                    } else {
                        UIManager.setLookAndFeel(new FlatMacLightLaf());
                    }

                    //actualizamos todo el arbol de componentes
                    SwingUtilities.updateComponentTreeUI(vista.frame);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                //ponemos la condición distinto a null para evitar null pointer exception
                if (ultimaRutaExportada != null) {
                    guardarConfiguracion();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
            //lo sacamos del try-catch para que siempre que le demos a exit se salga de la app
        }
    }

    @Override
    //en este metodo, cogemos los datos del calzado seleccionado de list1 y los plasmamos en los txt correspondientes
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Calzado calzadoSeleccionado = vista.list1.getSelectedValue();
            vista.codigoSKUTxt.setText(calzadoSeleccionado.getCodigoSKU());
            vista.marcaTxt.setText(calzadoSeleccionado.getMarca());
            vista.modeloTxt.setText(calzadoSeleccionado.getModelo());
            vista.fechaDeLanzamientoDPicker.setDate(calzadoSeleccionado.getFechaDeLanzamiento());
            //tambien nos cogeria setText en el DPicker
            vista.tallaSpinner.setValue(calzadoSeleccionado.getTalla());

            if (calzadoSeleccionado instanceof Deportiva) {
                vista.deportivaRadioButton.doClick();
                vista.deporteBox.setSelectedItem(((Deportiva) calzadoSeleccionado).getDeporte());

            } else if (calzadoSeleccionado instanceof Bota) {
                vista.botaRadioButton.doClick();
                if (((Bota) calzadoSeleccionado).isWaterProof()) {
                    vista.siCheckBox.setSelected(true);

                } else {
                    vista.siCheckBox.setSelected(false);
                }

            } else if (calzadoSeleccionado instanceof Sandalia) {
                vista.sandaliaRadioButton.doClick();
                if (((Sandalia) calzadoSeleccionado).isAbierta()) {
                    vista.siCheckBox.setSelected(true);
                } else {
                    vista.siCheckBox.setSelected(false);
                }
            }
        }
    }

    //no los uso

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private boolean hayCamposVacios() {
        if (vista.codigoSKUTxt.getText().isEmpty() ||
                vista.marcaTxt.getText().isEmpty() ||
                vista.modeloTxt.getText().isEmpty() ||
                vista.fechaDeLanzamientoDPicker.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
