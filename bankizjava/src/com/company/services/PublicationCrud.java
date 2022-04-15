package com.company.services;
import com.company.entities.Publication;
import com.company.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PublicationCrud {
    public void ajouterPublication(Publication p) {
        try {
            String requete = "INSERT INTO publication (nom_client_id,titre_publication,description_publication,categorie_publication,date_publication,image_publication) VALUES "
            +"(?,?,?,?,?,?)";

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,p.getNom_client_id());
            pst.setString(2,p.getTitre_publication());
            pst.setString(3,p.getDescription_publication());
            pst.setString(4,p.getCategorie_publication());
            pst.setString(5,p.getDate_publication());
            pst.setString(6,p.getImage_publication());
            pst.executeUpdate();
            System.out.println("Publication ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PublicationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Publication> listerPublications() {
        List<Publication>myList = new ArrayList();
        try{
            String requete = "SELECT * FROM publication";
            Statement st =  MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while(rs.next()) {
                Publication p = new Publication();
                p.setId(rs.getInt(1));
                p.setNom_client_id(rs.getInt(2));
                p.setTitre_publication(rs.getString("titre_publication"));
                p.setDescription_publication(rs.getString("description_publication"));
                p.setCategorie_publication(rs.getString("categorie_publication"));
                p.setDate_publication(rs.getString("date_publication"));
                p.setImage_publication(rs.getString("image_publication"));
                myList.add(p);
                //System.out.println(p.getId() + ":" + p.getNom_client_id() + ":" + p.getTitre_publication() + ":" + p.getDescription_publication() + ":" + p .getCategorie_publication() + ":" + p.getDate_publication() +":"+ p.getImage_publication());
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public void modifierPublication(Publication p) {
        try {
            String requete = "UPDATE publication SET titre_publication=?,description_publication=?,categorie_publication=?,date_publication=?,image_publication=?  WHERE id=?";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            dtf.format(now);
            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            //pst.setInt(1,p.getNom_client_id());
            pst.setString(1,p.getTitre_publication());
            pst.setString(2,p.getDescription_publication());
            pst.setString(3,p.getCategorie_publication());
            pst.setString(4,p.getDate_publication());
            pst.setString(5,p.getImage_publication());
            pst.setInt(6,p.getId());
            pst.executeUpdate();
            System.out.println("Publication modifiee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PublicationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerPublication(Publication p) {
        try {
            String requete = "DELETE  FROM publication WHERE id=?";
            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,p.getId());
            pst.executeUpdate();
            System.out.println("Publication supprimee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PublicationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int nbPublication() {
        List<Publication> pub = new ArrayList<>();
        String requete = "SELECT titre_publication FROM publication";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Publication c = new Publication();
                c.setTitre_publication(rs.getString("titre_publication"));
                pub.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pub.size();
    }

}
