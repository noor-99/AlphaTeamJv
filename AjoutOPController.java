/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.entities.OperationCredit;
import edu.workshopjdbc3a48.services.ServiceOperationCredit;
import edu.workshopjdbc3a48.utils.DataSource;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
    public void initialize(URL url, ResourceBundle rb) 
        {
        
        try {
            
            Connection con = DataSource.getInstance().getCnx();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/ajoutoperationfront.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/affichercreditfront.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
     System.out.println("Welcome");
        
    }

}
