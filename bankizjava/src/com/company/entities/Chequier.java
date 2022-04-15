package com.company.entities;

import java.util.Date;

public class Chequier {
    private int id ;
    private int num_compte_id;
    private Date date_creation;
    private int nom_client_id;
    private int client_tel;
    private int motif_chequier;
    private int etat_chequier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_compte_id() {
        return num_compte_id;
    }

    public void setNum_compte_id(int num_compte_id) {
        this.num_compte_id = num_compte_id;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public int getNom_client_id() {
        return nom_client_id;
    }

    public void setNom_client_id(int nom_client_id) {
        this.nom_client_id = nom_client_id;
    }

    public int getClient_tel() {
        return client_tel;
    }

    public void setClient_tel(int client_tel) {
        this.client_tel = client_tel;
    }

    public int getMotif_chequier() {
        return motif_chequier;
    }

    public void setMotif_chequier(int motif_chequier) {
        this.motif_chequier = motif_chequier;
    }

    public int getEtat_chequier() {
        return etat_chequier;
    }

    public void setEtat_chequier(int etat_chequier) {
        this.etat_chequier = etat_chequier;
    }

    public Chequier() {
    }

    public Chequier(int id, int num_compte_id, Date date_creation, int nom_client_id, int client_tel, int motif_chequier, int etat_chequier) {
        this.id = id;
        this.num_compte_id = num_compte_id;
        this.date_creation = date_creation;
        this.nom_client_id = nom_client_id;
        this.client_tel = client_tel;
        this.motif_chequier = motif_chequier;
        this.etat_chequier = etat_chequier;
    }

    public Chequier(int num_compte_id, Date date_creation, int nom_client_id, int client_tel, int motif_chequier, int etat_chequier) {
        this.num_compte_id = num_compte_id;
        this.date_creation = date_creation;
        this.nom_client_id = nom_client_id;
        this.client_tel = client_tel;
        this.motif_chequier = motif_chequier;
        this.etat_chequier = etat_chequier;
    }

    @Override
    public String toString() {
        return "Chequier{" +
                "id=" + id +
                ", num_compte_id=" + num_compte_id +
                ", date_creation=" + date_creation +
                ", nom_client_id='" + nom_client_id + '\'' +
                ", client_tel=" + client_tel +
                ", motif_chequier=" + motif_chequier +
                ", etat_chequier=" + etat_chequier +
                '}';
    }
}
