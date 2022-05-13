package edu.bankiz.gui.Comptes.Back;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import edu.bankiz.entities.Compte;
import edu.bankiz.entities.Session;
import edu.bankiz.entities.Transaction;
import edu.bankiz.entities.Utilisateur;
import edu.bankiz.services.CompteController;
import edu.bankiz.services.TransactionController;
import edu.bankiz.services.UtilisateurCRUD;
import javafx.application.Platform;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;

import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;

public class ComptesBackController implements Initializable {

    @FXML
    private TableColumn AffichedateCompteBack;
    @FXML
    private TableColumn AfficheRIBCompteBack;
    @FXML
    private TableColumn AffichenumCompteBack;
    @FXML
    private TableColumn AfficheEtatCompteBack;
    @FXML
    private TableColumn AffichetypeCompteBack;
    @FXML
    private TableColumn AffichetauxCompteBack;
    @FXML
    private TableColumn AffichenomClientCompteBack;
    @FXML
    private TableColumn AffichesoldeCompteBack;
    @FXML
    private TableColumn AfficheseuilCompteBack;
    @FXML
    private TableView TableauCompteBack;
    @FXML
    private ComboBox rechercheCompteBack;

    @FXML
    private TextField interetBack;
    @FXML
    private TextField seuilBack;
    @FXML
    private TextField soldeBack;
    @FXML
    private ComboBox nomClientBack;
    @FXML
    private Button btnAjouterCompteBack;
    @FXML
    private Button btnSupprimerCompteBack;
    @FXML
    private ComboBox typeCompteBack;
    @FXML
    private Button btnModifierCompteBack;
    @FXML
    private Button btnResetCompteBack;

    public ObservableList<Compte> listCompte = FXCollections.observableArrayList();
    public ObservableList<String> rechercheCompte = FXCollections.observableArrayList();
    public ObservableList<String> namesAjout = FXCollections.observableArrayList();
    @FXML
    private Button ComptesBack;
    @FXML
    private Button pubBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private AnchorPane ContainerContenuBack;
    @FXML
    private Button btnAcceuilBack;
    @FXML
    private Button btnChequesBack;
    @FXML
    private Button btnCreditsBack;
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
    private Label labelInteretCompteBack;
    @FXML
    private Label controleSoldeCompteBack;
    @FXML
    private Label controleInteretCompteBack;
    @FXML
    private Label labelSeuilCompteBack;
    @FXML
    private Label controleClientCompteBack;
    @FXML
    private Label labelTypeCompteBack;
    @FXML
    private Label controleSeuilCompteBack;
    @FXML
    private Label labelSoldeCompteBack;
    @FXML
    private Label controleTypeCompteBack;
    @FXML
    private Label labelNomClientCompteBack;
    @FXML
    private Button btnFichierExcel;
    @FXML
    private Button btnRechercherCompteBackEpargne;
    @FXML
    private Button btnReleveBack;
    @FXML
    private Button btnRechercheBack;
    @FXML
    private Button btnRechercherCompteCourantBack;
    @FXML
    private Button btnCommentaire;
//    @FXML
//    private Label labelTemps;
//
//    private volatile boolean stop = false;
//    @FXML
//    private Label labelDateNow;

