package edu.bankiz.gui.utilisateur;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    @javafx.fxml.FXML
    private Button utilisateur;

    @javafx.fxml.FXML
    public void On_Click_Utilisateur(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UtilisateurBack.fxml"));///charger les element de fxml
            Parent root = loader.load();
            Stage stage =new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("back utilisateur");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
