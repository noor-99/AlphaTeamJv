package edu.bankiz.gui.Comptes.Front;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.sun.org.apache.xml.internal.security.Init;
import edu.bankiz.entities.Compte;
import edu.bankiz.entities.Session;
import edu.bankiz.entities.Transaction;
import edu.bankiz.services.CompteController;
import edu.bankiz.services.TransactionController;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class MonCompteBancaireController implements Initializable {
    @javafx.fxml.FXML
    private TableView tableTransactionsFront;
    @javafx.fxml.FXML
    private Button btnReleveFront;
    @javafx.fxml.FXML
    private Label soldeFront;
    @javafx.fxml.FXML
    private Button btnDesactiverCompteFront;
    @javafx.fxml.FXML
    private Button btnInformationCompte;
    @FXML
    private TableColumn tabDateTransFront;
    @FXML
    private TableColumn tabRecepteurFront;
    @FXML
    private TableColumn tabMontantFront;
    @FXML
    private TableColumn tabEtatTransFront;
    @FXML
    private TableColumn tabEmetteurFront;
    @FXML
    private TableColumn tabDescriptionFront;
    @FXML
    private Button btnCreditFront;
    @FXML
    private Button btnCartesFront;
    @FXML
    private Button btnProfileFront;
    @FXML
    private Button btnAccueilFront;
    @FXML
    private Button btnTransFront;
    @FXML
    private Button btnForumFront;
    @FXML
    private Button btnLogoutFront;
    @FXML
    private Button btnChequesFront;

    public ObservableList<Transaction> listTransFront = FXCollections.observableArrayList();
    @FXML
    private ImageView QrCode;

    @FXML
    public void afficherTransactionsFront() {
        TransactionController transCont = new TransactionController();
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        List<Transaction> trans = transCont.afficherMesTransactions(cmpt.getId());
        List<Transaction> transRecepteur = transCont.afficherMesTransactionsRecepteur(cmpt.getRIB_Compte());
        if (!trans.isEmpty()) {
            for (int i = 0; i < trans.size(); i++) {
                listTransFront.add(trans.get(i));
            }
        }
        if (!transRecepteur.isEmpty()) {
            for (int i = 0; i < transRecepteur.size(); i++) {
                listTransFront.add(transRecepteur.get(i));
            }
        }

        tabDateTransFront.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("date_transaction"));
        tabRecepteurFront.setCellValueFactory(new PropertyValueFactory<Transaction, String>("fullname_recepteur"));
        tabMontantFront.setCellValueFactory(new PropertyValueFactory<Transaction, String>("montant_transaction"));
        tabEtatTransFront.setCellValueFactory(new PropertyValueFactory<Transaction, String>("etat_transaction"));
        tabEmetteurFront.setCellValueFactory(new PropertyValueFactory<Transaction, String>("nomemet"));
        tabDescriptionFront.setCellValueFactory(new PropertyValueFactory<Transaction, String>("description_transaction"));
        tableTransactionsFront.setItems(listTransFront.sorted());
    }

    @FXML
    public void afficherSolde() {
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        soldeFront.setText(String.valueOf(cmpt.getSolde_compte()));
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        afficherSolde();
        afficherTransactionsFront();
        GenererQrCode();
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        if(cmpt.getEtat_compte() == 1){
            btnDesactiverCompteFront.setText("Désactiver mon compte");
        }else if (cmpt.getEtat_compte() == 2){
            btnDesactiverCompteFront.setText("Activer mon compte");
        }
    }

    @javafx.fxml.FXML
    public void ChargerInformationsCompte(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Mon Compte Bancaire");
            stage.initStyle(StageStyle.TRANSPARENT);
            Parent root = FXMLLoader.load(getClass().getResource("InformationsCompte.fxml"));

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
    public void GenererReleveCompteFront(ActionEvent actionEvent) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        TransactionController transCont = new TransactionController();
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        List<Transaction> trans = transCont.afficherMesTransactionsReleve(cmpt.getId());

        try {
            String file_name = "C:\\Users\\msi\\Desktop\\Gitlab\\bankiz\\src\\RelevesDeCompte\\ReleveDeCompte_" + Session.getUsername() + " " + Session.getPrenom() + "_" + dtf.format(now) + ".pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));

            document.open();

            Font boldPetit = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font boldMoyen = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font boldGrand = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Font petit = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

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
                    Paragraph p2 = new Paragraph("De " + trans.get(i).getNomemet() + " Vers " +trans.get(i).getFullname_recepteur(), petit);
                    c2.addElement(p2);
                    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    c2.setPadding(5);
                    Paragraph p3 = new Paragraph(trans.get(i).getDescription_transaction());
                    c3.addElement(p3);
                    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    c3.setPadding(5);
                    int idEmet = comptCont.rechercherCompte(trans.get(i).getRIB_emetteur()).getFullname_client_id();
                    if (idEmet == Session.getId()){
                        Paragraph p5 = new Paragraph(String.valueOf(trans.get(i).getMontant_transaction()));
                        Paragraph p4 = new Paragraph(" ");
                        c4.addElement(p4);
                        c5.addElement(p5);
                        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c4.setPadding(5);
                        c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c5.setPadding(5);
                    }else{
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

    @javafx.fxml.FXML
    public void DesactiverCompteFront(ActionEvent actionEvent) {
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        if(cmpt.getEtat_compte() == 1){
            CompteController.DesactiverCompte(cmpt.getId());
            btnDesactiverCompteFront.setText("Activer mon compte");
        }else if (cmpt.getEtat_compte() == 2){
            CompteController.ActiverCompte(cmpt.getId());
            btnDesactiverCompteFront.setText("Désactiver mon compte");
        }
    }

    @FXML
    public void RedirectAccueilFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("E-Bank - Bankiz");
//            stage.initStyle(StageStyle.TRANSPARENT);
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

    @FXML
    public void redirectTransactionsFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Effectuer une Transaction Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Transactions/Front/TransactionsFront.fxml"));

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

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
    public void redirectForumFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Forum Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/PublicationFront/PublicationFront.fxml"));

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

    @FXML
    public void GenererQrCode(){
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        String path = "C:\\Users\\msi\\Desktop\\Gitlab\\bankiz\\src\\QrCodes\\QrCode_"+cmpt.getFullname()+".png";

        try {
            BitMatrix matrix = new MultiFormatWriter().encode(cmpt.getRIB_Compte(), BarcodeFormat.QR_CODE, 500, 500 );
            MatrixToImageWriter.writeToFile(matrix, "png", Paths.get(path).toFile());
            InputStream stream = new FileInputStream(path);
            Image image = new Image(stream);
            QrCode.setPreserveRatio(true);
            QrCode.setFitWidth(200);
            QrCode.setFitHeight(200);
            QrCode.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
