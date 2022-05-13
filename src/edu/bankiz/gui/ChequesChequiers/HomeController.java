package edu.bankiz.gui.ChequesChequiers;

import edu.bankiz.entities.Chequier;
import edu.bankiz.services.ChequierCrud;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private VBox pnItems = null;
    private Button btnOverview;

    private Button btnOrders;

    private Button btnCustomers;

    private Button btnMenus;


    private Pane pnlCustomer;
    private Button btncheque;
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    private Pane pnlMenus;
    @FXML
    private TableView<Chequier> table;
    @FXML
    private TableColumn<Chequier,String> numeroc;
    @FXML
    private ImageView datec;
    @FXML
    private TableColumn<Chequier,String> motif;
    @FXML
    private TableColumn<Chequier,String> nomc;
    @FXML
    private TableColumn<Chequier,String> num;
    @FXML
    private TableColumn<Chequier,String> etat;

    Connection connection = null;
    ObservableList<Chequier> n = FXCollections.observableArrayList();
    @FXML
    private Button confirmer;
    ChequierCrud cc=new ChequierCrud();
    int id;
    @FXML
    private Button supprimer;
    private Label sommechequesa;
    private Label chequiersAcc;
    @FXML
    private Label chequiersAtt;
    @FXML
    private Label nbr1;
    @FXML
    private Label nbr2;


    int myIndex;
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
    private Button TransactionsBack1;
    @FXML
    private Button btnChequiersBack;
    @FXML
    private Button btnCreditsBack;
    @FXML
    private Button btnCartesBack;
    @FXML
    private TableColumn<Chequier, String> datecc;
    @FXML
    private Button btnAccueilBack;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnCommentaires;

    // Connection connection = null;
    // ObservableList<Cheque> n = FXCollections.observableArrayList();
    // ObservableList<Chequier> b = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        connection = MyConnection.getInstance().getCnx();
        refresh();

    }

    ChequierCrud chh=new ChequierCrud();

    public void refresh()
    {
        n.clear();
        confirmer.setDisable(true);
        supprimer.setDisable(true);

        nbr1.setText(""+cc.listerChequierEtat().size());
        nbr2.setText(""+cc.listerChequierEtat0().size());
        //n.clear();
        n.addAll(cc.listerChequier());
        numeroc.setCellValueFactory(new PropertyValueFactory<Chequier,String>("num_compte_id"));
        datecc.setCellValueFactory(new PropertyValueFactory<Chequier,String>("date_creation"));
        motif.setCellValueFactory(new PropertyValueFactory<Chequier,String>("motif_chequier"));
        nomc.setCellValueFactory(new PropertyValueFactory<Chequier,String>("nom_client_id"));
        num.setCellValueFactory(new PropertyValueFactory<Chequier,String>("client_tel"));
        etat.setCellValueFactory(new PropertyValueFactory<Chequier,String>("etat_chequier"));
        table.setItems(n);
        table.setRowFactory(tv ->{
                    TableRow<Chequier> myRow=new TableRow<>();
                    myRow.setOnMouseClicked(ev ->{
                        if(ev.getClickCount()==1 && (!myRow.isEmpty())){
                            myIndex=table.getSelectionModel().getSelectedIndex();
                            id=table.getItems().get(myIndex).getId();
                            confirmer.setDisable(false);
                            supprimer.setDisable(false);
                            System.out.println(String.valueOf(table.getItems().get(myIndex).getId()));
                        }
                    });
                    return myRow;
                }
        );

    }

    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
        if (actionEvent.getSource()==btncheque)
        {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();

        }
    }

    @FXML
    private void say(ActionEvent event) {
        cc.updateEtat(id);
        refresh();

    }
    @FXML
    private void said(ActionEvent event) {
        Chequier c=cc.recherchechequier(id);
        cc.supprimerChequier(c);
        refresh();
    }


    private void accpetbtn(ActionEvent event) {
        String nbr = cc.countCheqAcc();
        chequiersAcc.setText(nbr);

    }

    private void waitbtn(ActionEvent event) {
        String nbr = cc.countCheqAtt();
        chequiersAtt.setText(nbr);
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
    private void versChequesBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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
    public void versAccueilBakc(ActionEvent actionEvent) {
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
    public void versCommentairesBack(ActionEvent actionEvent) {
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