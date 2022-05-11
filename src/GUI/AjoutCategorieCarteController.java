/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXToggleButton;
import com.mycompany.app_desktop.services.CategorieDAO;

import com.mycompany.app_desktop.utils.ICategorieDAO;

import com.mycompany.app_desktop.entities.CategorieCarte;


import com.mycompany.app_desktop.utils.CategorieListner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import com.mycompany.app_desktop.entities.Participation_categorie;
import com.mycompany.app_desktop.utils.IparticipationcategorieDAO;
import com.mycompany.app_desktop.services.Participation_categorieDAO;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author hamza
 */
public class AjoutCategorieCarteController implements Initializable {

    private List<CategorieCarte> allCategorie=new ArrayList();
    private TextField type;
    private TextField description;
    
    private TextField prix;
    private TextField montant_max;
 
 
    private TextField id;
   
    private AnchorPane rootPane;
    @FXML
    private HBox topHbox;
    @FXML
    private TextField searchBox;
    @FXML
    private JFXToggleButton sortToggle;
    @FXML
    private Button ajoutButton;
    @FXML
    private HBox modifBox;
    private Text titreText;
    @FXML
    private Button modifBtn;
    @FXML
    private ImageView imageView;
    @FXML
    private Text dateText;
    private Text lieuText;
    @FXML
    private Text descriptionText;
    @FXML
    private HBox participerBox;
            CategorieCarte chosenEvent;

    @FXML
    private Button participerBTN;
         CategorieCarte sideBarEvenement=new CategorieCarte();

  
      private CategorieListner listner;
    @FXML
    private Text typeText;
    @FXML
    private Text prixText;
    @FXML
    private Text montantmaxText;
    @FXML
    private ScrollPane scrollCategorieCarte;
    @FXML
    private GridPane gridcategorie;
    @FXML
    private Button btncategorie;
    @FXML
    private AnchorPane haja;
   
  
  
 
     @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        this.allCategorie=fechCategorie();
        try {
             if(allCategorie.size()>0){
            setChosenCategorie(this.allCategorie.get(0));}
        } catch (IOException ex) {
            Logger.getLogger(AjoutCategorieCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
       listner= (CategorieCarte e) -> {
           try {
               setChosenCategorie(e);
           } catch (IOException ex) {
               Logger.getLogger(AjoutCategorieCarteController.class.getName()).log(Level.SEVERE, null, ex);
           }
        };
       this.displayGrid(allCategorie);
            
    }
  
public List<CategorieCarte> fechCategorie(){
    ICategorieDAO edao=new CategorieDAO();
    return edao.fechCategorie();
    
}
private void setChosenCategorie(CategorieCarte categorie) throws IOException {
        chosenEvent=categorie;
        this.participerBTN.setVisible(true);
        modifBtn.setVisible(true);
    
       sideBarEvenement=categorie;
       typeText.setText(categorie.getType());
       descriptionText.setText(categorie.getDescription());
       dateText.setText(categorie.getDate_categorie().toString());
       prixText.setText(categorie.getPrix());
        montantmaxText.setText(categorie.getMontant_max());
       Image eventImage;
            if(categorie.getImage()==null){
                eventImage=new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
            }else {
                eventImage=new Image("/image/"+categorie.getImage());
            }
        imageView.setImage(eventImage);
       } 
 private void displayGrid(List<CategorieCarte> categorie ){
            int column=0;
            int row=1;
        try {
        for(int i=0;i<categorie.size();i++){
            listner.onClickListner(categorie.get(i));
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CategorieCard.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            
            CategorieCardController cardController=fxmlLoader.getController();
            cardController.setData(categorie.get(i),listner);
             
            if (column == 1) {
                    column = 0;
                    row++;
                }
            
            gridcategorie.add(anchorPane,column++, row);
            gridcategorie.setMinWidth(Region.USE_COMPUTED_SIZE);
            gridcategorie.setPrefWidth(Region.USE_COMPUTED_SIZE);
            gridcategorie.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                gridcategorie.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridcategorie.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridcategorie.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
            }
        catch (IOException ex) {
            Logger.getLogger(AjoutCategorieCarteController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        
    }
  


    @FXML
    private void search(KeyEvent event) {
    String word=searchBox.getText();
      List<CategorieCarte> searchList=this.allCategorie.stream()
                .filter((e)->{return e.getType().toUpperCase().startsWith(word.toUpperCase());})
                .collect(Collectors.toList());
      this.gridcategorie.getChildren().clear();
      this.displayGrid(searchList);
    }

    @FXML
    private void handleToggle(ActionEvent event) {
       if(sortToggle.isSelected()){
           List<CategorieCarte> sortedGrid=this.sortByDate();
           this.gridcategorie.getChildren().clear();
           this.displayGrid(sortedGrid);
        }else{
            this.gridcategorie.getChildren().clear();
            this.displayGrid(this.allCategorie);
        }
    }
       private List<CategorieCarte> sortByDate(){
 List<CategorieCarte> sorted=this.allCategorie.stream().
          sorted((e1,e2)->e2.getDate_categorie().compareTo(e1.getDate_categorie()))
         .collect(Collectors.toList());
         return sorted;
    }
       @FXML
private void getCategoriesView(ActionEvent event){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ajoutCategorie.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AjoutCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
}


    @FXML
    private void ajoutCategorieCarte(ActionEvent event) throws IOException {
              AnchorPane panee = FXMLLoader.load(getClass().getResource("ajoutCategorie.fxml"));
            haja.getChildren().setAll(panee);
    }

    @FXML
    private void switchscene(ActionEvent event)  throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierCategorie.fxml"));
            Pane orderView = loader.load();
            ModifierCategorieController contr=loader.getController();
            contr.setData(chosenEvent);
                       haja.getChildren().setAll(orderView);
   
        
    }

    private void Notification(){
        
        String title = "Saveme : merci pour votre aide";
        String message = "un nouveau profil a été ajouté avec succes ";
       
       
            Notification notification = Notifications.SUCCESS;

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotification(notification);
        tray.showAndWait();  
        }
    


 
}
