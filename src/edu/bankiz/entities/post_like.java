package edu.bankiz.entities;

public class post_like {
    private int id;
    private int publication_id;
    private int nom_client_id;

    public post_like() {

    }

    public post_like(int id, int publication_id, int nom_client_id) {
        this.id = id;
        this.publication_id = publication_id;
        this.nom_client_id = nom_client_id;
    }

    public post_like(int publication_id, int nom_client_id) {
        this.publication_id = publication_id;
        this.nom_client_id = nom_client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public int getNom_client_id() {
        return nom_client_id;
    }

    public void setNom_client_id(int nom_client_id) {
        this.nom_client_id = nom_client_id;
    }
}
