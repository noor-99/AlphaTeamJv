package edu.bankiz.gui.utilisateur;

import edu.bankiz.entities.Reclamation;
import edu.bankiz.entities.Session;
import edu.bankiz.entities.Utilisateur;
import edu.bankiz.services.ReclamationCRUD;
import edu.bankiz.services.UtilisateurCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReclamationBackController {
    @javafx.fxml.FXML
    private TableColumn etat_back_col;
    @javafx.fxml.FXML
    private TableColumn date_back_col;
    @javafx.fxml.FXML
    private TableView<Reclamation> TableViewBackReclamation;
    @javafx.fxml.FXML
    private TableColumn utilisateur_back_col;
    @javafx.fxml.FXML
    private TableColumn desc_back_col;
    @javafx.fxml.FXML
    private TableColumn Type_back_col;

    ReclamationCRUD rec = new ReclamationCRUD();
    Reclamation r = new Reclamation();
    UtilisateurCRUD pcd = new UtilisateurCRUD();
    Utilisateur u = new Utilisateur();
    public void initialize(){
        ObservableList<Reclamation> donne= FXCollections.observableArrayList();

        donne.clear();
        for (Reclamation Reclamation : donne = FXCollections.observableArrayList(rec.afficherReclamation())) {
            /*u = pcd.findByID(r.getNom_u_id());
            String var = u.getNom_u();*/
            Type_back_col.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
            date_back_col.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
            etat_back_col.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
            desc_back_col.setCellValueFactory(new PropertyValueFactory<>("desc_rec"));
            //utilisateur_back_col.setText(var);

            TableViewBackReclamation.setItems((ObservableList) donne);
        }
    }
}
