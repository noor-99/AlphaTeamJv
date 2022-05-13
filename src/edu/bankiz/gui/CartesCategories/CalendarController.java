/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.gui.CartesCategories;

import edu.bankiz.entities.CategorieCarte;
import edu.bankiz.entities.EvenementCalendrier;
import edu.bankiz.services.CategorieDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.Agenda;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class CalendarController implements Initializable {

    @FXML
    private BorderPane cell;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      VCalendar vCalendar = new VCalendar();
    
     Agenda a=new Agenda();
     a.applyCss();
      
        BorderPane root = new BorderPane();
             List<EvenementCalendrier> elements = participationToAppoitments();
        a.appointments().addAll(elements);
        
       cell.setCenter(a);
        
   
    
}
  private  List<EvenementCalendrier> participationToAppoitments(){
        CategorieDAO participationDao=new CategorieDAO();
        List<CategorieCarte> evenements=participationDao.fechCategorie();
        return EvenementCalendrier.appointmentsList(evenements);
        
    }
}
