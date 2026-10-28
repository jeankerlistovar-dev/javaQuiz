
package com.mycompany.javaquiz;

import java.util.ArrayList;

public class Quiz {
    private ArrayList<Pregunta> preguntas; 
    private int indicePreguntaActual;      
    private int aciertos;                  
    private int errores;                  

    public Quiz() {
        this.preguntas = new ArrayList<>();
        this.indicePreguntaActual = 0;
        this.aciertos = 0;
        this.errores = 0;
    }

    public void agregarPregunta(Pregunta p) {
        this.preguntas.add(p);
    }

    public Pregunta getPreguntaActual() {
        if (indicePreguntaActual < preguntas.size()) {
            return preguntas.get(indicePreguntaActual);
        }
        return null;
    }

    public boolean verificarRespuesta(int opcionSeleccionada) {
        Pregunta actual = getPreguntaActual();
        if (actual != null && opcionSeleccionada == actual.getRespuestaCorrecta()) {
            aciertos++; 
            return true;
        } else {
            errores++;  
            return false;
        }
    }

    public void siguientePregunta() {
        indicePreguntaActual++;
    }

    public boolean esFinDelQuiz() {
        return indicePreguntaActual >= preguntas.size();
    }

    public int getAciertos() { return aciertos; }
    public int getErrores() { return errores; }
    public int getTotalPreguntas() { return preguntas.size(); }
    public int getIndiceActual() { return indicePreguntaActual; }
    
    public double getPorcentajeAciertos() {
        if (preguntas.isEmpty()) return 0;
        return ((double) aciertos / preguntas.size()) * 100;
    }

    public void reiniciar() {
        this.indicePreguntaActual = 0;
        this.aciertos = 0;
        this.errores = 0;
    }
}