package edu.bankiz.services;

import edu.bankiz.entities.Compte;
import edu.bankiz.tools.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompteController {
    public void ajouterCompte(Compte cmpt) {
        try {
            String requete = "INSERT INTO compte (num_compte,rib_compte,solde_compte,date_creation,type_compte,seuil_compte,taux_interet,etat_compte,fullname_client_id) VALUES "
                    + "('" + cmpt.getNum_compte() + "','" + cmpt.getRIB_Compte() + "','" + cmpt.getSolde_compte() + "','" + cmpt.getDate_creation() + "','" + cmpt.getType_compte() + "','"
                    + cmpt.getSeuil_compte() + "','" + cmpt.getTaux_interet() + "','" + cmpt.getEtat_compte() + "','" + cmpt.getFullname_client_id() + "')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Compte ajouté avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(CompteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ajouterCompte2(Compte cmpt) {
        try {
            String requete = "INSERT INTO compte (num_compte,rib_compte,solde_compte,date_creation,type_compte,seuil_compte,taux_interet,etat_compte,fullname_client_id) VALUES "
                    + "(?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setLong(1, cmpt.getNum_compte());
            pst.setString(2, cmpt.getRIB_Compte());
            pst.setFloat(3, cmpt.getSolde_compte());
            pst.setString(4, cmpt.getDate_creation());
            pst.setString(5, cmpt.getType_compte());
            pst.setFloat(6, cmpt.getSeuil_compte());
            pst.setFloat(7, cmpt.getTaux_interet());
            pst.setInt(8, cmpt.getEtat_compte());
            pst.setInt(9, cmpt.getFullname_client_id());
            pst.executeUpdate();
            System.out.println("Compte ajouté avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Compte> afficherComptes() {
        List<Compte> myList = new ArrayList();
        try {
            String requete = "SELECT * from compte INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Compte cmpt = new Compte();
                cmpt.setId(rs.getInt(1));
                cmpt.setNum_compte(rs.getLong("num_compte"));
                cmpt.setRIB_Compte(rs.getString("RIB_Compte"));
                cmpt.setSolde_compte(rs.getFloat("solde_compte"));
                cmpt.setSeuil_compte(rs.getFloat("seuil_compte"));
                cmpt.setType_compte(rs.getString("type_compte"));
                cmpt.setTaux_interet(rs.getFloat("taux_interet"));
                cmpt.setEtat_compte(rs.getInt("etat_compte"));
                cmpt.setDate_creation(rs.getString("date_creation"));
                cmpt.setFullname_client_id(rs.getInt("fullname_client_id"));
                myList.add(cmpt);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public Compte afficherMonCompte(int id) {
        Compte cmpt = new Compte();
//        int id = 23;
        try {
            String requete = "SELECT * from compte INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) where fullname_client_id=" + id + "";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                cmpt.setId(rs.getInt(1));
                cmpt.setNum_compte(rs.getLong("num_compte"));
                cmpt.setRIB_Compte(rs.getString("RIB_Compte"));
                cmpt.setSolde_compte(rs.getFloat("solde_compte"));
                cmpt.setSeuil_compte(rs.getFloat("seuil_compte"));
                cmpt.setType_compte(rs.getString("type_compte"));
                cmpt.setTaux_interet(rs.getFloat("taux_interet"));
                cmpt.setEtat_compte(rs.getInt("etat_compte"));
                cmpt.setDate_creation(rs.getString("date_creation"));
                cmpt.setFullname_client_id(rs.getInt("fullname_client_id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cmpt;
    }

    public Compte rechercherCompte(int id) {
        Compte cmpt = new Compte();
//        int id = 23;
        try {
            String requete = "SELECT * from compte INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) where compte.id=" + id + "";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                cmpt.setId(rs.getInt(1));
                cmpt.setNum_compte(rs.getLong("num_compte"));
                cmpt.setRIB_Compte(rs.getString("RIB_Compte"));
                cmpt.setSolde_compte(rs.getFloat("solde_compte"));
                cmpt.setSeuil_compte(rs.getFloat("seuil_compte"));
                cmpt.setType_compte(rs.getString("type_compte"));
                cmpt.setTaux_interet(rs.getFloat("taux_interet"));
                cmpt.setEtat_compte(rs.getInt("etat_compte"));
                cmpt.setDate_creation(rs.getString("date_creation"));
                cmpt.setFullname_client_id(rs.getInt("fullname_client_id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cmpt;
    }

    public Compte rechercherCompteRib(String rib) {
        String r = rib.replaceAll("\\s+","");
        Compte cmpt = new Compte();
        try {
            String requete = "SELECT * from compte INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) where replace(compte.rib_compte, ' ', '') = " + r + "";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                cmpt.setId(rs.getInt(1));
                cmpt.setNum_compte(rs.getLong("num_compte"));
                cmpt.setRIB_Compte(rs.getString("rib_compte"));
                cmpt.setSolde_compte(rs.getFloat("solde_compte"));
                cmpt.setSeuil_compte(rs.getFloat("seuil_compte"));
                cmpt.setType_compte(rs.getString("type_compte"));
                cmpt.setTaux_interet(rs.getFloat("taux_interet"));
                cmpt.setEtat_compte(rs.getInt("etat_compte"));
                cmpt.setDate_creation(rs.getString("date_creation"));
                cmpt.setFullname_client_id(rs.getInt("fullname_client_id"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cmpt;
    }

    public void modifierCompte(Compte cmpt, int id) {
        try {
            String requete = "UPDATE compte SET num_compte=?,rib_compte=?,solde_compte=?,date_creation=?,type_compte=?,seuil_compte=?,taux_interet=?,etat_compte=?,fullname_client_id=? WHERE id=? ";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setLong(1, cmpt.getNum_compte());
            pst.setString(2, cmpt.getRIB_Compte());
            pst.setFloat(3, cmpt.getSolde_compte());
            pst.setString(4, cmpt.getDate_creation());
            pst.setString(5, cmpt.getType_compte());
            pst.setFloat(6, cmpt.getSeuil_compte());
            pst.setFloat(7, cmpt.getTaux_interet());
            pst.setInt(8, cmpt.getEtat_compte());
            pst.setInt(9, cmpt.getFullname_client_id());
            pst.setInt(10, id);
            pst.executeUpdate();
            System.out.println("Compte modifié avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerCompte(int id) {
        try {
            String requete = "DELETE FROM compte WHERE id='" + id + "'";
            Statement pst = MyConnection.getInstance().getCnx().createStatement();
            pst.executeUpdate(requete);
            System.out.println("Compte supprimé avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(CompteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificationTransaction(int emetteur, String recepteur, float montant) {
        Compte cmpt_emetteur = this.rechercherCompte(emetteur);
        Compte cmpt_recepteur = this.rechercherCompteRib(recepteur);
        cmpt_emetteur.setSolde_compte(cmpt_emetteur.getSolde_compte() - montant);
        if (cmpt_recepteur.getId() != 0) {
            cmpt_recepteur.setSolde_compte(cmpt_recepteur.getSolde_compte() + montant);
            this.modifierCompte(cmpt_recepteur, cmpt_recepteur.getId());
        }
        this.modifierCompte(cmpt_emetteur, cmpt_emetteur.getId());
    }

//   ********************* Fonctions Autres ***************

    public long GenererNumCompte() {
        long leftLimit = 00000000000L;
        long rightLimit = 99999999999L;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return generatedLong;
    }
}
