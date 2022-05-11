package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeFrontController {
    @javafx.fxml.FXML
    private Button btnCartesFront;
    @javafx.fxml.FXML
    private Button btnCreditFront;
    @javafx.fxml.FXML
    private Button btnProfileFront;
    @javafx.fxml.FXML
    private Button btnAccueilFront;
    @javafx.fxml.FXML
    private Button btnForumFront;
    @javafx.fxml.FXML
    private Button btnCompteFront;
    @javafx.fxml.FXML
    private Button btnLogoutFront;
    @javafx.fxml.FXML
    private Button btnChequesFront;
    @javafx.fxml.FXML
    private Button btnTransFront;

    @javafx.fxml.FXML
    public void redirectCartesFront(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void redirectLogoutFront(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void redirectCreditFront(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void redirectProfileFront(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void redirectForumFront(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void redirectChequesFront(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void redirectCompteFront(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Mon Compte Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Comptes/Front/MonCompteBancaire.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectTransactionsFront(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Effectuer une Transaction Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Transactions/Front/TransactionsFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
