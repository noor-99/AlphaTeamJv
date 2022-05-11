/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app_desktop.utils;
import com.mycompany.app_desktop.entities.CategorieCarte;

import java.sql.Date;
import java.sql.Timestamp;


/**
 *
 * @author Hrouz
 */
public class Statics {
    static Date tempDate = new Date(19992909);
    public static CategorieCarte currentUser = new CategorieCarte(1,"sss","sss","2022","255",tempDate,tempDate,"test@test.test");
public static int livreid;

    // 8,"Tester User",tempDate,"profilepic.jpg",1,500,"test@test.test"
}
