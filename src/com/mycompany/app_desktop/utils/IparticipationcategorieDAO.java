/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app_desktop.utils;

import com.mycompany.app_desktop.entities.CategorieCarte;
import com.mycompany.app_desktop.entities.Participation_categorie;
import java.sql.SQLException;

import java.util.List;

/**
 *
 * @author Khaled
 */
public interface IparticipationcategorieDAO {
    
    
    public void insererParticipation(Participation_categorie p);
    public void updateParticipation(Participation_categorie p);
    public void deleteParticipation(Participation_categorie p);
   List<CategorieCarte> fetchParticipationByUser(int id);
    List<Participation_categorie> fetchParticipationByEvent(int id);


    boolean verifEvenementUser(CategorieCarte e  );
       boolean fetchUpcomingEvents(CategorieCarte u);

    
}
