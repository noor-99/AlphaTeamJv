/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.Home;




import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button btnUtilisateursBack;
    @FXML
    private Button btnReclamationsBack;
    @FXML
    private Button pubBack;
    @FXML
    private Button ComptesBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private Button TransactionsBack1;
    @FXML
    private Button btnChequiersBack;
    @FXML
    private Button btnCreditsBack;
    @FXML
    private Button btnCartesBack;
    
    @FXML
    private AnchorPane contenuBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void versUtilisateursBack(ActionEvent event) {
    }

    @FXML
    private void openPublicationBack(ActionEvent event) {
    }

    @FXML
    private void versComptesBack(ActionEvent event) {
    }

    @FXML
    private void versTransactionsBack(ActionEvent event) {
    }

    @FXML
    private void versChequesBack(ActionEvent event) {
    }

    @FXML
    private void versChequiersBack(ActionEvent event) {
    }

    @FXML
    private void versCreditsBack(ActionEvent event) {
    }

    @FXML
    private void carte(MouseEvent event) {
    }


    @FXML
    private void logoutBack(MouseEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
}
