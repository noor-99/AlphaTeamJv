/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.bankiz.gui.CreditsOperations;

import java.net.URL;
import java.util.ResourceBundle;

import edu.bankiz.gui.utilisateur.AutentificationController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import edu.bankiz.entities.Credit;
import edu.bankiz.services.ServiceCredit;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutcreditfrontController implements Initializable {

    @FXML
    private TextField tfmontcredit;
    @FXML
    private TextField tfdureecredit;
    @FXML
    private TextField tftypecredit;
    @FXML
    private Button btvalider;


    @FXML
    private TextField tfnumero_compte;
    @FXML
    private ImageView imagebank;
//    Image myimage =new Image(getClass().getResourceAsStream("imageB.png"));

    /**
     * Initializes the controller class.
     */

    @FXML
    private Button btcredit;
    @FXML
    private Button btnCreditFront;
    @FXML
    private Button btnCartesFront;
    @FXML
    private Button btnProfileFront;
    @FXML
    private Button btnAccueilFront;
    @FXML
    private Button btnTransFront;
    @FXML
    private Button btnForumFront;
    @FXML
    private Button btnReclamationFront;
    @FXML
    private Button btnCompteFront;
    @FXML
    private Button btnLogoutFront;
    @FXML
    private Button btnChequesFront;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void savecredit(ActionEvent event) {










        /////////////datepe////////////////////////

        //LocalDate ld = DPdatepe.getValue();
//Calendar c =  Calendar.getInstance();
//c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
//Date datepe = c.getTime();
//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//String strDate = dateFormat.format(datepe);

//java.sql.Date sqldatepe = java.sql.Date.valueOf(strDate);



        java.sql.Date sqldatepe=new java.sql.Date(Calendar.getInstance().getTime().getTime());



///////////////////////datede////////////////////////

//LocalDate ld1 = DPdatede.getValue();
//Calendar c1 =  Calendar.getInstance();
//c1.set(ld1.getYear(), ld1.getMonthValue(), ld1.getDayOfMonth());
//Date datede = c.getTime();
//DateFormat dateFormat1 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//String strDate1 = dateFormat1.format(datede);

//java.sql.Date sqldatede = java.sql.Date.valueOf(strDate1);

        java.sql.Date sqldatede=  new java.sql.Date(Calendar.getInstance().getTime().getTime());
/////////////////////////////////////////








        //////////////////////echeance//////////////////////////////////
        //LocalDate ld2 = DPecheance.getValue();
//Calendar c2 =  Calendar.getInstance();
//c2.set(ld2.getYear(), ld2.getMonthValue(), ld2.getDayOfMonth());
//Date echeance = c.getTime();
//DateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//String strDate2 = dateFormat1.format(echeance);

//java.sql.Date sqlecheance = java.sql.Date.valueOf(strDate2);
        java.sql.Date sqlecheance= new java.sql.Date(Calendar.getInstance().getTime().getTime());





        int tauxInteret = 20;

//.............................................................decision.............................

        boolean decision = false;


/////////..............tfetatCredit..................................



        String typecredit = tftypecredit.getText();
        String etatCredit = "demande";
        boolean test = true;

        if ((tfmontcredit.getText()).isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);//before(date2)
            alert1.setTitle("Information");
            alert1.setHeaderText(null);
            alert1.setContentText("mont credit invalide");

            alert1.showAndWait();
            test = false;
        }
        if ((tfdureecredit.getText()).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("la duree credit est invalide");

            alert.showAndWait();
            test = false;
        }else if (!tfdureecredit.getText().isEmpty()){
            if (Integer.parseInt(tfdureecredit.getText())>10) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("la durre de credit doit etre < 10 ");

                alert.showAndWait();
                test = false;
            }
        }
        if (typecredit.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("typecredit credit est invalide");

            alert.showAndWait();
            test = false;
        }
         if ((tfnumero_compte.getText()).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("numero_compte est invalide");

            alert.showAndWait();
             test = false;
         }

        if (test) {
            int dureecredit = Integer.parseInt(tfdureecredit.getText());
            int montcredit = Integer.parseInt(tfmontcredit.getText());
            int compte = Integer.parseInt(tfnumero_compte.getText());
            ServiceCredit sc1=new ServiceCredit();
            Credit hama =new Credit(montcredit,sqldatepe,sqldatede,dureecredit,sqlecheance,tauxInteret,decision,etatCredit,typecredit,compte);
            sc1.ajouter2(hama);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);


            a.setTitle("Credit ajoutée");
            a.show();

//            sc1.ajouter2(hama);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bankiz/gui/CreditsOperations/affichercreditfront.fxml"));
                Parent root = loader.load();
                tfmontcredit.getScene().setRoot(root);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }




    }
    @FXML
    void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bankiz/gui/CreditsOperations/affichercreditfront.fxml"));
            Parent root = loader.load();
            tfmontcredit.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


    @FXML
    public void versAccueil(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("E-Bank Bankiz");
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

    @FXML
    public void redirectReclamationFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Effectuer une Reclamation");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Reclamations/AjouterRecFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
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

    @FXML
    public void redirectCreditFront(ActionEvent actionEvent) {
    }

    @FXML
    public void redirectProfileFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Mon Profile");
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

    @FXML
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void ciaoPopup(Event event) {
        try {
            root = FXMLLoader.load(getClass().getResource("HomeFront.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AutentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
    public void redirectPopUp(Event event) {
        try {

            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            Parent root = FXMLLoader.load(getClass().getResource("../gui/utilisateur/PopUpProfile.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
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
}