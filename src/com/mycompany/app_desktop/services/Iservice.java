/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app_desktop.services;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author hp
 */
public interface Iservice<T> {
      public void ajouter(T p)throws SQLException;
    public int supprimer(int id)throws SQLException;
    public void modifier(T p)throws SQLException;
    public List<T> getAll()throws SQLException;
}
