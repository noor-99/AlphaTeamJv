/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.entities;

import jfxtras.scene.control.agenda.Agenda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khaled
 */
public class CategorieCalendrier implements Agenda.Appointment {
    CategorieCarte categorie;
    Agenda.AppointmentGroupImpl group = new Agenda.AppointmentGroupImpl();
    
    
    CategorieCalendrier(){
        
    }
    CategorieCalendrier(CategorieCarte e){
      this.categorie=e;
        
    }
    @Override
    public Boolean isWholeDay() {
        return false;
    }

    @Override
    public void setWholeDay(Boolean bln) {
        
    }

    @Override
    public String getSummary() {
        return this.categorie.getDescription();
    }

    @Override
    public void setSummary(String string) {
       this.categorie.setDescription(string);
    }

    @Override
    public String getDescription() {
        return this.categorie.getDescription();
    }

    @Override
    public void setDescription(String string) {
        this.categorie.setDescription(string);
       
    }

    @Override
    public String getLocation() {
        return categorie.getType();
    }

    @Override
    public void setLocation(String string) {
       categorie.setType(string);
    }

    @Override
    public Agenda.AppointmentGroup getAppointmentGroup() {
        
        group.setStyleClass("calendar");
        return group;
    }

    @Override
    public void setAppointmentGroup(Agenda.AppointmentGroup ag) {
        Agenda.AppointmentGroupImpl group2 = new Agenda.AppointmentGroupImpl();
        this.group=group2 ;
    }

    @Override
    public LocalDateTime getStartLocalDateTime() {
        return categorie.getDate_categorie().toLocalDateTime();
    }
    
     @Override
    public LocalDateTime getEndLocalDateTime() {
        return categorie.getDate_categorie().toLocalDateTime().plusHours(2);
    }
    
    
 public static  List<CategorieCalendrier>  appointmentsList(List<CategorieCarte> list){
    List<CategorieCalendrier> appointmets =new ArrayList<CategorieCalendrier>();
        for(int i=0;i<list.size();i++){
            appointmets.add(new CategorieCalendrier(list.get(i)) );    
        }
        return appointmets;
    
    
    
}
    
    
}
