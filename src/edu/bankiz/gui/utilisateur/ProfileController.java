package edu.bankiz.gui.utilisateur;

import edu.bankiz.entities.Reclamation;
import edu.bankiz.entities.Session;
import edu.bankiz.entities.Utilisateur;
import edu.bankiz.services.ReclamationCRUD;
import edu.bankiz.services.UtilisateurCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController {
    UtilisateurCRUD pcd = new UtilisateurCRUD();
    @javafx.fxml.FXML
    private TableView<Utilisateur> tabelViewProfile;
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
    int idModifier;
    String emailModif;
    Utilisateur U = new Utilisateur();
    @javafx.fxml.FXML
    private TextField cin_profile;
    @javafx.fxml.FXML
    private TextField telephone_profile;
    @javafx.fxml.FXML
    private DatePicker date_naissance_profile;
    @javafx.fxml.FXML
    private Button modifier_profile;
    @javafx.fxml.FXML
    private TextField prenom_profile;
    @javafx.fxml.FXML
    private TextField nom_profile;
    @javafx.fxml.FXML
    private TextField email_profile;
    @javafx.fxml.FXML
    private TableColumn desc_rec_col;
    @javafx.fxml.FXML
    private TableColumn date_rec_col;
    @javafx.fxml.FXML
    private TableColumn etat_rec_col;
    @javafx.fxml.FXML
    private TableColumn type_rec_col;
    @javafx.fxml.FXML
    private TableView<Reclamation> TableViewReclamtionProfile;
    ReclamationCRUD rec = new ReclamationCRUD();

    public void initialize() {
        ObservableList<Utilisateur> data=FXCollections.observableArrayList();

        data.clear();
        for (Utilisateur utilisateur : data = FXCollections.observableArrayList(pcd.afficherProfile())) {
            cin_col.setCellValueFactory(new PropertyValueFactory<>("cin_u"));
            nom_col.setCellValueFactory(new PropertyValueFactory<>("nom_u"));
            prenom_col.setCellValueFactory(new PropertyValueFactory<>("prenom_u"));
            date_naissance_col.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
            tel_col.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
            email_col.setCellValueFactory(new PropertyValueFactory<>("email_u"));
            role_col.setCellValueFactory(new PropertyValueFactory<>("role"));
            tabelViewProfile.setItems((ObservableList) data);
        }

        ObservableList<Reclamation> donne=FXCollections.observableArrayList();

        donne.clear();
        for (Reclamation Reclamation : donne = FXCollections.observableArrayList(rec.afficherMesReclamations(Session.getId()))) {
            type_rec_col.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
            date_rec_col.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
            etat_rec_col.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
            desc_rec_col.setCellValueFactory(new PropertyValueFactory<>("desc_rec"));

            TableViewReclamtionProfile.setItems((ObservableList) donne);
        }

    }

    @javafx.fxml.FXML
    public void On_Click_TableViewProfile(Event event) {
        String email =tabelViewProfile.getSelectionModel().getSelectedItem().getEmail_u();
        Utilisateur user= pcd.findByMail(email);
        idModifier=user.getId();

        cin_profile.setText(String.valueOf(user.getCin_u()));
        nom_profile.setText(user.getNom_u());
        prenom_profile.setText(user.getPrenom_u());
        date_naissance_profile.setValue(LocalDate.parse(String.valueOf(user.getDate_naissance())));
        email_profile.setText(user.getEmail_u());
        telephone_profile.setText(String.valueOf(user.getNum_tel()));

        emailModif= user.getEmail_u();
    }

    @javafx.fxml.FXML
    public void On_Click_Modifier_Profile(ActionEvent actionEvent) {
        StringBuilder errors=new StringBuilder();
        if(!pcd.email_Validation(email_profile.getText())){
            errors.append("- Email invalide !\n");
        }
        if(nom_profile.getText().trim().isEmpty()){
            errors.append("- Le Nom est obligatoire\n");//string s --- s+="erreur"
        }
        if(prenom_profile.getText().trim().isEmpty()){
            errors.append("- Le Prenom est obligatoire\n");//string s --- s+="erreur"
        }
        if(date_naissance_profile.getValue()==null){
            errors.append("- Le champ date de naissance est obligatoire !\n");
        }
        try{
            Integer.parseInt(cin_profile.getText());
        }catch(NumberFormatException e){
            errors.append("- CIN invalide !\n");
        }
        try{
            Integer.parseInt(telephone_profile.getText());
        }catch(NumberFormatException e){
            errors.append("- Numero Telephone invalide !\n");
        }
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else{
            U.setCin_u(Integer.parseInt(cin_profile.getText()));
            U.setNom_u(nom_profile.getText());
            U.setPrenom_u(prenom_profile.getText());
            U.setDate_naissance(Date.valueOf(date_naissance_profile.getValue()));
            U.setEmail_u(email_profile.getText());
            U.setNum_tel(Integer.parseInt(telephone_profile.getText()));

            UtilisateurCRUD pcd =  new UtilisateurCRUD();
            pcd.modifierProfile(U,idModifier);
            JOptionPane.showMessageDialog(null, "Modification effectué avec success");
        }
        initialize();
    }
}
