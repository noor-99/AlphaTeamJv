/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.gui.CartesCategories;

import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import com.jfoenix.controls.JFXToggleButton;
import edu.bankiz.entities.CategorieCarte;
import edu.bankiz.gui.utilisateur.Utilisateur;
import edu.bankiz.services.CategorieDAO;
import edu.bankiz.tools.CategorieListner;
import edu.bankiz.tools.ICategorieDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class AjoutCategorieCarteController implements Initializable {

    private List<CategorieCarte> allCategorie = new ArrayList();
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

    private Button btnCommentaireBack;
    @FXML
    private Button TransactionsBack1;
    @FXML
    private Button btnCreditsBack;
    @FXML
    private AnchorPane contenuBack;
    @FXML
    private Button ComptesBack;
    @FXML
    private Button pubBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private Button btnCartesBack;
    @FXML
    private Button btnReclamationsBack;
    @FXML
    private Button btnChequiersBack;
    @FXML
    private Button btnUtilisateursBack;
    @FXML
    private Button btnLogoutBack;

    @FXML
    private Button participerBTN;
    CategorieCarte sideBarEvenement = new CategorieCarte();


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

        this.allCategorie = fechCategorie();
        try {
            if (allCategorie.size() > 0) {
                setChosenCategorie(this.allCategorie.get(0));
            }
        } catch (IOException ex) {
            Logger.getLogger(AjoutCategorieCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listner = (CategorieCarte e) -> {
            try {
                setChosenCategorie(e);
            } catch (IOException ex) {
                Logger.getLogger(AjoutCategorieCarteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        this.displayGrid(allCategorie);

    }

    public List<CategorieCarte> fechCategorie() {
        ICategorieDAO edao = new CategorieDAO();
        return edao.fechCategorie();

    }

    private void setChosenCategorie(CategorieCarte categorie) throws IOException {
        chosenEvent = categorie;
        this.participerBTN.setVisible(true);
        modifBtn.setVisible(true);

        sideBarEvenement = categorie;
        typeText.setText(categorie.getType());
        descriptionText.setText(categorie.getDescription());
        dateText.setText(categorie.getDate_categorie().toString());
        prixText.setText(categorie.getPrix());
        montantmaxText.setText(categorie.getMontant_max());
        Image eventImage;
        if (categorie.getImage() == null) {
            eventImage = new Image("/edu/esprit/util/assets/img/pasdimage.jpg");
        } else {
            eventImage = new Image("/Images/" + categorie.getImage());
        }
        imageView.setImage(eventImage);
    }

    private void displayGrid(List<CategorieCarte> categorie) {
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < categorie.size(); i++) {
                listner.onClickListner(categorie.get(i));
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CategorieCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CategorieCardController cardController = fxmlLoader.getController();
                cardController.setData(categorie.get(i), listner);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                gridcategorie.add(anchorPane, column++, row);
                gridcategorie.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridcategorie.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridcategorie.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                gridcategorie.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridcategorie.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridcategorie.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(AjoutCategorieCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }


    @FXML
    private void search(KeyEvent event) {
        String word = searchBox.getText();
        List<CategorieCarte> searchList = this.allCategorie.stream()
                .filter((e) -> {
                    return e.getType().toUpperCase().startsWith(word.toUpperCase());
                })
                .collect(Collectors.toList());
        this.gridcategorie.getChildren().clear();
        this.displayGrid(searchList);
    }

    @FXML
    private void handleToggle(ActionEvent event) {
        if (sortToggle.isSelected()) {
            List<CategorieCarte> sortedGrid = this.sortByDate();
            this.gridcategorie.getChildren().clear();
            this.displayGrid(sortedGrid);
        } else {
            this.gridcategorie.getChildren().clear();
            this.displayGrid(this.allCategorie);
        }
    }

    private List<CategorieCarte> sortByDate() {
        List<CategorieCarte> sorted = this.allCategorie.stream().
                sorted((e1, e2) -> e2.getDate_categorie().compareTo(e1.getDate_categorie()))
                .collect(Collectors.toList());
        return sorted;
    }

    @FXML
    private void getCategoriesView(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Categories");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CartesCategories/AjoutCarte.fxml"));
//            FXMLLoader fxmlLoader = new FXMLLoader(CategorieCarte.class.getResource("AjoutCarte.fxml"));

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
    private void ajoutCategorieCarte(ActionEvent event) throws IOException {
        AnchorPane panee = FXMLLoader.load(getClass().getResource("ajoutCategorie.fxml"));
        haja.getChildren().setAll(panee);
    }

    @FXML
    private void switchscene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierCategorie.fxml"));
        Pane orderView = loader.load();
        ModifierCategorieController contr = loader.getController();
        contr.setData(chosenEvent);
        haja.getChildren().setAll(orderView);


    }

    private void Notification() {

        String title = "Saveme : merci pour votre aide";
        String message = "un nouveau profil a été ajouté avec succes ";


        Notification notification = Notifications.SUCCESS;

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotification(notification);
        tray.showAndWait();
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
    public void logoutBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

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
    public void openCommentaireBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Commentaires Forum Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CommentaireBack/CommentaireBack.fxml"));

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
    public void openReclamationBack(ActionEvent actionEvent) {
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
    public void versAccueilBack(ActionEvent actionEvent) {
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
