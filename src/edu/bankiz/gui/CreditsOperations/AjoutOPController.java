/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.bankiz.gui.CreditsOperations;

import edu.bankiz.entities.OperationCredit;
import edu.bankiz.gui.utilisateur.AutentificationController;
import edu.bankiz.tools.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutOPController implements Initializable {
    
    @FXML
    private TableView<OperationCredit> table_stade;
    @FXML
    private TableColumn<OperationCredit, String> col_id;
    @FXML
    private TableColumn<OperationCredit, String> col_dateop;
    @FXML
    private TableColumn<OperationCredit, String> col_montpayer;
    @FXML
    private TableColumn<OperationCredit, String> col_echance;
    @FXML
    private TableColumn<OperationCredit, String> col_tauxinteret;
    @FXML
    private TableColumn<OperationCredit, String> col_solvabilite;
    @FXML
    private TableColumn<OperationCredit, String> col_typeoperation;
    @FXML
    private TableColumn<OperationCredit, String> col_creditid;
      @FXML
    private Button btajout;

    

    /**
     * Initializes the controller class.
     */
       private Connection cnx = null ;
            private PreparedStatement pst = null;
            


     private ObservableList<OperationCredit> listM;
         private ObservableList<OperationCredit> listS = FXCollections.observableArrayList();
     ArrayList name = new ArrayList();

         private ObservableList<OperationCredit> listT = FXCollections.observableArrayList();

      ObservableList<OperationCredit> list = FXCollections.observableArrayList();
    
    @FXML
    private Button btc;
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

    public void initialize(URL url, ResourceBundle rb)
        {
        
        try {
            
            Connection con = MyConnection.getInstance().getCnx();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM operation_credit");
            while(rs.next()){
                list.add(new OperationCredit(rs.getInt(1),rs.getDate(2),rs.getInt(3),rs.getDate(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getInt(8)));
                
                table_stade.setItems(list);
                table_stade.refresh();
            }   } catch (SQLException ex) {    
            Logger.getLogger(AfficherOperationController.class.getName()).log(Level.SEVERE, null, ex);}
            try {
                AfficherStade();
            } catch (SQLException ex1) {
                Logger.getLogger(AfficherOperationController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        
    }
        
        public void AfficherStade() throws SQLException{
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_dateop.setCellValueFactory(new PropertyValueFactory<>("dateOp"));
          col_montpayer.setCellValueFactory(new PropertyValueFactory<>("montPayer"));
          col_echance.setCellValueFactory(new PropertyValueFactory<>("echeance"));
         col_tauxinteret.setCellValueFactory(new PropertyValueFactory<>("tauxInteret"));
          col_solvabilite.setCellValueFactory(new PropertyValueFactory<>("solvabilite"));
          col_typeoperation.setCellValueFactory(new PropertyValueFactory<>("typeOperation"));
          col_creditid.setCellValueFactory(new PropertyValueFactory<>("creditid"));
          
          

        
        //UpdateTable();
        table_stade.setItems(list);
        
    }
          public OperationCredit gettempOperationCredit(TableColumn.CellEditEvent edittedCell) {
        OperationCredit test = table_stade.getSelectionModel().getSelectedItem();
        return test;
    }

    

    
       @FXML
    void ajouteropp(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bankiz/gui/CreditsOperations/ajoutoperationfront.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
     System.out.println("Welcome");
        
    }
    
    @FXML
    void gocredit(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bankiz/gui/CreditsOperations/affichercreditfront.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
     System.out.println("Welcome");
        
    }


    private Stage stage;
    private Scene scene;
    private Parent root;

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
