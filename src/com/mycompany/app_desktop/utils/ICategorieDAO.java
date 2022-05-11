/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app_desktop.utils;
import com.mycompany.app_desktop.entities.CategorieCarte;
import java.sql.SQLException;

import java.util.List;

/**
 *
 * @author Khaled
 */
public interface ICategorieDAO <T>{
    
    void insertCategorie(CategorieCarte e );
    void deleteCategorie(int id);
    void updateCategorie(CategorieCarte e);
    List<CategorieCarte> fechCategorie();
    CategorieCarte fetchCategorietById(int id);
    List<CategorieCarte> fetchPopularCategorie();
    
    
    
}
