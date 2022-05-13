package edu.bankiz.services;

import edu.bankiz.entities.post_like;
import edu.bankiz.tools.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikesController {

    public void insertLike( post_like p)
    {
        //boolean f = false;
        try{
            String requete = "INSERT into post_like(publication_id, nom_client_id) VALUES" +"(?,?)";

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getPublication_id());
            pst.setInt(2,p.getNom_client_id());
            pst.executeUpdate();
            //f= true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return f;
    }

    public int countLikePost(post_like p) {
        int count = 0;
        try {
            String requete = "SELECT COUNT(*) FROM post_like WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, p.getId());
            ResultSet set = pst.executeQuery();
            if(set.next()) {
                count = set.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;

    }
}
