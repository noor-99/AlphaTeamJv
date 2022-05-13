/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.bankiz.gui.CreditsOperations;

import edu.bankiz.entities.Credit;
import edu.bankiz.services.ServiceCredit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField tfmontcredit;
    @FXML
    private TextField tfdureecredit;
    @FXML
    private TextField tftypecredit;
    @FXML
    private Button btvaliderr;
    @FXML
    private DatePicker DPdatepe;
    @FXML
    private DatePicker DPdatede;
    @FXML
    private DatePicker DPecheance;
    @FXML
    private CheckBox CBdecision;
    @FXML
    private TextField tftauxInteret;
    @FXML
    private TextField tfetatCredit;
    @FXML
    private TextField tfnumero_compte;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnCommentaireBack;
    @FXML
    private Button TransactionsBack1;
    @FXML
    private AnchorPane contenuBack;
    @FXML
    private AnchorPane root;
    @FXML
    private Button ComptesBack;
    @FXML
    private Button pubBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private Button btnCartesBack;
    @FXML
    private Button btnReclamationsBack;
    @FXML
    private Button btnChequiersBack;
    @FXML
    private Button btnUtilisateursBack;
    @FXML
    private Button btnLogoutBack;
    @FXML
    private Button btnAccueil;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  @FXML//savecreditt
    void savecreditt(ActionEvent event) {
        
        
        
        int montcredit = Integer.parseInt(tfmontcredit.getText());
        
        
        /////////////datepe////////////////////////
        
        //LocalDate ld = DPdatepe.getValue();
//Calendar c =  Calendar.getInstance();
//c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
//Date datepe = c.getTime(); 
//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
//String strDate = dateFormat.format(datepe); 

//java.sql.Date sqldatepe = java.sql.Date.valueOf(strDate);



java.sql.Date sqldatepe= java.sql.Date.valueOf(DPdatepe.getValue());
  


///////////////////////datede////////////////////////

//LocalDate ld1 = DPdatede.getValue();
//Calendar c1 =  Calendar.getInstance();
//c1.set(ld1.getYear(), ld1.getMonthValue(), ld1.getDayOfMonth());
//Date datede = c.getTime();
//DateFormat dateFormat1 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
//String strDate1 = dateFormat1.format(datede); 

//java.sql.Date sqldatede = java.sql.Date.valueOf(strDate1);

java.sql.Date sqldatede= java.sql.Date.valueOf(DPdatede.getValue());
/////////////////////////////////////////

        
          int dureecredit = Integer.parseInt(tfdureecredit.getText());
        
        
        
        
        
      //////////////////////echeance//////////////////////////////////  
        //LocalDate ld2 = DPecheance.getValue();
//Calendar c2 =  Calendar.getInstance();
//c2.set(ld2.getYear(), ld2.getMonthValue(), ld2.getDayOfMonth());
//Date echeance = c.getTime();
//DateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
//String strDate2 = dateFormat1.format(echeance); 

//java.sql.Date sqlecheance = java.sql.Date.valueOf(strDate2);
java.sql.Date sqlecheance= java.sql.Date.valueOf(DPecheance.getValue());





int tauxInteret = Integer.parseInt(tftauxInteret.getText());

//.............................................................decision.............................

     boolean decision = CBdecision.isSelected();

if(decision){
   CBdecision.setSelected(false);
} else {
   CBdecision.setSelected(true);
}
/////////..............tfetatCredit..................................



String typecredit = tftypecredit.getText();
String etatCredit = tfetatCredit.getText();
int compte = Integer.parseInt(tfnumero_compte.getText());
if (dureecredit<1) {
    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);//before(date2)
    alert1.setTitle("Information");
    alert1.setHeaderText(null);
    alert1.setContentText("mont credit invalide");
    
    alert1.showAndWait();
        } else if (dureecredit >5) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("la duree credit doit etre inferieure a 5");
            
            alert.showAndWait();
        }
        else {
            
            ServiceCredit sc1=new ServiceCredit();
            Credit hama =new Credit(montcredit,sqldatepe,sqldatede,dureecredit,sqlecheance,tauxInteret,decision,etatCredit,typecredit,compte);
            sc1.ajouter2(hama);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Credit ajoutée");
            a.show();
            sc1.ajouter2(hama);
                          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bankiz/gui/CreditsOperations/afficherCredit.fxml"));
            Parent root = loader.load();
            CBdecision.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }


        
    }

    @FXML
    public void OnRetour(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("E-Bank Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("afficherCredit.fxml"));

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
    public void versChequiersBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chequiers ");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/ChequesChequiers/Home.fxml"));

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
    public void versComptesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Comptes Bancaires");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Comptes/Back/ComptesBack.fxml"));

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
    public void versUtilisateursBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Liste Utilisateurs");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/utilisateur/UtilisateurBack.fxml"));

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
    public void versChequesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chèques ");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/ChequesChequiers/Baccheque.fxml"));

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
    public void logoutBack(ActionEvent actionEvent) {
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
    public void versCartesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les cartes ");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CartesCategories/AjoutCarte.fxml"));

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
    public void openPublicationBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Publications");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/PublicationBack/PublicationBack.fxml"));

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
    public void versTransactionsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions Bancaires");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Transactions/Back/TransactionsBack.fxml"));

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
    public void openCommentaireBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Commentaires Forum Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CommentaireBack/CommentaireBack.fxml"));

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
    public void openReclamationBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Liste Reclamations");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Reclamations/ReclamationBack.fxml"));

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
    public void versAccueilBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Dashboard Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/Home/Home.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Deprecated
    public void versCreditsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CreditsOperations/afficherCredit.fxml"));

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
