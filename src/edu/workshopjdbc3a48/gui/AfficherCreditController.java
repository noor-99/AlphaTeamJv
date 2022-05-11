/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.workshopjdbc3a48.gui;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jxl.write.WriteException;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherCreditController implements Initializable {

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
    private ImageView image1;
    
    @FXML
    private Label label;
     @FXML
    private Button btoperation;
  

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
       

    @FXML
    private void suprimerC(ActionEvent event) {
        TableColumn.CellEditEvent edittedcell = null;
        Credit x = gettempCredit(edittedcell);
        int i = x.getId();
        ServiceCredit cat = new ServiceCredit();
          cat.supprimer(i);
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/afficherCredit.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
          
          
          /////
          
          
            //UpdateTable();UpdateTable();
           
           
    }
    /*public LocalDate LOCAL (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(dateString,formatter);
    return localDate;
}*/

    @FXML
    private void updatecredit(ActionEvent event) {
         Credit matiere = table_stade.getSelectionModel().getSelectedItem();
        if (matiere != null) {
            // Create the custom dialog
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Mise à jour Box");
            dialog.setHeaderText("Mise à jour de la Credit " + matiere.getId());

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
            TextField tf = new TextField();     //DatePicker
            tf.setPromptText("id");
            tf.setText(valueOf(matiere.getId()));
            ///numero_compte
            TextField tf1 = new TextField();
            tf1.setPromptText("numero_compte");
            tf1.setText(valueOf(matiere.getNumero_compte()));
            
            ///montCredit
            
              TextField tf2 = new TextField();
            tf2.setPromptText("montCredit");
            tf2.setText(valueOf(matiere.getMontCredit()));
            
            //datepr
            Date date = (Date) matiere.getDatepe();  
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
    String strDate = formatter.format(date);
    //
            DatePicker tf3 = new DatePicker();
            tf3.setPromptText("datepe");
             /*DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
                String strDat = dateFormat.format(matiere.getDatepe()); 
                
    tf3.setValue(LOCAL(strDat));*/

            
            
            //tf3.setValue(LocalDate.(matiere.getDatepe()));
            
           // tf3.setText(strDate);
            ///
             DatePicker tf4 = new DatePicker();
            tf4.setPromptText("datede");
             /*String strDat1 = dateFormat.format(matiere.getDatede()); 
                try {
    tf3.setValue(LOCAL(strDat1));
} catch (NullPointerException e) {}*/
            
            //tf4.setValue(new PropertyValueFactory<>("dateOp"));//datePicker.setValue(t
            
                //dureeC
            TextField tf5 = new TextField();
            tf5.setPromptText("dureeC");
            tf5.setText(valueOf(matiere.getDureeC()));
            ///////echeance
             DatePicker tf6 = new DatePicker();
            tf6.setPromptText("echeance");
             /*String strDat2 = dateFormat.format(matiere.getEcheance()); 
                try {
    tf6.setValue(LOCAL(strDat2));
} catch (NullPointerException e) {}*/
            //tf6.setText(valueOf(matiere.getEcheance()));
            
            /////tauxInteret
             TextField tf7 = new TextField();
            tf7.setPromptText("tauxInteret");
            tf7.setText(valueOf(matiere.getDureeC()));
            ///////decision
             CheckBox tf8 = new CheckBox();
           // tf8.setPromptText("decision");
            tf8.setText(valueOf(matiere.isDecision()));
            //////etatCredit
            TextField tf9 = new TextField();
            tf9.setPromptText("etatCredit");
            tf9.setText(valueOf(matiere.getEtatCredit()));
            ///////typeCredit
            TextField tf10 = new TextField();
            tf10.setPromptText("typeCredit");
            tf10.setText(valueOf(matiere.getTypeCredit()));
            
            
            
            
            
            
                                                                  //int dureecredit = Integer.parseInt(tfdureecredit.getText());
            //niveau combobox init
          /*  ComboBox<String> cb = new ComboBox<>();
            cb.setItems(niveauxList());
            cb.setValue(matiere.getNiveau());*/

            //add tf and cb to the grid +lables
            grid.add(new Label("id de la credit:"), 0, 0);
            grid.add(tf, 1, 0);
            grid.add(new Label("numero_compte:"), 0, 1);
            grid.add(tf1, 1, 1);
            ////////////
            grid.add(new Label("montCredit:"), 0, 2);
            grid.add(tf2, 1, 2);
            grid.add(new Label("datepe:"), 0, 3);
            grid.add(tf3, 1, 3);
            grid.add(new Label("datede:"), 0, 4);
            grid.add(tf4, 1, 4);
            ////
            grid.add(new Label("dureeC:"), 0, 5);
            grid.add(tf5, 1, 5);
            ///
            grid.add(new Label("echeance:"), 0, 6);
            grid.add(tf6, 1, 6);
            ///
            grid.add(new Label("tauxInteret:"), 0, 7);
            grid.add(tf7, 1, 7);
            ///
            grid.add(new Label("decision:"), 0, 8);
            grid.add(tf8, 1, 8);
            ///
            grid.add(new Label("etatCredit:"), 0, 9);
            grid.add(tf9, 1, 9);
            //
            grid.add(new Label("typeCredit:"), 0, 9);
            grid.add(tf10, 1, 9);
            //
            dialog.getDialogPane().setContent(grid);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == saveButtonType) {
                        int id = Integer.parseInt(tf.getText());
                        int numero_compte = Integer.parseInt(tf1.getText());
                        int montCredit = Integer.parseInt(tf2.getText());//
                        java.sql.Date sqldatepe= java.sql.Date.valueOf(tf3.getValue());
                        java.sql.Date sqldatede= java.sql.Date.valueOf(tf4.getValue());
                        int dureeC = Integer.parseInt(tf5.getText());
                        java.sql.Date sqlecheance= java.sql.Date.valueOf(tf6.getValue());
                        int tauxInteret= Integer.parseInt(tf7.getText());
                        //
                           boolean decision =(boolean) tf8.isSelected();
                           if(decision){
   tf8.setSelected(false);
} else {
   tf8.setSelected(true);
}
//etatCredit
String etatCredit = tf9.getText();
String typeCredit = tf10.getText();
                        
                        
                        
                        
                        
                        Credit newMat = new Credit(id,montCredit,sqldatepe,sqldatede,dureeC,sqlecheance,tauxInteret,(boolean)decision,etatCredit,typeCredit,numero_compte);
                        ServiceCredit ms = new ServiceCredit ();
                        ms.modifier(newMat);
                         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/afficherCredit.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            }
            else { //si aucune matière sélectionnée à partir du tableau
           Alert alert1 = new Alert(Alert.AlertType.INFORMATION);//before(date2)
    alert1.setTitle("Aucune Operation n'a été sélectionnée");
    alert1.setHeaderText("Veuillez sélectionner une ligne depuis la table des matières");
    alert1.setContentText("Veuillez sélectionner une ligne depuis la table des matières");
    alert1.showAndWait();
        }
            
                //controle de saisie
                
                       /* int id = Integer.parseInt(tf.getText());
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
    }

}

    


}}*/
        }}
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
    void demander(ActionEvent event) {
                    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/inscription.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void exportexcel(ActionEvent event) throws IOException, WriteException {
     
        excel test = new excel();
        test.setOutputFile("src\\les y.xls");
        test.write();
        System.out
                .println("Please check the result file under src\\les y.xls ");
        label.setText(" fichier télécharger ");
        
    }
       @FXML
    void si(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/state.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
      @FXML
    void goperation(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/afficherOperation.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
      @FXML
    void mari(MouseEvent event) {
              try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/afficherOperation.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
 
    
     
    


}