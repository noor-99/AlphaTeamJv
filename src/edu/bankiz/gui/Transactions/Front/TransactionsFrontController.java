package edu.bankiz.gui.Transactions.Front;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import edu.bankiz.entities.Compte;
import edu.bankiz.entities.Session;
import edu.bankiz.entities.Transaction;
import edu.bankiz.services.CompteController;
import edu.bankiz.services.TransactionController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.krb5.internal.crypto.Des;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class TransactionsFrontController implements Initializable {
    @javafx.fxml.FXML
    private Button btnCartesFront;
    @javafx.fxml.FXML
    private Button btnCreditFront;
    @javafx.fxml.FXML
    private Button btnProfileFront;
    @javafx.fxml.FXML
    private Button btnAccueilFront;
    @javafx.fxml.FXML
    private Button btnTransFront;
    @javafx.fxml.FXML
    private Button btnForumFront;
    @javafx.fxml.FXML
    private Button btnCompteFront;
    @javafx.fxml.FXML
    private Button btnLogoutFront;
    @javafx.fxml.FXML
    private Button btnChequesFront;
    @javafx.fxml.FXML
    private Button btnAjouterTransCompteFront;
    @javafx.fxml.FXML
    private TextArea DescriptionTransCompteFront;
    @javafx.fxml.FXML
    private TextField MontantTransCompteFront;
    @javafx.fxml.FXML
    private TextField TransCompteRribReceptFront;
    @javafx.fxml.FXML
    private ComboBox TypeTransCompteFront;
    @FXML
    private TextField TransCompteNomRecepFront;
    @FXML
    private Label controleDescription;
    @FXML
    private Label controleTypeTransFront;
    @FXML
    private Label controleMontantTransFront;
    @FXML
    private Label controleRibRecepteurFront1;
    @FXML
    private Label controleRibRecepteurFront2;
    @FXML
    private Label controleNomRecepteurFront;
    @FXML
    private Button btnDownloadQr;
    @FXML
    private AnchorPane AnchorPaneTransFront;
    @FXML
    private Button btnCancelQrCode;

//    @FXML
//    private Label labelTemps;
//
//    private volatile boolean stop = false;
//    @FXML
//    private Label labelDateNow;

    @FXML
    void clearFieldsTransFront() {
        TransCompteNomRecepFront.clear();
        DescriptionTransCompteFront.clear();
        MontantTransCompteFront.clear();
        TransCompteRribReceptFront.clear();
        TypeTransCompteFront.getSelectionModel().selectFirst();
    }

    @FXML
    public void fillComboboxTransFront() {
        TypeTransCompteFront.getSelectionModel().selectFirst();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        fillComboboxTransFront();
        clearFieldsTransFront();

        TransCompteRribReceptFront.setStyle(null);
        controleRibRecepteurFront1.setVisible(false);
        controleRibRecepteurFront2.setVisible(false);
        TransCompteNomRecepFront.setStyle(null);
        controleNomRecepteurFront.setVisible(false);
        MontantTransCompteFront.setStyle(null);
        controleMontantTransFront.setVisible(false);
        DescriptionTransCompteFront.setStyle(null);
        controleDescription.setVisible(false);
        TypeTransCompteFront.setStyle(null);
        controleTypeTransFront.setVisible(false);

        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        MontantTransCompteFront.setPromptText("Montant < " + cmpt.getSolde_compte());

//        TimeNow();
    }

    @javafx.fxml.FXML
    public void redirectCartesFront(ActionEvent actionEvent) {
        try {
//            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Calendrier");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CartesCategories/Calendar.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            stage.setTitle("Passer Reclamations");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Reclamations/AjouterRecFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void ValiderTransactionCompteFront(ActionEvent actionEvent) {
        TransactionController transCont = new TransactionController();
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());

        if (validateAllTransactions()) {

            String description = DescriptionTransCompteFront.getText();
            String nomRecepteur = TransCompteNomRecepFront.getText();
            String ribRecepteur = TransCompteRribReceptFront.getText();
            Float montant = Float.valueOf(MontantTransCompteFront.getText());
            String type = TypeTransCompteFront.getSelectionModel().getSelectedItem().toString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Transaction Bancaire Via Compte");
            alert.setHeaderText(null);
            alert.setContentText("Transaction Bancaire effectuée avec succès !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Images/logo-Final.png"));
            alert.showAndWait();

            Transaction trans = new Transaction(cmpt.getId(), ribRecepteur, montant, dtf.format(now), description, cmpt.getId(), nomRecepteur, type, 1);
            transCont.ajouterTransaction2(trans);
            comptCont.modificationTransaction(trans.getRIB_emetteur(), trans.getRIB_recepteur(), trans.getMontant_transaction());

            clearFieldsTransFront();

            TransCompteRribReceptFront.setStyle(null);
            controleRibRecepteurFront1.setVisible(false);
            controleRibRecepteurFront2.setVisible(false);
            TransCompteNomRecepFront.setStyle(null);
            controleNomRecepteurFront.setVisible(false);
            MontantTransCompteFront.setStyle(null);
            controleMontantTransFront.setVisible(false);
            DescriptionTransCompteFront.setStyle(null);
            controleDescription.setVisible(false);
            TypeTransCompteFront.setStyle(null);
            controleTypeTransFront.setVisible(false);

//        ******** Redirection Vers Mon Compte *********
            try {
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                Stage stage2 = new Stage();
                stage.setTitle("Mon Compte Bancaire");
                Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Comptes/Front/MonCompteBancaire.fxml"));

                Scene scene = new Scene(root);
                Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
                stage2.getIcons().add(icon);
                stage2.setScene(scene);
                stage2.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @javafx.fxml.FXML
    public void redirectAccueilFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("E-Bank - Bankiz");
            stage.initStyle(StageStyle.TRANSPARENT);
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/HomeFront/HomeFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../../../Images/logo-Final.png"));
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
        if (!TransCompteRribReceptFront.getText().isEmpty()) {
            if (TransCompteRribReceptFront.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][ ][0-9]" +
                    "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][ ][0-9][0-9]")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validateNomRecepteur() {
        if (!TransCompteNomRecepFront.getText().isEmpty()) {
            if (TransCompteNomRecepFront.getText().matches("[a-zA-Z]+[ ][a-zA-Z]+")) {
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
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        if (!MontantTransCompteFront.getText().isEmpty()) {
            if (MontantTransCompteFront.getText().matches("[0-9]+[.,][0-9]*") || MontantTransCompteFront.getText().matches("[0-9]*")) {
                if (Float.valueOf(MontantTransCompteFront.getText().toString()) > 0 && Float.valueOf(MontantTransCompteFront.getText().toString()) < cmpt.getSolde_compte()) {
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
        if (!DescriptionTransCompteFront.getText().isEmpty()) {
            if (DescriptionTransCompteFront.getText().matches("[a-zA-Z ]*")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validateTypeTransactions() {
        if (TypeTransCompteFront.getSelectionModel().getSelectedIndex() != -1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateAllTransactions() {
        if (validateDescription() && validateMontantTransaction() && validateTypeTransactions() && validateRibRecepteur() && validateNomRecepteur()) {
            TransCompteRribReceptFront.setStyle(null);
            controleRibRecepteurFront1.setVisible(false);
            controleRibRecepteurFront2.setVisible(false);
            TransCompteNomRecepFront.setStyle(null);
            controleNomRecepteurFront.setVisible(false);
            MontantTransCompteFront.setStyle(null);
            controleMontantTransFront.setVisible(false);
            DescriptionTransCompteFront.setStyle(null);
            controleDescription.setVisible(false);
            TypeTransCompteFront.setStyle(null);
            controleTypeTransFront.setVisible(false);

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
                TransCompteNomRecepFront.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleNomRecepteurFront.setVisible(true);
            } else {
                TransCompteNomRecepFront.setStyle(null);
                controleNomRecepteurFront.setVisible(false);
            }
            if (!validateRibRecepteur()) {
                TransCompteRribReceptFront.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleRibRecepteurFront1.setVisible(true);
                controleRibRecepteurFront2.setVisible(true);
            } else {
                TransCompteRribReceptFront.setStyle(null);
                controleRibRecepteurFront1.setVisible(false);
                controleRibRecepteurFront2.setVisible(false);
            }
            if (!validateTypeTransactions()) {
                TypeTransCompteFront.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleTypeTransFront.setVisible(true);
            } else {
                TypeTransCompteFront.setStyle(null);
                controleTypeTransFront.setVisible(false);
            }
            if (!validateMontantTransaction()) {
                MontantTransCompteFront.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleMontantTransFront.setVisible(true);
            } else {
                MontantTransCompteFront.setStyle(null);
                controleMontantTransFront.setVisible(false);
            }
            if (!validateDescription()) {
                DescriptionTransCompteFront.setStyle(" -fx-text-box-border: #B22222; -fx-focus-color: #B22222; -fx-border-width : 5");
                controleDescription.setVisible(true);
            } else {
                DescriptionTransCompteFront.setStyle(null);
                controleDescription.setVisible(false);
            }
            return false;
        }
    }

//    ********************* Autre *******************

    @FXML
    public void downloadQrCode(ActionEvent actionEvent) {
        CompteController comptCont = new CompteController();

        String path = "C:\\Users\\msi\\Desktop\\Gitlab\\bankiz\\src\\QrCodes\\";
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("QrCode du récepteur");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        fileChooser.setInitialDirectory(new File(path));

        Stage stage = (Stage) AnchorPaneTransFront.getScene().getWindow();

        File qrCode = fileChooser.showOpenDialog(stage);
        if (qrCode != null) {
            String pathQr = qrCode.getPath();

            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel >();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            String decryptQr = readQRCode(pathQr, charset, hintMap);

            String nom = comptCont.rechercherCompteRib(decryptQr).getFullname();
            StringBuilder ribFinal = new StringBuilder();
            int i = 0;
            for(char c:decryptQr.toCharArray()){
                if(i==9 || i ==20){
                    ribFinal.append(" ");
                    ribFinal.append(c);
                }else{
                    ribFinal.append(c);
                }
                i ++;
            }
            TransCompteRribReceptFront.setText(ribFinal.toString().trim());
            TransCompteRribReceptFront.setDisable(true);

            TransCompteNomRecepFront.setText(nom);
            TransCompteNomRecepFront.setDisable(true);

            btnCancelQrCode.setVisible(true);
        }
    }

    public static String readQRCode(String filePath, String charset, Map hintMap) {
        Result qrCodeResult =null;
        try {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(new FileInputStream(filePath)))));
            qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrCodeResult.getText();
    }

    @FXML
    public void cancelQrCode(ActionEvent actionEvent) {
        TransCompteRribReceptFront.clear();
        TransCompteRribReceptFront.setDisable(false);

        TransCompteNomRecepFront.clear();
        TransCompteNomRecepFront.setDisable(false);

        btnCancelQrCode.setVisible(false);
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
