package com.example.chessprim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
// *===== KLASA GŁÓWNA=====*
//* Klasa uruchamia okienko i cały program
public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Podpina plik fxml do okna
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("View.fxml"));

        // Inicjuje okno
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Chess");
        stage.getIcons().add(new Image("C:\\Users\\januf\\OneDrive\\Pulpit\\IDEA\\" +
                "ChessPrim\\src\\main\\resources\\com\\example\\chessprim\\icon.png"));
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    } // > start

    public static void main(String[] args) {
        launch();
    } // > main
} // > App