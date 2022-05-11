/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.services.ServiceCredit;
import edu.workshopjdbc3a48.services.ServiceOperationCredit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StateController implements Initializable {
    
    @FXML
    private Button btcredit;
    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    /**    @FXML
    private Button btcredit;
     * Initializes the controller class.
     */
    
    @FXML
    void rcredit(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/workshopjdbc3a48/gui/afficherCredit.fxml"));
            Parent root = loader.load();
            y.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            XYChart.Series series = new XYChart.Series();
            ServiceCredit sc=new ServiceCredit();
            List<Integer> a= sc.listecredit ();
            
            for(int elem: a)
            {
                System.out.println (elem);
                
                ServiceOperationCredit c=new ServiceOperationCredit();
                
                int i= c.getAll10(elem);
                String g=String.valueOf(elem); 
                
                series.getData().add(new XYChart.Data(g,elem));
                System.out.println (i+"ET"+elem);
            }
            
            lineChart.getData().addAll(series);
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(StateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
