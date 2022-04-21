package edu.bankiz.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yoser oualha
 */
public class MyConnection {
    private final String url ="jdbc:mysql://localhost:3306/bankiz";
    private final String login="root";
    private final String pwd="";

    Connection cnx;
    public static MyConnection instance;
    private MyConnection() {
        try {
            cnx= DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ex.getMessage()");
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }



}