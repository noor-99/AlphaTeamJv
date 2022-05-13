package edu.bankiz.services;

import edu.bankiz.entities.Publication;
import edu.bankiz.tools.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PublicationController {

    /*public ObservableList<Publication> afficherPublications() {
        //List<Publication> myList = new ArrayList();
        ObservableList<Publication> myList = FXCollections.observableArrayList();
        try {
            String requete = "SELECT * from publication INNER JOIN utilisateur ON (publication.nom_client_id = utilisateur.id) ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Publication p = new Publication();
                p.setId(rs.getInt(1));
                p.setTitre_publication(rs.getString("titre_publication"));
                p.setDescription_publication(rs.getString("description_publication"));
                p.setCategorie_publication(rs.getString("categorie_publication"));
                p.setDate_publication(rs.getString("date_publication"));
                p.setImage_publication(rs.getString("image_publication"));
                p.setNom_client_id(rs.getInt("nom_client_id"));
                p.setFullname(rs.getString("nom_u")+ ' '+ rs.getString("prenom_u"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    } */
    public List<Publication> afficherPublications() {
        List<Publication> myList = new ArrayList();
        try {
            String requete = "SELECT * from publication INNER JOIN utilisateur ON (publication.nom_client_id = utilisateur.id) ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Publication p = new Publication();
                p.setId(rs.getInt(1));
                p.setTitre_publication(rs.getString("titre_publication"));
                p.setDescription_publication(rs.getString("description_publication"));
                p.setCategorie_publication(rs.getString("categorie_publication"));
                p.setDate_publication(rs.getString("date_publication"));
                p.setImage_publication(rs.getString("image_publication"));
                p.setNom_client_id(rs.getInt("nom_client_id"));
                p.setFullname(rs.getString("nom_u")+ ' '+ rs.getString("prenom_u"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return myList;
    }
    public List<String> afficherNomClientPublication() {
        List<String> myList = new ArrayList();
        String nom ="";
        try {
            String requete = "SELECT DISTINCT nom_u, prenom_u from publication INNER JOIN utilisateur ON (publication.nom_client_id= utilisateur.id) ";
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
    public void ajouterPublication(Publication p) {
        try {
            String requete = "INSERT INTO publication (nom_client_id,titre_publication,description_publication,categorie_publication,date_publication,image_publication) VALUES "
                    +"(?,?,?,?,?,?)";

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getNom_client_id());
            pst.setString(2,p.getTitre_publication());
            pst.setString(3,p.getDescription_publication());
            pst.setString(4,p.getCategorie_publication());
            pst.setString(5,p.getDate_publication());
            pst.setString(6,p.getImage_publication());
            pst.executeUpdate();
            //JOptionPane.showMessageDialog(null,"Publication ajoutee avec succees");
            System.out.println("Publication ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PublicationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerPublication(int id) {
        try {
            String requete = "DELETE FROM publication WHERE id='" + id + "'";
            Statement pst = MyConnection.getInstance().getCnx().createStatement();
            pst.executeUpdate(requete);
            System.out.println("Publication supprimé avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Publication> rechercherPublicationNom() {
        List<Publication> myList = new ArrayList();
        try {
            String requete = "SELECT * from publication  where categorie_publication = ?";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Publication p = new Publication();
                p.setId(rs.getInt(1));
                p.setTitre_publication(rs.getString("titre_publication"));
                p.setDescription_publication(rs.getString("description_publication"));
                p.setCategorie_publication(rs.getString("categorie_publication"));
                p.setDate_publication(rs.getString("date_publication"));
                p.setImage_publication(rs.getString("image_publication"));
                p.setNom_client_id(rs.getInt("nom_client_id"));
                p.setFullname(rs.getString("nom_u")+ ' '+ rs.getString("prenom_u"));
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public List<Publication> getIdPublication(String name) {
        String[] arrOfStr = name.split(" ");
        List<Publication> u = afficherPublications();
        List<Publication> resultat = u.stream().filter(Publication -> arrOfStr[0].toLowerCase().equals(Publication.getTitre_publication().toLowerCase())).collect(Collectors.toList());
        System.out.println(resultat.get(1).getId());
        if (resultat.isEmpty()) {
            System.out.println("publication n existe pas");
        } else {
            System.out.println("publication existe");
        }
        return resultat;
    }
    public void modifierPublication(Publication p, int id) {
        try {
            String requete = "UPDATE publication SET nom_client_id=?,titre_publication=?,description_publication=?,categorie_publication=?,date_publication=?,image_publication=?  WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,p.getNom_client_id());
            pst.setString(2,p.getTitre_publication());
            pst.setString(3,p.getDescription_publication());
            pst.setString(4,p.getCategorie_publication());
            pst.setString(5,p.getDate_publication());
            pst.setString(6,p.getImage_publication());
            pst.setInt(7,id);
            pst.executeUpdate();
            System.out.println("Publication modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    public List<Publication> afficherPublicationDetaille(int id) {
        List<Publication> myListPublication = new ArrayList<>();
        try {
            String requete = "SELECT * from publication where id=" + id;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Publication p = new Publication();
                p.setId(rs.getInt(1));
                p.setTitre_publication(rs.getString("titre_publication"));
                p.setDescription_publication(rs.getString("description_publication"));
                p.setCategorie_publication(rs.getString("categorie_publication"));
                p.setDate_publication(rs.getString("date_publication"));
                p.setImage_publication(rs.getString("image_publication"));
                p.setNom_client_id(rs.getInt("nom_client_id"));
                p.setFullname(rs.getString("nom_u")+ ' '+ rs.getString("prenom_u"));
                myListPublication.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myListPublication;
    }
    public Publication rechercherPublication(int id) {
        Publication p = new Publication();
//        int id = 23;
        try {
            String requete = "SELECT * from publication INNER JOIN utilisateur ON (publication.nom_client_id = utilisateur.id) where publication.id=" + id + "";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setTitre_publication(rs.getString("titre_publication"));
                p.setDescription_publication(rs.getString("description_publication"));
                p.setCategorie_publication(rs.getString("categorie_publication"));
                p.setDate_publication(rs.getString("date_publication"));
                p.setImage_publication(rs.getString("image_publication"));
                p.setNom_client_id(rs.getInt("nom_client_id"));
                p.setFullname(rs.getString("nom_u")+ ' '+ rs.getString("prenom_u"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public Publication afficherPublicationDetaille2(int id) {
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
    }
    public List<String> afficherTitrePublicationFront() {
        List<String> myListPublication = new ArrayList<>();
        try {
            String requete = "SELECT titre_publication FROM publication";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                myListPublication.add(rs.getString("titre_publication"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myListPublication;
    }
    public List<Publication> getIdTitre(String titre) {
        String[] arrOfStr = titre.split(" ");
        List<Publication> u = afficherPublications();
        List<Publication> resultat = u.stream().filter(Publication -> arrOfStr[0].toLowerCase().equals(Publication.getTitre_publication().toLowerCase())).collect(Collectors.toList());

        System.out.println(resultat.get(1).getId());

        return resultat;
    }

}
