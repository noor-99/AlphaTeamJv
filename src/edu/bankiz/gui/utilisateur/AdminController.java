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

public class AdminController {
    @javafx.fxml.FXML
    private Button utilisateur;
    @javafx.fxml.FXML
    private Button reclamations;
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    @javafx.fxml.FXML
    public void On_Click_Rec_Back(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("ReclamationBack.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
