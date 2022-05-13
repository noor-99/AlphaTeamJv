/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.bankiz.gui.CreditsOperations;

import edu.bankiz.entities.Credit;
import edu.bankiz.gui.utilisateur.AutentificationController;
import edu.bankiz.services.ServiceCredit;
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
import javafx.scene.text.Text;
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
//

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffichercreditfrontController implements Initializable {

    @FXML
    private TableView<Credit> table_stade;
    @FXML
    private TableColumn<?,?> col_id;
    @FXML
    private TableColumn<Credit,?> col_montcredit;
    @FXML
    private TableColumn<Credit, String> col_datepe;
    @FXML
    private TableColumn<Credit, String> col_datede;
    @FXML
    private TableColumn<Credit, String> col_dureec;
    @FXML
    private TableColumn<Credit, String> col_echeance;
    @FXML
    private TableColumn<Credit, String> col_tauxint;
    @FXML
    private TableColumn<Credit, String> col_decision;
    @FXML
    private TableColumn<Credit, String> col_etatc;
    @FXML
    private TableColumn<Credit, String> col_typec;
        @FXML
    private Button btdemande;
           @FXML
    private Button btsim;
     @FXML
    private Text txtcreance;

    /**
     * Initializes the controller class.
     */
     private Connection cnx = null ;
            private PreparedStatement pst = null;
            


     private ObservableList<Credit> listM;
         private ObservableList<Credit> listS = FXCollections.observableArrayList();
     ArrayList name = new ArrayList();

         private ObservableList<Credit> listT = FXCollections.observableArrayList();

      ObservableList<Credit> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Credit, String> col_numeroC;
    @FXML
    private Button btoperation;
    @FXML
    private Button btnCartesFront;
    @FXML
    private Button btnProfileFront;
    @FXML
    private Button btnTransFront;
    @FXML
    private Button btnReclamationFront;
    @FXML
    private Button btnCreditFront;
    @FXML
    private Button btnAccueilFront;
    @FXML
    private Button btnForumFront;
    @FXML
    private Button btnCompteFront;
    @FXML
    private Button btnLogoutFront;
    @FXML
    private Button btnChequesFront;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = MyConnection.getInstance().getCnx();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM credit");
            while(rs.next()){
                list.add(new Credit(rs.getInt(1),rs.getInt(3),rs.getDate(4),rs.getDate(5),rs.getInt(6),rs.getDate(7),rs.getInt(8),rs.getBoolean(9),rs.getString(10),rs.getString(11),rs.getInt(2)));
                
                table_stade.setItems(list);
                table_stade.refresh();
                
                
            }   } catch (SQLException ex) {    
            Logger.getLogger(AfficherCreditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            AfficherStade();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherCreditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    }

 
     public void AfficherStade() throws SQLException{
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_montcredit.setCellValueFactory(new PropertyValueFactory<>("montCredit"));
          col_datepe.setCellValueFactory(new PropertyValueFactory<>("datepe"));
          col_datede.setCellValueFactory(new PropertyValueFactory<>("datede"));
         col_dureec.setCellValueFactory(new PropertyValueFactory<>("dureeC"));
          col_tauxint.setCellValueFactory(new PropertyValueFactory<>("tauxInteret"));
          col_echeance.setCellValueFactory(new PropertyValueFactory<>("echeance"));
          col_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));
          col_etatc.setCellValueFactory(new PropertyValueFactory<>("etatCredit"));
          col_typec.setCellValueFactory(new PropertyValueFactory<>("typeCredit"));
          col_numeroC.setCellValueFactory(new PropertyValueFactory<>("numero_compte"));
          

        
        //UpdateTable();
        table_stade.setItems(list);
        
    }
          public Credit gettempCredit(TableColumn.CellEditEvent edittedCell) {
        Credit test = table_stade.getSelectionModel().getSelectedItem();
        return test;
    }
       

   
    /*public LocalDate LOCAL (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(dateString,formatter);
    return localDate;
}*/

    
    /*private PDFViewer m_PDFViewer;
    
    public void start(Stage stage) throws Exception
	{
		m_PDFViewer = new PDFViewer();
		BorderPane borderPane = new BorderPane(m_PDFViewer);
		Scene scene = new Scene(borderPane);
		stage.setTitle("JavaFX PDFViewer - Qoppa Software");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}*/

    

     @FXML
    void deman(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bankiz/gui/CreditsOperations/ajoutcreditfront.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
     System.out.println("Welcome");

    }
       @FXML
    void simm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bankiz/gui/CreditsOperations/simulation.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
     System.out.println("Welcome");

    }
       @FXML
    void operationaff(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bankiz/gui/CreditsOperations/ajoutOP.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
     System.out.println("Welcome");

    }
       @FXML
    void calculercreance(ActionEvent event) {
        Credit matiere = table_stade.getSelectionModel().getSelectedItem();
        int i=matiere.getId();
        ServiceCredit sc=new ServiceCredit(); 
        float e =sc.getAllech(i)*100;
        String a="vous avez payé:"+e+"%de votre Credit";
        txtcreance.setText(a);

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
