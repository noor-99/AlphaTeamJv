/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app_desktop.services;




import com.mycompany.app_desktop.utils.myconnection;

import com.mycompany.app_desktop.entities.CategorieCarte;
import com.mycompany.app_desktop.utils.myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.app_desktop.utils.ICategorieDAO;

/**
 *
 * @author Khaled
 */
public class CategorieDAO implements ICategorieDAO{
    Connection connection;
    
    public CategorieDAO(){
          connection = myconnection.getInstance().getCnx();
    } 

    @Override
    public void insertCategorie(CategorieCarte e) {
       String query="INSERT INTO categoriecarte (type ,description ,prix,montant_max,date_categorie,image) VALUES (?,?,?,?,?,?)";
             try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, e.getType());
            ps.setString(2, e.getDescription());
            ps.setString(3, e.getPrix());
             ps.setString(4, e.getMontant_max());
            ps.setTimestamp(5, e.getDate_categorie());
          
            ps.setString(6, e.getImage());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void deleteCategorie(int id) {
        String query="DELETE FROM categoriecarte where id=?";
         try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void updateCategorie(CategorieCarte e) {
        String query = "update categoriecarte set type=?, description=?, prix=? , montant_max=?, date_categorie=?, image=? where id=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, e.getType());
           
            ps.setString(2, e.getDescription());
            ps.setString(3, e.getPrix());
            ps.setString(4, e.getMontant_max());
       
             ps.setTimestamp(5, e.getDate_categorie());
                         ps.setString(6, e.getImage());
               ps.setInt(7, e.getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
        
    }

    @Override
    public List<CategorieCarte> fechCategorie() {
       List<CategorieCarte> listeCategorieCarte = new ArrayList<CategorieCarte>();

        String requete = "select * from categoriecarte";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
        
    
            while (resultat.next()) {
                CategorieCarte e = new CategorieCarte ();
                e.setId(resultat.getInt(1));
                e.setType(resultat.getString(3));
                e.setDescription(resultat.getString(4));
                e.setPrix(resultat.getString(5));
                e.setMontant_max(resultat.getString(6));
                e.setDate_creation_categorie((resultat.getDate(7)));
                e.setDate_categorie((resultat.getTimestamp(8)));
               
                e.setImage(resultat.getString(9));
                listeCategorieCarte.add(e);
                System.out.println(e);
            }
            return listeCategorieCarte;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des stocks " + ex.getMessage());
            return null;
        }
    }

    @Override
    public CategorieCarte fetchCategorietById(int id) {
        String requete ="select * from categoriecarte where id= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            statement.setInt(1,id);
            ResultSet resultat = statement.executeQuery();
            CategorieCarte e = new CategorieCarte ();
           
            while (resultat.next()) {
                e.setId(resultat.getInt(1));
                e.setType(resultat.getString(3));
                e.setDescription(resultat.getString(4));
                e.setPrix(resultat.getString(5));
                e.setMontant_max(resultat.getString(6));
                 e.setDate_creation_categorie((resultat.getDate(7)));
                 e.setDate_categorie((resultat.getTimestamp(8)));
               
                e.setImage(resultat.getString(9));
                System.out.println(e);
            }
            return e;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des evenement 2 " + ex.getMessage());
            return null;
        }
        
    }
    
    @Override
    public List<CategorieCarte> fetchPopularCategorie() {
     
      String query ="select count(*) , e.* from categoriecarte p , evenement e where e.id_evenement=p.id_evenement GROUP by p.id_evenement order by count(*) desc LIMIT 3";
      Statement statement;
        try {
            statement = connection.createStatement();
      ResultSet resultat;
       
            resultat = statement.executeQuery(query);
        
     
      List<CategorieCarte> listeCategorieCarte = new ArrayList<CategorieCarte>();
         while (resultat.next()) {
                CategorieCarte e = new CategorieCarte ();
                e.setId(resultat.getInt(1));
                e.setType(resultat.getString(3));
                e.setDescription(resultat.getString(4));
                e.setPrix(resultat.getString(5));
                e.setMontant_max(resultat.getString(6));
                e.setDate_creation_categorie((resultat.getDate(7)));
                e.setDate_categorie((resultat.getTimestamp(8)));
              
                e.setImage(resultat.getString(9));
                listeCategorieCarte.add(e);
                System.out.println(e);
            }
        
      return listeCategorieCarte ;
        } catch (SQLException ex) {
            Logger.getLogger(CategorieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

   
        
    }

    
    

