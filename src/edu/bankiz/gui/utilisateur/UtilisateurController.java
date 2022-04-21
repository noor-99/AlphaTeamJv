package edu.bankiz.gui.utilisateur;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UtilisateurController {
    @javafx.fxml.FXML
    private Button profile;


    @javafx.fxml.FXML
    public void On_Click_Profile(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));///charger les element de fxml
            Parent root = loader.load();
            Stage stage =new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Profile utilisateur");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
