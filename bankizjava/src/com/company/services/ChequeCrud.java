package com.company.services;

import com.company.entities.Cheque;

import com.company.entities.Chequier;
import com.company.utils.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChequeCrud {


    public void ajouterCheque(Cheque c) {
        try {
            String requete = "INSERT INTO cheques (proprietaire_id,date_cheque,montant,lieu,signature,client_tel,destinataire_id,idchequiers_id,rib_sender,rib_reciever) VALUES "
                    +"(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,c.getProprietaire_id());
          //  java.text.SimpleDateFormat sdf =
                  //  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //  pst.setDate(2, Date.valueOf(sdf.format(c.getDate_cheque())));
            pst.setDate(2, (Date) c.getDate_cheque());
            pst.setDouble(3,c.getMontant());
            pst.setString(4,c.getLieu());
            pst.setInt(5,c.getSignature());
            pst.setInt(6,c.getClient_tel());
            pst.setInt(7,c.getDestinataire_id());
            pst.setInt(8,c.getIdchequiers_id());
            pst.setString(9,c.getRib_sender());
            pst.setString(10,c.getRib_reciever());
            pst.executeUpdate();
            System.out.println("Cheque ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("Nour: probleme dans les l'odre des champs dans BD");


        }
    }

    public void UpdateCheque(Cheque c) {

        String requete = "UPDATE cheques SET montant=?,lieu=?,client_tel=?,idchequiers_id=?  WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            pst.setDouble(1,c.getMontant());
            pst.setString(2,c.getLieu());
            pst.setInt(3,c.getClient_tel());
            pst.setInt(4,c.getIdchequiers_id());
            pst.setInt(5,c.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Cheque> listerCheque() {
        List<Cheque> myList = new ArrayList();

        // (proprietaire_id,date_cheque,montant,lieu,signature,client_tel,destinataire_id,idchequiers_id,rib_sender,rib_reciever)

        try{
            Statement st =  MyConnection.getInstance().getCnx().createStatement();
            String req="select * from cheques";
            ResultSet rs= st.executeQuery(req);
            while(rs.next()){
                Cheque c = new Cheque();
                c.setId(rs.getInt(1));
                c.setProprietaire_id(rs.getInt(2));
                c.setDate_cheque(rs.getDate(3));
                c.setMontant(rs.getInt(4));
                c.setLieu(rs.getString(5));
                c.setSignature(rs.getInt(6));
                c.setClient_tel(rs.getInt(7));
                c.setDestinataire_id(rs.getInt(8));
                c.setIdchequiers_id(rs.getInt(9));
                c.setRib_sender(rs.getString(10));
                c.setRib_reciever(rs.getString(11));
                System.out.println( c.toString());
                myList.add(c);
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void supprimerCheque(Cheque c) {

        try {

            String requete = "DELETE  FROM cheques WHERE id=?";
            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,c.getId());
            pst.executeUpdate();
            System.out.println("Chéque supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public List<Cheque> listerCheq(int id) {
        List<Cheque> myownList = new ArrayList();
        try{

            String req="select * from cheques where idchequiers_id=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            ResultSet rs= st.executeQuery(req);
            while(rs.next()){
                Cheque c = new Cheque();
                c.setId(rs.getInt(1));
                c.setProprietaire_id(rs.getInt(2));
                c.setDate_cheque(rs.getDate(3));
                c.setMontant(rs.getInt(4));
                c.setLieu(rs.getString(5));
                c.setSignature(rs.getInt(6));
                c.setClient_tel(rs.getInt(7));
                c.setDestinataire_id(rs.getInt(8));
                c.setIdchequiers_id(rs.getInt(9));
                c.setRib_sender(rs.getString(10));
                c.setRib_reciever(rs.getString(11));
                System.out.println( c.toString());
                myownList.add(c);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } return myownList;
    }


    public List<Cheque> recherchecheque( String nom) {
        List<Cheque> myList = new ArrayList<Cheque>();
        String requete = null;
        try {

            requete = "SELECT * from cheques where lieu like '%" + nom + "%'"; //MAJUSCULE NON OBLIGATOIRE


            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next())
            {
                Cheque c = new Cheque();
                c.setId(rs.getInt(1));
                c.setProprietaire_id(rs.getInt(2));
                c.setMontant(rs.getInt(3));
                c.setLieu(rs.getString(4));
                c.setSignature(rs.getInt(5));
                c.setClient_tel(rs.getInt(6));
                c.setDate_cheque(rs.getDate(7));
                c.setDestinataire_id(rs.getInt(8));
                c.setIdchequiers_id(rs.getInt(9));
                c.setRib_sender(rs.getString(10));
                c.setRib_reciever(rs.getString(11));
                System.out.println( c.toString());
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Nour: probleme dans l'ordre des champs dans BD");
        }
        return myList;

    }
































}


















