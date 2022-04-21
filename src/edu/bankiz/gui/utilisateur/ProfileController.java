package edu.bankiz.gui.utilisateur;

import edu.bankiz.entities.Utilisateur;
import edu.bankiz.services.UtilisateurCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController {
    UtilisateurCRUD pcd = new UtilisateurCRUD();
    @javafx.fxml.FXML
    private TableView tabelViewProfile;
    @javafx.fxml.FXML
    private TableColumn date_naissance_col;
    @javafx.fxml.FXML
    private TableColumn cin_col;
    @javafx.fxml.FXML
    private TableColumn nom_col;
    @javafx.fxml.FXML
    private TableColumn prenom_col;
    @javafx.fxml.FXML
    private TableColumn tel_col;
    @javafx.fxml.FXML
    private TableColumn role_col;
    @javafx.fxml.FXML
    private TableColumn email_col;

    public void initialize() {
        ObservableList<Utilisateur> data=FXCollections.observableArrayList();

        data.clear();
        for (Utilisateur utilisateur : data = FXCollections.observableArrayList(pcd.afficherProfile())) {

        }
        cin_col.setCellValueFactory(new PropertyValueFactory<>("cin_u"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom_u"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<>("prenom_u"));
        date_naissance_col.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tel_col.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email_u"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("role"));
        tabelViewProfile.setItems((ObservableList) data);
    }

}
