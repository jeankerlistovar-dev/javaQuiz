
package com.mycompany.javaquiz;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class ClasificacionController {

    @FXML private Label lblAciertos;
    @FXML private Label lblErrores;
    @FXML private Label lblNota;
    @FXML private TextField txtNombre;
    @FXML private Button btnGuardar;

    @FXML
    public void initialize() {
        Quiz quiz = App.getQuiz();
        lblAciertos.setText("Aciertos: " + quiz.getAciertos());
        lblErrores.setText("Errores: " + quiz.getErrores());
        lblNota.setText("Nota Final: " + String.format("%.1f", quiz.getPorcentajeAciertos()) + "%");
    }

    @FXML
    private void accionGuardar() {
        if (txtNombre.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Escribe tu nombre.");
            alert.showAndWait();
        } else {
            GestorArchivos.guardarPuntuacion("puntuaciones.txt", txtNombre.getText(), App.getQuiz().getPorcentajeAciertos());
            btnGuardar.setDisable(true);
            txtNombre.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Puntuación guardada.");
            alert.showAndWait();
        }
    }

    @FXML
    private void accionVolver() throws IOException {
        App.setRoot("menu");
    }
}