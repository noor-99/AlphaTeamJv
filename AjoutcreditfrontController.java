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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import edu.workshopjdbc3a48.entities.Credit;
import edu.workshopjdbc3a48.services.ServiceCredit;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savecredit(ActionEvent event) {
        
        
        
        int montcredit = Integer.parseInt(tfmontcredit.getText());
        
        
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

        
          int dureecredit = Integer.parseInt(tfdureecredit.getText());
        
        
        
        
        
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
        } else if (typecredit==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("typecredit credit doit invalide");
            
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/affichercreditfront.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/affichercreditfront.fxml"));
            Parent root = loader.load();
            tfmontcredit.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    
}
