/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.gui.CartesCategories;

import edu.bankiz.entities.CategorieCarte;
import edu.bankiz.tools.CategorieListner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class CategorieCardController implements Initializable {

    @FXML
    private Label typeCategorie;
    @FXML
    private ImageView image;
    @FXML
    private Text descriptionText;
    @FXML
    private HBox boxTest;
    @FXML
    private Label dateLabel;
    @FXML
    private Label prix;
    @FXML
    private Label montantmax;
        CategorieListner listner ;
            CategorieCarte categorie;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       void setData(CategorieCarte e,CategorieListner listner) {
        this.listner=listner;
        this.categorie=e;
       typeCategorie.setText(categorie.getType());
       this.descriptionText.setText(categorie.getDescription());
         Image eventImage;
            if(categorie.getImage()==null){
                eventImage=new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
            }else {
                System.out.println(categorie.getImage());
                eventImage=new Image("/Images/"+e.getImage());
            }
       image.setImage(eventImage);
       
       dateLabel.setText(categorie.getDate_categorie().toString());
        if(categorie.getDate_categorie().compareTo(new Date())<0) {
            dateLabel.getStyleClass().add("expired");
        } 
       montantmax.setText(categorie.getMontant_max());
              prix.setText(categorie.getPrix());

       //boxTest.getChildren().remove(0, 2);
       
    }
}
