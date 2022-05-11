/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshopjdbc3a48.tests;

import edu.workshopjdbc3a48.entities.Credit;
import edu.workshopjdbc3a48.entities.OperationCredit;
import edu.workshopjdbc3a48.entities.Personne;
import edu.workshopjdbc3a48.services.ServiceCredit;
import edu.workshopjdbc3a48.services.ServiceOperationCredit;
import edu.workshopjdbc3a48.services.ServicePersonne;
import edu.workshopjdbc3a48.utils.DataSource;
import java.sql.SQLException;
import static java.time.Clock.system;
import static java.time.InstantSource.system;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author abdelazizmezri
 */
public class MainClass {
    
    public static void main(String[] args) throws SQLException {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
      
      
     ServiceCredit sc=new ServiceCredit(); 
     Credit c =new Credit(2210,date, date,2,date,20,true,"demande", "voiture",1);
     sc.ajouter2(c);
     List<Credit>maliste;
     /*
     maliste=sc.getAll();
      for(Credit i: maliste)
       {
       	 System.out.println (maliste.get(1));
       }
      List<Integer>a=sc.listecredit ();
        for(int elem: a)
       {
       	 System.out.println (elem);
       }*/
        float e =sc.getAllech(6);
        System.out.println (e);
     //System.out.println(maliste.get(2));
                                           
     
    
     /*
     ServiceOperationCredit c=new ServiceOperationCredit();
 OperationCredit cp =new OperationCredit(date,100,date,20,1,"payement",10);
     c.ajouter2(cp);
    int i= c.getAll10(10);
    
     System.out.println(i); */
     
     
     //sc.simulation (50000,3,1000,"maison");
     
     //sc.getAll();
     
     
     
     
     
     //ServiceCredit sc=new ServiceCredit(); 
     
     //sc.supprimer(14);
     /*ServiceOperationCredit sc=new ServiceOperationCredit(); 
     
     sc.getAll();*/
     
  
     
     
     
     
     /*sp.ajouter(p1);
        sp.ajouter(p2);
        sp.ajouter2(p3);
        sp.ajouter2(p4);*/
        
       // sp.supprimer(3);java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        
    }
    
}
