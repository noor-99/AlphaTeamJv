/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app_desktop.services;
import com.mycompany.app_desktop.entities.CategorieCarte;
import com.mycompany.app_desktop.utils.myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hp
 */
public class CategorieCarteServices implements Iservice<CategorieCarte> {
          private  final Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public CategorieCarteServices() {
        conn = myconnection.getInstance().getCnx();
    }
@Override
    public void ajouter(CategorieCarte p) {
     String req = "INSERT INTO `categoriecarte` (`type`,`description`,`prix`,`montant_max`) VALUES ('" + p.getType() + "', '" + p.getDescription() + "', '" + p.getPrix() + "', '" + p.getMontant_max() +  "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("categorie created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void ajouter2(CategorieCarte p) {
        try {
            String req = "INSERT INTO `categoriecarte`  (`type`,`description`,`prix`,`montant_max`) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p.getType());
            ps.setString(2, p.getDescription());
             ps.setString(2, p.getPrix());
              
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       @Override
    public int supprimer(int id)throws SQLException {
    int i = 0;
        try {
            Statement ste = conn.createStatement();
            String sql = "delete from categoriecarte where id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieCarteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
     @Override
    public void modifier(CategorieCarte p) {
        try {
            String req = "UPDATE `categoriecarte` SET `idclient` = '" + p.getType() + "', `date_ex` = '" + p.getDescription() + "', `mp` = '" + p.getPrix() + "', `login` = '" + p.getMontant_max() +  "' WHERE `personne`.`id` = " + p.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<CategorieCarte> getAll() {
        List<CategorieCarte> list = new ArrayList<>();
         String req = "Select * from categoriecarte";
       try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                CategorieCarte p = new CategorieCarte();
                p.setId( rs.getInt("id") );
                p.setType(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setPrix(rs.getString(5));
                p.setMontant_max( rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    }
     
   

