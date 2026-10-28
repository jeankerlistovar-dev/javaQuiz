
package com.mycompany.javaquiz;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class QuizController {

    @FXML private Label lblPreguntaNro;
    @FXML private Label lblEnunciado;
    @FXML private ProgressBar barraProgreso;
    @FXML private RadioButton opt1, opt2, opt3, opt4;
    @FXML private ToggleGroup grupoOpciones;
    @FXML private Button btnAccion; 

    private boolean yaComprobado = false;

    @FXML
    public void initialize() {
        yaComprobado = false;
        
        if (grupoOpciones == null) {
            grupoOpciones = new ToggleGroup();
        }
        opt1.setToggleGroup(grupoOpciones);
        opt2.setToggleGroup(grupoOpciones);
        opt3.setToggleGroup(grupoOpciones);
        opt4.setToggleGroup(grupoOpciones);
        
        actualizarPregunta();
    }

    @FXML
    private void accionBoton() throws IOException {
        Quiz quiz = App.getQuiz();

        if (!yaComprobado) {
            RadioButton seleccionado = (RadioButton) grupoOpciones.getSelectedToggle();

            if (seleccionado == null) {
                if (opt1.isSelected()) seleccionado = opt1;
                else if (opt2.isSelected()) seleccionado = opt2;
                else if (opt3.isSelected()) seleccionado = opt3;
                else if (opt4.isSelected()) seleccionado = opt4;
            }

            if (seleccionado == null) {
                Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecciona una opción.");
                alerta.showAndWait();
                return;
            }

            int respuesta = 1;
            if (seleccionado == opt2) respuesta = 2;
            if (seleccionado == opt3) respuesta = 3;
            if (seleccionado == opt4) respuesta = 4;

            boolean correcto = quiz.verificarRespuesta(respuesta);

            Alert alerta = new Alert(correcto ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR, correcto ? "¡Correcto!" : "Incorrecto.");
            alerta.showAndWait();

            btnAccion.setText("Siguiente");
            yaComprobado = true;
        } else {
            quiz.siguientePregunta();
            if (quiz.esFinDelQuiz()) {
                App.setRoot("clasificacion");
            } else {
                yaComprobado = false;
                btnAccion.setText("Comprobar");
                actualizarPregunta();
            }
        }
    }

    private void actualizarPregunta() {
        Quiz quiz = App.getQuiz();
        Pregunta actual = quiz.getPreguntaActual();
        if (actual != null) {
            int num = quiz.getIndiceActual() + 1;
            lblPreguntaNro.setText("Pregunta " + num + " de " + quiz.getTotalPreguntas());
            barraProgreso.setProgress((double) quiz.getIndiceActual() / quiz.getTotalPreguntas());

            lblEnunciado.setText(actual.getEnunciado());
            opt1.setText(actual.getOpcion1());
            opt2.setText(actual.getOpcion2());
            opt3.setText(actual.getOpcion3());
            opt4.setText(actual.getOpcion4());

            if (grupoOpciones.getSelectedToggle() != null) {
                grupoOpciones.getSelectedToggle().setSelected(false);
            }
        }
    }
}