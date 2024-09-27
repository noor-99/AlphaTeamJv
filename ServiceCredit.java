/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.workshopjdbc3a48.services;

import edu.workshopjdbc3a48.entities.Credit;

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
public class ServiceCredit {
       Connection cnx = DataSource.getInstance().getCnx();
       
       public void ajouter2(Credit c) {
        try {
            String req = "INSERT INTO credit (mont_credit,datepe,datede,duree_C,echeance,taux_Interet,decision,etat_Credit,type_Credit) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getMontCredit());
           // Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(c.getDatepe());
            ps.setDate(2, (Date) c.getDatepe());
            ps.setDate(3, (Date) c.getDatede());
            ps.setInt(4, c.getDureeC());
            ps.setDate(5, (Date) c.getEcheance());
            ps.setInt(6, c.getTauxInteret());
            ps.setBoolean(7, c.isDecision());
            ps.setString(8, c.getEtatCredit());
            ps.setString(9  , c.getTypeCredit());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   /* 
    public List<Credit> listeCredit(){
		List<Credit> list=new ArrayList<>();
		
		try {
			String req = "select * from credi"; 
            Statement st = Connection.getInstance().getCnx().createStatement();
            Credit rs = st.executeQuery(req);
            ServiceCredit ns=new ServiceCredit();
            while (rs.next()) {
            	Credit c=new Credit();
            	c.setId(rs.getInt("id"));
            	c.setClasse(rs.getString("classe"));
            	c.setNiveau(ns.getOneById(rs.getString("niveau_id")));
            	list.add(c);
            }
		}catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		
		return list;
	} */
         public List<Credit> getAll() {
        List<Credit> list = new ArrayList<>();
         String req = "Select * from credit";
       try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            ResultSet rs= pst.executeQuery();
            
            while(rs.next()){
                Credit p = new Credit();
                p.setId( rs.getInt("id") );
                p.setMontCredit(rs.getInt(2));
                p.setDatepe(rs.getDate(3));
                p.setDatede(rs.getDate(4));
                p.setDureeC( rs.getInt(5));
                p.setEcheance( rs.getDate(6));
                p.setTauxInteret( rs.getInt(7));
                p.setDecision( rs.getBoolean(8));
                p.setEtatCredit( rs.getString(9));
                 p.setTypeCredit( rs.getString(10));
                
                
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
           public void modifier(Credit p) {
        try {
            String req = "UPDATE credit SET mont_Credit = '" + p.getMontCredit() + "', datepe = '" + p.getDatepe() + "', duree_c = '" + p.getDureeC() + "', echeance = '" + p.getEcheance() + "', taux_interet = '" + p.getTauxInteret() +"', decision = '" + p.isDecision() +"', etat_credit = '" + p.getEtatCredit() +"', type_credit = '" + p.getTypeCredit() + "' WHERE credit.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Carte updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
          
        }
    }
            public void supprimer(int id) {
        try {
            String req = "DELETE FROM credit WHERE id = " + id;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Credit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public void simulation (int montCredit,int dureec,int salaire,String typeCredit) {
            int tauxInteret=20;
            switch (typeCredit) {
  case "voiture":
      tauxInteret=20;
      if(((montCredit)*(1+(tauxInteret/100))/(12*dureec) )>((salaire)/2))
    System.out.println(" tu peux avoir un credit voiture avec une écheance de"+((montCredit)*(1+(tauxInteret/100))/(12*dureec) )+"par mois sur"+dureec+"années");
      else
System.out.println("votre salaire de"+salaire+"vous ne permet pas d avoire cette credit esaiyer une autre foit");
    break;
  case "maison":
      tauxInteret=25;
      if(((montCredit)*(1+(tauxInteret/100))/(12*dureec) )>((salaire)/2))
    System.out.println(" tu peux avoir un credit voiture avec une écheance de"+((montCredit)*(1+(tauxInteret/100))/(12*dureec) )+"sur"+dureec+"années");
      else
System.out.println("votre salaire de"+salaire+"vous ne permet pas d avoire cette credit esaiyer une autre foit");
    break;
  case "etude":
      tauxInteret=15;
      if(((montCredit)*(1+(tauxInteret/100))/(12*dureec) )>((salaire)/2))
    System.out.println(" tu peux avoir un credit voiture avec une écheance de"+((montCredit)*(1+(tauxInteret/100))/(12*dureec) )+"sur"+dureec+"années");
      else
System.out.println("votre salaire de"+salaire+"vous ne permet pas d avoire cette credit esaiyer une autre foit");
    break;
  case "voiyage":
      tauxInteret=10;
      if(((montCredit)*(1+(tauxInteret/100))/(12*dureec) )>((salaire)/2))
    System.out.println(" tu peux avoir un credit voiture avec une écheance de"+((montCredit)*(1+(tauxInteret/100))/(12*dureec) )+"sur"+dureec+"années");
      else
System.out.println("votre salaire de"+salaire+"vous ne permet pas d avoire cette credit esaiyer une autre foit");
    break;
  case "afaire":
      tauxInteret=25;
      if(((montCredit)*(1+(tauxInteret/100))/(12*dureec) )>((salaire)/2))
    System.out.println(" tu peux avoir un credit voiture avec une écheance de"+((montCredit)*(1+(tauxInteret/100))/(12*dureec) )+"sur"+dureec+"années");
      else
System.out.println("votre salaire de"+salaire+"vous ne permet pas d avoire cette credit esaiyer une autre foit");
    break;
  
}
    }
}
