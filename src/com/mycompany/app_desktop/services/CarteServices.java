/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app_desktop.services;
import com.mycompany.app_desktop.entities.Carte;
import com.mycompany.app_desktop.utils.myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hp
 */
public class CarteServices implements Iservice<Carte> {
          private  final Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public CarteServices() {
        conn = myconnection.getInstance().getCnx();
    }
@Override
    public void ajouter(Carte p) {
     String req = "INSERT INTO `carte` (`idclient`,`date_ex`,`mp`,`login`,`num_carte`) VALUES ('" + p.getIdclient() + "', '" + p.getDate_ex() + "', '" + p.getMp() + "', '" + p.getLogin() + "', '" + p.getNum_carte() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Carte created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void ajouter2(Carte p) {
        try {
            String req = "INSERT INTO `carte`  (`idclient`,`date_ex`,`mp`,`login`,`num_carte`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p.getIdclient());
            ps.setString(2, p.getDate_ex());
             ps.setString(2, p.getMp());
              ps.setString(2, p.getLogin());
               ps.setString(2, p.getNum_carte());
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
            String sql = "delete from carte where id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CarteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
     @Override
    public void modifier(Carte p) {
        try {
            String req = "UPDATE `carte` SET `idclient` = '" + p.getIdclient() + "', `date_ex` = '" + p.getDate_ex() + "', `mp` = '" + p.getMp() + "', `login` = '" + p.getLogin() + "', `num_carte` = '" + p.getNum_carte() + "' WHERE `personne`.`id` = " + p.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Carte updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Carte> getAll() {
        List<Carte> list = new ArrayList<>();
         String req = "Select * from carte";
       try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Carte p = new Carte();
                p.setId( rs.getInt("id") );
                p.setIdclient(rs.getString(2));
                p.setDate_ex(rs.getString(3));
                p.setMp(rs.getString(4));
                p.setLogin( rs.getString(5) );
                p.setNum_carte( rs.getString(6) );
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    }
     
   

