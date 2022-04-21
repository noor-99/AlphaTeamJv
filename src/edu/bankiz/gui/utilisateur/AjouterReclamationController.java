package edu.bankiz.gui.utilisateur;

import edu.bankiz.entities.Reclamation;
import edu.bankiz.entities.Session;
import edu.bankiz.services.ReclamationCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterReclamationController {
    @javafx.fxml.FXML
    private TextField desc_rec_Ajouter;
    @javafx.fxml.FXML
    private Button ajouter_rec;
    @javafx.fxml.FXML
    private TextField type_rec_Ajouter;
    private Stage stage;
    private Scene scene;
    private Parent root;

    Reclamation R = new Reclamation();
    ReclamationCRUD rec = new ReclamationCRUD();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    @javafx.fxml.FXML
    public void On_Cick_PasserRec(ActionEvent actionEvent) {
        StringBuilder errors=new StringBuilder();
        if(type_rec_Ajouter.getText().trim().isEmpty()){
            errors.append("- Le Type est obligatoire\n");//string s --- s+="erreur"
        }
        if(desc_rec_Ajouter.getText().trim().isEmpty()){
            errors.append("- La description est obligatoire\n");//string s --- s+="erreur"
        }
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }else {
            R.setNom_u_id(Session.getId());
            R.setType_rec(type_rec_Ajouter.getText());
            R.setDate_rec(dtf.format(now));
            R.setDesc_rec(desc_rec_Ajouter.getText());
            R.setEtat_rec("Encour");

            rec.ajouterReclamation(R);
            JOptionPane.showMessageDialog(null, "Reclamation passé avec success");
            try {
                root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


    }
}
