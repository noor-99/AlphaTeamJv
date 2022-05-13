package edu.bankiz.services;

import edu.bankiz.entities.Commentaire;
import edu.bankiz.tools.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CommentaireController {
    public List<Commentaire> afficherCommentaires() {
        List<Commentaire> myList = new ArrayList();
        try {
            String requete = "SELECT * from commentaire INNER JOIN utilisateur ON (commentaire.nom_client_id = utilisateur.id) INNER JOIN publication ON(commentaire.id_publication_id = publication.id)";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Commentaire p = new Commentaire();
                p.setId(rs.getInt(1));
                p.setId_publication_id(rs.getInt("id_publication_id"));
                p.setTitre_publication(rs.getString("titre_publication"));
                p.setDescription_commentaire(rs.getString("description_commentaire"));
                p.setDate_commentaire(rs.getString("date_commentaire"));
                p.setNom_client_id(rs.getInt("nom_client_id"));
                p.setFullname(rs.getString("nom_u")+ ' '+ rs.getString("prenom_u"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public List<Commentaire> sortByDate(){
        List<Commentaire> commentaires= afficherCommentaires();
        List<Commentaire> resultat=commentaires.stream().sorted(Comparator.comparing(Commentaire::getDate_commentaire)).collect(Collectors.toList());
        return resultat;

    }

    public List<Commentaire> sortByTitre(){
        List<Commentaire> commentaires=afficherCommentaires();
        List<Commentaire> resultat=commentaires.stream().sorted(Comparator.comparing(Commentaire::getTitre_publication)).collect(Collectors.toList());
        return resultat;
    }

    public List<Commentaire> sortByNom(){
        List<Commentaire> commentaires=afficherCommentaires();
        List<Commentaire> resultat=commentaires.stream().sorted(Comparator.comparing(Commentaire::getFullname)).collect(Collectors.toList());
        return resultat;
    }
    public void ajouterCommentaire(Commentaire c) {
        try {
            String requete = "INSERT INTO commentaire (id_publication_id,nom_client_id,description_commentaire,date_commentaire) VALUES "
                    +"(?,?,?,?)";

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c.getId_publication_id());
            pst.setInt(2, c.getNom_client_id());
            pst.setString(3,c.getDescription_commentaire());
            pst.setString(4,c.getDate_commentaire());
            pst.executeUpdate();
            //JOptionPane.showMessageDialog(null,"Publication ajoutee avec succees");
            System.out.println("Commentaire ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PublicationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerCommentaire(int id) {
        try {
            String requete = "DELETE FROM commentaire WHERE id='" + id + "'";
            Statement pst = MyConnection.getInstance().getCnx().createStatement();
            pst.executeUpdate(requete);
            System.out.println("Commentaire supprimé avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierCommentaire(Commentaire c, int id) {
        try {
            String requete = "UPDATE commentaire SET id_publication_id=?,nom_client_id=?,description_commentaire=?,date_commentaire=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,c.getId_publication_id());
            pst.setInt(2,c.getNom_client_id());
            pst.setString(3,c.getDescription_commentaire());
            pst.setString(4,c.getDate_commentaire());
            pst.setInt(5,id);
            pst.executeUpdate();
            System.out.println("Commentaire modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<String> afficherNomClientCommentaire() {
        List<String> myList = new ArrayList();
        String nom ="";
        try {
            String requete = "SELECT DISTINCT nom_u, prenom_u from commentaire INNER JOIN utilisateur ON (commentaire.nom_client_id= utilisateur.id) ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                nom = rs.getString("nom_u") + ' ' + rs.getString("prenom_u");
                myList.add(nom);
            }
        } catch (SQLException ex) {

            //Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList;
    }
    public List<String> afficherTitreCommentaire() {
        List<String> myList = new ArrayList();
        String titre ="";
        try {
            String requete = "SELECT DISTINCT titre_publication from commentaire INNER JOIN publication ON (commentaire.id_publication_id= publication.id) ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                titre = rs.getString("titre_publication");
                myList.add(titre);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    /*public Commentaire afficherPublicationParId(int id) {
        Publication myListPublication = new Publication();
        try {
            String requete = "SELECT * from publication inner join utilisateur ON (publication.nom_client_id= utilisateur.id) where publication.id=" + id;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                myListPublication.setId(rs.getInt(1));
                myListPublication.setTitre_publication(rs.getString("titre_publication"));
                myListPublication.setDescription_publication(rs.getString("description_publication"));
                myListPublication.setCategorie_publication(rs.getString("categorie_publication"));
                myListPublication.setDate_publication(rs.getString("date_publication"));
                myListPublication.setImage_publication(rs.getString("image_publication"));
                myListPublication.setNom_client_id(rs.getInt("nom_client_id"));
                myListPublication.setFullname(rs.getString("nom_u")+ ' '+ rs.getString("prenom_u"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myListPublication;
    } */

    public Commentaire rechercherCommentaire(int id) {
        Commentaire p = new Commentaire();
//        int id = 23;
        try {
            String requete = "SELECT * from commentaire INNER JOIN utilisateur ON (commentaire.nom_client_id = utilisateur.id) where commentaire.id=" + id + "";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setId_publication_id(rs.getInt("id_publication_id"));
                p.setNom_client_id(rs.getInt("nom_client_id"));
                p.setDescription_commentaire("description_commentaire");
                p.setDate_commentaire("date_commentaire");
                p.setTitre_publication(rs.getString("titre_publication"));
                p.setFullname(rs.getString("nom_u")+ ' '+ rs.getString("prenom_u"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }
}
