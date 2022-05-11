/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app_desktop.services;


import com.mycompany.app_desktop.utils.IparticipationcategorieDAO;
import com.mycompany.app_desktop.entities.CategorieCarte;
import com.mycompany.app_desktop.entities.Participation_categorie;
import com.mycompany.app_desktop.utils.ICategorieDAO;


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

/**
 *
 * @author Khaled
 */
public class Participation_categorieDAO implements IparticipationcategorieDAO {
    
    
     Connection connection;
    
    public Participation_categorieDAO(){
          connection = myconnection.getInstance().getCnx();
    } 

    @Override
    public void insererParticipation(Participation_categorie p) {
       String query="INSERT INTO participation_evenement (id_evenement) VALUES (?)";
             try {
            PreparedStatement ps = connection.prepareStatement(query);
          
            ps.setInt(1, p.getCategorieCarte().getId());
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    @Override
    public void updateParticipation(Participation_categorie p) {
        String query = "update participation_evenement set date_participation=? where (id_utilisateur=? and id_evenement=?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, p.getDate_participation().toString());
            
            ps.setInt(2, p.getCategorieCarte().getId());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deleteParticipation(Participation_categorie p) {
        String query="DELETE FROM participation_evenement where id_evenement=? and id_utilisateur=?";
         try {
            PreparedStatement ps = connection.prepareStatement(query);
         
            ps.setInt(2, p.getCategorieCarte().getId());
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
 @Override
    public List<CategorieCarte> fetchParticipationByUser(int id) {
        List<CategorieCarte> listeEvenement = new ArrayList<CategorieCarte>();

        String requete = "select * from participation_evenement where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            
            statement.setInt(1, id);
            ResultSet resultat = statement.executeQuery();
           
            ICategorieDAO edao=new CategorieDAO();
            while (resultat.next()) {
                CategorieCarte e = edao.fetchCategorietById(resultat.getInt(2));
                listeEvenement.add(e);
                System.out.println(e);
            }
            return listeEvenement;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des categorie " + ex.getMessage());
            return null;
        }
    }

   
    @Override
    public List<Participation_categorie> fetchParticipationByEvent(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifEvenementUser(CategorieCarte e) {
        String requete = "select * from participation_evenement where  id_evenement=?";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            
          
            statement.setInt(1,e.getId());
            ResultSet resultat = statement.executeQuery();
            
            return resultat.last();
            
        
    }    catch (SQLException ex) {
             Logger.getLogger(Participation_categorieDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    return false;
    
    
}

    @Override
    public boolean fetchUpcomingEvents(CategorieCarte u) {
      List<CategorieCarte> listeEvenement = new ArrayList<CategorieCarte>();

        String requete = "SELECT e.* , u.* , t.* from participation_evenement p JOIN evenement e on p.id_evenement=e.id_evenement  where e.date_evenement> now() ;";
        try {
            PreparedStatement statement = connection.prepareStatement(requete);
            
            statement.setInt(1, u.getId());
            ResultSet resultat = statement.executeQuery();
            if (resultat.next() == false) { 
                
                return false; 
            }

 
            
            return true;
          
    
            /*while (resultat.next()) { 
                Evenement e=new Evenement();
                e.setId_evenement(resultat.getInt(1));
                e.setTitre_evenement(resultat.getString(2));
                e.setAdresse_evenement(resultat.getString(3));
                e.setDescription_evenement(resultat.getString(4));
                e.setDate_creation_evenement(resultat.getDate(5));
                e.setDate_evenement(resultat.getTimestamp(6));
                e.setImage(resultat.getString(9));
                e.setUtilisateur(u);
                Type_evenement t= new Type_evenement();
                t.setId_type_evenement(resultat.getInt(17));
                t.setLibelle_type_evenement(resultat.getString(17));
                e.setType_evenements(t);
                
                 
                listeEvenement.add(e);
                System.out.println(e);
            }
                return listeEvenement;
                    */
            
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des categorie " + ex.getMessage());
            return false;
        }
    }
    
    
}