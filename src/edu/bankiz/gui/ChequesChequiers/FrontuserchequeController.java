/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.gui.ChequesChequiers;

import edu.bankiz.entities.Cheque;
import edu.bankiz.entities.Chequier;
import edu.bankiz.entities.Compte;
import edu.bankiz.entities.Session;
import edu.bankiz.services.ChequeCrud;
import edu.bankiz.services.ChequierCrud;
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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class FrontuserchequeController implements Initializable {


    @FXML
    private TableView<Cheque> table;

    @FXML
    private Button fairecheque;


    private Button modifiercheque;
    private Button supprimercheque;


    @FXML
    private Label nbr2;
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

    /**
     * Initializes the controller class.
     */
    Connection connection = null;
    ObservableList<Cheque> n = FXCollections.observableArrayList();
    ObservableList<Chequier> b = FXCollections.observableArrayList();
    @FXML
    private TableView<Chequier> table2;
    @FXML
    private TableColumn<Chequier, String> motiftable2;
    @FXML
    private TableColumn<Chequier, String> etattable2;

    int chequierid;
    int chequeid;
    int myIndex;
    int myIndex2;
    int etat;
    CompteController comptCont = new CompteController();
    Compte cmpt = comptCont.afficherMonCompte(Session.getId());
    int idcompte = cmpt.getId();
    @FXML
    private TableColumn<Cheque, String> ghost;
    @FXML
    private Button confirmercheque;
    @FXML
    private TextField l3;
    @FXML
    private TextField l1;
    @FXML
    private DatePicker l4;
    @FXML
    private TextField l2;
    @FXML
    private TextField l5;
    @FXML
    private TextField l6;
    @FXML
    private Pane frm;
    @FXML
    private Label s;
    @FXML
    private Label d;
    @FXML
    private Label de;
    @FXML
    private Label l;
    @FXML
    private Label m;
    @FXML
    private Label p;
    @FXML
    private Button X;
    @FXML
    private Pane tab;
    @FXML
    private TextField chp;
    @FXML
    private Button pc;
    @FXML
    private Button btnccl;

    @FXML
    private Button demanderch;
    @FXML
    private TextField chp2;
    @FXML
    private AnchorPane anc1;
    @FXML
    private AnchorPane anc2;
    @FXML
    private Label chequiersAcc1;
    @FXML
    private TextField search1;
    @FXML
    private AnchorPane anc3;
    @FXML
    private Button btnAccueilFront;
    @FXML
    private Button btnCompteFront;
    @FXML
    private Button btnTransFront;
    @FXML
    private Button btnForumFront;
    @FXML
    private Button btnCreditFront;
    @FXML
    private Button btnCartesFront;
    @FXML
    private Button btnChequesFront;
    @FXML
    private Button btnLogoutFront;
    @FXML
    private Button btnReclamationsFront;
    @FXML
    private Button devise;
    @FXML
    private Label money;

    double montantd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh(idcompte); //id session ouverte
        System.out.println("dgfhjgdfjhg");
        table2.setRowFactory(tv -> {
            TableRow<Chequier> myRow = new TableRow<>(); // constructeur
            myRow.setOnMouseClicked(event -> {

                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {

                    myIndex = table2.getSelectionModel().getSelectedIndex();

                    etat = table2.getItems().get(myIndex).getEtat_chequier();
                    int idd = table2.getItems().get(myIndex).getId();

                    if (etat == 1 && ch.verifnbcheque(idd)) {

                        refresh2(table2.getItems().get(myIndex).getMotif_chequier());
                        System.out.println(String.valueOf(table2.getItems().get(myIndex).getMotif_chequier()));
                        fairecheque.setDisable(false);

                    } else {
                        fairecheque.setDisable(true);

                        n.clear();
                        table.setItems(n);
                    } }
            });
            return myRow;
        });

        table.setRowFactory(tv -> {
            TableRow<Cheque> myRow = new TableRow<>(); // constructeur
            myRow.setOnMouseClicked(event -> {

                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex2 = table.getSelectionModel().getSelectedIndex();
                    chequeid = table2.getItems().get(myIndex).getId();
                    montantd = table.getItems().get(myIndex).getMontant();
                }
            }); return myRow;
        });
        chp2.setText(Session.getUsername() + " " + Session.getPrenom());
        chp2.setDisable(true);

        l1.setText(Session.getUsername() + " " + Session.getPrenom());
        l1.setDisable(true);
    }

    ChequeCrud ch = new ChequeCrud();

    public void refresh2(String motif) {

        connection = MyConnection.getInstance().getCnx();
        chequierid = chh.motifToId(motif);
        n.clear();
        nbr2.setText(""+ch.listerCheq(chequierid).size());
        n.addAll(ch.listerCheq(chequierid)); //ch cheque, chh chequier
        prop.setCellValueFactory(new PropertyValueFactory<Cheque,String>("full_name"));
        montant.setCellValueFactory(new PropertyValueFactory<Cheque,String>("montant"));
        lieu.setCellValueFactory(new PropertyValueFactory<Cheque,String>("lieu"));
        date.setCellValueFactory(new PropertyValueFactory<Cheque,String>("date_cheque"));
        dest.setCellValueFactory(new PropertyValueFactory<Cheque,String>("full_named"));
        signature.setCellValueFactory(new PropertyValueFactory<Cheque,String>("signature"));//id+motif AI
        ghost.setCellValueFactory(new PropertyValueFactory<Cheque,String>("id"));
        table.setItems(n);

    }

    ChequierCrud chh = new ChequierCrud();

    public void refresh(int i) {
        tab.setVisible(false);
        frm.setVisible(false);
        fairecheque.setDisable(true);

        connection = MyConnection.getInstance().getCnx();
        b.clear();
        b.addAll(chh.listerChequiercmp(i));
        System.out.println(chh.listerChequiercmp(i));
        System.out.println("fff");
        motiftable2.setCellValueFactory(new PropertyValueFactory<Chequier, String>("motif_chequier"));
        etattable2.setCellValueFactory(new PropertyValueFactory<Chequier, String>("etat")); //setcellvalue temchi tchouf G&S mtaa entity hedhika khater G bech irajeelna valeur bech nhotouha
        table2.setItems(b);
    }


    private void demanderchequier(ActionEvent event) {

        tab.setVisible(true);
        frm.setVisible(false);
    }


    @FXML
    private void fairecheque(ActionEvent event) {
        frm.setVisible(true);
        // tab.setVisible(false);
    }


    @FXML
    private void psc(ActionEvent event) {
        if ((chp.getText().equals("") || chp.getText().matches("[0-9]*") || chp2.getText().equals("") || chp2.getText().matches("[a-zA-Z]*"))) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Veuillez saisir motif!");
            alert.setHeaderText("Veuillez vérifiez votre saisie!");
            alert.showAndWait();

        } else {

            if (table2.getItems().size() < 5) {


                Chequier ccc = new Chequier();
                ccc.setNum_compte_id(idcompte);
                ccc.setMotif_chequier(chp.getText());
                ccc.setEtat_chequier(0);
                ccc.setNom_client_id(Session.getId());

                //ccc.setClient_tel(Integer.parseInt(chp2.getText()));
                ccc.setClient_tel(Session.getNumero_tel());
                chh.ajouterChequier(ccc);
                chp.clear();
//                chp2.clear();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Vous avez dépasser le nombre limite de chéquiers! Veuillez réessayez plus tard!");
                alert.showAndWait();

            }
        }
        refresh(idcompte);
    }

    @FXML
    private void btnccl(ActionEvent event) {
        refresh(idcompte);
    }

    @FXML
    private void confirmercheque(ActionEvent event) {
        if (
                (l1.getText().equals("")) || (l2.getText().equals("")) || (l3.getText().equals("")) || (l4.getValue() == null) || (l5.getText().equals("")) || (l6.getText().equals(""))) {

            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("Veuillez remplir tous les champs avant de faire le chèque!");
            alert.setHeaderText("Veuillez remplir les champs vides!");

            alert.showAndWait();
        } else {

            Cheque ccc = new Cheque();
            ccc.setProprietaire_id(idcompte);
            ccc.setMontant(Integer.parseInt(l2.getText()));
            ccc.setLieu(l3.getText());

            LocalDate localDate = l4.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            ccc.setDate_cheque(date);
            ccc.setIdchequiers_id(chh.motifToId(table2.getItems().get(myIndex).getMotif_chequier()));
            ccc.setDestinataire_id(Integer.parseInt(l5.getText()));
            ccc.setSignature(Integer.parseInt(l6.getText()));

            CompteController comptCont = new CompteController();

            ccc.setNom_client_id(cmpt.getFullname());
            ccc.setClient_tel(Session.getNumero_tel());

            Compte compte = comptCont.rechercherCompte(Integer.parseInt(l5.getText()));
            System.out.println("===========================");
            System.out.println(compte);
            System.out.println("===========================");
            ccc.setRib_reciever(compte.getRIB_Compte());
            ccc.setRib_sender(cmpt.getRIB_Compte());
            System.out.println("*********************** ");
            System.out.println(ccc);
            System.out.println("*********************** ");

            ch.ajouterCheque(ccc);
//            l1.clear();
            l2.clear();
            l3.clear();
            l4.setValue(LocalDate.now());
            // l4.clear();
            l5.clear();
            l6.clear();

        }
        refresh(idcompte);
    }

    @FXML
    private void xbtn(ActionEvent event) {
        refresh(idcompte);

    }

    @FXML
    private void demander(ActionEvent event) {
        tab.setVisible(true);
        frm.setVisible(false);
    }

    public void fullchequier() {
    }

    @FXML
    private void redirectCompteFront(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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

    @FXML
    private void redirectTransactionsFront(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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

    @FXML
    private void redirectForumFront(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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

    @FXML
    private void redirectCreditFront(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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

    @FXML
    private void redirectCartesFront(ActionEvent event) {
        try {
//            ((Node) (event.getSource())).getScene().getWindow().hide();

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

    @FXML
    private void redirectLogoutFront(KeyEvent event) {
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
    private void search(KeyEvent event) {
        String s = search1.getText();

        table2.getItems().clear();
        ChequierCrud cc = new ChequierCrud();
        List<Chequier> c = cc.listerChequiercmp(idcompte);
        for (int i = 0; i < c.size(); i++) {
            if (kelma(c.get(i).getMotif_chequier(), s)) {
                b.addAll(c.get(i));
            }
        }
        motiftable2.setCellValueFactory(new PropertyValueFactory<Chequier, String>("motif_chequier"));
        etattable2.setCellValueFactory(new PropertyValueFactory<Chequier, String>("etat"));
        table2.setItems(b);
    }

    public boolean kelma(String s1, String s2) {
        String[] l1 = null;

        l1 = s1.split(" ");
        String[] l2 = s2.split("(?!^)");
        for (int i = 0; i < l1.length; i++) {
            if (l1[i].toUpperCase().contains(s2.toUpperCase())) {
                return true;
            }
        }
        return false;
    }


    @FXML
    public void redirectAccueilFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("E-Bank - Bankiz");
            stage.initStyle(StageStyle.TRANSPARENT);
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

    @FXML
    public void redirectReclamationsFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Passer Reclamations");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Reclamations/AjouterRecFront.fxml"));

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
    public void devise(ActionEvent actionEvent) {
        double rs;

        rs = montantd/ Float.parseFloat(JsonReader.show("TND"));
        money.setText(""+rs);
        money.setVisible(true);

    }

}
