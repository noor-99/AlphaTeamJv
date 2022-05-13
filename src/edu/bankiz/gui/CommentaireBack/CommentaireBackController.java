package edu.bankiz.gui.CommentaireBack;

import edu.bankiz.entities.Commentaire;
import edu.bankiz.services.CommentaireController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CommentaireBackController implements Initializable {
    @FXML
    private Button btnStat;
    @FXML
    private Button btnSupprimerCommentaire;
    @FXML
    private Button btnAccueilBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficherCommentaire();

    }
    public ObservableList<Commentaire> listCommentaires = FXCollections.observableArrayList();
    CommentaireController us =new CommentaireController();
    ObservableList<String> ss=FXCollections.observableArrayList();
    public ObservableList<String> rechercheCommentaire = FXCollections.observableArrayList();
    public ObservableList<String> rechercheCommentaireTitre = FXCollections.observableArrayList();

    @FXML
    private Button ComptesBack;
    @FXML
    private Button pubBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private Button TransactionsBack1;
    @FXML
    private Button btnCartesBack;
    @FXML
    private TableColumn afficherNomCommentaire;
    @FXML
    private Label erreurTitreBack;
    @FXML
    private Button btnReclamationsBack;
    @FXML
    private TableColumn afficherDateCommentaire;
    @FXML
    private Button btnCreditsBack;
    @FXML
    private AnchorPane contenuBack;
    @FXML
    private Button btnChequiersBack;
    @FXML
    private TableColumn afficherDescriptionCommentaire;
    @FXML
    private TableColumn afficherTitre;
    @FXML
    private Button btnUtilisateursBack;
    @FXML
    private Button btnLogoutBack;
    @FXML
    private AnchorPane root;
    @FXML
    private Label erreurDescriptionBack;
    @FXML
    private TableView tableauCommentaire;

    @FXML
    public void versCreditsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CreditsOperations/afficherCredit.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versCartesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les cartes ");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CartesCategories/AjoutCarte.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void openPublicationBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Publications");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/PublicationBack/PublicationBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versChequiersBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chequiers ");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/ChequesChequiers/Home.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versComptesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Comptes Bancaires");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Comptes/Back/ComptesBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
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
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions Bancaires");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Transactions/Back/TransactionsBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versUtilisateursBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Liste Utilisateurs");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/utilisateur/UtilisateurBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versChequesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chèques ");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/ChequesChequiers/Baccheque.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void logoutBack(Event event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/utilisateur/Autentification.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void colonnesToTextfield(Event event) {
    }
    private void afficherCommentaire() {
        CommentaireController cc = new CommentaireController();
        List<Commentaire> commentaires = cc.afficherCommentaires();
        if (!commentaires.isEmpty()) {
            for (int i = 0; i < commentaires.size(); i++) {
                listCommentaires.add(commentaires.get(i));
            }
        }
        afficherTitre.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("titre_publication"));
        afficherNomCommentaire.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("fullname"));
        afficherDescriptionCommentaire.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("description_commentaire"));
        afficherDateCommentaire.setCellValueFactory(new PropertyValueFactory<Commentaire, String >("date_commentaire"));
        tableauCommentaire.setItems(listCommentaires);
    }

    @FXML
    public void versStat(MouseEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Statistiques");
            Parent root = FXMLLoader.load(getClass().getResource("Statistiques.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void supprimerCommentaire(ActionEvent actionEvent) {
        CommentaireController pc = new CommentaireController();

        TablePosition pos = (TablePosition) tableauCommentaire.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        String selected = tableauCommentaire.getItems().get(index).toString();
        selected = selected.substring(15, selected.indexOf(","));
        System.out.println(selected);
        Commentaire c = pc.rechercherCommentaire(Integer.parseInt(selected));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Commentaire");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous supprimer le commentaire de " + c.getFullname() + " ?" );
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("Images/logo-Final.png"));
        ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        String finalSelected = selected;
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                pc.supprimerCommentaire(Integer.parseInt(finalSelected));

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Commentaire");
                alert2.setHeaderText(null);
                alert2.setContentText("Commentaire supprimé avec succès !");
                Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                alert2.showAndWait();

                tableauCommentaire.getItems().removeAll(tableauCommentaire.getSelectionModel().getSelectedItem());
            }
        });
    }

    @FXML
    public void versReclamationBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Liste Reclamations");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Reclamations/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void verAcceuilBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Dashboard Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/Home/Home.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
