package edu.bankiz.gui.utilisateur;

import edu.bankiz.entities.Utilisateur;
import edu.bankiz.services.UtilisateurCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class UtilisateurBackController {
    UtilisateurCRUD pcd = new UtilisateurCRUD();
    @javafx.fxml.FXML
    private TableColumn email_B;
    @javafx.fxml.FXML
    private TableColumn telephone_B;
    @javafx.fxml.FXML
    private TableView<Utilisateur> TableViewBackUser;
    @javafx.fxml.FXML
    private TableColumn prenom_B;
    @javafx.fxml.FXML
    private TableColumn date_naissance_B;
    @javafx.fxml.FXML
    private TableColumn nom_B;
    @javafx.fxml.FXML
    private TableColumn role_B;
    @javafx.fxml.FXML
    private TableColumn cin_B;
    @javafx.fxml.FXML
    private TextField cin_Ajout_B;
    @javafx.fxml.FXML
    private TextField prenom_Ajout_B;
    @javafx.fxml.FXML
    private TextField role_Ajout_B;
    @javafx.fxml.FXML
    private TextField telephone_Ajout_B;
    @javafx.fxml.FXML
    private TextField nom_Ajout_B;
    @javafx.fxml.FXML
    private TextField email_Ajout_B;
    @javafx.fxml.FXML
    private PasswordField mdp_Ajout_B;
    @javafx.fxml.FXML
    private DatePicker date_Ajout_B;
    @javafx.fxml.FXML
    private Button Ajouter_utilisateur_B;
    @javafx.fxml.FXML
    private Button ModifierUtilisateur;
    int idModifier;
    String emailModif;
    Utilisateur U = new Utilisateur();

    public void initialize() {
        ObservableList<edu.bankiz.entities.Utilisateur> data= FXCollections.observableArrayList();

        data.clear();
        for (Utilisateur utilisateur : data = FXCollections.observableArrayList(pcd.afficherUtilisateur())) {
            cin_B.setCellValueFactory(new PropertyValueFactory<>("cin_u"));
            nom_B.setCellValueFactory(new PropertyValueFactory<>("nom_u"));
            prenom_B.setCellValueFactory(new PropertyValueFactory<>("prenom_u"));
            date_naissance_B.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
            telephone_B.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
            email_B.setCellValueFactory(new PropertyValueFactory<>("email_u"));
            role_B.setCellValueFactory(new PropertyValueFactory<>("role"));
            TableViewBackUser.setItems((ObservableList) data);
            addEtatColumn();


        }

    }
    public void refresh(){
        cin_Ajout_B.setText("");
        nom_Ajout_B.setText("");
        prenom_Ajout_B.setText("");
        date_Ajout_B.setValue(null);
        email_Ajout_B.setText("");
        telephone_Ajout_B.setText("");
        mdp_Ajout_B.setText("");
    }

    @javafx.fxml.FXML
    public void On_Click_AjouterUtilisateur(ActionEvent actionEvent) {
        StringBuilder errors=new StringBuilder();
        if(!pcd.email_Validation(email_Ajout_B.getText())){
            errors.append("- Email invalide !\n");
        }
        if(nom_Ajout_B.getText().trim().isEmpty()){
            errors.append("- Le Nom est obligatoire\n");//string s --- s+="erreur"
        }
        if(prenom_Ajout_B.getText().trim().isEmpty()){
            errors.append("- Le Prenom est obligatoire\n");//string s --- s+="erreur"
        }
        if(date_Ajout_B.getValue()==null){
            errors.append("- Le champ date de naissance est obligatoire !\n");
        }
        try{
            Integer.parseInt(cin_Ajout_B.getText());
        }catch(NumberFormatException e){
            errors.append("- CIN invalide !\n");
        }
        try{
            Integer.parseInt(telephone_Ajout_B.getText());
        }catch(NumberFormatException e){
            errors.append("- Numero Telephone invalide !\n");
        }
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else {
            Utilisateur U = new Utilisateur();
            U.setCin_u(Integer.parseInt(cin_Ajout_B.getText()));
            U.setNom_u(nom_Ajout_B.getText());
            U.setPrenom_u(prenom_Ajout_B.getText());
            U.setDate_naissance(Date.valueOf(date_Ajout_B.getValue()));
            U.setEmail_u(email_Ajout_B.getText());
            U.setNum_tel(Integer.parseInt(telephone_Ajout_B.getText()));
            //U.setRole("ROLE_CLIENT");
            U.setRole(role_Ajout_B.getText());
            U.setMot_de_passe(mdp_Ajout_B.getText());


            pcd.ajouterUtilisateur(U);

            refresh();
            JOptionPane.showMessageDialog(null, "vous ajouter un utilisateur avec success");
            //JOptionPane.showMessageDialog(null, "vous avez inscrit avec success", "Success", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void addEtatColumn() {
        TableColumn<Utilisateur, Void> colBtn = new TableColumn("Etat");

        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> cellFactory = new Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>>() {

            public TableCell<Utilisateur, Void> call(final TableColumn<Utilisateur, Void> param) {
                final TableCell<Utilisateur, Void> cell = new TableCell<Utilisateur, Void>() {
                    ComboBox combEtat = new ComboBox();

                    {
                        combEtat.getItems().add("Bloquer");
                        combEtat.getItems().add("Debloquer");
                        combEtat.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                            Utilisateur data = getTableView().getItems().get(getIndex());
                            data.setEtat((String) newValue);
                            UtilisateurCRUD.UpdatePersonne(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Utilisateur data = getTableView().getItems().get(getIndex());
                            combEtat.setValue(data.getEtat());
                            setGraphic(combEtat);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        TableViewBackUser.getColumns().add(colBtn);

    }



    @javafx.fxml.FXML
    public void On_Click_ModifierU(ActionEvent actionEvent) {

        StringBuilder errors=new StringBuilder();
        if(!pcd.email_Validation(email_Ajout_B.getText())){
            errors.append("- Email invalide !\n");
        }
        if(nom_Ajout_B.getText().trim().isEmpty()){
            errors.append("- Le Nom est obligatoire\n");//string s --- s+="erreur"
        }
        if(prenom_Ajout_B.getText().trim().isEmpty()){
            errors.append("- Le Prenom est obligatoire\n");//string s --- s+="erreur"
        }
        if(date_Ajout_B.getValue()==null){
            errors.append("- Le champ date de naissance est obligatoire !\n");
        }
        try{
            Integer.parseInt(cin_Ajout_B.getText());
        }catch(NumberFormatException e){
            errors.append("- CIN invalide !\n");
        }
        try{
            Integer.parseInt(telephone_Ajout_B.getText());
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
            U.setCin_u(Integer.parseInt(cin_Ajout_B.getText()));
            U.setNom_u(nom_Ajout_B.getText());
            U.setPrenom_u(prenom_Ajout_B.getText());
            U.setDate_naissance(Date.valueOf(date_Ajout_B.getValue()));
            U.setEmail_u(email_Ajout_B.getText());
            U.setNum_tel(Integer.parseInt(telephone_Ajout_B.getText()));
            //U.setRole("ROLE_CLIENT");
            U.setRole(role_Ajout_B.getText());
            U.setMot_de_passe(mdp_Ajout_B.getText());

            UtilisateurCRUD pcd =  new UtilisateurCRUD();
            pcd.modifierUtlisateur(U,idModifier);
            JOptionPane.showMessageDialog(null, "Modification effectué avec success");


        }
        initialize();
    }

    @javafx.fxml.FXML
    public void On_Click_TableViewUser(Event event) {
        String email =TableViewBackUser.getSelectionModel().getSelectedItem().getEmail_u();
        Utilisateur user= pcd.findByMail(email);
        idModifier=user.getId();

        cin_Ajout_B.setText(String.valueOf(user.getCin_u()));
        nom_Ajout_B.setText(user.getNom_u());
        prenom_Ajout_B.setText(user.getPrenom_u());
        date_Ajout_B.setValue(LocalDate.parse(String.valueOf(user.getDate_naissance())));
        email_Ajout_B.setText(user.getEmail_u());
        telephone_Ajout_B.setText(String.valueOf(user.getNum_tel()));
        role_Ajout_B.setText(user.getRole());
        mdp_Ajout_B.setText(user.getMot_de_passe());
        emailModif= user.getEmail_u();
    }
}
