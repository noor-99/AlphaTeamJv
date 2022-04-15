package com.company.services;

import com.company.entities.Chequier;

import com.company.utils.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChequierCrud {


    public void ajouterChequier(Chequier c) {
        try {
            String requete = "INSERT INTO chequier (num_compte_id,date_creation,motif_chequier,etat_chequier,nom_client_id,client_tel) VALUES "
                    +"(?,?,?,?,?,?)";

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,c.getNum_compte_id());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            pst.setDate(2,date);
            pst.setInt(3,c.getMotif_chequier());
            pst.setInt(4,c.getEtat_chequier());
            pst.setInt(5,c.getNom_client_id());
            pst.setInt(6,c.getClient_tel());
            pst.executeUpdate();
            System.out.println("Chequier ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(PublicationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public void UpdateChequier(Chequier c) {

            String requete = "UPDATE chequier SET num_compte_id=?,motif_chequier=?,etat_chequier=?,client_tel=?  WHERE id=?";
            try {
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setInt(1,c.getNum_compte_id());
                pst.setInt(2,c.getMotif_chequier());
                pst.setInt(3,c.getEtat_chequier());
                pst.setInt(4,c.getClient_tel());
                pst.setInt(5,c.getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }




   }

    public List<Chequier> listerChequier() {
        List<Chequier> myList = new ArrayList();

       try{
           Statement st =  MyConnection.getInstance().getCnx().createStatement();
           String req="select * from chequier";
           ResultSet rs= st.executeQuery(req);
           while(rs.next()){
               Chequier c = new Chequier();
               c.setId(rs.getInt(1));
               c.setNum_compte_id(rs.getInt(2));
               c.setDate_creation(rs.getDate(3));
               c.setMotif_chequier(rs.getInt(4));
               c.setEtat_chequier(rs.getInt(5));
               c.setNom_client_id(rs.getInt(6));
               c.setClient_tel(rs.getInt(7));
               System.out.println( c.toString());
               myList.add(c);
           }



        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void supprimerChequier(Chequier c) {
        try {
            String requete = "DELETE  FROM chequier WHERE id=?";
            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,c.getId());
            pst.executeUpdate();
            System.out.println("Chéquier supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }



}
