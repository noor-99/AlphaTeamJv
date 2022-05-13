
package edu.bankiz.services;

import edu.bankiz.entities.Chequier;
import edu.bankiz.tools.MyConnection;

import java.sql.*;
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
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            pst.setDate(2,date);
            pst.setString(3,c.getMotif_chequier());
            pst.setInt(4,c.getEtat_chequier());
            pst.setInt(5,c.getNom_client_id());
            pst.setInt(6,c.getClient_tel());
            pst.executeUpdate();
            System.out.println("Chequier ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void UpdateChequier(Chequier c) {

        String requete = "UPDATE chequier SET num_compte_id=?,motif_chequier=?,etat_chequier=?,client_tel=? WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,c.getNum_compte_id());
            pst.setString(2,c.getMotif_chequier());
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
                c.setMotif_chequier(rs.getString(4));
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

    public boolean updateEtat(int id)
    {
        ChequierCrud cs =new ChequierCrud();
        String requete = "update chequier set etat_chequier =1 where id=?";
        if (cs.VerifEtatChequier(id)==0)
        {
            try
            {
                PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setInt(1, id);
                pst.executeUpdate();
                System.out.println("Etat chequier updated");
                return true;
            } catch (SQLException ex)
            {
                System.out.println(ex.toString());
                return false;
            }
        }
        else System.out.println("non");
        return false ;
    }
    public int VerifEtatChequier(int m)
    {
        int v=0;
        String requete ="SELECT etat_chequier  from chequier  where id=?" ;

        try {
            PreparedStatement pst =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1,m);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                v=rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }return v ;

    }

    public static Chequier recherchechequier(int id) {
        Chequier ch= null;
        String requete = null;
        try {

            requete = "SELECT * from chequier where id="+id;
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next())
            {
                Chequier c = new Chequier();
                c.setId(rs.getInt(1));
                c.setNum_compte_id(rs.getInt(2));
                c.setDate_creation(rs.getDate(3));
                c.setMotif_chequier(rs.getString(4));
                c.setEtat_chequier(rs.getInt(5));
                c.setNom_client_id(rs.getInt(6));
                c.setClient_tel(rs.getInt(7));
                System.out.println( c.toString());
                ch=c;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Nour: probleme dans l'ordre des champs dans BD");
        }
        return ch;

    }

    public String countCheqAcc() {
        String nbrChequesAcc = "0";
        try {
            String req = "SELECT COUNT(*) FROM chequier WHERE etat_chequier = 1";
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                return nbrChequesAcc = rs.getString(1);
            }
            return nbrChequesAcc;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbrChequesAcc;

    }

    public String countCheqAtt() {
        String nbrChequesAtt = "0";
        try {
            String req = "SELECT COUNT(*) FROM chequier WHERE etat_chequier = 0";
            Statement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                return nbrChequesAtt = rs.getString(1);
            }
            return nbrChequesAtt;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbrChequesAtt;

    }
    public List<Chequier> listerChequierEtat() {
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
                c.setMotif_chequier(rs.getString(4));
                c.setEtat_chequier(rs.getInt(5));
                c.setNom_client_id(rs.getInt(6));
                c.setClient_tel(rs.getInt(7));
                System.out.println( c.toString());
                if(c.getEtat_chequier()==1)
                {
                    myList.add(c);
                }

            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<Chequier> listerChequierEtat0() {
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
                c.setMotif_chequier(rs.getString(4));
                c.setEtat_chequier(rs.getInt(5));
                c.setNom_client_id(rs.getInt(6));
                c.setClient_tel(rs.getInt(7));
                System.out.println( c.toString());
                if(c.getEtat_chequier()==0)
                {
                    myList.add(c);
                }

            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<Chequier> listerChequiercmp(int ncmp) {
        List<Chequier> myList = new ArrayList();

        try{

            String req="select * from chequier WHERE num_compte_id=?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1,ncmp);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Chequier c = new Chequier();
                c.setId(rs.getInt(1));
                c.setNum_compte_id(rs.getInt(2));
                c.setDate_creation(rs.getDate(3));
                c.setMotif_chequier(rs.getString(4));
                c.setEtat_chequier(rs.getInt(5));
                c.setNom_client_id(rs.getInt(6));
                c.setClient_tel(rs.getInt(7));
                System.out.println( c.toString());
//                System.out.println( c.toString());
//                System.out.println( c.toString());
                myList.add(c);
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }


    public int motifToId(String motif){
        int r=-1;
        try{
            String req="select * from chequier where motif_chequier=?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1,motif);


            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                r=rs.getInt(1);

            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;

    }
}