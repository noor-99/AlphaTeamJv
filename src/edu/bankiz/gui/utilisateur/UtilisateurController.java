package edu.bankiz.gui.utilisateur;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilisateurController {
    @javafx.fxml.FXML
    private Button profile;
    @javafx.fxml.FXML
    private Button reclamation;
    private Stage stage;
    private Scene scene;
    private Parent root;


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

    @javafx.fxml.FXML
    public void On_Click_Reclamation(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
