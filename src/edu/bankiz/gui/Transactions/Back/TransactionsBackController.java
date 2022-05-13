package edu.bankiz.gui.Transactions.Back;

import edu.bankiz.entities.Compte;
import edu.bankiz.entities.Transaction;
import edu.bankiz.services.CompteController;
import edu.bankiz.services.TransactionController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionsBackController implements Initializable {

    @javafx.fxml.FXML
    private TableColumn tabRibRecBack;
    @javafx.fxml.FXML
    private TableColumn tabNomEmBack;
    @javafx.fxml.FXML
    private TableColumn tabDescriptionBack;
    @javafx.fxml.FXML
    private TableColumn tabMontantBack;
    @javafx.fxml.FXML
    private TableView transactions_Back;
    @javafx.fxml.FXML
    private TableColumn tabNomRecBanck;
    @javafx.fxml.FXML
    private TableColumn tabEtatBack;
    @javafx.fxml.FXML
    private TableColumn tabRibEmBack;
    @javafx.fxml.FXML
    private TableColumn tabDateBack;
    @javafx.fxml.FXML
    private TableColumn tabTypeBack;

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection cnx2;
    @FXML
    private Button btnSupprimerTransBack;
    @FXML
    private ComboBox RibEmetteurBack;
    @FXML
    private ComboBox TypeTransBack;
    @FXML
    private TextField DescriptionTransBack;
    @FXML
    private Button btnResetTransBack;
    @FXML
    private TextField NomRecepteurBack;
    @FXML
    private Button btnModiferTransBack;
    @FXML
    private TextField RibRecepteurBack;
    @FXML
    private Button btnAjouterTransBack;
    @FXML
    private TextField MontantTransBack;

    public ObservableList<Transaction> listTrans = FXCollections.observableArrayList();
    public ObservableList<String> comboboxEmetteur = FXCollections.observableArrayList();
    @FXML
    private Button btnAcceuilBack;
    @FXML
    private AnchorPane contenuBack;
    @FXML
    private AnchorPane root;
    @FXML
    private Button ComptesBack;
    @FXML
    private Button pubBack;
    @FXML
    private Button TransactionsBack;
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
    private Label controleEmetteurBack;
    @FXML
    private Label controleNomRecepteurBack;
    @FXML
    private Label controleRibRecepteurBack;
    @FXML
    private Label controleDescriptionBack;
    @FXML
    private Label controleMontantTransBack;
    @FXML
    private Label controleTypeTransBack;
    @FXML
    private Button btnAutreRecepteurTransBack;
    @FXML
    private ComboBox ComboboxRibRecepteurBack;
    @FXML
    private Button btnAnnulerAutreTransBack;
    @FXML
    private Button btnCommentaires;

//    @FXML
//    private Label labelTemps;
//
//    private volatile boolean stop = false;
//    @FXML
//    private Label labelDateNow;

    @FXML
    public void afficherTransactions() {
        TransactionController transCont = new TransactionController();
        List<Transaction> trans = transCont.afficherTransactions();
        if (!trans.isEmpty()) {
            for (int i = 0; i < trans.size(); i++) {
                listTrans.add(trans.get(i));
            }
        }
        addEtatColumn();

        tabRibRecBack.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("RIB_recepteur"));
        tabNomEmBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("nomemet"));
        tabDescriptionBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("description_transaction"));
        tabNomRecBanck.setCellValueFactory(new PropertyValueFactory<Transaction, String>("fullname_recepteur"));
        tabEtatBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("etat_transaction"));
        tabRibEmBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("ribemet"));
        tabDateBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date_transaction"));
        tabTypeBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("type_transaction"));
        tabMontantBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("montant_transaction"));
        transactions_Back.setItems(listTrans);
    }

    private void addEtatColumn() {
        TableColumn<Transaction, Void> colBtn = new TableColumn("Etat Transaction");

        Callback<TableColumn<Transaction, Void>, TableCell<Transaction, Void>> cellFactory = new Callback<TableColumn<Transaction, Void>, TableCell<Transaction, Void>>() {

            public TableCell<Transaction, Void> call(final TableColumn<Transaction, Void> param) {
                final TableCell<Transaction, Void> cell = new TableCell<Transaction, Void>() {
                    ComboBox combEtat = new ComboBox();

                    {
                        combEtat.getItems().add("Aboutie");
                        combEtat.getItems().add("Annulée");
                        combEtat.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                            Transaction data = getTableView().getItems().get(getIndex());
                            if(newValue == "Aboutie"){
                                data.setEtat_transaction(1);
                            }else{
                                data.setEtat_transaction(2);
                            }
                            TransactionController transss = new TransactionController();
                            transss.modifierTransaction(data, data.getId());

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Transaction data = getTableView().getItems().get(getIndex());
                            if(data.getEtat_transaction() == 1){
                                combEtat.setValue("Aboutie");
                            }else if(data.getEtat_transaction() == 2){
                                combEtat.setValue("Annulée");
                            }else if (data.getEtat_transaction() == 0){
                                combEtat.getItems().add("En cours");
                                combEtat.setValue("En cours");
                            }
                            setGraphic(combEtat);

                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        transactions_Back.getColumns().add(colBtn);
    }

    @FXML
    public void fillComboboxTrans() {
        CompteController comptCont = new CompteController();
        List<String> rib_noms = comptCont.afficherNomRib();
        if (!rib_noms.isEmpty()) {
            for (int i = 0; i < rib_noms.size(); i++) {
                comboboxEmetteur.add(rib_noms.get(i));
            }
        }
        RibEmetteurBack.setItems(comboboxEmetteur);
        RibEmetteurBack.getSelectionModel().selectFirst();

        ComboboxRibRecepteurBack.setItems(comboboxEmetteur);
        ComboboxRibRecepteurBack.getSelectionModel().selectFirst();

        TypeTransBack.getSelectionModel().selectFirst();

        controleRibRecepteurBack.setText("* Veuillez choisir un recepteur *");
        controleRibRecepteurBack.setVisible(false);
    }

    @FXML
    void clearFieldsTrans() {
        DescriptionTransBack.clear();
        NomRecepteurBack.clear();
        RibRecepteurBack.clear();
        MontantTransBack.clear();
        RibEmetteurBack.getSelectionModel().selectFirst();
        ComboboxRibRecepteurBack.getSelectionModel().selectFirst();
        TypeTransBack.getSelectionModel().selectFirst();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        afficherTransactions();
        fillComboboxTrans();
        clearFieldsTrans();

        NomRecepteurBack.setStyle(null);
        controleNomRecepteurBack.setVisible(false);
        RibRecepteurBack.setStyle(null);
        controleRibRecepteurBack.setVisible(false);
        TypeTransBack.setStyle(null);
        controleTypeTransBack.setVisible(false);
        RibEmetteurBack.setStyle(null);
        ComboboxRibRecepteurBack.setStyle(null);
        controleEmetteurBack.setVisible(false);
        MontantTransBack.setStyle(null);
        controleMontantTransBack.setVisible(false);
        DescriptionTransBack.setStyle(null);
        controleDescriptionBack.setVisible(false);

        if( ComboboxRibRecepteurBack.isVisible()){
            NomRecepteurBack.setDisable(true);
        }else{
            NomRecepteurBack.setDisable(false);
        }

//        TimeNow();
    }

    @FXML
    public void ajouterTransactionBack(ActionEvent actionEvent) {
        TransactionController transCont = new TransactionController();
        CompteController comptCont = new CompteController();

        if (validateAllTransactions()) {
            String emetteur = RibEmetteurBack.getSelectionModel().getSelectedItem().toString();
            String[] arrOfStr = emetteur.split("-");
            System.out.println(arrOfStr[0]);
            System.out.println(arrOfStr[1]);
            Compte cmptEmetteur = comptCont.rechercherCompteRib(arrOfStr[0]);
//        System.out.println(cmptEmetteur.getId());

            String description = DescriptionTransBack.getText();
            String nomRecepteur = "";
            String ribRecepteur = "";
            if (ComboboxRibRecepteurBack.isVisible()) {
                String recep = ComboboxRibRecepteurBack.getSelectionModel().getSelectedItem().toString();
                String[] arrOfStr1 = recep.split("-");
                System.out.println(arrOfStr1[0]);
                System.out.println(arrOfStr1[1]);
                Compte cmptRecep = comptCont.rechercherCompteRib(arrOfStr1[0]);

                nomRecepteur = cmptRecep.getFullname();
                ribRecepteur = cmptRecep.getRIB_Compte();
            } else {
                nomRecepteur = NomRecepteurBack.getText();
                ribRecepteur = RibRecepteurBack.getText();
            }
            Float montant = Float.valueOf(MontantTransBack.getText());
            String type = TypeTransBack.getSelectionModel().getSelectedItem().toString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion des transactions bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Transaction Bancaire effectuée avec succès !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();

            Transaction trans = new Transaction(cmptEmetteur.getId(), ribRecepteur, montant, dtf.format(now), description, cmptEmetteur.getId(), nomRecepteur, type, 1);
            transCont.ajouterTransaction2(trans);
            comptCont.modificationTransaction(trans.getRIB_emetteur(), trans.getRIB_recepteur(), trans.getMontant_transaction());

            transactions_Back.getItems().clear();
            afficherTransactions();
            clearFieldsTrans();

            NomRecepteurBack.setStyle(null);
            controleNomRecepteurBack.setVisible(false);
            RibRecepteurBack.setStyle(null);
            controleRibRecepteurBack.setVisible(false);
            TypeTransBack.setStyle(null);
            controleTypeTransBack.setVisible(false);
            RibEmetteurBack.setStyle(null);
            ComboboxRibRecepteurBack.setStyle(null);
            ComboboxRibRecepteurBack.setVisible(true);
            controleEmetteurBack.setVisible(false);
            MontantTransBack.setStyle(null);
            controleMontantTransBack.setVisible(false);
            DescriptionTransBack.setStyle(null);
            controleDescriptionBack.setVisible(false);
            if( ComboboxRibRecepteurBack.isVisible()){
                NomRecepteurBack.setDisable(true);
            }else{
                NomRecepteurBack.setDisable(false);
            }

            btnAutreRecepteurTransBack.setVisible(true);
            btnAnnulerAutreTransBack.setVisible(false);
        }
    }

    @FXML
    public void modifierTransactionBack(ActionEvent actionEvent) {
        if(!transactions_Back.getSelectionModel().getSelectedCells().isEmpty()) {

            CompteController comptCont = new CompteController();
            TransactionController transCont = new TransactionController();

            if (validateAllModifierTransactions()) {
                Transaction trans = (Transaction) transactions_Back.getSelectionModel().getSelectedItem();

                String emetteur = RibEmetteurBack.getSelectionModel().getSelectedItem().toString();
                System.out.println(emetteur);
                String[] arrOfStr = emetteur.split("-");
                System.out.println(arrOfStr[0]);
                System.out.println(arrOfStr[1]);
                Compte cmptEmetteur = comptCont.rechercherCompteRib(arrOfStr[0]);

                String description = DescriptionTransBack.getText();
                String nomRecepteur = NomRecepteurBack.getText();
                String ribRecepteur = RibRecepteurBack.getText();
                Float montant = Float.valueOf(MontantTransBack.getText());
                String type = TypeTransBack.getSelectionModel().getSelectedItem().toString();

                float montantancien = trans.getMontant_transaction();

                trans.setDescription_transaction(description);
                trans.setFullname_recepteur(nomRecepteur);
                trans.setRIB_recepteur(ribRecepteur);
                trans.setMontant_transaction(montant);
                trans.setType_transaction(type);

                transCont.modifierTransaction(trans, trans.getId());
                float diff = montant - montantancien;
                comptCont.modificationTransaction(trans.getRIB_emetteur(), trans.getRIB_recepteur(), diff);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Gestion des transactions bancaires");
                alert.setHeaderText(null);
                alert.setContentText("Transaction Bancaire modifiée avec succès !");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("Images/logo-Final.png"));
                alert.showAndWait();

                transactions_Back.getItems().clear();

                afficherTransactions();
                RibEmetteurBack.setDisable(false);
                ComboboxRibRecepteurBack.setDisable(false);
                btnAnnulerAutreTransBack.setDisable(false);
                btnAutreRecepteurTransBack.setDisable(false);
                NomRecepteurBack.setDisable(false);
                RibRecepteurBack.setDisable(false);
                clearFieldsTrans();

                NomRecepteurBack.setStyle(null);
                controleNomRecepteurBack.setVisible(false);
                RibRecepteurBack.setStyle(null);
                controleRibRecepteurBack.setVisible(false);
                TypeTransBack.setStyle(null);
                controleTypeTransBack.setVisible(false);
                RibEmetteurBack.setStyle(null);
                ComboboxRibRecepteurBack.setStyle(null);
                ComboboxRibRecepteurBack.setVisible(true);
                controleEmetteurBack.setVisible(false);
                MontantTransBack.setStyle(null);
                controleMontantTransBack.setVisible(false);
                DescriptionTransBack.setStyle(null);
                controleDescriptionBack.setVisible(false);
                if (ComboboxRibRecepteurBack.isVisible()) {
                    NomRecepteurBack.setDisable(true);
                } else {
                    NomRecepteurBack.setDisable(false);
                }

                btnAutreRecepteurTransBack.setVisible(true);
                btnAnnulerAutreTransBack.setVisible(false);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion des transactions bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner la transaction à modifier !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();
        }
    }

    @FXML
    public void supprimerTransactionBack(ActionEvent actionEvent) {
        if(!transactions_Back.getSelectionModel().getSelectedCells().isEmpty()){
            TransactionController trans = new TransactionController();


            TablePosition pos = (TablePosition) transactions_Back.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            String selected = transactions_Back.getItems().get(index).toString();
            selected = selected.substring(12, selected.indexOf(" : "));
            Transaction tr = trans.rechercherTransaction(Integer.parseInt(selected));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Gestion des transactions bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous supprimer la transaction effectuée par " + tr.getNomemet() + " pour " + tr.getFullname_recepteur() + " ?");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
            ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);
            String finalSelected = selected;
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    trans.supprimerTransaction(Integer.parseInt(finalSelected));
                    transactions_Back.getItems().removeAll(transactions_Back.getSelectionModel().getSelectedItem());

                    RibEmetteurBack.setDisable(false);
                    ComboboxRibRecepteurBack.setDisable(false);
                    btnAnnulerAutreTransBack.setDisable(false);
                    btnAutreRecepteurTransBack.setDisable(false);
                    NomRecepteurBack.setDisable(false);
                    RibRecepteurBack.setDisable(false);
                    ComboboxRibRecepteurBack.setVisible(true);
                    if( ComboboxRibRecepteurBack.isVisible()){
                        NomRecepteurBack.setDisable(true);
                    }else{
                        NomRecepteurBack.setDisable(false);
                    }
                }
            });
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion des transactions bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner la transaction à supprimer !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();
        }

    }

    @FXML
    public void resetTransactionBack(ActionEvent actionEvent) {
        transactions_Back.getItems().clear();
        RibEmetteurBack.getItems().clear();
        ComboboxRibRecepteurBack.getItems().clear();
        RibEmetteurBack.setDisable(false);

        clearFieldsTrans();
        fillComboboxTrans();
        afficherTransactions();

        NomRecepteurBack.setStyle(null);
        controleNomRecepteurBack.setVisible(false);
        RibRecepteurBack.setStyle(null);
        controleRibRecepteurBack.setVisible(false);
        TypeTransBack.setStyle(null);
        controleTypeTransBack.setVisible(false);
        RibEmetteurBack.setStyle(null);
        ComboboxRibRecepteurBack.setStyle(null);
        ComboboxRibRecepteurBack.setVisible(true);
        controleEmetteurBack.setVisible(false);
        MontantTransBack.setStyle(null);
        controleMontantTransBack.setVisible(false);
        DescriptionTransBack.setStyle(null);
        controleDescriptionBack.setVisible(false);

        RibEmetteurBack.setDisable(false);
        ComboboxRibRecepteurBack.setDisable(false);
        btnAnnulerAutreTransBack.setDisable(false);
        btnAutreRecepteurTransBack.setDisable(false);
        NomRecepteurBack.setDisable(false);
        RibRecepteurBack.setDisable(false);

        if( ComboboxRibRecepteurBack.isVisible()){
            NomRecepteurBack.setDisable(true);
        }else{
            NomRecepteurBack.setDisable(false);
        }

        btnAutreRecepteurTransBack.setVisible(true);
        btnAnnulerAutreTransBack.setVisible(false);
    }

    @FXML
    public void doubleClickTransBack(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            RibEmetteurBack.setDisable(true);
            ComboboxRibRecepteurBack.setDisable(true);
            ComboboxRibRecepteurBack.setVisible(false);
            btnAnnulerAutreTransBack.setDisable(true);
            btnAutreRecepteurTransBack.setDisable(true);
            NomRecepteurBack.setDisable(true);
            RibRecepteurBack.setDisable(true);

//            Recherche et affectation dans les Textfield
            CompteController comptCont = new CompteController();
            TransactionController transCont = new TransactionController();
            int x = 0;

            Transaction trans = (Transaction) transactions_Back.getSelectionModel().getSelectedItem();
//            System.out.println(trans);
            List<String> rib_noms = comptCont.afficherNomRib();
            if (!rib_noms.isEmpty()) {
                for (int i = 0; i < rib_noms.size(); i++) {
                    if (rib_noms.get(i).equals(transCont.afficherMonNomRib(trans.getId()))) {
                        x = i;
                    }
                }
            }
            RibEmetteurBack.getSelectionModel().select(x);
            ComboboxRibRecepteurBack.getSelectionModel().select(x);

            DescriptionTransBack.setText(String.valueOf(trans.getDescription_transaction()));
            NomRecepteurBack.setText(String.valueOf(trans.getFullname_recepteur()));
            RibRecepteurBack.setText(String.valueOf(trans.getRIB_recepteur()));
            MontantTransBack.setText(String.valueOf(trans.getMontant_transaction()));
            if (trans.getType_transaction().toLowerCase().equals("virement")) {
                TypeTransBack.getSelectionModel().selectFirst();
            } else if (trans.getType_transaction().toLowerCase().equals("encaissement")) {
                TypeTransBack.getSelectionModel().select(1);
            } else if (trans.getType_transaction().toLowerCase().equals("investissement")) {
                TypeTransBack.getSelectionModel().selectLast();
            }
        }
    }

    @FXML
    public void versComptesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Comptes Bancaires");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Comptes/Back/ComptesBack.fxml"));

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
    private boolean validateRibRecepteur() {
        if (ComboboxRibRecepteurBack.isVisible()) {
            if (ComboboxRibRecepteurBack.getSelectionModel().getSelectedIndex() != -1) {
                return true;
            }else{
                return false;
            }
        } else {
            if (!RibRecepteurBack.getText().isEmpty()) {
                if (RibRecepteurBack.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][ ][0-9]" +
                        "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][ ][0-9][0-9]")) {
                    return true;
                } else {
                    return false;
                }
            }else{
                return false;
            }
        }
    }


    private boolean validateNomRecepteur() {
        if (!NomRecepteurBack.getText().isEmpty()) {
            if (NomRecepteurBack.getText().matches("[a-zA-Z]+[ ][a-zA-Z]+")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validateMontantTransaction() {
        CompteController comptCont = new CompteController();
        String emetteur = RibEmetteurBack.getSelectionModel().getSelectedItem().toString();
        String[] arrOfStr = emetteur.split("-");
        System.out.println(arrOfStr[0]);
        System.out.println(arrOfStr[1]);
        Compte cmptEmetteur = comptCont.rechercherCompteRib(arrOfStr[0]);
        if (!MontantTransBack.getText().isEmpty()) {
            if (MontantTransBack.getText().matches("[0-9]+[.,][0-9]*") || MontantTransBack.getText().matches("[0-9]*")) {
                if (Float.valueOf(MontantTransBack.getText().toString()) > 0 && Float.valueOf(MontantTransBack.getText()) < cmptEmetteur.getSolde_compte()) {
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

    private boolean validateDescription() {
        if (!DescriptionTransBack.getText().isEmpty()) {
            if (DescriptionTransBack.getText().matches("[a-zA-Z ]*")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validateTypeTransactions() {
        if (TypeTransBack.getSelectionModel().getSelectedIndex() != -1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateRibEmetteur() {
        if (RibEmetteurBack.getSelectionModel().getSelectedIndex() != -1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateAllTransactions() {
        if (validateDescription() && validateMontantTransaction() && validateTypeTransactions() && validateRibEmetteur() && validateRibRecepteur() && validateNomRecepteur()) {
            NomRecepteurBack.setStyle(null);
            controleNomRecepteurBack.setVisible(false);
            RibRecepteurBack.setStyle(null);
            controleRibRecepteurBack.setVisible(false);
            TypeTransBack.setStyle(null);
            controleTypeTransBack.setVisible(false);
            RibEmetteurBack.setStyle(null);
            ComboboxRibRecepteurBack.setStyle(null);
            controleEmetteurBack.setVisible(false);
            MontantTransBack.setStyle(null);
            controleMontantTransBack.setVisible(false);
            DescriptionTransBack.setStyle(null);
            controleDescriptionBack.setVisible(false);

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur ");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs correctement !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();

            if (!validateNomRecepteur()) {
                NomRecepteurBack.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleNomRecepteurBack.setVisible(true);
            } else {
                NomRecepteurBack.setStyle(null);
                controleNomRecepteurBack.setVisible(false);
            }
            if (!validateRibRecepteur()) {
                RibRecepteurBack.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                ComboboxRibRecepteurBack.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleRibRecepteurBack.setVisible(true);
            } else {
                RibRecepteurBack.setStyle(null);
                ComboboxRibRecepteurBack.setStyle(null);
                controleRibRecepteurBack.setVisible(false);
            }
            if (!validateTypeTransactions()) {
                TypeTransBack.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleTypeTransBack.setVisible(true);
            } else {
                TypeTransBack.setStyle(null);
                controleTypeTransBack.setVisible(false);
            }
            if (!validateRibEmetteur()) {
                RibEmetteurBack.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleEmetteurBack.setVisible(true);
            } else {
                RibEmetteurBack.setStyle(null);
                controleEmetteurBack.setVisible(false);
            }
            if (!validateMontantTransaction()) {
                MontantTransBack.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleMontantTransBack.setVisible(true);
            } else {
                MontantTransBack.setStyle(null);
                controleMontantTransBack.setVisible(false);
            }
            if (!validateDescription()) {
                DescriptionTransBack.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleDescriptionBack.setVisible(true);
            } else {
                DescriptionTransBack.setStyle(null);
                controleDescriptionBack.setVisible(false);
            }
            return false;
        }
    }

    private boolean validateAllModifierTransactions() {
        if (validateDescription() && validateMontantTransaction() && validateTypeTransactions()) {
            NomRecepteurBack.setStyle(null);
            controleNomRecepteurBack.setVisible(false);
            RibRecepteurBack.setStyle(null);
            controleRibRecepteurBack.setVisible(false);
            TypeTransBack.setStyle(null);
            controleTypeTransBack.setVisible(false);
            RibEmetteurBack.setStyle(null);
            ComboboxRibRecepteurBack.setStyle(null);
            controleEmetteurBack.setVisible(false);
            MontantTransBack.setStyle(null);
            controleMontantTransBack.setVisible(false);
            DescriptionTransBack.setStyle(null);
            controleDescriptionBack.setVisible(false);

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur ");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs correctement !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();

            if (!validateTypeTransactions()) {
                TypeTransBack.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleTypeTransBack.setVisible(true);
            } else {
                TypeTransBack.setStyle(null);
                controleTypeTransBack.setVisible(false);
            }
            if (!validateMontantTransaction()) {
                MontantTransBack.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleMontantTransBack.setVisible(true);
            } else {
                MontantTransBack.setStyle(null);
                controleMontantTransBack.setVisible(false);
            }
            if (!validateDescription()) {
                DescriptionTransBack.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleDescriptionBack.setVisible(true);
            } else {
                DescriptionTransBack.setStyle(null);
                controleDescriptionBack.setVisible(false);
            }
            return false;
        }
    }

    //    ************* Autre *************************
    @FXML
    public void autreRecepteurTransBack(ActionEvent actionEvent) {
        ComboboxRibRecepteurBack.setVisible(false);
        btnAnnulerAutreTransBack.setVisible(true);
        if(controleDescriptionBack.isVisible() || controleEmetteurBack.isVisible() || controleTypeTransBack.isVisible() || controleNomRecepteurBack.isVisible()){
            controleRibRecepteurBack.setText("* Le RIB doit être sous la forme :                                          XXXXXXXXX XXXXXXXXXXX XX *");
            controleRibRecepteurBack.setVisible(true);
            RibRecepteurBack.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
        }
        NomRecepteurBack.setDisable(false);
    }

    @FXML
    public void AnnulerAutreRecepteurTransBack(ActionEvent actionEvent) {
        btnAnnulerAutreTransBack.setVisible(false);
        ComboboxRibRecepteurBack.setVisible(true);
        controleRibRecepteurBack.setText("* Veuillez choisir un recepteur *");
        controleRibRecepteurBack.setVisible(false);
        NomRecepteurBack.setDisable(true);
    }

    @FXML
    public void OnComboboxRibRecep(ActionEvent actionEvent) {
        CompteController comptCont = new CompteController();

        String recep = ComboboxRibRecepteurBack.getSelectionModel().getSelectedItem().toString();
        String[] arrOfStr1 = recep.split("-");
        System.out.println(arrOfStr1[0]);
        System.out.println(arrOfStr1[1]);
        Compte cmptRecep = comptCont.rechercherCompteRib(arrOfStr1[0]);

        NomRecepteurBack.setText(cmptRecep.getFullname());
    }

    @FXML
    public void OnComboboxRibEmet(ActionEvent actionEvent) {
        CompteController comptCont = new CompteController();

        String emet = RibEmetteurBack.getSelectionModel().getSelectedItem().toString();
        String[] arrOfStr1 = emet.split("-");
        System.out.println(arrOfStr1[0]);
        System.out.println(arrOfStr1[1]);
        Compte cmptEmet = comptCont.rechercherCompteRib(arrOfStr1[0]);

        MontantTransBack.setPromptText("Montant < "+cmptEmet.getSolde_compte());

    }

    @FXML
    public void versCommentairesBack(ActionEvent actionEvent) {
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
