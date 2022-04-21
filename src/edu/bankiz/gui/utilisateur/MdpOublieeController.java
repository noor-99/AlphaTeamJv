package edu.bankiz.gui.utilisateur;

import edu.bankiz.services.UtilisateurCRUD;
import edu.bankiz.tools.MailApi;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MdpOublieeController {
    String emailPW = null;
    @javafx.fxml.FXML
    private Button search;
    @javafx.fxml.FXML
    private TextField code;
    @javafx.fxml.FXML
    private TextField email_search;
    @javafx.fxml.FXML
    private TextField new_pwd;
    @javafx.fxml.FXML
    private TextField confirme_pwd;

    UtilisateurCRUD pcd = new UtilisateurCRUD();
    Utilisateur u = new Utilisateur();
    int n;
    @javafx.fxml.FXML
    private Button btn_modifier;

    public void initialize() {
        // TODO
        btn_modifier.setVisible(false);
        code.setVisible(false);
        new_pwd.setVisible(false);
        confirme_pwd.setVisible(false);
        Random rand =new Random();
        n=rand.nextInt(99999);
    }

    @javafx.fxml.FXML
    public void On_Click_Chercher(ActionEvent actionEvent) {
        if(pcd.verifierEmailBd(email_search.getText())){
            String mail = email_search.getText();
            MailApi.send("testutilisateurs1@gmail.com", "espritBankiz", mail, "Forgot password", "This is your code for updating your password: "+n);
            email_search.setVisible(false);
            search.setVisible(false);
            btn_modifier.setVisible(true);
            code.setVisible(true);
            new_pwd.setVisible(true);
            confirme_pwd.setVisible(true);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid email");
            alert.setContentText("Email doesn't exist");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void On_Click_ModifierMDP(ActionEvent actionEvent) {
        System.out.println(u);
        if (code.getText().equals(String.valueOf(n)) && new_pwd.getText().equals(confirme_pwd.getText())){
            String mail = email_search.getText();
            String pwd = new_pwd.getText();
            pcd.modifierPassword(mail,pwd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("Mot de passe changee");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("bnnnnnnnn");
            alert.setContentText("nnnnnnnnnnnn");
            alert.showAndWait();
        }

    }
}
