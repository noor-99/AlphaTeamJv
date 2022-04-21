package edu.bankiz.gui.utilisateur;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import edu.bankiz.entities.Session;
import edu.bankiz.entities.Utilisateur;
import edu.bankiz.services.UtilisateurCRUD;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.security.provider.MD5;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    @javafx.fxml.FXML
    private PasswordField mdp_login;
    @javafx.fxml.FXML
    private Button se_connecter;
    @javafx.fxml.FXML
    private TextField email_login;

    private Stage stage;
    private Scene scene;
    private Parent root;

    UtilisateurCRUD pcd = new UtilisateurCRUD();
    @javafx.fxml.FXML
    private Button mdp_oubliee;

    @javafx.fxml.FXML
    public void On_Click_Se_Connecter(ActionEvent actionEvent) {
        if (pcd.Check_login(email_login.getText(),mdp_login.getText()))
        {

            if (pcd.login(email_login.getText(),mdp_login.getText())){
                Utilisateur u = pcd.findByMail(email_login.getText());


                System.out.println( Session.getEtat());

                switch (u.getRole()){
                    case "ROLE_USER":
                        try {
                            if(Session.getEtat().equals("Debloquer")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Utilisateur.fxml"));///charger les element de fxml
                                Parent root = loader.load();
                                Stage stage =new Stage();
                                Scene scene = new Scene(root);
                                stage.setTitle("Dashbord User");
                                stage.setScene(scene);
                                stage.show();}
                            else{
                                JOptionPane.showMessageDialog(null, "bloquer", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ROLE_ADMIN" :
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));///charger les element de fxml
                            Parent root = loader.load();
                            Stage stage =new Stage();
                            Scene scene = new Scene(root);
                            stage.setTitle("Dashbord ADMIN");
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }



            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Email or password invalid!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @javafx.fxml.FXML
    public void On_Click_mdpOubliee(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MdpOubliee.fxml"));///charger les element de fxml
            Parent root = loader.load();
            Stage stage =new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Reset mot de passe");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
