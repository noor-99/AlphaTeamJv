/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app_desktop.utils;
 import com.mycompany.app_desktop.entities.Carte;
import com.mycompany.app_desktop.entities.CategorieCarte;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
/**
 *
 * @author hp
 */
public class myconnection {
    private static myconnection instance ;
    private Connection cnx ;
    
    private final String URL ="jdbc:mysql://localhost:3306/bankiz7";
    private final String LOGIN ="root";
    private final String PASSWORD ="";
    
    public myconnection(){
    try {
        cnx =DriverManager.getConnection(URL, LOGIN, PASSWORD);
        System.out.println("Connecting !");
        
    
    }catch(SQLException ex){
        System.err.println(ex.getMessage());
    }
}
    
    public static myconnection getInstance(){
     if (instance == null){
        instance = new myconnection();
    }
    return instance ;
    
    }
    public Connection getCnx(){
    return cnx ;
    }
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bankiz7","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
           
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
     public static ObservableList<Carte> getDataCarte(){
        Connection conn = ConnectDb();
        ObservableList<Carte> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from carte");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
   list.add(new Carte(rs.getInt("id") ,rs.getString("idclinet") ,rs.getTimestamp("date_ex"), rs.getString("mp"), rs.getString("login"), rs.getString("num_carte")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
       public static ObservableList<CategorieCarte> getDataCategorieCarte(){
        Connection conn = ConnectDb();
        ObservableList<CategorieCarte> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps;
            ps = conn.prepareStatement("select * from categoriecarte");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
   list.add(new CategorieCarte(rs.getString("type") ,rs.getString("description"), rs.getString("prix"), rs.getString("montant_max"), rs.getTimestamp("date_categorie"), rs.getString("image")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
     
     
   
     
    }
   


