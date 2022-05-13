package edu.bankiz.gui.PublicationBack;


import edu.bankiz.entities.Publication;
import edu.bankiz.services.PublicationController;
import edu.bankiz.services.UtilisateurCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class PublicationBackController implements Initializable {
    public ObservableList<String> recherchePublication = FXCollections.observableArrayList();
    public ObservableList<Publication> listPublication = FXCollections.observableArrayList();
    public ObservableList<String> namesAjout = FXCollections.observableArrayList();

    @FXML private ComboBox nomClientBack;
    @FXML private TextField titreP;
    @FXML private ImageView imageP;
    @FXML private Button btnCreditsBack;
    @FXML private AnchorPane contenuBack;
    @FXML private AnchorPane root;
    @FXML private Button ComptesBack;
    @FXML private Button pubBack;
    @FXML private Button TransactionsBack;
    @FXML private TableView tableauPublications;
    @FXML private Label labelSeuilCompteBack;
    @FXML private ComboBox categP;
    @FXML private Button btnCartesBack;
    @FXML private TableColumn afficherDate;
    @FXML private Button ajouterP;
    @FXML private Button modifierP;
    @FXML private Label labelSoldeCompteBack;
    @FXML private Button btnReclamationsBack;
    @FXML private Button BoutonImage;
    @FXML private TextArea descP;
    @FXML private Label pathPublication;
    @FXML private TableColumn afficherCategorie;
    @FXML private Button btnChequiersBack;
    @FXML private TableColumn afficherDescription;
    @FXML private TableColumn afficherTitre;
    @FXML private Button btnUtilisateursBack;
    @FXML private Label labelNomClientCompteBack;
    @FXML private Button btnLogoutBack;
    @FXML private TableColumn afficherNomClient;
    @FXML private TableColumn afficherImge;
    @FXML
    private Button supP;
    @FXML
    private Label erreurTitreBack;
    @FXML
    private Label erreurDescriptionBack;
    @FXML
    private Button btnCommntaireBack;
    @FXML
    private TextField searchPub;
    @FXML
    private Button btnStat;
    @FXML
    private Button btnChequesBack;
    @FXML
    private Button btnAccueilBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categP.setValue("Categorie");
        categP.getItems().add("Crypto-monnaie");
        categP.getItems().add("Devises");
        afficherPublications();
        fillCombobox();
        titreP.clear();
        descP.clear();
        nomClientBack.getSelectionModel().selectFirst();
        titreP.setStyle(null);
        erreurTitreBack.setVisible(false);
        descP.setStyle(null);
        erreurDescriptionBack.setVisible(false);

    }

    private void fillCombobox() {
       /* PublicationController pc = new PublicationController();
        List<String> noms = pc.afficherNomClientPublication();
        if (!noms.isEmpty()) {
            for (int i = 0; i < noms.size(); i++) {
                recherchePublication.add(noms.get(i));
            }
        }
        nomClientBack.setItems(recherchePublication);
        nomClientBack.getSelectionModel().selectFirst(); */
        PublicationController pc = new PublicationController();
//        Combobox Recherche
        List<String> noms = pc.afficherNomClientPublication();
        if (!noms.isEmpty()) {
            for (int i = 0; i < noms.size(); i++) {
                recherchePublication.add(noms.get(i));
            }
        }

//        Combobox Ajout
        UtilisateurCRUD userCont = new UtilisateurCRUD();

        List<String> names = userCont.afficherFullnameUtilisateur();
        if (!names.isEmpty()) {
            for (int i = 0; i < names.size(); i++) {
                namesAjout.add(names.get(i));
            }
        }

        nomClientBack.setItems(namesAjout);

        nomClientBack.getSelectionModel().selectFirst();
    }

    private void afficherPublications() {
        PublicationController pc = new PublicationController();
        List<Publication> publications = pc.afficherPublications();
        //ObservableList<Publication> publications = pc.afficherPublications();
        if (!publications.isEmpty()) {
            for (int i = 0; i < publications.size(); i++) {
                listPublication.add(publications.get(i));
            }
        }

        //afficherNomClient.setCellValueFactory(new PropertyValueFactory<Publication, String>("nom_client_id"));
        afficherNomClient.setCellValueFactory(new PropertyValueFactory<Publication, String>("fullname"));
        afficherTitre.setCellValueFactory(new PropertyValueFactory<Publication, String>("titre_publication"));
        afficherDescription.setCellValueFactory(new PropertyValueFactory<Publication, String>("description_publication"));
        afficherCategorie.setCellValueFactory(new PropertyValueFactory<Publication, String>("categorie_publication"));
        afficherDate.setCellValueFactory(new PropertyValueFactory<Publication, String>("date_publication"));
        afficherImge.setCellValueFactory(new PropertyValueFactory<Publication, String>("image_publication"));
        tableauPublications.setItems(listPublication);
        FilteredList<Publication> filteredData= new FilteredList<>(listPublication, b->true);
        searchPub.textProperty().addListener((observable,oldValue,newValue)->{
            filteredData.setPredicate(Publication->{
                if(newValue.isEmpty()||  newValue==null){
                    return true;
                }
                String searchkeyword=newValue.toLowerCase();
                if(Publication.getTitre_publication().toLowerCase().indexOf(searchkeyword)> -1){
                    return true;
                }else if (Publication.getDescription_publication().toLowerCase().indexOf(searchkeyword)> -1){
                    return true;
                }else
                    return false;
            });
        });
        SortedList<Publication> sortedData=new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableauPublications.comparatorProperty());
        tableauPublications.setItems(sortedData);
    }

    @FXML public void supprimerPublication(ActionEvent actionEvent) {
        PublicationController pc = new PublicationController();

        TablePosition pos = (TablePosition) tableauPublications.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        String selected = tableauPublications.getItems().get(index).toString();
        selected = selected.substring(15, selected.indexOf(","));
        System.out.println(selected);
        Publication p = pc.rechercherPublication(Integer.parseInt(selected));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Publication");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous supprimer la publication de " + p.getFullname() + " ?" );
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("Images/logo-Final.png"));
        ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);
        String finalSelected = selected;
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                pc.supprimerPublication(Integer.parseInt(finalSelected));

                List <Publication> publi = pc.afficherPublications();
                listPublication.clear();
                listPublication.addAll(publi);
                tableauPublications.setItems(listPublication);
