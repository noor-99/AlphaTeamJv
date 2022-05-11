/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.mycompany.app_desktop.utils.ICategorieDAO;
import com.mycompany.app_desktop.services.CategorieDAO;
import com.mycompany.app_desktop.entities.CategorieCarte;

import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author hamza
 */
public class ModifierCategorieController implements Initializable {

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
    String imageName;
     Alert alert;
    @FXML
    private TextField prixInput;
     CategorieCarte categorie;
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
        });
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
        LocalTime value = timepicker.getValue();    
        Timestamp date = this.prepareSelectedDate();
        categorie.setType(this.typeInput.getText());
        categorie.setPrix(this.prixInput.getText());
        categorie.setMontant_max(this.montantmax.getText());
        categorie.setDescription(this.descriptionInput.getText());
        categorie.setDate_categorie(date);
      
        categorie.setImage(imageName);
        ICategorieDAO edao=new CategorieDAO();
        edao.updateCategorie(categorie);
        succesAlert();
       
      
    }

    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException, IOException  {
     FileChooser chooser =  new FileChooser();
        Stage stage = (Stage)anchor.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if (file != null) { 
             imagePath.setText(file.toString());
             File directory = new File("/src/image");
             String destination = directory.toString();
                if(!directory.exists())
                {
                    directory.mkdir();
                }
                File sourceFile=null;
                File destinationFile = null;
                imageName = file.toString().substring(file.toString().lastIndexOf('\\')+1);
                sourceFile = new File(file.toString());
                destinationFile = new File( "src/image/"+imageName);
                if(!destinationFile.exists())
                {
                Files.copy(sourceFile.toPath(), destinationFile.toPath());
                }
            
         }
    }
         public void setData(CategorieCarte e){
        this.categorie=e;
        this.typeInput.setText(categorie.getType());
         this.prixInput.setText(categorie.getPrix());
        this.descriptionInput.setText(categorie.getDescription());
        this.montantmax.setText(categorie.getMontant_max());
        this.dateInput.setValue(categorie.getDate_categorie().toLocalDateTime().toLocalDate());
        this.timepicker.setValue(categorie.getDate_categorie().toLocalDateTime().toLocalTime());
        this.imagePath.setText(categorie.getImage());
      
        
         
    }
      private void succesAlert(){
        if(alert==null){
         alert=new Alert(Alert.AlertType.INFORMATION);
        }
      alert.setAlertType(Alert.AlertType.INFORMATION);
      alert.setTitle("Success");
      alert.setContentText("categorie ajouté avec succes");
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
       private Timestamp prepareSelectedDate(){
        LocalDate value = this.dateInput.getValue();
        LocalTime value1 = this.timepicker.getValue();
        LocalDateTime value2= LocalDateTime.of(value,value1);
        Timestamp selectedDate=Timestamp.valueOf(value2);
        System.out.println(selectedDate);
        return selectedDate;
        
        
    }
    @FXML
       private void getCategoriesView(ActionEvent event){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/AjoutCategorieCarte.fxml"));
            anchor.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AjoutCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
