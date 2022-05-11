package com.mycompany.app_desktop.entities;

import java.util.Date;

/**
 *
 * @author yoser oualha
 */
public class Utilisateur {
    private int id;
    private int cin_u;
    private String nom_u;
    private String prenom_u;
    private String date_naissance ;
    private String email_u;
    private int num_tel;
    private String role;
    private String mot_de_passe;

    public Utilisateur( int cin_u, String nom_u, String prenom_u, String date_naissance, String email_u, int num_tel, String role, String mot_de_passe) {
        //this.id = id;
        this.cin_u = cin_u;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.date_naissance = date_naissance;
        this.email_u = email_u;
        this.num_tel = num_tel;
        this.role = role;
        this.mot_de_passe = mot_de_passe;
    }

    public Utilisateur() {
       }

    

    public int getId() {
        return id;
    }

    public int getCin_u() {
        return cin_u;
    }

    public String getNom_u() {
        return nom_u;
    }

    public String getPrenom_u() {
        return prenom_u;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public String getEmail_u() {
        return email_u;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public String getRole() {
        return role;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCin_u(int cin_u) {
        this.cin_u = cin_u;
    }

    public void setNom_u(String nom_u) {
        this.nom_u = nom_u;
    }

    public void setPrenom_u(String prenom_u) {
        this.prenom_u = prenom_u;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setEmail_u(String email_u) {
        this.email_u = email_u;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "cin_u=" + cin_u + ", nom_u=" + nom_u + ", prenom_u=" + prenom_u + ", date_naissance=" + date_naissance + ", email_u=" + email_u + ", num_tel=" + num_tel + ", role=" + role + ", mot_de_passe=" + mot_de_passe + '}';
    }
    
    
}
