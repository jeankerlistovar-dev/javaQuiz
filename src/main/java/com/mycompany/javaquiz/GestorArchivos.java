
package com.mycompany.javaquiz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivos {

    public static ArrayList<Pregunta> cargarPreguntas(String rutaArchivo) {
        ArrayList<Pregunta> listaDePreguntas = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                
                if (partes.length == 6) {
                    String enunciado = partes[0];
                    String o1 = partes[1];
                    String o2 = partes[2];
                    String o3 = partes[3];
                    String o4 = partes[4];
                    int respuestaCorrecta = Integer.parseInt(partes[5].trim()); 
                    
                    Pregunta nuevaPregunta = new Pregunta(enunciado, o1, o2, o3, o4, respuestaCorrecta);
                    
                    listaDePreguntas.add(nuevaPregunta);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de preguntas: " + e.getMessage());
        }
        
        return listaDePreguntas; 
    }

    public static void guardarPuntuacion(String rutaArchivo, String nombreJugador, double porcentajeNota) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(nombreJugador + " - " + String.format("%.1f", porcentajeNota) + "%");
            bw.newLine(); 
        } catch (IOException e) {
            System.out.println("No se pudo guardar la puntuación en el disco: " + e.getMessage());
        }
    }
}
