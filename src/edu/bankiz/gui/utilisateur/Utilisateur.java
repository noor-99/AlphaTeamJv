package edu.bankiz.gui.utilisateur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Utilisateur extends Application {//yoser.walha@esprit.tn
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Utilisateur.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 680);
        stage.setTitle("Inscription!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
