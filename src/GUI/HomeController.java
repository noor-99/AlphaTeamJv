package GUI;

import GUI.AjoutCarteController;
import javafx.event.ActionEvent;
import javafx.event.Event;

import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
    private Parent fxml;
 @FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private Button pubBack;
    @FXML
    private AnchorPane root;
    @FXML
    private Button ComptesBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private Button btnCreditsBack;
    @FXML
    private AnchorPane contenuBack;
    @FXML
    private Button C;
    @FXML
    private Button btnUtilisateursBack;
    @FXML
    private Button TransactionsBack1;
    @FXML
    private ImageView btnLogoutBack;
    @FXML
    private Button btnCartesBack;
    @FXML
    private Button btnReclamationsBack;
      @FXML
    private AnchorPane rootPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
     @FXML
    private void handleClicks(ActionEvent event) {
    }


    @FXML
    public void openPublicationBack(Event event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/PublicationBack/PublicationBack.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
 private void loadPage (String page) {
            Parent root = null;
        try {
            root = FXMLLoader.load (getClass () .getResource (page+".fxml") ) ;
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bp.setCenter(root);
        
   }

    @FXML
    public void versComptesBack(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Comptes Bancaires");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Comptes/Back/ComptesBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versTransactionsBack(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Transactions Bancaires");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Transactions/Back/TransactionsBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versCreditsBack(ActionEvent actionEvent) {
    }

    @FXML
    public void versCartesBack(ActionEvent actionEvent) {
            try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/AjoutCategorieCarte.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AjoutCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    @FXML
    public void versChequiersBack(ActionEvent actionEvent) {
    }

    @FXML
    public void versUtilisateursBack(ActionEvent actionEvent) {
    }

    @FXML
    public void versChequesBack(ActionEvent actionEvent) {
    }

    @FXML
    public void logoutBack(Event event) {
    }
     @FXML
    private void carte(MouseEvent event) {
        loadPage("AjoutCarte");
    }
      @FXML
    private void categorie(MouseEvent event) {
        loadPage("AjoutCategorieCarte");
    }
}
