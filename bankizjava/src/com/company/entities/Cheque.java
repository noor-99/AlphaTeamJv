package com.company.entities;

import java.util.Date;

public class Cheque {
    private int id ;
    private int proprietaire_id;
    private Date date_cheque;
    private String nom_client_id;
    private double montant;
    private String lieu;
    private int signature;
    private int client_tel;
    private int idchequiers_id;
    private int destinataire_id;
    private String rib_sender;
    private String rib_reciever;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProprietaire_id() {
        return proprietaire_id;
    }

    public void setProprietaire_id(int proprietaire_id) {
        this.proprietaire_id = proprietaire_id;
    }

    public Date getDate_cheque() {
        return date_cheque;
    }

    public void setDate_cheque(Date date_cheque) {
        this.date_cheque = date_cheque;
    }

    public String getNom_client_id() {
        return nom_client_id;
    }

    public void setNom_client_id(String nom_client_id) {
        this.nom_client_id = nom_client_id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getSignature() {
        return signature;
    }

    public void setSignature(int signature) {
        this.signature = signature;
    }

    public int getClient_tel() {
        return client_tel;
    }

    public void setClient_tel(int client_tel) {
        this.client_tel = client_tel;
    }

    public int getDestinataire_id() {
        return destinataire_id;
    }

    public void setDestinataire_id(int destinataire_id) {
        this.destinataire_id = destinataire_id;
    }

    public String getRib_sender() {
        return rib_sender;
    }

    public void setRib_sender(String rib_sender) {
        this.rib_sender = rib_sender;
    }

    public String getRib_reciever() {
        return rib_reciever;
    }

    public void setRib_reciever(String rib_reciever) {
        this.rib_reciever = rib_reciever;
    }

    public Cheque() {
    }

    public Cheque(int id, int proprietaire_id, Date date_cheque, String nom_client_id, double montant, String lieu, int signature, int client_tel, int idchequiers_id, int destinataire_id, String rib_sender, String rib_reciever) {
        this.id = id;
        this.proprietaire_id = proprietaire_id;
        this.date_cheque = date_cheque;
        this.nom_client_id = nom_client_id;
        this.montant = montant;
        this.lieu = lieu;
        this.signature = signature;
        this.client_tel = client_tel;
        this.idchequiers_id = idchequiers_id;
        this.destinataire_id = destinataire_id;
        this.rib_sender = rib_sender;
        this.rib_reciever = rib_reciever;
    }

    public int getIdchequiers_id() {
        return idchequiers_id;
    }

    public void setIdchequiers_id(int idchequiers_id) {
        this.idchequiers_id = idchequiers_id;
    }

    public Cheque(int proprietaire_id, Date date_cheque, String nom_client_id, double montant, String lieu, int signature, int client_tel, int idchequiers_id, int destinataire_id, String rib_sender, String rib_reciever) {
        this.proprietaire_id = proprietaire_id;
        this.date_cheque = date_cheque;
        this.nom_client_id = nom_client_id;
        this.montant = montant;
        this.lieu = lieu;
        this.signature = signature;
        this.client_tel = client_tel;
        this.idchequiers_id = idchequiers_id;
        this.destinataire_id = destinataire_id;
        this.rib_sender = rib_sender;
        this.rib_reciever = rib_reciever;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "id=" + id +
                ", proprietaire_id=" + proprietaire_id +
                ", date_cheque=" + date_cheque +
                ", nom_client_id='" + nom_client_id + '\'' +
                ", montant=" + montant +
                ", lieu='" + lieu + '\'' +
                ", signature=" + signature +
                ", client_tel=" + client_tel +
                ", destinataire_id=" + destinataire_id +
                ", rib_sender='" + rib_sender + '\'' +
                ", rib_reciever='" + rib_reciever + '\'' +
                '}';
    }
}
