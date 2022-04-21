package edu.bankiz.gui.utilisateur;

import edu.bankiz.entities.Utilisateur;
import edu.bankiz.services.UtilisateurCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InscriptionController {
    @javafx.fxml.FXML
    private Button inscription;
    @javafx.fxml.FXML
    private TextField cin;
    @javafx.fxml.FXML
    private TextField telephone;
    @javafx.fxml.FXML
    private PasswordField mot_de_passe;
    @javafx.fxml.FXML
    private TextField nom;
    @javafx.fxml.FXML
    private TextField prenom;
    @javafx.fxml.FXML
    private TextField email;
    @javafx.fxml.FXML
    private DatePicker date_naissance;

    UtilisateurCRUD pcd = new UtilisateurCRUD();
    @javafx.fxml.FXML
    private Label age;
    @javafx.fxml.FXML
    private Button retour_login;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Deprecated
    public void refresh(){
        cin.setText("");
        nom.setText("");
        prenom.setText("");
        date_naissance.setValue(null);
        email.setText("");
        telephone.setText("");
        mot_de_passe.setText("");
        age.setText("");
    }
    public void initialize() {
        date_naissance.valueProperty().addListener((ov, oldValue, newValue) -> {
            UtilisateurCRUD pcd = new UtilisateurCRUD();
            int a = pcd.calculateAge(Date.valueOf(newValue));
            age.setText(String.valueOf(a));
            setAge("votre age est :"+String.valueOf(a)+" Ans");
        });
    }
    public void setAge(String age) {
        this.age.setText(String.valueOf(age));
    }

    public void On_Click_Inscription(ActionEvent actionEvent) {
        StringBuilder errors=new StringBuilder();
        if(!pcd.email_Validation(email.getText())){
            errors.append("- Email invalide !\n");
            //email.setText("Ce champs est obligatoire!!!");
        }
        if(nom.getText().trim().isEmpty()){
            errors.append("- Le Nom est obligatoire\n");//string s --- s+="erreur"
            //nom.setText("Ce champs est obligatoire!!!");
        }
        if(prenom.getText().trim().isEmpty()){
            errors.append("- Le Prenom est obligatoire\n");//string s --- s+="erreur"
        }
        if(date_naissance.getValue()==null){
            errors.append("- Le champ date de naissance est obligatoire !\n");
        }
        try{
            Integer.parseInt(cin.getText());
        }catch(NumberFormatException e){
            errors.append("- CIN invalide !\n");
        }
        try{
            Integer.parseInt(telephone.getText());
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
            U.setCin_u(Integer.parseInt(cin.getText()));
            U.setNom_u(nom.getText());
            U.setPrenom_u(prenom.getText());
            U.setDate_naissance(Date.valueOf(date_naissance.getValue()));
            U.setEmail_u(email.getText());
            U.setNum_tel(Integer.parseInt(telephone.getText()));
            U.setRole("ROLE_USER");
            U.setMot_de_passe(mot_de_passe.getText());


            pcd.ajouterUtilisateur(U);

            refresh();
            JOptionPane.showMessageDialog(null, "vous avez inscrit avec success");
            //JOptionPane.showMessageDialog(null, "vous avez inscrit avec success", "Success", JOptionPane.ERROR_MESSAGE);
            try {
                root = FXMLLoader.load(getClass().getResource("login.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @javafx.fxml.FXML
    public void On_Click_retour_login(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
