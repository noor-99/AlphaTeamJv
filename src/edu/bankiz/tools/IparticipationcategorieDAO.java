/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.tools;

import edu.bankiz.entities.CategorieCarte;
import edu.bankiz.entities.Participation_categorie;

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
