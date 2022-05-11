/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AjoutCategorieCarteController;
import com.mycompany.app_desktop.entities.Carte;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import com.mycompany.app_desktop.services.CarteServices;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import com.mycompany.app_desktop.utils.myconnection;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFDocument;



/**
 * FXML Controller class
 *
 * @author hamza
 */
public class AjoutCarteController implements Initializable {

    @FXML
    private TextField idclient;
      @FXML
    private TextField date;
    @FXML
    private TextField mp;
  
    @FXML
    private TextField login;
    @FXML
    private TextField num_carte;
    @FXML
    private TableView<Carte> table_stade;
  
    @FXML
    private TableColumn<Carte, String> col_idclient;
    @FXML
    private TableColumn<Carte, String> col_date;
    @FXML
    private TableColumn<Carte, String> col_mp;
    @FXML
    private TableColumn<Carte, String> col_login;
    @FXML
    private TableColumn<Carte, String> col_num;
     @FXML
    private TextField fid;
        private Connection cnx = null ;
            private PreparedStatement pst = null;
            


     private ObservableList<Carte> listM;
         private ObservableList<Carte> listS = FXCollections.observableArrayList();
     ArrayList name = new ArrayList();

         private ObservableList<Carte> listT = FXCollections.observableArrayList();

      ObservableList<Carte> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TextField id;
    @FXML
    private Button btncategorie;
    @FXML
    private AnchorPane rootPane;
    
    
    public void initialize(URL url, ResourceBundle rb) {
         try {
            Connection cnx = myconnection.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM carte");
            while(rs.next()){
            list.add(new Carte(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            
            table_stade.setItems(list);
            table_stade.refresh();
            
          }
            } catch (SQLException ex) {
            Logger.getLogger(AjoutCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            AfficherStade();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
          Connection cnx = myconnection.getInstance().getCnx();
       
        
    }
    
    
     public void AfficherStade() throws SQLException{
          col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
          col_idclient.setCellValueFactory(new PropertyValueFactory<>("idclient"));
          col_date.setCellValueFactory(new PropertyValueFactory<>("date_ex"));
          col_mp.setCellValueFactory(new PropertyValueFactory<>("mp"));
          col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
          col_num.setCellValueFactory(new PropertyValueFactory<>("num_carte"));

        
        //UpdateTable();
        table_stade.setItems(list);
        
    }

      
 
 
        public void UpdateTable() throws SQLException{
            
        col_idclient.setCellValueFactory(new PropertyValueFactory<>("idclient"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_ex"));
        col_mp.setCellValueFactory(new PropertyValueFactory<>("mp"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_num.setCellValueFactory(new PropertyValueFactory<>("num_carte"));

         listM = myconnection.getDataCarte();
        
        table_stade.setItems(listM);
        }
         public Carte gettempCarte(TableColumn.CellEditEvent edittedCell) {
        Carte test = table_stade.getSelectionModel().getSelectedItem();
        return test;
    }
         
         
           @FXML
    private void getSelected(MouseEvent event) {
        int index = table_stade.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    id.setText(col_id.getCellData(index).toString());

    idclient.setText(col_idclient.getCellData(index));
    date.setText(col_date.getCellData(index));
    mp.setText(col_mp.getCellData(index).toString());
      login.setText(col_login.getCellData(index).toString());
        num_carte.setText(col_num.getCellData(index).toString());
    
    
    }
         @FXML
    private void Edit(ActionEvent event) {
        try {
            UpdateTable();
            cnx = myconnection.ConnectDb();
            String value1 = idclient.getText();
            int value7 = Integer.parseInt(id.getText());
               String value2 = date.getText();
            String value3 = mp.getText();
           String value4 = login.getText();
            String value5 = num_carte.getText();

           
            String sql = "update carte set idclient= '"+value1+"',date_ex= '"+value2+"',mp= '"+
                    value3+"',login= '"+value4+"',num_carte= '"+value5+"' where id='"+value7+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("UPDATED");
                
                alert.showAndWait();
            UpdateTable();
            
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

 
    
     @FXML
    private void AjouterCarte(ActionEvent event) throws SQLException {
           if(controleDeSaisi()){
        CarteServices sp = new CarteServices();
        
        sp.ajouter(new Carte(idclient.getText(),date.getText(),mp.getText(),login.getText(),num_carte.getText()));
        
         col_idclient.setCellValueFactory(new PropertyValueFactory<>("idclient"));
         col_date.setCellValueFactory(new PropertyValueFactory<>("date_ex"));
         col_mp.setCellValueFactory(new PropertyValueFactory<>("mp"));
         col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
         col_num.setCellValueFactory(new PropertyValueFactory<>("num_carte"));
         
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Carte Ajouté");
                
                alert.showAndWait();
        UpdateTable();
        //table_stade.refresh();
}
    }
    
    
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         TableColumn.CellEditEvent edittedcell = null;
        Carte x = gettempCarte(edittedcell);
       // UpdateTable();

        if (x != null) {

            int i = x.getId();
            CarteServices cat = new CarteServices();

            int s = cat.supprimer(i);
            //UpdateTable();
           
            
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("carte deleted");
                
                alert.showAndWait();
                list.clear();
        AfficherStade();
                table_stade.setItems(list);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
        

    }
 
            private boolean controleDeSaisi() {  

        if (idclient.getText().isEmpty() || mp.getText().isEmpty() || date.getText().isEmpty() || login.getText().isEmpty()
                 || num_carte.getText().isEmpty()
                ) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[A-z]*", login.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le login ! ");
                login.requestFocus();
                login.selectEnd();
                return false;
            }}
              return true;}
     
   public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
@FXML
private void getCategoriesView(ActionEvent event){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/AjoutCategorieCarte.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AjoutCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
  
   
}
