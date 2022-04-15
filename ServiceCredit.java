/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Credit;

import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class ServiceCredit {
       Connection cnx = DataSource.getInstance().getCnx();
       public void ajouter2(Credit c) {
        try {
            String req = "INSERT INTO credit (montCredit,datepe,datede,dureeC,echeance,tauxInteret,decision,etatCredit,typeCredit) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getMontCredit());
            ps.setString(2, c.getDatepe());
            ps.setString(3, c.getDatede());
            ps.setInt(4, c.getDureeC());
            ps.setString(5, c.getEcheance());
            ps.setInt(6, c.getTauxInteret());
            ps.setBoolean(7, c.isDecision());
            ps.setString(8, c.getEtatCredit());
            ps.setString(9  , c.getTypeCredit());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
