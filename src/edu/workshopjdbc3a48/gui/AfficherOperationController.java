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
public class AfficherOperationController implements Initializable {

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
    private Button btsuprimer;
    @FXML
    private Button Bupdate;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
    private void suprimerop(ActionEvent event) {
         TableColumn.CellEditEvent edittedcell = null;
        OperationCredit x = gettempOperationCredit(edittedcell);
        int i = x.getId();
        ServiceOperationCredit cat = new ServiceOperationCredit();
          cat.supprimer(i);
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/afficherOperation.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void updateoperation(ActionEvent event) {
          OperationCredit matiere = table_stade.getSelectionModel().getSelectedItem();
        if (matiere != null) {
            // Create the custom dialog
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Mise à jour Box");
            dialog.setHeaderText("Mise à jour de la Operation " + matiere.getId());

            // Set the icon///////////////////////arj3ileha
           // File fileRefresh = new File("images/refresh_green.png");
            ////Image refresh = new Image(fileRefresh.toURI().toString());
            //dialog.setGraphic(new ImageView(refresh));
            // Set the button types
            ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

            // Create the nom and niveau labels and fields
            //grid setting
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
             grid.setHgap(10);
            grid.setVgap(10);
            //grid.setPadding(new Insets(ajout, 150, 10, 10));
            grid.setVgap(10);
            grid.setVgap(10);
            //nom tf init
            TextField tf = new TextField();
            tf.setPromptText("id");
            tf.setText(valueOf(matiere.getId()));
            ///montPayer
            TextField tf1 = new TextField();
            tf1.setPromptText("montPayer");
            tf1.setText(valueOf(matiere.getMontPayer()));
            
            ///
            
              TextField tf2 = new TextField();
            tf2.setPromptText("tauxInteret");
            tf2.setText(valueOf(matiere.getTauxInteret()));
            
            //solvabilite
            TextField tf3 = new TextField();
            tf3.setPromptText("solvabilite");
            tf3.setText(valueOf(matiere.getSolvabilite()));
            
                //typeOperation
            TextField tf4 = new TextField();
            tf4.setPromptText("typeOperation");
            tf4.setText(valueOf(matiere.getTypeOperation()));
            
            
            
                                                                  //int dureecredit = Integer.parseInt(tfdureecredit.getText());
            //niveau combobox init
          /*  ComboBox<String> cb = new ComboBox<>();
            cb.setItems(niveauxList());
            cb.setValue(matiere.getNiveau());*/

            //add tf and cb to the grid +lables
            grid.add(new Label("id de l operatioon:"), 0, 0);
            grid.add(tf, 1, 0);
            grid.add(new Label("montpayer:"), 0, 1);
            grid.add(tf1, 1, 1);
            ////////////
            grid.add(new Label("tauxInteret:"), 0, 2);
            grid.add(tf2, 1, 2);
            grid.add(new Label("solvabilite:"), 0, 3);
            grid.add(tf3, 1, 3);
            grid.add(new Label("typeOperation:"), 0, 4);
            grid.add(tf4, 1, 4);
            dialog.getDialogPane().setContent(grid);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == saveButtonType) {
                //controle de saisie
                
                        int id = Integer.parseInt(tf.getText());
                        int montcredit = Integer.parseInt(tf1.getText());
                        int tauxInteret = Integer.parseInt(tf2.getText());
                        int solvabilite = Integer.parseInt(tf3.getText());
                        
                        OperationCredit newMat = new OperationCredit(id,matiere.getDateOp(),montcredit,matiere.getEcheance(),tauxInteret,solvabilite,tf4.getText());
                        ServiceOperationCredit ms = new ServiceOperationCredit ();
                        ms.modifier(newMat);
                         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/afficherOperation.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                   
            }
        } else { //si aucune matière sélectionnée à partir du tableau
           Alert alert1 = new Alert(Alert.AlertType.INFORMATION);//before(date2)
    alert1.setTitle("Aucune Operation n'a été sélectionnée");
    alert1.setHeaderText("Veuillez sélectionner une ligne depuis la table des matières");
    alert1.setContentText("Veuillez sélectionner une ligne depuis la table des matières");
    alert1.showAndWait();
        }
    }
       @FXML
    void ajouteropp(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/operationa.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
     System.out.println("Welcome");
        
    }

    }
       
    

       
          
