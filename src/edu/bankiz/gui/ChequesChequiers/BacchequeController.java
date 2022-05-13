/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.gui.ChequesChequiers;

import edu.bankiz.entities.Cheque;
import edu.bankiz.services.ChequeCrud;
import edu.bankiz.services.CompteController;
import edu.bankiz.tools.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class BacchequeController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button btnUtilisateursBack;
    @FXML
    private Button btnReclamationsBack;
    @FXML
    private Button pubBack;
    @FXML
    private Button ComptesBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private Button btnChequiersBack;
    @FXML
    private Button btnCreditsBack;
    @FXML
    private Button btnCartesBack;
    @FXML
    private AnchorPane contenuBack;
    @FXML
    private TableView<Cheque> table;
    @FXML
    private TableColumn<Cheque, String> prop;
    @FXML
    private TableColumn<Cheque, String> montant;
    @FXML
    private TableColumn<Cheque, String> lieu;
    @FXML
    private TableColumn<Cheque, String> date;
    @FXML
    private TableColumn<Cheque, String> dest;
    @FXML
    private TableColumn<Cheque, String> signature;
    @FXML
    private TableColumn<Cheque, String> ghost;
    @FXML
    private Button encaisser;
    @FXML
    private Button supprimer;
    @FXML
    private Button btnChequesBack;
    @FXML
    private Button btnAccueilBack;
    @FXML
    private Button btnLogoutBakc;

    /**
     * Initializes the controller class.
     */
    float flous;
    int emetteur;
    String recepteur;
    int id;
    int myIndex2;
    @FXML
    private Button btnCommentaire;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        refresh();
        table.setRowFactory(tv -> {
            TableRow<Cheque> myRow = new TableRow<>(); // constructeur
            myRow.setOnMouseClicked(event -> {

                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex2 = table.getSelectionModel().getSelectedIndex();

                    flous = (float) table.getItems().get(myIndex2).getMontant();
                    emetteur = table.getItems().get(myIndex2).getProprietaire_id();
                    recepteur = String.valueOf(table.getItems().get(myIndex2).getDestinataire_id());
                    id = table.getItems().get(myIndex2).getId();


                }
            });return myRow;
        });
    }

    @FXML
    private void versUtilisateursBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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
    private void openPublicationBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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
    private void versComptesBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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
    private void versTransactionsBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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
    private void versChequiersBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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
    private void versCreditsBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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
    private void versCartesBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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
    private void encaisser(ActionEvent event) throws Exception {
        System.out.println("Preparing to send email!");
        CompteController sn = new CompteController();
        sn.modificationTransaction(emetteur, recepteur, flous);
        ChequeCrud sp = new ChequeCrud();
        sp.supprimerCheque(id);
        sendmail("nour.boughattas@esprit.tn");
        refresh();

    }
    public static void sendmail (String recepient) throws Exception {
        Properties properties=new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls","true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port","465");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        String myAccountEmail="boughatas.nour@gmail.com";
        String password="nourskyp123";
        Session session= Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });
        Message message=prepareMessage(session, myAccountEmail, recepient);
        Transport.send(message);
        System.out.println ("Message sent successfully!");
    }




    public static Message prepareMessage(Session session, String myAccountEmail, String recepient)
    {

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient (Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Bankiz à votre service");
            message.setText("Votre chèque a été encaissé avec succés!");
            return message;
        }catch(Exception ex){
            Logger.getLogger(BacchequeController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }




    @FXML
    private void supprimer(ActionEvent event) {
        ChequeCrud sp = new ChequeCrud();
        sp.supprimerCheque(id);
        refresh();

    }

    ChequeCrud ch = new ChequeCrud();
    Connection connection = null;
    ObservableList<Cheque> n = FXCollections.observableArrayList();

    public void refresh(){

        connection = MyConnection.getInstance().getCnx();
        n.clear();
        n.addAll(ch.Chequelister());

        prop.setCellValueFactory(new PropertyValueFactory<Cheque,String>("full_name"));
        montant.setCellValueFactory(new PropertyValueFactory<Cheque,String>("montant"));
        lieu.setCellValueFactory(new PropertyValueFactory<Cheque,String>("lieu"));
        date.setCellValueFactory(new PropertyValueFactory<Cheque,String>("date_cheque"));
        dest.setCellValueFactory(new PropertyValueFactory<Cheque,String>("full_named"));
        signature.setCellValueFactory(new PropertyValueFactory<Cheque,String>("signature"));//id+motif AI
        ghost.setCellValueFactory(new PropertyValueFactory<Cheque,String>("id"));
        table.setItems(n);


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
    public void versCommentaireBack(ActionEvent actionEvent) {
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
}
