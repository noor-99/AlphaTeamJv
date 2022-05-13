/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bankiz.tools;
import edu.bankiz.entities.CategorieCarte;

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