    @FXML
    public void afficherComptes() {
        CompteController comptCont = new CompteController();
        List<Compte> comptes = comptCont.afficherComptes();
        if (!comptes.isEmpty()) {
            for (int i = 0; i < comptes.size(); i++) {
                listCompte.add(comptes.get(i));
            }
        }

        addEtatColumn();

        AffichedateCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("date_creation"));
        AfficheRIBCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("RIB_Compte"));
        AffichenumCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("num_compte"));
        AfficheEtatCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("etat_compte"));
        AffichetypeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("type_compte"));
        AffichetauxCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("taux_interet"));
        AffichenomClientCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("fullname"));
        AffichesoldeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("solde_compte"));
        AfficheseuilCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("seuil_compte"));
        TableauCompteBack.setItems(listCompte);
    }

    private void addEtatColumn() {
        TableColumn<Compte, Void> colBtn = new TableColumn("Etat Compte");

        Callback<TableColumn<Compte, Void>, TableCell<Compte, Void>> cellFactory = new Callback<TableColumn<Compte, Void>, TableCell<Compte, Void>>() {

            public TableCell<Compte, Void> call(final TableColumn<Compte, Void> param) {
                final TableCell<Compte, Void> cell = new TableCell<Compte, Void>() {
                    ComboBox combEtat = new ComboBox();

                    {
                        combEtat.getItems().add("Activé");
                        combEtat.getItems().add("Désactivé");
                        combEtat.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                            Compte data = getTableView().getItems().get(getIndex());
                            if(newValue == "Activé"){
                                data.setEtat_compte(1);
                            }else{
                                data.setEtat_compte(2);
                            }
                            CompteController.modifierCompte(data, data.getId());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Compte data = getTableView().getItems().get(getIndex());
                            if(data.getEtat_compte() == 1){
                                combEtat.setValue("Activé");
                            }else if(data.getEtat_compte() == 2){
                                combEtat.setValue("Désactivé");
                            }else if (data.getEtat_compte() == 0){
                                combEtat.getItems().add("En cours d'activation");
                                combEtat.setValue("En cours d'activation");
                            }
                            setGraphic(combEtat);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        TableauCompteBack.getColumns().add(colBtn);
    }

    @FXML
    public void fillCombobox() {
        CompteController comptCont = new CompteController();
//        Combobox Recherche
        List<String> noms = comptCont.afficherNomClientComptes();
        if (!noms.isEmpty()) {
            for (int i = 0; i < noms.size(); i++) {
                rechercheCompte.add(noms.get(i));
            }
        }
        rechercheCompteBack.setItems(rechercheCompte);
        rechercheCompteBack.getSelectionModel().selectFirst();

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
        typeCompteBack.getSelectionModel().selectFirst();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        afficherComptes();
        fillCombobox();
        clearFields();

        nomClientBack.setStyle(null);
        controleClientCompteBack.setVisible(false);
        typeCompteBack.setStyle(null);
        controleTypeCompteBack.setVisible(false);
        interetBack.setStyle(null);
        controleInteretCompteBack.setVisible(false);
        soldeBack.setStyle(null);
        controleSoldeCompteBack.setVisible(false);
        seuilBack.setStyle(null);
        controleSeuilCompteBack.setVisible(false);

//        TimeNow();
    }

    @FXML
    void clearFields() {
        interetBack.clear();
        seuilBack.clear();
        soldeBack.clear();
        nomClientBack.getSelectionModel().selectFirst();
        typeCompteBack.getSelectionModel().selectFirst();
    }

    @FXML
    public void ajouterCompteBack(ActionEvent actionEvent) {
        CompteController comptCont = new CompteController();
        UtilisateurCRUD user = new UtilisateurCRUD();

        if (validateAllCompte()) {
            String nom = nomClientBack.getSelectionModel().getSelectedItem().toString();

            int idClient = user.getIdFromFullname(nom).get(0).getId();

            Float solde = Float.valueOf(soldeBack.getText());
            Float seuil = Float.valueOf(seuilBack.getText());
            Float interet = Float.valueOf(interetBack.getText());
            String type = typeCompteBack.getSelectionModel().getSelectedItem().toString();
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            long numCompte = comptCont.GenererNumCompte();
            Compte cmpt = new Compte(numCompte, "123451234" + numCompte + "12", solde, dtf.format(now), type, seuil, interet, idClient, 0);
            comptCont.ajouterCompte2(cmpt);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion des comptes bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Compte Bancaire ajouté avec succès !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();
            TableauCompteBack.getItems().clear();
            afficherComptes();
            clearFields();

            nomClientBack.setStyle(null);
            seuilBack.setStyle(null);
            soldeBack.setStyle(null);
            interetBack.setStyle(null);
            typeCompteBack.setStyle(null);
        }
    }


    @FXML
    public void rechercherCompteBack(ActionEvent actionEvent) {
        TableauCompteBack.getItems().clear();

        UtilisateurCRUD user = new UtilisateurCRUD();
        CompteController cmpt = new CompteController();

        String nom = rechercheCompteBack.getSelectionModel().getSelectedItem().toString();
        int idClient = user.getIdFromFullname(nom).get(0).getId();

        List<Compte> cmpts = cmpt.afficherComptesFullname(idClient);
        if (!cmpts.isEmpty()) {
            for (int i = 0; i < cmpts.size(); i++) {
                listCompte.add(cmpts.get(i));
            }
        }
        AffichedateCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("date_creation"));
        AfficheRIBCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("RIB_Compte"));
        AffichenumCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("num_compte"));
        AfficheEtatCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("etat_compte"));
        AffichetypeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("type_compte"));
        AffichetauxCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("taux_interet"));
        AffichenomClientCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("fullname"));
        AffichesoldeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("solde_compte"));
        AfficheseuilCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("seuil_compte"));
        TableauCompteBack.setItems(listCompte);
    }

    @FXML
    public void supprimerCompteBack(ActionEvent actionEvent) {
        if(!TableauCompteBack.getSelectionModel().getSelectedCells().isEmpty()) {

            CompteController cmpt = new CompteController();
            TransactionController transCont = new TransactionController();

            TablePosition pos = (TablePosition) TableauCompteBack.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            String selected = TableauCompteBack.getItems().get(index).toString();
            selected = selected.substring(7, selected.indexOf(" : "));
            Compte c = cmpt.rechercherCompte(Integer.parseInt(selected));

            List<Transaction> trans = transCont.SuppressionCompteTransaction(c.getId());
            System.out.println(trans);
            if(trans.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Gestion des comptes bancaires");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous supprimer le compte de " + c.getFullname() + " ?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("Images/logo-Final.png"));
                ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, cancelButton);
                String finalSelected = selected;
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {
                        cmpt.supprimerCompte(Integer.parseInt(finalSelected));

                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Gestion des comptes bancaires");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Compte Bancaire supprimé avec succès !");
                        Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                        stage2.getIcons().add(new Image("Images/logo-Final.png"));
                        alert2.showAndWait();

                        TableauCompteBack.getItems().removeAll(TableauCompteBack.getSelectionModel().getSelectedItem());
                    }
                });
            }else{
                System.out.println("lkjhlkcdsjhmlsqkjhlkqjshdkjhdsqblksqdjhkvgqdxnmlkXLSJHVGDCKBLXNQMW");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gestion des comptes bancaires");
                alert.setHeaderText(null);
                alert.setContentText("Ce compte a déjà effectué des transactions. Voulez vous supprimer le compte et ses transactions ?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("Images/logo-Final.png"));
                ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, cancelButton);
                String finalSelected = selected;
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {
                        for (int i = 0; i <trans.size(); i++){
                            transCont.supprimerTransaction(trans.get(i).getId());
                        }
                        cmpt.supprimerCompte(Integer.parseInt(finalSelected));

                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Gestion des comptes bancaires");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Compte Bancaire et transactions supprimés avec succès !");
                        Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                        stage2.getIcons().add(new Image("Images/logo-Final.png"));
                        alert2.showAndWait();

                        TableauCompteBack.getItems().removeAll(TableauCompteBack.getSelectionModel().getSelectedItem());
                    }
                });
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion des comptes bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner le compte bancaire à supprimer !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();
        }
    }

    public void doubleClickCompteBack(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            nomClientBack.setDisable(true);

//            Recherche et affectation dans les Textfield
            CompteController comptCont = new CompteController();
            UtilisateurCRUD user = new UtilisateurCRUD();
            int x = 0;

            Compte cmpt = (Compte) TableauCompteBack.getSelectionModel().getSelectedItem();

            List<String> names = user.afficherFullnameUtilisateur();
            if (!names.isEmpty()) {
                for (int i = 0; i < names.size(); i++) {
                    if (names.get(i).equals(comptCont.afficherMonCompte(cmpt.getFullname_client_id()).getFullname())) {
                        x = i;
                    }
                }
            }
            nomClientBack.getSelectionModel().select(x);
            soldeBack.setText(String.valueOf(cmpt.getSolde_compte()));
            seuilBack.setText(String.valueOf(cmpt.getSeuil_compte()));
            interetBack.setText(String.valueOf(cmpt.getTaux_interet()));
            if (cmpt.getType_compte().toLowerCase().equals("courant")) {
                typeCompteBack.getSelectionModel().selectFirst();
            } else {
                typeCompteBack.getSelectionModel().selectLast();
            }
        }
    }

    @FXML
    public void doubleClickCompteBack(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            nomClientBack.setDisable(true);

//            Recherche et affectation dans les Textfield
            CompteController comptCont = new CompteController();
            UtilisateurCRUD user = new UtilisateurCRUD();
            int x = 0;

            Compte cmpt = (Compte) TableauCompteBack.getSelectionModel().getSelectedItem();

            List<String> names = user.afficherFullnameUtilisateur();
            if (!names.isEmpty()) {
                for (int i = 0; i < names.size(); i++) {
                    if (names.get(i).equals(comptCont.afficherMonCompte(cmpt.getFullname_client_id()).getFullname())) {
                        x = i;
                    }
                }
            }
            nomClientBack.getSelectionModel().select(x);
            soldeBack.setText(String.valueOf(cmpt.getSolde_compte()));
            seuilBack.setText(String.valueOf(cmpt.getSeuil_compte()));
            interetBack.setText(String.valueOf(cmpt.getTaux_interet()));
            if (cmpt.getType_compte().toLowerCase().equals("courant")) {
                typeCompteBack.getSelectionModel().selectFirst();
            } else {
                typeCompteBack.getSelectionModel().selectLast();
            }
        }
    }

    @FXML
    public void modifierCompteBack(ActionEvent actionEvent) {
        if(!TableauCompteBack.getSelectionModel().getSelectedCells().isEmpty()) {

            CompteController comptCont = new CompteController();
            UtilisateurCRUD user = new UtilisateurCRUD();

//        String nom = nomClientBack.getSelectionModel().getSelectedItem().toString();
//        int idClient = user.getIdFromFullname(nom).get(0).getId();
//        Compte cmpt = comptCont.afficherMonCompte(idClient);
            if (validateAllCompte()) {
                Compte cmpt = (Compte) TableauCompteBack.getSelectionModel().getSelectedItem();

                Float solde = Float.valueOf(soldeBack.getText());
                Float seuil = Float.valueOf(seuilBack.getText());
                Float interet = Float.valueOf(interetBack.getText());
                String type = typeCompteBack.getSelectionModel().getSelectedItem().toString();

                cmpt.setSolde_compte(solde);
                cmpt.setSeuil_compte(seuil);
                cmpt.setTaux_interet(interet);
                cmpt.setType_compte(type);

                comptCont.modifierCompte(cmpt, cmpt.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Gestion des comptes bancaires");
                alert.setHeaderText(null);
                alert.setContentText("Compte Bancaire modifié avec succès !");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("Images/logo-Final.png"));
                alert.showAndWait();

                TableauCompteBack.getItems().clear();

                afficherComptes();
                nomClientBack.setDisable(false);
                clearFields();

                nomClientBack.setStyle(null);
                seuilBack.setStyle(null);
                soldeBack.setStyle(null);
                interetBack.setStyle(null);
                typeCompteBack.setStyle(null);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion des comptes bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner le compte bancaire à modifier !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();
        }

    }

    @FXML
    public void resetCompteBack(ActionEvent actionEvent) {
        TableauCompteBack.getItems().clear();
        rechercheCompteBack.getItems().clear();
        nomClientBack.getItems().clear();

        nomClientBack.setDisable(false);

        clearFields();
        fillCombobox();
        afficherComptes();

        nomClientBack.setStyle(null);
        controleClientCompteBack.setVisible(false);
        typeCompteBack.setStyle(null);
        controleTypeCompteBack.setVisible(false);
        interetBack.setStyle(null);
        controleInteretCompteBack.setVisible(false);
        soldeBack.setStyle(null);
        controleSoldeCompteBack.setVisible(false);
        seuilBack.setStyle(null);
        controleSeuilCompteBack.setVisible(false);
    }

    @FXML
    public void openPublicationBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Publications");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/PublicationBack/PublicationBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            stage.setTitle("Les Transactions Bancaires");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Transactions/Back/TransactionsBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versCreditsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CreditsOperations/afficherCredit.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    //    ************************* CONTROLE DE SAISIE **************************
    private boolean validateSoldeCompte() {
        if (!soldeBack.getText().isEmpty()) {
            if (soldeBack.getText().matches("[0-9]*[.,][0-9]*") || soldeBack.getText().matches("[0-9]*")) {
                if (Float.valueOf(soldeBack.getText().toString()) > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validateSeuilCompte() {
        float seuil;
        if (!seuilBack.getText().isEmpty()) {
            if (seuilBack.getText().matches("[0-9]*[.,][0-9]*") || seuilBack.getText().matches("[0-9]*")) {
                if (!soldeBack.getText().isEmpty()) {
                    seuil = Float.valueOf(soldeBack.getText()) * 0.5f;
                } else {
                    seuil = 0f;
                }
                if (Float.valueOf(seuilBack.getText().toString()) > 0 && Float.valueOf(seuilBack.getText().toString()) <= seuil) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validatetauxInteret() {
        if (!interetBack.getText().isEmpty()) {
            if (interetBack.getText().matches("[0][.,][0-9]*")) {
                if (Float.valueOf(interetBack.getText()) < 0.1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validateClientCompte() {
        if (nomClientBack.getSelectionModel().getSelectedIndex() != -1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateTypeCompte() {
        if (typeCompteBack.getSelectionModel().getSelectedIndex() != -1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateAllCompte() {
        if (validateClientCompte() && validateSeuilCompte() && validateSoldeCompte() && validatetauxInteret() && validateTypeCompte()) {
            nomClientBack.setStyle(null);
            controleClientCompteBack.setVisible(false);
            typeCompteBack.setStyle(null);
            controleTypeCompteBack.setVisible(false);
            interetBack.setStyle(null);
            controleInteretCompteBack.setVisible(false);
            soldeBack.setStyle(null);
            controleSoldeCompteBack.setVisible(false);
            seuilBack.setStyle(null);
            controleSeuilCompteBack.setVisible(false);

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur ");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs correctement !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();

            if (!validateClientCompte()) {
                nomClientBack.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleClientCompteBack.setVisible(true);
            } else {
                nomClientBack.setStyle(null);
                controleClientCompteBack.setVisible(false);
            }
            if (!validateSeuilCompte()) {
                seuilBack.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleSeuilCompteBack.setVisible(true);
            } else {
                seuilBack.setStyle(null);
                controleSeuilCompteBack.setVisible(false);
            }
            if (!validateSoldeCompte()) {
                soldeBack.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleSoldeCompteBack.setVisible(true);
            } else {
                soldeBack.setStyle(null);
                controleSoldeCompteBack.setVisible(false);
            }
            if (!validatetauxInteret()) {
                interetBack.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleInteretCompteBack.setVisible(true);
            } else {
                interetBack.setStyle(null);
                controleInteretCompteBack.setVisible(false);
            }
            if (!validateTypeCompte()) {
                typeCompteBack.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleTypeCompteBack.setVisible(true);
            } else {
                typeCompteBack.setStyle(null);
                controleTypeCompteBack.setVisible(false);
            }
            return false;
        }
    }

// ************************** Autre *************
    @FXML
    public void genererFichierExcel(ActionEvent actionEvent) {
        CompteController comptCont = new CompteController();
        List<Compte> listeComptes = comptCont.afficherComptes();
        try {
            String filename = "C:\\Users\\msi\\Desktop\\Gitlab\\bankiz\\src\\ComptesBancaires\\ComptesBancaires.xls";

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Comptes Bancaires");

            sheet.setColumnWidth(0,(int)17.25*256);
            sheet.setColumnWidth(1,(int)25.25*256);
            sheet.setColumnWidth(2,(int)22.25*256);
            sheet.setColumnWidth(3,(int)13.25*256);
            sheet.setColumnWidth(4,(int)15.25*256);
            sheet.setColumnWidth(5,(int)15.25*256);
            sheet.setColumnWidth(6,(int)15.25*256);
            sheet.setColumnWidth(7,(int)23.25*256);
            sheet.setColumnWidth(8,(int)20.25*256);
            sheet.setColumnWidth(9,(int)7.25*256);
            sheet.setColumnWidth(10,(int)25.25*256);
            sheet.setColumnWidth(11,(int)25.25*256);
            sheet.setColumnWidth(12,(int)25.25*256);

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Num Compte");
            rowhead.createCell(1).setCellValue("RIB");
            rowhead.createCell(2).setCellValue("Nom Client");
            rowhead.createCell(3).setCellValue("Type Compte");
            rowhead.createCell(4).setCellValue("Solde");
            rowhead.createCell(5).setCellValue("Seuil Compte");
            rowhead.createCell(6).setCellValue("Taux d'intérêt");
            rowhead.createCell(7).setCellValue("Date de création");
            rowhead.createCell(8).setCellValue("Etat Compte");
            rowhead.createCell(9).setCellValue("");
            rowhead.createCell(10).setCellValue("Total Comptes Bancaires");
            rowhead.createCell(11).setCellValue("Total Comptes Courants");
            rowhead.createCell(12).setCellValue("Total Comptes Epargnes");

            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            font.setColor(IndexedColors.GOLD.getIndex());
            style.setFont(font);
            for(int i = 0; i < rowhead.getLastCellNum(); i++){
                rowhead.getCell(i).setCellStyle(style);
            }
            style.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);
            rowhead.getCell(0).setCellStyle(style);

            CellStyle style2 = workbook.createCellStyle();
            Font font2 = workbook.createFont();
            font2.setBold(true);
            font2.setColor(IndexedColors.WHITE.getIndex());
            style2.setFont(font2);
            style2.setFillForegroundColor(IndexedColors.RED.getIndex());
            style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style2.setAlignment(HorizontalAlignment.CENTER);

            for (int i=1; i<listeComptes.size()+1;i++){
                HSSFRow row = sheet.createRow((short)i);

                row.createCell(0).setCellValue(String.valueOf(listeComptes.get(i-1).getNum_compte()));
                row.createCell(1).setCellValue(listeComptes.get(i-1).getRIB_Compte());
                row.createCell(2).setCellValue(listeComptes.get(i-1).getFullname());
                row.createCell(3).setCellValue(listeComptes.get(i-1).getType_compte());
                row.createCell(4).setCellValue(String.valueOf(listeComptes.get(i-1).getSolde_compte()));
                row.createCell(5).setCellValue(String.valueOf(listeComptes.get(i-1).getSeuil_compte()));
                row.createCell(6).setCellValue(listeComptes.get(i-1).getTaux_interet());
                row.createCell(7).setCellValue(listeComptes.get(i-1).getDate_creation());
                if(listeComptes.get(i-1).getEtat_compte() == 0){
                    row.createCell(8).setCellValue("En cours d'activation");
                }else if (listeComptes.get(i-1).getEtat_compte() == 1){
                    row.createCell(8).setCellValue("Activé");
                }else{
                    row.createCell(8).setCellValue("Désactivé");
                }
                if (i == 1){
                    row.createCell(10).setCellValue(comptCont.nbrComptes());
                    row.createCell(11).setCellValue(comptCont.nbrComptesCourant());
                    row.createCell(12).setCellValue(comptCont.nbrComptesEpargne());

                    row.getCell(10).setCellStyle(style2);
                    row.getCell(11).setCellStyle(style2);
                    row.getCell(12).setCellStyle(style2);

                }
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Fichier Excel Généré !");

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void calculSeuil(Event event) {
        if(!soldeBack.getText().isEmpty()){
            float seuil = Float.valueOf(soldeBack.getText())/2;
            seuilBack.setPromptText(" > 50% solde (= "+seuil+" DT)");
        }
    }

    @FXML
    public void rechercherCompteBackEpargne(ActionEvent actionEvent) {
        TableauCompteBack.getItems().clear();

        CompteController cmpt = new CompteController();

        List<Compte> cmpts = cmpt.afficherComptesEpargnes();
        if (!cmpts.isEmpty()) {
            for (int i = 0; i < cmpts.size(); i++) {
                listCompte.add(cmpts.get(i));
            }
        }
        AffichedateCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("date_creation"));
        AfficheRIBCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("RIB_Compte"));
        AffichenumCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("num_compte"));
        AfficheEtatCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("etat_compte"));
        AffichetypeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("type_compte"));
        AffichetauxCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("taux_interet"));
        AffichenomClientCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("fullname"));
        AffichesoldeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("solde_compte"));
        AfficheseuilCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("seuil_compte"));
        TableauCompteBack.setItems(listCompte);
    }

    @FXML
    public void rechercherCompteCourantBack(ActionEvent actionEvent) {
        TableauCompteBack.getItems().clear();

        CompteController cmpt = new CompteController();

        List<Compte> cmpts = cmpt.afficherComptesCourants();
        if (!cmpts.isEmpty()) {
            for (int i = 0; i < cmpts.size(); i++) {
                listCompte.add(cmpts.get(i));
            }
        }
        AffichedateCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("date_creation"));
        AfficheRIBCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("RIB_Compte"));
        AffichenumCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("num_compte"));
        AfficheEtatCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("etat_compte"));
        AffichetypeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("type_compte"));
        AffichetauxCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("taux_interet"));
        AffichenomClientCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("fullname"));
        AffichesoldeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("solde_compte"));
        AfficheseuilCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("seuil_compte"));
        TableauCompteBack.setItems(listCompte);
    }

    @FXML
    public void genererReleveCompteBack(ActionEvent actionEvent) {
        if (!TableauCompteBack.getSelectionModel().getSelectedCells().isEmpty()) {

            CompteController comptCont = new CompteController();
            TransactionController transCont = new TransactionController();

            TablePosition pos = (TablePosition) TableauCompteBack.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            String selected = TableauCompteBack.getItems().get(index).toString();
            selected = selected.substring(7, selected.indexOf(" : "));
            Compte cmpt = comptCont.rechercherCompte(Integer.parseInt(selected));

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDateTime now = LocalDateTime.now();

            List<Transaction> trans = transCont.afficherMesTransactionsReleve(cmpt.getId());

            try {
                String file_name = "C:\\Users\\msi\\Desktop\\Gitlab\\bankiz\\src\\RelevesDeCompte\\ReleveDeCompte_" + cmpt.getFullname() + "_" + dtf.format(now) + ".pdf";
                Document document = new Document();

                PdfWriter.getInstance(document, new FileOutputStream(file_name));

                document.open();

                com.itextpdf.text.Font boldPetit = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font boldMoyen = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font boldGrand = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20, com.itextpdf.text.Font.BOLD);
                com.itextpdf.text.Font petit = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, com.itextpdf.text.Font.NORMAL);

                Paragraph para = new Paragraph("Relevé de compte bancaire ", boldMoyen);
                Paragraph para2 = new Paragraph("Adresse : 06, Bloc I - Esprit");
                Paragraph para3 = new Paragraph("Horaires : 08:00 - 19:00");
                Paragraph para4 = new Paragraph("Tel : +216 12 345 678");
                Paragraph para5 = new Paragraph("Email : contact@bankiz.tn");
                Paragraph para6 = new Paragraph("Monsieur/Madame " + cmpt.getFullname(), boldGrand);
                Paragraph para7 = new Paragraph("Numéro de compte : " + cmpt.getNum_compte());
                Paragraph para8 = new Paragraph("Date  : Le " + dtf.format(now));
                Paragraph para9 = new Paragraph("Solde  : " + cmpt.getSolde_compte() + " DT");
                para.setAlignment(2);
                para2.setAlignment(2);
                para3.setAlignment(2);
                para4.setAlignment(2);
                para5.setAlignment(2);
                para8.setAlignment(2);
                para9.setAlignment(2);

                LineSeparator separator = new LineSeparator();
                separator.setLineColor(new BaseColor(246, 194, 62));
//            separator.setLineColor(BaseColor.YELLOW);

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                PdfPCell c1 = new PdfPCell(new Phrase("Date", boldPetit));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setPadding(5);
                table.addCell(c1);
                PdfPCell c2 = new PdfPCell(new Phrase("De/Vers", boldPetit));
                c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                c2.setPadding(5);
                table.addCell(c2);
                PdfPCell c3 = new PdfPCell(new Phrase("Description", boldPetit));
                c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                c3.setPadding(5);
                table.addCell(c3);
                PdfPCell c4 = new PdfPCell(new Phrase("Débit", boldPetit));
                c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                c4.setPadding(5);
                table.addCell(c4);
                PdfPCell c5 = new PdfPCell(new Phrase("Crédit", boldPetit));
                c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                c5.setPadding(5);
                table.addCell(c5);

                if (!trans.isEmpty()) {
                    for (int i = 0; i < trans.size(); i++) {
                        c1 = new PdfPCell();
                        c2 = new PdfPCell();
                        c3 = new PdfPCell();
                        c4 = new PdfPCell();
                        c5 = new PdfPCell();
                        Paragraph p1 = new Paragraph(trans.get(i).getDate_transaction());
                        c1.addElement(p1);
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c1.setPadding(5);
                        Paragraph p2 = new Paragraph("De " + trans.get(i).getNomemet() + " Vers " + trans.get(i).getFullname_recepteur(), petit);
                        c2.addElement(p2);
                        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c2.setPadding(5);
                        Paragraph p3 = new Paragraph(trans.get(i).getDescription_transaction());
                        c3.addElement(p3);
                        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c3.setPadding(5);
                        int idEmet = comptCont.rechercherCompte(trans.get(i).getRIB_emetteur()).getFullname_client_id();
                        if (idEmet == Session.getId()) {
                            Paragraph p5 = new Paragraph(String.valueOf(trans.get(i).getMontant_transaction()));
                            Paragraph p4 = new Paragraph(" ");
                            c4.addElement(p4);
                            c5.addElement(p5);
                            c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                            c4.setPadding(5);
                            c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                            c5.setPadding(5);
                        } else {
                            Paragraph p4 = new Paragraph(String.valueOf(trans.get(i).getMontant_transaction()));
                            Paragraph p5 = new Paragraph(" ");
                            c4.addElement(p4);
                            c5.addElement(p5);
                            c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                            c4.setPadding(5);
                            c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                            c5.setPadding(5);
                        }
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(c1);
                        table.addCell(c2);
                        table.addCell(c3);
                        table.addCell(c4);
                        table.addCell(c5);
                    }
                }

                com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("C:\\Users\\msi\\Desktop\\Gitlab\\bankiz\\src\\Images\\logo-Final.png");
                img.scaleAbsolute(100, 60);
                document.add(img);
                document.add(para2);
                document.add(para3);
                document.add(para4);
                document.add(para5);
                document.add(new Paragraph(" "));
                document.add(separator);
                document.add(new Paragraph(" "));
                document.add(para6);
                document.add(para7);
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                document.add(para);
                document.add(para8);
                document.add(new Paragraph(" "));
                document.add(new Paragraph(" "));
                document.add(table);
                document.add(new Paragraph(" "));
                document.add(para9);
                document.close();

                System.out.println("PDF généré !");

                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file_name);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    public void versCommentaireBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Commentaires Forum Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CommentaireBack/CommentaireBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    private void TimeNow(){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDateTime now = LocalDateTime.now();
//        labelDateNow.setText(dtf.format(now));
//        Thread thread = new Thread(()->{
//            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//            while(!stop){
//                try{
//                    Thread.sleep(1000);
//                }catch (Exception e){
//                    System.out.println(e);
//                }
//                final String timeNow = sdf.format(new Date());
//                Platform.runLater(()->{
//                    labelTemps.setText(timeNow);
//                });
//            }
//        });
//        thread.start();
//    }
}
