package edu.bankiz.gui.CommentaireFront;

import edu.bankiz.entities.Commentaire;
import edu.bankiz.entities.Publication;
import edu.bankiz.entities.Session;
import edu.bankiz.entities.Utilisateur;
import edu.bankiz.services.CommentaireController;
import edu.bankiz.services.PublicationController;
import edu.bankiz.services.UtilisateurCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class CommentaireFrontController implements Initializable {
    public ObservableList<Commentaire> listCommentaires = FXCollections.observableArrayList();
    public ObservableList<String> rechercheCommentaire = FXCollections.observableArrayList();
    public ObservableList<String> namesAjout = FXCollections.observableArrayList();
    public ObservableList<String> publicationsAjout = FXCollections.observableArrayList();
    public ObservableList<String> rechercheCommentaireTitre = FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private Label controleDescription;
    @javafx.fxml.FXML
    private TextArea DescriptionCommentaireFront;
    @javafx.fxml.FXML
    private Button btnCreditFront;
    @javafx.fxml.FXML
    private Label controleTypeTransFront;
    @javafx.fxml.FXML
    private Label controleMontantTransFront;
    @javafx.fxml.FXML
    private Button btnCartesFront;
    @javafx.fxml.FXML
    private Label controleRibRecepteurFront1;
    @javafx.fxml.FXML
    private Button btnProfileFront;
    @javafx.fxml.FXML
    private Label controleRibRecepteurFront2;
    @javafx.fxml.FXML
    private Button btnAccueilFront;
    @javafx.fxml.FXML
    private Button btnTransFront;
    @javafx.fxml.FXML
    private Button btnForumFront;
    @javafx.fxml.FXML
    private Label controleNomRecepteurFront;
    @javafx.fxml.FXML
    private ComboBox nomClientFront;
    @javafx.fxml.FXML
    private Button btnCompteFront;
    @javafx.fxml.FXML
    private Button btnLogoutFront;
    @javafx.fxml.FXML
    private Button btnChequesFront;
    @javafx.fxml.FXML
    private Button btnAjouterCommentaire;
    @javafx.fxml.FXML
    private TableView tableauCommentaireFront;
    @javafx.fxml.FXML
    private TableColumn afficherNomCommentaire;
    @javafx.fxml.FXML
    private TableColumn afficherDateCommentaire;
    @javafx.fxml.FXML
    private TableColumn afficherDescriptionCommentaire;
    @javafx.fxml.FXML
    private ComboBox comboBoxTitreFront;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillcombobox();
        afficherCommentaire();
        fillcombobox2();
    }

    private void fillcombobox2() {
            CommentaireController cc = new CommentaireController();
            List<String> noms = cc.afficherTitreCommentaire();
            if (!noms.isEmpty()) {
                for (int i = 0; i < noms.size(); i++) {
                    rechercheCommentaireTitre.add(noms.get(i));
                }
            }
            PublicationController pc = new PublicationController();
            List<String> publications = pc.afficherTitrePublicationFront();
        if (!publications.isEmpty()) {
            for (int i = 0; i < publications.size(); i++) {
                publicationsAjout.add(publications.get(i));
            }
        }

            comboBoxTitreFront.setItems(publicationsAjout);
            comboBoxTitreFront.getSelectionModel().selectFirst();
    }

    private void fillcombobox() {
        CommentaireController c = new CommentaireController();
        List<String> noms = c.afficherNomClientCommentaire();
        if (!noms.isEmpty()) {
            for (int i = 0; i < noms.size(); i++) {
                rechercheCommentaire.add(noms.get(i));
            }
        }
        UtilisateurCRUD userCont = new UtilisateurCRUD();

        List<Utilisateur> names = userCont.afficherProfile();
        if (!names.isEmpty()){
                namesAjout.add(names.get(0).getPrenom_u() + " " + names.get(0).getNom_u());
        }

        nomClientFront.setItems(namesAjout);

        nomClientFront.getSelectionModel().selectFirst();
    }

    @javafx.fxml.FXML
    public void redirectCartesFront(ActionEvent actionEvent) {
        try {
//            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Calendrier");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CartesCategories/Calendar.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectLogoutFront(ActionEvent actionEvent) {
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

    @javafx.fxml.FXML
    public void redirectAccueilFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("E-Bank Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/HomeFront/HomeFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectCreditFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CreditsOperations/affichercreditfront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectProfileFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Mon Profile");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/utilisateur/Profile.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectForumFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Forum Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/PublicationFront/PublicationFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectChequesFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mes cheques & chequiers");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/ChequesChequiers/FrontuserchequeController.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectCompteFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mon Compte Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Comptes/Front/MonCompteBancaire.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void ajouterCommentaireFront(ActionEvent actionEvent) {
        CommentaireController pc = new CommentaireController();
        UtilisateurCRUD user = new UtilisateurCRUD();
        PublicationController p = new PublicationController();
        /* if(validateAllPublications()) { */
            String nom = nomClientFront.getSelectionModel().getSelectedItem().toString();
            String titre = comboBoxTitreFront.getSelectionModel().getSelectedItem().toString();
        int idTitre = 0;
        Publication ppppp = new Publication();

        PublicationController pb = new PublicationController();
            List<Publication> pubs = pb.afficherPublications();
        if (!pubs.isEmpty()) {
            for (int i = 0; i < pubs.size(); i++) {
                if (pubs.get(i).getTitre_publication().toLowerCase().equals(titre.toLowerCase())){
                    idTitre = pubs.get(i).getId();
                    ppppp = pubs.get(i);
                    System.out.println("TIIIIITRE :" + idTitre);
                }
            }
        }

//            int idClient = user.getIdFromFullname(nom).get(0).getId();
        String description = String.valueOf(DescriptionCommentaireFront.getText());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Commentaire c = new Commentaire(idTitre, Session.getId(),description,dtf.format(now));
            pc.ajouterCommentaire(c);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Commentaires");
            alert.setHeaderText(null);
            alert.setContentText("Commentaire ajouté avec succès !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();

            tableauCommentaireFront.getItems().clear();
            afficherCommentaire();
           /* clearFields();
            nomClientBack.setStyle(null);
            titreP.setStyle(null);
            erreurTitreBack.setVisible(false);
            descP.setStyle(null);
            erreurDescriptionBack.setVisible(false); */

    }
    private void afficherCommentaire() {
        CommentaireController cc = new CommentaireController();
        List<Commentaire> commentaires = cc.afficherCommentaires();
        if (!commentaires.isEmpty()) {
            for (int i = 0; i < commentaires.size(); i++) {
                listCommentaires.add(commentaires.get(i));
            }
        }
        afficherNomCommentaire.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("fullname"));
        afficherDescriptionCommentaire.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("description_commentaire"));
        afficherDateCommentaire.setCellValueFactory(new PropertyValueFactory<Commentaire, String >("date_commentaire"));
        tableauCommentaireFront.setItems(listCommentaires);
    }

    @javafx.fxml.FXML
    public void versTransactionsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Effectuer une Transaction Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Transactions/Front/TransactionsFront.fxml"));

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
