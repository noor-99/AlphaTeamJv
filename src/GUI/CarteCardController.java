/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.mycompany.app_desktop.entities.Carte;
import com.mycompany.app_desktop.utils.CarteListner;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class CarteCardController implements Initializable {

    @FXML
    private Label numcarte;
    @FXML
    private ImageView image;
    @FXML
    private Text loginText;
    @FXML
    private HBox boxTest;
    @FXML
    private Label dateLabel;
    @FXML
    private Label idclient;
     CarteListner listner ;
  Carte carte;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
     void setData(Carte e,CarteListner listner) {
        this.listner=listner;
        this.carte=e;
       numcarte.setText(carte.getNum_carte());
       this.loginText.setText(carte.getLogin());
         
       
       dateLabel.setText(carte.getDate_ex().toString());
        if(carte.getDate_ex().compareTo(new Date())<0) {
            dateLabel.getStyleClass().add("expired");
        } 
       idclient.setText(carte.getIdclient());
       //boxTest.getChildren().remove(0, 2);
       
    }
    private void setSideBar(MouseEvent event) {
            if(listner!=null){
        listner.onClickListner(carte);
            }
}
}