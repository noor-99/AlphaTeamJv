/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.services.ServiceCredit;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author user
 */
public class SimulationController implements Initializable {

    private Label tfmontCredit;
    @FXML
    private TextField dureec;
    @FXML
    private TextField salaire;
    @FXML
    private ComboBox cbtypeCredit;
    @FXML
    private Text txresutla;
    @FXML
    private Button btsimulation;
    @FXML
    private Label labmmmm;
    @FXML
    private TextField montCredit;
    
        @FXML
    private ImageView kkimage;
            @FXML
    private Button btcreditt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("maison","voiture","etude","voiyage","afaire");
        cbtypeCredit.setItems(list);
    }    

    @FXML
    private void slect(ActionEvent event) {
        String s=cbtypeCredit.getSelectionModel().getSelectedItem().toString();
        
    }
    
    @FXML
    void dddd(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/affichercreditfront.fxml"));
            Parent root = loader.load();
            salaire.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void simuler(ActionEvent event) {
        int mCredit = Integer.parseInt(montCredit.getText());
        int dc = Integer.parseInt(dureec.getText());
        int sal = Integer.parseInt(salaire.getText());//cbtypeCredit
        String typeCredit=cbtypeCredit.getSelectionModel().getSelectedItem().toString();
        
        
        ServiceCredit sc=new ServiceCredit();
        String a=sc.simulation (mCredit,dc,sal,typeCredit);
        txresutla.setText(a);
    }
       @FXML
    void rjoua(MouseEvent event) {
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/affichercreditfront.fxml"));
            Parent root = loader.load();
            kkimage.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
      
    
}
