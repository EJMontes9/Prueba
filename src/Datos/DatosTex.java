package Datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Formatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JAZ
 */
public class DatosTex {

    File archi;
    boolean comprobar = false;
    DatosTex datos=null;

    public void abrir(JTextPane txtArea) {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto", "docx", "txt","doc");
        String nombre, contenido;
        JFileChooser archivo = new JFileChooser();
        archivo.setFileFilter(filtro);
        archivo.setDialogTitle("Archivos de Admin...");
        int comparar = archivo.showOpenDialog(null);

        if (comparar == JFileChooser.APPROVE_OPTION) {
            archi = archivo.getSelectedFile();
            nombre = archi.getName();

            try {

                FileInputStream f = new FileInputStream(archi);
                InputStreamReader f2 = new InputStreamReader(f);
                BufferedReader linea = new BufferedReader(f2);
                contenido = "";
                while (linea.ready()) {
                    String linea_archi = linea.readLine();
                    contenido = contenido + linea_archi + "\n";
                }
                linea.close();
                txtArea.setText(contenido);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void guardar(JTextPane txtArea) {
        if (comprobar == true) {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto", "docx", "txt");
            JFileChooser archivo = new JFileChooser();
            archivo.setFileFilter(filtro);
            archivo.setDialogTitle("Admin Guardar......");
            int comparar = archivo.showSaveDialog(null);
            comprobar = false;
            if (comparar == JFileChooser.APPROVE_OPTION) {
                final Formatter crear;
                try {
                    crear = new Formatter(archivo.getSelectedFile() + ".docx");
                    crear.format(txtArea.getText());
                    crear.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        } else if (comprobar == false) {
            FileNameExtensionFilter filtro= new FileNameExtensionFilter("Archivos de texto", "txt");
            JFileChooser archivo = new JFileChooser();
            archivo.setFileFilter(filtro);
            archivo.setDialogTitle("Invitado Guardar......");
            int comparar = archivo.showSaveDialog(null);
            comprobar = true;
            if (comparar == JFileChooser.APPROVE_OPTION) {
                final Formatter crear;
                try {
                    crear = new Formatter(archivo.getSelectedFile() + ".txt");
                    crear.format(txtArea.getText());
                    crear.close();

                } catch (Exception e) {
                }

            }
        }

    }

}
