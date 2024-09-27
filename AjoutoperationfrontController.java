/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.workshopjdbc3a48.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import edu.workshopjdbc3a48.entities.OperationCredit;
//import static edu.workshopjdbc3a48.gui.AjoutOPController.message1;
import edu.workshopjdbc3a48.services.ServiceOperationCredit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * FXML Controller class
 *
 * @author user
 */
public class AjoutoperationfrontController implements Initializable {

    @FXML
    private ComboBox<String> listetypeoperation;
    @FXML
    private TextField tfmontpayer;
    @FXML
    private TextField tfcreditid;
    @FXML
    private DatePicker dpecheance;
    @FXML
    private Button btvaliderop;
    @FXML
    private ImageView imgcredit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("paiement","rachat","remboursement");
        listetypeoperation.setItems(list);
        // TODO
    }    

    @FXML
    private void slecte(ActionEvent event) {
        String typeoperation=listetypeoperation.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void saveop(ActionEvent event) {
        String typeoperation=listetypeoperation.getSelectionModel().getSelectedItem().toString();
        java.sql.Date sqldateoperation= new java.sql.Date(Calendar.getInstance().getTime().getTime());
        int montpayer = Integer.parseInt(tfmontpayer.getText());
        java.sql.Date sqlecheance= java.sql.Date.valueOf(dpecheance.getValue());
        
        int solvabilite = 1;
        int creditid = Integer.parseInt(tfcreditid.getText());
        int tauxinteret = 20;
        
        
        ServiceOperationCredit c=new ServiceOperationCredit();
 OperationCredit cp =new OperationCredit(sqldateoperation,montpayer,sqlecheance,tauxinteret,solvabilite,typeoperation,creditid);
     c.ajouter2(cp);
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/ajoutOP.fxml"));
            Parent root = loader.load();
            btvaliderop.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
     
     System.out.println("Welcome");
        
        
    }
    
}
