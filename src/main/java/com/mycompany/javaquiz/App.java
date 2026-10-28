package com.mycompany.javaquiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class App {

    private static Scene escena;
    private static Quiz quiz;
    private static String rutaCSS;

    public static void main(String[] args) {
        quiz = new Quiz();
        
        try {
            java.net.URL urlPreguntas = App.class.getResource("/preguntas.txt");
            if (urlPreguntas != null) {
                ArrayList<Pregunta> cargadas = GestorArchivos.cargarPreguntas(urlPreguntas.getPath());
                for (Pregunta p : cargadas) {
                    quiz.agregarPregunta(p);
                }
            } else {
                System.out.println("ERROR: No se encuentra 'preguntas.txt' en src/main/resources");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar las preguntas: " + e.getMessage());
        }

        Application.launch(Lanzador.class, args);
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent raiz = fxmlLoader.load();
        escena.setRoot(raiz);
        
        if (rutaCSS != null && !escena.getStylesheets().contains(rutaCSS)) {
            escena.getStylesheets().add(rutaCSS);
        }
    }

    public static Quiz getQuiz() { return quiz; }
    public static String getRutaCSS() { return rutaCSS; }

    public static class Lanzador extends Application {
        @Override
        public void start(Stage primaryStage) throws IOException {

            java.net.URL urlCSS = App.class.getResource("estilos.css");
            if (urlCSS != null) {
                rutaCSS = urlCSS.toExternalForm();
            } else {
                System.out.println("ADVERTENCIA: No se encontró 'estilos.css' junto a los FXML");
            }

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
            Parent raiz = fxmlLoader.load();
            
            escena = new Scene(raiz, 500, 490);
            
            if (rutaCSS != null) {
                escena.getStylesheets().add(rutaCSS);
            }
            
            primaryStage.setTitle("JavaQuizFX");
            primaryStage.setScene(escena);
            primaryStage.show();
        }
    }
}