//                tableauPublications.getItems().removeAll(tableauPublications.getSelectionModel().getSelectedItem());


                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Publication");
                alert2.setHeaderText(null);
                alert2.setContentText("Publication supprimé avec succès !");
                Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                alert2.showAndWait();
            }
        });
    }
    @FXML public void versChequiersBack(ActionEvent actionEvent) {
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
    @FXML public void versComptesBack(ActionEvent actionEvent) {
        try {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Comptes Bancaires");
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
    @FXML public void versUtilisateursBack(ActionEvent actionEvent) {

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
    @FXML public void versChequesBack(ActionEvent actionEvent) {
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
    @FXML public void versCreditsBack(ActionEvent actionEvent) {
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
    @FXML public void versCartesBack(ActionEvent actionEvent) {
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
    @FXML public void versTransactionsBack(ActionEvent actionEvent) {

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
    @FXML public void ajouterColonnesTextField(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {

            PublicationController pc = new PublicationController();
            UtilisateurCRUD user = new UtilisateurCRUD();
            int x = 0;

            Publication p = (Publication) tableauPublications.getSelectionModel().getSelectedItem();

            nomClientBack.getSelectionModel().select(x);
            titreP.setText(String.valueOf(p.getTitre_publication()));
            descP.setText(String.valueOf(p.getDescription_publication()));
            if (p.getCategorie_publication().toLowerCase().equals("crypto-monnaie")) {
                categP.getSelectionModel().selectFirst();
            } else {
                categP.getSelectionModel().selectLast();
            }
        }
    }
    @FXML public void ajouterPublicationBack(ActionEvent actionEvent) {
        PublicationController pc = new PublicationController();
        UtilisateurCRUD user = new UtilisateurCRUD();
        if(validateAllPublications()) {
            String nom = nomClientBack.getSelectionModel().getSelectedItem().toString();

            int idClient = user.getIdFromFullname(nom).get(0).getId();

            String titre = String.valueOf(titreP.getText());
            String description = String.valueOf(descP.getText());
            String categorie = categP.getSelectionModel().getSelectedItem().toString();
            String image = imageP.getImage().toString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Publication p = new Publication(idClient, titre, description, categorie, dtf.format(now), image);
            pc.ajouterPublication(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Publications");
            alert.setHeaderText(null);
            alert.setContentText("Publication ajoutée avec succès !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();

            listPublication.clear();
            tableauPublications.setItems(listPublication);
            afficherPublications();
            clearFields();
            nomClientBack.setStyle(null);
            titreP.setStyle(null);
            erreurTitreBack.setVisible(false);
            descP.setStyle(null);
            erreurDescriptionBack.setVisible(false);
        }
    }
    @FXML public void modifierPublicationBack(ActionEvent actionEvent) {
        if(validateAllPublications()) {
            PublicationController pc = new PublicationController();
            UtilisateurCRUD user = new UtilisateurCRUD();

            Publication p = (Publication) tableauPublications.getSelectionModel().getSelectedItem();

            String titre = String.valueOf(titreP.getText());
            String description = String.valueOf(descP.getText());
            String categorie = categP.getSelectionModel().getSelectedItem().toString();
            //String image = imageP.getImage().toString();

            p.setTitre_publication(titre);
            p.setDescription_publication(description);
            p.setCategorie_publication(categorie);
            //p.setImage_publication(image);

            pc.modifierPublication(p, p.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Publication");
            alert.setHeaderText(null);
            alert.setContentText("Publication modifiée avec succès !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();

            listPublication.clear();
            tableauPublications.setItems(listPublication);

            afficherPublications();
            //nomClientBack.setDisable(false);
            clearFields();
            titreP.setStyle(null);
            erreurTitreBack.setVisible(false);
            descP.setStyle(null);
            erreurDescriptionBack.setVisible(false);
        }
    }
    @FXML public void ajouterImageBack(ActionEvent actionEvent) {
        FileChooser image = new FileChooser();
        //image.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images Files", ".png",".jpg"));
        File f = image.showOpenDialog(null);
        if (f != null) {
            pathPublication.setText(f.getAbsolutePath());
            String sourcePath = f.getPath();
            String destinationPath = "src/Images/";
            File sourceFile = new File(sourcePath);
            File destinationFile = new File(destinationPath + sourceFile.getName());
            Image img = new Image(f.toURI().toString(), imageP.getFitWidth(),imageP.getFitHeight(),true,true);
            imageP.setImage(img);
            try {
                Files.copy(sourceFile.toPath(), destinationFile.toPath(), REPLACE_EXISTING);
                // Static Methods To Copy Copy source path to destination path
            } catch (Exception e) {
                System.out.println(e.getMessage());  // printing in case of error.
            }

        }
    }
    private void clearFields() {
        titreP.clear();
        descP.clear();
        nomClientBack.getSelectionModel().selectFirst();
        categP.getSelectionModel().selectFirst();
        imageP.clipProperty();

    }
    private boolean validateDescription() {
        if (!descP.getText().isEmpty()) {

        } else {
            return false;
        }
        return true;
    }
    private boolean validateTitre() {
        if (!titreP.getText().isEmpty()) {

        } else {
            return false;
        }
        return true;
    }
    private boolean validateAllPublications() {
        if (validateDescription() && validateTitre()) {
            titreP.setStyle(null);
            erreurTitreBack.setVisible(false);
            descP.setStyle(null);
            erreurDescriptionBack.setVisible(false);

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur ");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs correctement !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();

            if (!validateTitre()) {
                titreP.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                erreurTitreBack.setVisible(true);
            } else {
                titreP.setStyle(null);
                erreurTitreBack.setVisible(false);
            }
            if (!validateDescription()) {
                descP.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                erreurDescriptionBack.setVisible(true);
            } else {
                descP.setStyle(null);
                erreurDescriptionBack.setVisible(false);
            }
            return false;
        }
    }

    @FXML
    public void versCommentaireBack(ActionEvent actionEvent) {

        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Commentaires");
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
    public void versLogoutBack(ActionEvent actionEvent) {
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
    public void versReclamationsBack(ActionEvent actionEvent) {
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
