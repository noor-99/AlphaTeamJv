package edu.bankiz.gui.Comptes.Front;

import edu.bankiz.entities.Compte;
import edu.bankiz.entities.Session;
import edu.bankiz.services.CompteController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationsCompteController implements Initializable {
    @javafx.fxml.FXML
    private Label TypeCompteInfo;
    @javafx.fxml.FXML
    private Label SoldeInfo;
    @javafx.fxml.FXML
    private Label SeuilInfo;
    @javafx.fxml.FXML
    private Button btnCloseInfo;
    @javafx.fxml.FXML
    private Label DateCompteInfo;
    @javafx.fxml.FXML
    private Label EtatCompteInfo;
    @javafx.fxml.FXML
    private Label RibInfo;
    @javafx.fxml.FXML
    private Label TauxInteretInfo;
    @javafx.fxml.FXML
    private Label NumCompteInfo;


    @FXML
    public void afficherInfos()  {
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        TypeCompteInfo.setText(cmpt.getType_compte());
        SoldeInfo.setText(String.valueOf(cmpt.getSolde_compte()));
        SeuilInfo.setText(String.valueOf(cmpt.getSeuil_compte()));
        DateCompteInfo.setText(cmpt.getDate_creation());
        EtatCompteInfo.setText(String.valueOf(cmpt.getEtat_compte()));
        TauxInteretInfo.setText(String.valueOf(cmpt.getTaux_interet()));
        NumCompteInfo.setText(String.valueOf(cmpt.getNum_compte()));
        RibInfo.setText(cmpt.getRIB_Compte());
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        afficherInfos();
    }
    @javafx.fxml.FXML
    public void CloseInfo(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
