package com.gonzalo9597.practica1ud1gonzalosebastiangarcia.gui;

import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Deportiva;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Bota;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Calzado;
import com.gonzalo9597.practica1ud1gonzalosebastiangarcia.base.Sandalia;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalzadosModelo {
    private ArrayList<Calzado> listaCalzados;

    public CalzadosModelo() {
        listaCalzados = new ArrayList<Calzado>();
    }

    public ArrayList<Calzado> obtenerCalzados(){
        return listaCalzados;
    }

    //altaDeportiva
    public void altaDeportiva(String codigoSKU, String marca,
                            String modelo,
                            LocalDate fechaDeLanzamiento,
                            double talla, String deporte) {
        Deportiva nuevaDeportiva = new Deportiva(codigoSKU,marca,modelo,
                fechaDeLanzamiento,talla,deporte);
        listaCalzados.add(nuevaDeportiva);
    }

    //altaBota
    public void altaBota(String codigoSKU, String marca,
                             String modelo,
                             LocalDate fechaDeLanzamiento,
                             double talla, boolean waterProof) {
        Bota nuevaBota = new Bota(codigoSKU,marca,modelo,
                fechaDeLanzamiento,talla,waterProof);
        listaCalzados.add(nuevaBota);
    }

    //altaSandalia
    public void altaSandalia(String codigoSKU, String marca,
                             String modelo,
                             LocalDate fechaDeLanzamiento,
                             double talla, boolean abierta) {
        Sandalia nuevaSandalia = new Sandalia(codigoSKU,marca,modelo,
                fechaDeLanzamiento,talla,abierta);
        listaCalzados.add(nuevaSandalia);
    }

    //existeCodigoSKU
    public boolean existeCodigoSKU(String codigoSKU) {
        for (Calzado unCalzado : listaCalzados) {
            if (unCalzado.getCodigoSKU().equals(codigoSKU)) {
                return true;
            }
        }
        return false;
    }

    //exportarXML
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //implementacion DOM -> web
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null,"xml",null);

        //añado el nodo raiz (primera etiqueta)
        Element raiz = documento.createElement("Calzados");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoCalzado=null;
        Element nodoDatos=null;
        Text texto=null;

        //voy a añadir una etiqueta dentro de calzado
        //en funcion de si es deportiva o bota
        for (Calzado unCalzado : listaCalzados) {
            if (unCalzado instanceof Deportiva) {
                nodoCalzado=documento.createElement("Deportiva");
            } else if (unCalzado instanceof Bota){
                nodoCalzado=documento.createElement("Bota");
            } else{
                nodoCalzado=documento.createElement("Sandalia");
            }
            raiz.appendChild(nodoCalzado);

            //dentro de la etiqueta calzado
            //tengo deportiva bota y sandalia
            //atributos comunes (codigoSKU,marca,modelo,fechaDeLanzamiento,talla)

            nodoDatos=documento.createElement("codigoSKU");
            nodoCalzado.appendChild(nodoDatos);

            texto=documento.createTextNode(unCalzado.getCodigoSKU());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("marca");
            nodoCalzado.appendChild(nodoDatos);

            texto=documento.createTextNode(unCalzado.getMarca());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("modelo");
            nodoCalzado.appendChild(nodoDatos);

            texto=documento.createTextNode(unCalzado.getModelo());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("fecha-de-lanzamiento");
            nodoCalzado.appendChild(nodoDatos);

            texto=documento.createTextNode(String.valueOf(unCalzado.getFechaDeLanzamiento()));
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("talla");
            nodoCalzado.appendChild(nodoDatos);

            texto=documento.createTextNode(String.valueOf(unCalzado.getTalla()));
            nodoDatos.appendChild(texto);

            //como hay campos que dependen del tipo de calzado
            //volvemos a comprobar
            if (unCalzado instanceof Deportiva) {
                nodoDatos=documento.createElement("deporte");
                nodoCalzado.appendChild(nodoDatos);

                texto=documento.createTextNode(((Deportiva) unCalzado).getDeporte());
                nodoDatos.appendChild(texto);
            } else if(unCalzado instanceof Bota) {
                nodoDatos=documento.createElement("waterProof");
                nodoCalzado.appendChild(nodoDatos);

                texto=documento.createTextNode(String.valueOf(((Bota) unCalzado).getWaterProof()));
                nodoDatos.appendChild(texto);
            } else{
                nodoDatos=documento.createElement("abierta");
                nodoCalzado.appendChild(nodoDatos);

                texto=documento.createTextNode(String.valueOf(((Sandalia) unCalzado).getAbierta()));
                nodoDatos.appendChild(texto);
            }
        }
        //guardo los datos en fichero
        Source source = new DOMSource(documento);
        Result result = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,result);

    }

    //importarXML
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaCalzados =new ArrayList<Calzado>();
        Deportiva nuevaDeportiva =null;
        Bota nuevaBota =null;
        Sandalia nuevaSandalia=null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i=0;i<listaElementos.getLength();i++) {
            Element nodoCalzado= (Element) listaElementos.item(i);

            if (nodoCalzado.getTagName().equals("Deportiva")) {
                nuevaDeportiva =new Deportiva();
                nuevaDeportiva.setCodigoSKU(nodoCalzado.getChildNodes().item(0).getTextContent());
                nuevaDeportiva.setMarca(nodoCalzado.getChildNodes().item(1).getTextContent());
                nuevaDeportiva.setModelo(nodoCalzado.getChildNodes().item(2).getTextContent());
                nuevaDeportiva.setFechaDeLanzamiento(LocalDate.parse(nodoCalzado.getChildNodes().item(3).getTextContent()));
                nuevaDeportiva.setTalla(Double.parseDouble(nodoCalzado.getChildNodes().item(4).getTextContent()));
                nuevaDeportiva.setDeporte(nodoCalzado.getChildNodes().item(5).getTextContent());
                listaCalzados.add(nuevaDeportiva);
            } else if(nodoCalzado.getTagName().equals("Bota")){
                nuevaBota =new Bota();
                nuevaBota.setCodigoSKU(nodoCalzado.getChildNodes().item(0).getTextContent());
                nuevaBota.setMarca(nodoCalzado.getChildNodes().item(1).getTextContent());
                nuevaBota.setModelo(nodoCalzado.getChildNodes().item(2).getTextContent());
                nuevaBota.setFechaDeLanzamiento(LocalDate.parse(nodoCalzado.getChildNodes().item(3).getTextContent()));
                nuevaBota.setTalla(Double.parseDouble(nodoCalzado.getChildNodes().item(4).getTextContent()));
                nuevaBota.setWaterProof(Boolean.parseBoolean(nodoCalzado.getChildNodes().item(5).getTextContent()));
                listaCalzados.add(nuevaBota);
            } else{
                nuevaSandalia =new Sandalia();
                nuevaSandalia.setCodigoSKU(nodoCalzado.getChildNodes().item(0).getTextContent());
                nuevaSandalia.setMarca(nodoCalzado.getChildNodes().item(1).getTextContent());
                nuevaSandalia.setModelo(nodoCalzado.getChildNodes().item(2).getTextContent());
                nuevaSandalia.setFechaDeLanzamiento(LocalDate.parse(nodoCalzado.getChildNodes().item(3).getTextContent()));
                nuevaSandalia.setTalla(Double.parseDouble(nodoCalzado.getChildNodes().item(4).getTextContent()));
                nuevaSandalia.setAbierta(Boolean.parseBoolean(nodoCalzado.getChildNodes().item(5).getTextContent()));
                listaCalzados.add(nuevaSandalia);
            }
        }
    }

}
