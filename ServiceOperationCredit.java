/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.OperationCredit;

import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author user
 */
public class ServiceOperationCredit {
    Connection cnx = DataSource.getInstance().getCnx();
       
       public void ajouter2(OperationCredit c) {
        try {
            String req = "INSERT INTO operation_credit (date_Op,mont_Payer,echeance,taux_Interet,solvabilite,type_Operation) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
           
           // Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(c.getDatepe());
            ps.setDate(1, (Date) c.getDateOp());
           
            ps.setInt(2, c.getMontPayer());
            ps.setDate(3, (Date) c.getEcheance());
            ps.setInt(4, c.getTauxInteret());
             ps.setInt(5, c.getSolvabilite());
          
            ps.setString(6, c.getTypeOperation());
           
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}

     public List<OperationCredit> getAll() {
        List<OperationCredit> list = new ArrayList<>();
         String req = "Select * from operation_credit";
       try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                OperationCredit p = new OperationCredit();
                p.setId( rs.getInt("id") );
                
                p.setDateOp(rs.getDate(2));
                
                p.setMontPayer( rs.getInt(3));
                p.setEcheance( rs.getDate(4));
                p.setTauxInteret( rs.getInt(5));
                p.setSolvabilite( rs.getInt(6));
                
                p.setTypeOperation( rs.getString(7));
                
                
                
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
         public void modifier(OperationCredit p) {
        try {
            String req = "UPDATE operation_credit SET date_op = '" + p.getDateOp() + "', mont_payer = '" + p.getMontPayer() + "', duree_c = '" + "', echeance = '" + p.getEcheance() + "', taux_interet = '" + p.getTauxInteret() +"', solvabilite = '" + p.getSolvabilite() +"', type_operation = '" + p.getTypeOperation() + "' WHERE operation_credit.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Carte updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
          
        }
    }
              public void supprimer(int id) {
        try {
            String req = "DELETE FROM operation_credit WHERE id = " + id;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("OperationCredit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
