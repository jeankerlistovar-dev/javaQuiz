
package com.mycompany.javaquiz;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MenuController {

    @FXML private Button botonIniciar;

    @FXML
    private void accionIniciar() throws IOException {
        App.getQuiz().reiniciar();
        App.setRoot("quiz"); 
    }

    @FXML
    private void accionPuntuaciones() {
        StringBuilder historial = new StringBuilder();
        File archivoPuntos = new File("puntuaciones.txt");
        
        if (!archivoPuntos.exists()) {
            historial.append("No hay registros todavía.");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(archivoPuntos))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    historial.append(linea).append("\n");
                }
            } catch (IOException ex) {
                historial.append("Error al leer el historial.");
            }
        }
        
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, historial.toString());
        alerta.setTitle("Puntuaciones");
        alerta.setHeaderText("Historial:");
        alerta.showAndWait();
    }

    @FXML
    private void accionInstrucciones() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, "Selecciona la respuesta correcta y pulsa Comprobar.");
        alerta.setTitle("Instrucciones");
        alerta.showAndWait();
    }

    @FXML
    private void accionSalir() {
        Stage stage = (Stage) botonIniciar.getScene().getWindow();
        stage.close();
    }
}
