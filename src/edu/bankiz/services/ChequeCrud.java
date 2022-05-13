package edu.bankiz.services;

import edu.bankiz.entities.Cheque;
import edu.bankiz.entities.Chequier;
import edu.bankiz.tools.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChequeCrud {

    public void ajouterCheque(Cheque c) {
        try {
            String requete = "INSERT INTO cheques (proprietaire_id,montant,lieu,signature,client_tel,date_cheque,destinataire_id,idchequiers_id,rib_sender,rib_reciever) VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c.getProprietaire_id());
            pst.setDouble(2, c.getMontant());
            pst.setString(3, c.getLieu());
            pst.setInt(4, c.getSignature());
            pst.setInt(5, c.getClient_tel());

            Date date = new Date(Calendar.getInstance().getTime().getTime());
            pst.setDate(6, date);

            pst.setInt(7, c.getDestinataire_id());
            pst.setInt(8, c.getIdchequiers_id());
            pst.setString(9, c.getRib_sender());
            pst.setString(10, c.getRib_reciever());
            if (verifnbcheque(c.getIdchequiers_id())) {
                pst.executeUpdate();
                System.out.println("KAJB Cheque ajouté");
            } else {
                System.out.println("Vous avez dépassez les 10 cheques, votre chequier est plein!");
            }

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

            pst.setDouble(1, c.getMontant());
            pst.setString(2, c.getLieu());
            pst.setInt(3, c.getClient_tel());
            pst.setInt(4, c.getIdchequiers_id());
            pst.setInt(5, c.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Cheque> Chequelister() {
        List<Cheque> myList = new ArrayList();

// (proprietaire_id,date_cheque,montant,lieu,signature,client_tel,destinataire_id,idchequiers_id,rib_sender,rib_reciever)
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            String req = "select * from cheques";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cheque c = new Cheque();
                c.setId(rs.getInt(1));
                c.setProprietaire_id(rs.getInt(2));

                java.util.Date date = null;
                Timestamp timestamp = rs.getTimestamp(7);
                if (timestamp != null)
                    date = new java.util.Date(timestamp.getTime());

                c.setDate_cheque(rs.getDate(7));
                c.setMontant(rs.getInt(3));
                c.setLieu(rs.getString(4));
                c.setSignature(rs.getInt(5));
                c.setClient_tel(rs.getInt(6));
                c.setDestinataire_id(rs.getInt(8));
                c.setIdchequiers_id(rs.getInt(9));
                c.setRib_sender(rs.getString(10));
                c.setRib_reciever(rs.getString(11));
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void supprimerCheque(int c) {

        try {

            String requete = "DELETE  FROM cheques WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c);
            pst.executeUpdate();
            System.out.println("Chéque supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public List<Cheque> listerCheq(int id) {
        List<Cheque> myList = new ArrayList();
        try {


            String req = "select * from cheques where idchequiers_id=" + id;
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);


            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cheque c = new Cheque();
                c.setId(rs.getInt(1));
                c.setProprietaire_id(rs.getInt(2));

                java.util.Date date = null;
                Timestamp timestamp = rs.getTimestamp(7);
                if (timestamp != null)
                    date = new java.util.Date(timestamp.getTime());

                c.setDate_cheque(date);
                c.setMontant(rs.getInt(3));
                c.setLieu(rs.getString(4));
                c.setSignature(rs.getInt(5));
                c.setClient_tel(rs.getInt(6));
                c.setDestinataire_id(rs.getInt(8));
                c.setIdchequiers_id(rs.getInt(9));
                c.setRib_sender(rs.getString(10));
                c.setRib_reciever(rs.getString(11));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }


    public List<Cheque> recherchecheque(String nom) {
        List<Cheque> myList = new ArrayList<Cheque>();
        String requete = null;

        try {
            requete = "SELECT * from cheques where lieu like '%" + nom + "%'";
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
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
                System.out.println(c.toString());
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Nour: probleme dans l'ordre des champs dans BD");
        }
        return myList;

    }

    public List<Cheque> afficherChequeTri() {
        List<Cheque> MyList = new ArrayList<>();
        String requete = null;

        try {
            requete = "SELECT * from cheques ORDER BY date_cheque ASC";

            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
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

                MyList.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return MyList;
    }

    public List<Cheque> afficherChequeTriDecroi() {
        List<Cheque> MyList = new ArrayList<>();
        String requete = null;

        try {
            requete = "SELECT * FROM cheques ORDER BY date_cheque DESC";

            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
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

                MyList.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return MyList;
    }

    public boolean verifnbcheque(int id) {
        String requete = "SELECT * from cheques where idchequiers_id=" + id;
        Statement pst = null;
        try {
            int i = 0;
            pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);

            while (rs.next()) {
                i++;
            }
            if (i > 9) {
                ChequierCrud cc = new ChequierCrud();
                Chequier c = cc.recherchechequier(id);
                c.setEtat_chequier(0);


                cc.UpdateChequier(c);

                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    public void transaction(int id) {
        CompteController cc = new CompteController();


    }

    public String proptoid(int id) {
        String r = "";
        try {
            String req = "select * from compte where id=?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                r = rs.getString(2);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;


    }

    public String desttoid(int id) {
        String r = "";
        try {
            String req = "select * from compte where id=?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                r = rs.getString(2);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }

    public String idtoname(String id) {
        String r = "";
        try {
            String req = "select * from utilisateur where id=?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, id);


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                r = rs.getString(4) + " " + rs.getString(3);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }


}

//TRIPARNOM
//    /*
//   * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
//* and open the template in the editor.
//*/
//package pkg3a20collection;
//import java.util.Comparator;
//* @author bhk
//*/
//public class TrieParNom implements Comparator<Etudiant>{
//  public int compare(Etudiant e1, Etudiant e2){
//    return e1.getNom().compareTo(e2.getNom());
//}
//}
