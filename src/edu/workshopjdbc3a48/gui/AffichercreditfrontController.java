/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.workshopjdbc3a48.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

//copy import
import com.qoppa.pdfViewerFX.PDFViewer;
import java.time.LocalDate;
import com.qoppa.pdfViewerFX.PDFViewer;

import edu.workshopjdbc3a48.entities.Credit;
import edu.workshopjdbc3a48.services.ServiceCredit;
import edu.workshopjdbc3a48.utils.DataSource;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jxl.write.WriteException;
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
    private Button btsuprimer;
    @FXML
    private Button Bupdate;
    @FXML
    private Button imp;
       @FXML
    private Button btoperation;
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = DataSource.getInstance().getCnx();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/ajoutcreditfront.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/simulation.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/ajoutOP.fxml"));
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


}
