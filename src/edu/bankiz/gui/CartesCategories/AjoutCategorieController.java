/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.gui.CartesCategories;

import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.jfoenix.controls.JFXTimePicker;
import edu.bankiz.entities.CategorieCarte;
import edu.bankiz.services.CategorieDAO;
import edu.bankiz.tools.ICategorieDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private DatePicker dateInput;
    @FXML
    private TextField typeInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private Button saveInput;
    @FXML
    private TextField montantmax;
    @FXML
    private TextField imagePath;
    @FXML
    private JFXTimePicker timepicker;
    @FXML
    private TextField prixInput;
     String  imageName;
 Alert alert;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button btnretour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        dateInput.setDayCellFactory((DatePicker d)-> 
        new DateCell(){
            @Override 
            public void updateItem(LocalDate item,boolean empty){
                super.updateItem(item, empty);
                setDisable(item.isBefore(LocalDate.now()));
                
            }
        })
;
    }    

    @FXML
    private void saveData(ActionEvent event) {
      if (typeInput.getText() == null || typeInput.getText().trim().isEmpty())
            {
                errorAlert("Le champ titre est vide!");
                return;
            }
        if (dateInput.getValue() == null )
            {
                errorAlert("Le champ date est vide!");
                return;
            }
        
        if (montantmax.getText() == null || typeInput.getText().trim().isEmpty())
            {
                montantmax.setText("");
            }
        if (descriptionInput.getText() == null || descriptionInput.getText().trim().isEmpty())
            {
               errorAlert("Le champ description est vide!");
               return;
            }
        if(this.timepicker.getValue()==null){
            errorAlert("L'heure est obligatoire");
               return;
        }
        LocalTime value = timepicker.getValue();    
     
        Timestamp date = this.prepareSelectedDate();
        CategorieCarte e = new CategorieCarte(this.typeInput.getText(),this.descriptionInput.getText(),this.prixInput.getText(),this.montantmax.getText(),date,this.imageName);
        ICategorieDAO edao=new CategorieDAO();
        edao.insertCategorie(e);
        succesAlert();
        clearForm();
      
    }
     @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)anchor.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) { 
             imagePath.setText(file.toString());
             File directory = new File("/src/Images");
             String destination = directory.toString();
                if(!directory.exists())
                {
                    directory.mkdir();
                }
                File sourceFile=null;
                File destinationFile = null;
                imageName = file.toString().substring(file.toString().lastIndexOf('\\')+1);
                sourceFile = new File(file.toString());
                destinationFile = new File( "src/Images/"+imageName);
                if(!destinationFile.exists())
                {
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
                }
            
         }
    }

      private Timestamp prepareSelectedDate(){
        LocalDate value = this.dateInput.getValue();
        LocalTime value1 = this.timepicker.getValue();
        LocalDateTime value2= LocalDateTime.of(value,value1);
        Timestamp selectedDate=Timestamp.valueOf(value2);
        System.out.println(selectedDate);
        return selectedDate;
        
        
        
        
    }
    private void clearForm() {
        this.typeInput.clear();
        this.montantmax.clear();
        this.descriptionInput.clear();
        this.imageName="";
        dateInput.setValue(null);
      
        this.imagePath.clear();
    }
    private void succesAlert(){
        if(alert==null){
         alert=new Alert(Alert.AlertType.INFORMATION);
        }
      alert.setAlertType(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setContentText("categorie ajouté avec succes");
      Notification();
      alert.show(); 
    }
     private void errorAlert(String content){
         if(alert==null){
            alert=new Alert(Alert.AlertType.ERROR);
        }
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(content);
        alert.show();
    }
    private void Notification(){
        
        String title = "Saveme : merci pour votre aide";
        String message = "un nouveau categorie a été ajouté avec succes ";
       
       
            Notification notification = Notifications.SUCCESS;

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotification(notification);
        tray.showAndWait();  
        }
    @FXML
private void getCategoriesView(ActionEvent event){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CartesCategories/AjoutCategorieCarte.fxml"));
            anchor.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AjoutCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
}
