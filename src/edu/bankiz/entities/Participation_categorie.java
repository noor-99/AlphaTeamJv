/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.entities;

import java.sql.Date;

/**
 *
 * @author Khaled
 */
public class Participation_categorie {

    private CategorieCarte categorie;
    private Date date_participation;

    public Participation_categorie(){}
    public Participation_categorie(Utilisateur id_utilisateur, CategorieCarte id, Date date_participation) {

        this.categorie = id;
        this.date_participation = date_participation;
    }

    public Participation_categorie( CategorieCarte id) {
      
        this.categorie = id;
    }

    public CategorieCarte getCategorie() {
        return categorie;
    }
    



    public CategorieCarte getCategorieCarte() {
        return categorie;
    }

    public void setId(CategorieCarte id) {
        this.categorie = id;
    }

    public Date getDate_participation() {
        return date_participation;
    }

    public void setDate_participation(Date date_participation) {
        this.date_participation = date_participation;
    }
    
}
