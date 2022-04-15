package com.company.services;

import com.company.entities.Commentaire;
import com.company.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentaireCrud {

    public void ajouterChequier(Commentaire c) {
        try {
            String requete = "INSERT INTO chequier (num_compte_id,nom_client_id,description_commentaire,date_commentaire) VALUES "
                    +"(?,?,?,?)";

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,c.getId_publication_id());
            pst.setInt(2,c.getNom_client_id());
            pst.setString(3,c.getDescription_commentaire());
            pst.setString(4,c.getDate_commentaire());
            pst.executeUpdate();
            System.out.println("Chequier ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PublicationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Commentaire> listerChequier() {
        List<Commentaire>myList = new ArrayList();
        try{
            String requete = "SELECT * FROM chequier";
            Statement st =  MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while(rs.next()) {
                Commentaire c = new Commentaire();
                c.setId(rs.getInt(1));
                c.setId_publication_id(rs.getInt(2));
                c.setNom_client_id(rs.getInt(3));
                c.setDescription_commentaire(rs.getString("description_commentaire"));
                c.setDate_commentaire(rs.getString("date_commentaire"));
                myList.add(c);
                //System.out.println(p.getId() + ":" + p.getNom_client_id() + ":" + p.getTitre_publication() + ":" + p.getDescription_publication() + ":" + p .getCategorie_publication() + ":" + p.getDate_publication() +":"+ p.getImage_publication());
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public void modifierCommentaire(Commentaire c) {
        try {
            String requete = "UPDATE commentaire SET description_commentaire=?,date_commentaire=?  WHERE id=?";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            dtf.format(now);
            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            //pst.setInt(1,p.getNom_client_id());
            pst.setString(1,c.getDescription_commentaire());
            pst.setString(2,c.getDate_commentaire());
            pst.setInt(3,c.getId());
            pst.executeUpdate();
            System.out.println("Commentaire modifiee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PublicationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerCommentaire(Commentaire c) {
        try {
            String requete = "DELETE  FROM commentaire WHERE id=?";
            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,c.getId());
            pst.executeUpdate();
            System.out.println("Commentaire supprimee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PublicationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int nbCommentaire() {
        List<Commentaire> pub = new ArrayList<>();
        String requete = "SELECT id FROM publication WHERE id_publication_id =?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commentaire c = new Commentaire();
                c.setId(rs.getInt("id"));
                pub.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pub.size();
    }
}
