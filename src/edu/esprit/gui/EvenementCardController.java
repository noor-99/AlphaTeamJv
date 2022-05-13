/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;
import edu.bankiz.tools.CarteListner;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class EvenementCardController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private HBox boxTest;
    @FXML
    private Label dateLabel;
    @FXML
    private Label numcarte;
    @FXML
    private Text loginText;
    @FXML
    private Label idclient;
     CarteListner listner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void setSideBar(MouseEvent event) {
    }
    
}
