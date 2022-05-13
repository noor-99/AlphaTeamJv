package edu.bankiz.gui.Reclamations;

import edu.bankiz.entities.Reclamation;
import edu.bankiz.entities.Session;
import edu.bankiz.gui.utilisateur.AutentificationController;
import edu.bankiz.services.ReclamationCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterRecFrontController {
    @javafx.fxml.FXML
    private Button btnCartesFront;
    @javafx.fxml.FXML
    private Button btnCreditFront;
    @javafx.fxml.FXML
    private Button btnProfileFront;
    @javafx.fxml.FXML
    private Button btnAccueilFront;
    @javafx.fxml.FXML
    private Button btnTransFront;
    @javafx.fxml.FXML
    private Button btnForumFront;
    @javafx.fxml.FXML
    private Button btnReclamationFront;
    @javafx.fxml.FXML
    private Button btnCompteFront;
    @javafx.fxml.FXML
    private Button btnLogoutFront;
    @javafx.fxml.FXML
    private Button btnChequesFront;
    @javafx.fxml.FXML
    private Button ajouter_rec;
    @javafx.fxml.FXML
    private TextField type_rec_Ajouter;
    @javafx.fxml.FXML
    private TextField desc_rec_Ajouter;

    private Stage stage;
    private Scene scene;
    private Parent root;

    Reclamation R = new Reclamation();
    ReclamationCRUD rec = new ReclamationCRUD();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    @javafx.fxml.FXML
    private Label cntrlType;
    @javafx.fxml.FXML
    private Label cntrlDesc;

    long millis=System.currentTimeMillis();
    java.sql.Date date = new java.sql.Date(millis);


    //******************Controle de saisie**************
    private boolean validateType() {
        if (!type_rec_Ajouter.getText().isEmpty()) {
            if (type_rec_Ajouter.getText().matches("(.*[a-z].*)")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validateDesc() {
        if (!desc_rec_Ajouter.getText().isEmpty()) {
            if (desc_rec_Ajouter.getText().matches("(.*[a-z].*)") ) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @javafx.fxml.FXML
    public void redirectCartesFront(ActionEvent actionEvent) {
        try {
//            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Calendrier");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CartesCategories/Calendar.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectLogoutFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/utilisateur/Autentification.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectCreditFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CreditsOperations/affichercreditfront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectProfileFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Profile");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/utilisateur/Profile.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void RedirectAccueilFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Acceuil");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/HomeFront/HomeFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectTransactionsFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Effectuer une Transaction Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Transactions/Front/TransactionsFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectForumFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Forum Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/PublicationFront/PublicationFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectChequesFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mes cheques & chequiers");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/ChequesChequiers/FrontuserchequeController.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectCompteFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mon Compte Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Comptes/Front/MonCompteBancaire.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void On_Cick_PasserRec(ActionEvent actionEvent) {
        if (validateType() && validateDesc()){
            R.setNom_u_id(Session.getId());
            R.setType_rec(type_rec_Ajouter.getText());
            //java.util.Date current_date = new java.util.Date();
            R.setDate_rec(date);
            R.setDesc_rec(desc_rec_Ajouter.getText());
            R.setEtat_rec("Encour");

            rec.ajouterReclamation(R);
//            Notifications b = Notifications.create()
//                    .title("Erreur")
//                    .text("Suppression impossible: Reclamation a depasée 24h depuis sa creation")
//                    .graphic(null)
//                    .position(Pos.TOP_CENTER)
//                    .hideAfter(Duration.seconds(5));
//            b.showWarning();
            JOptionPane.showMessageDialog(null, "Reclamation passé avec success");
            try {
                root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/utilisateur/Profile.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(AutentificationController.class.getName()).log(Level.SEVERE, null, ex);
            }

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            if (!validateType()){
                type_rec_Ajouter.setStyle("-fx-border-color: #f00020");
                if (type_rec_Ajouter.getText().isEmpty()){
                    cntrlType.setText("-Ce champs est obligatoire");
                }else {
                    cntrlType.setText("- Type invalide");
                }
            }else {
                type_rec_Ajouter.setStyle("-fx-border-color: #22780F");
            }
            if (!validateDesc()){
                desc_rec_Ajouter.setStyle("-fx-border-color: #f00020");
                cntrlDesc.setText("Ce champs contient que des caractere");
            }else{
                desc_rec_Ajouter.setStyle("-fx-border-color: #22780F");
            }
        }

    }
}
