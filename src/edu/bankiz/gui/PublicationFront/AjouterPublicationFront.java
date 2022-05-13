package edu.bankiz.gui.PublicationFront;

import edu.bankiz.entities.Publication;
import edu.bankiz.entities.Session;
import edu.bankiz.services.PublicationController;
import edu.bankiz.services.UtilisateurCRUD;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
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

public class AjouterPublicationFront implements Initializable {
    public ObservableList<String> recherchePublication = FXCollections.observableArrayList();
    public ObservableList<String> namesAjout = FXCollections.observableArrayList();

    @FXML
    private ImageView uploadedFilmPoster;
    @FXML
    private ComboBox categoriePublication;
    @FXML
    private Text newFilmTitle;
    @FXML
    private Label erreurDescription;
    @FXML
    private Button telechargerImage;
    @FXML
    private Label erreurTitre;
    @FXML
    private TextArea filmDescription;
    @FXML
    private TextField filmTrailer;
    @FXML
    private Text newFilmDescription;
    @FXML
    private Text newFilmCategorie;
    @FXML
    private ComboBox nomClientFront;

    @FXML
    public void storeFilmInfo(ActionEvent actionEvent) {
        PublicationController pc = new PublicationController();
        UtilisateurCRUD user = new UtilisateurCRUD();
        if (validateAllPublications()) {
            String nom = nomClientFront.getSelectionModel().getSelectedItem().toString();

            int idClient = user.getIdFromFullname(nom).get(0).getId();

            String titre = String.valueOf(newFilmTitle.getText());
            String description = String.valueOf(newFilmDescription.getText());
            String categorie = categoriePublication.getSelectionModel().getSelectedItem().toString();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Publication p = new Publication(Session.getId(), titre, description, categorie, dtf.format(now), telechargerImage.getText());

            pc.ajouterPublication(p);
            refresh();
            filmTrailer.setStyle(null);
            erreurTitre.setVisible(false);
            filmDescription.setStyle(null);
            erreurDescription.setVisible(false);
        }
    }

    @FXML
    public void launchViewFilms(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Articles");
            Parent root = FXMLLoader.load(getClass().getResource("PublicationFront.fxml"));

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
    public void clickComboboxCategoriee(Event event) {
        if(categoriePublication.getSelectionModel().getSelectedIndex() != -1){
            newFilmCategorie.setText(categoriePublication.getSelectionModel().getSelectedItem().toString());

            System.out.println("kjhkjhkjhj");
        }
    }

    @FXML
    public void updateFilmText(KeyEvent e) {
        switch (((Node) e.getSource()).getId()) {
            case "filmTrailer":
                if (filmTrailer.getText().length() > 20) {
                    filmTrailer.setEditable(false);
                }
                break;
            case "filmDescription":
                if (filmDescription.getText().length() > 220) {
                    filmDescription.setEditable(false);
                }
                break;
        }

        if (e.getCode().equals(KeyCode.BACK_SPACE)) {
            filmTrailer.setEditable(true);
            filmDescription.setEditable(true);
            categoriePublication.setEditable(true);
        }

        switch (((Node) e.getSource()).getId()) {
            case "filmTrailer":
                newFilmTitle.setText(filmTrailer.getText());
                break;
            case "filmDescription":
                newFilmDescription.setText(filmDescription.getText());
                break;
        }
        if(categoriePublication.getSelectionModel().getSelectedIndex() != -1){
            System.out.println("kjhkjhkjhj");
            newFilmCategorie.setText(categoriePublication.getSelectionModel().getSelectedItem().toString());
        }
    }

    @FXML
    public void uploadImageClick(ActionEvent actionEvent) {
        FileChooser image = new FileChooser();
        //image.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images Files", ".png",".jpg"));
        File f = image.showOpenDialog(null);
        if (f != null) {
            String sourcePath = f.getPath();
            String destinationPath = "C:\\Users\\msi\\Desktop\\FINALEBANKIZJAVA\\bankiz\\src\\Images\\";
            File sourceFile = new File(sourcePath);
            File destinationFile = new File(destinationPath + sourceFile.getName());
            Image img = new Image(f.toURI().toString(), uploadedFilmPoster.getFitWidth(),uploadedFilmPoster.getFitHeight(),true,true);
            uploadedFilmPoster.setImage(img);
            telechargerImage.setText(destinationFile.getAbsolutePath());
            try {
                Files.copy(sourceFile.toPath(), destinationFile.toPath(), REPLACE_EXISTING);
                // Static Methods To Copy Copy source path to destination path
            } catch (Exception e) {
                System.out.println(e.getMessage());  // printing in case of error.
            }

        }
    }
    public void refresh(){
        filmTrailer.setText("");
        filmDescription.setText("");
        categoriePublication.setValue(null);
        newFilmTitle.setText("");
        newFilmDescription.setText("");
        newFilmCategorie.setText("");
        uploadedFilmPoster.setImage(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriePublication.setValue("Categorie");
        categoriePublication.getItems().add("Crypto-monnaie");
        categoriePublication.getItems().add("Devises");
        //categoriePublication.getSelectionModel().selectFirst();
        fillcombobox();
        filmTrailer.setStyle(null);
        erreurTitre.setVisible(false);
        filmDescription.setStyle(null);
        erreurDescription.setVisible(false);
    }
    private void fillcombobox() {
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

        nomClientFront.setItems(namesAjout);

        nomClientFront.getSelectionModel().selectFirst();
    }
    private boolean validateDescription() {
        if (!filmDescription.getText().isEmpty()) {

        } else {
            return false;
        }
        return true;
    }
    private boolean validateTitre() {
        if (!filmTrailer.getText().isEmpty()) {

        } else {
            return false;
        }
        return true;
    }
    private boolean validateAllPublications() {
        if (validateDescription() && validateTitre()) {
            filmTrailer.setStyle(null);
            erreurTitre.setVisible(false);
            filmDescription.setStyle(null);
            erreurDescription.setVisible(false);

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
                filmTrailer.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                erreurTitre.setVisible(true);
            } else {
                filmTrailer.setStyle(null);
                erreurTitre.setVisible(false);
            }
            if (!validateDescription()) {
                filmDescription.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                erreurDescription.setVisible(true);
            } else {
                filmDescription.setStyle(null);
                erreurDescription.setVisible(false);
            }
            return false;
        }
    }
}
