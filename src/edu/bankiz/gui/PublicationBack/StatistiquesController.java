package edu.bankiz.gui.PublicationBack;

import edu.bankiz.tools.MyConnection;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StatistiquesController implements Initializable {
    @javafx.fxml.FXML
    private PieChart pc1;
    ObservableList<PieChart.Data> ol = FXCollections.observableArrayList();
    //ObservableList <PieChart.Data> o2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {


            //String requete = "SELECT commentaire.nom_client_id, Count(*) AS Nombre_de_Fois FROM commentaire GROUP BY commentaire.nom_client_id";
            //String requete ="SELECT publication.nom_client_id, COUNT(*) AS nombre_de_fois FROM publication GROUP BY publication.nom_client_id";
            //String requete = "SELECT DISTINCT nom_u, prenom_u from publication INNER JOIN utilisateur ON (publication.nom_client_id= utilisateur.id) GROUP BY publication.nom_client_id";
            String requete ="SELECT publication.categorie_publication, COUNT(*) AS nombre_de_fois FROM publication GROUP BY publication.categorie_publication";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs =  pst.executeQuery(requete);



            while(rs.next()){

                ol.addAll(new PieChart.Data(rs.getString(1),rs.getInt(2)));
                pc1.setData(ol);

                pc1.setLegendSide(Side.LEFT);

                FadeTransition f = new FadeTransition(Duration.seconds(6),pc1);

                f.setFromValue(0);
                f.setToValue(1);
                f.play();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        for (PieChart.Data data : pc1.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {

                public void handle(MouseEvent event) {
                    JOptionPane.showMessageDialog(null,"Lieu Hebergement   -- "+ data.getName()+ "nombre d'adresse --" +(int)data.getPieValue());
                }
            });
        }

       /* try {


            String requete = "SELECT titre, Count(*) AS Nombre_de_Fois from blogs INNER JOIN cmnts where blogs.id=cmnts.blog_id GROUP BY titre";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs =  pst.executeQuery(requete);



            while(rs.next()){

                o2.addAll(new PieChart.Data(rs.getString(1),rs.getInt(2)));
                pc1.setData(o2);

                pc1.setLegendSide(Side.LEFT);

                FadeTransition f = new FadeTransition(Duration.seconds(4),pc1);
                f.setFromValue(0);
                f.setToValue(1);
                f.play();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } */
        for (PieChart.Data data : pc1.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {

                public void handle(MouseEvent event) {
                    JOptionPane.showMessageDialog(null,"adresse   -- "+ data.getName()+ "nombre d'hebergement --" +(int)data.getPieValue());
                }
            });
        }

    }

    }

