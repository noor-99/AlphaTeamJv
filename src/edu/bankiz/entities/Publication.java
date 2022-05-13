package edu.bankiz.entities;

import java.util.Date;

public class Publication {
    private int id;
    private int nom_client_id;
    private String titre_publication;
    private String description_publication;
    private String categorie_publication;
    private String date_publication;
    private String image_publication;
    private String fullname;

    public Publication(int nom_client_id, String titre_publication, String description_publication, String categorie_publication, String date_publication, String image_publication, String fullname) {
        this.nom_client_id = nom_client_id;
        this.titre_publication = titre_publication;
        this.description_publication = description_publication;
        this.categorie_publication = categorie_publication;
        this.date_publication = date_publication;
        this.image_publication = image_publication;
        this.fullname = fullname;
    }

    public Publication() {
    }

    public Publication(int id, int nom_client_id, String titre_publication, String description_publication, String categorie_publication, String date_publication, String image_publication) {
        this.id = id;
        this.nom_client_id = nom_client_id;
        this.titre_publication = titre_publication;
        this.description_publication = description_publication;
        this.categorie_publication = categorie_publication;
        this.date_publication = date_publication;
        this.image_publication = image_publication;
    }


    public Publication(int nom_client_id, String titre_publication, String description_publication, String categorie_publication, String image_publication) {
        this.nom_client_id = nom_client_id;
        this.titre_publication = titre_publication;
        this.description_publication = description_publication;
        this.categorie_publication = categorie_publication;
        this.image_publication = image_publication;
    }

    public Publication(String titre_publication, String description_publication, String categorie_publication, String date_publication, String image_publication, String fullname) {
        this.titre_publication = titre_publication;
        this.description_publication = description_publication;
        this.categorie_publication = categorie_publication;
        this.date_publication = date_publication;
        this.image_publication = image_publication;
        this.fullname = fullname;
    }

    public Publication(int nom_client_id, String titre_publication, String description_publication, String categorie_publication, String date_publication, String image_publication) {
        this.nom_client_id = nom_client_id;
        this.titre_publication = titre_publication;
        this.description_publication = description_publication;
        this.categorie_publication = categorie_publication;
        this.date_publication = date_publication;
        this.image_publication = image_publication;
    }

    public Publication(String titre_publication, String description_publication, String categorie_publication, String date_publication, String image_publication) {
        this.titre_publication = titre_publication;
        this.description_publication = description_publication;
        this.categorie_publication = categorie_publication;
        this.date_publication = date_publication;
        this.image_publication = image_publication;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNom_client_id() {
        return nom_client_id;
    }

    public void setNom_client_id(int nom_client_id) {
        this.nom_client_id = nom_client_id;
    }

    public String getTitre_publication() {
        return titre_publication;
    }

    public void setTitre_publication(String titre_publication) {
        this.titre_publication = titre_publication;
    }

    public String getDescription_publication() {
        return description_publication;
    }

    public void setDescription_publication(String description_publication) {
        this.description_publication = description_publication;
    }

    public String getCategorie_publication() {
        return categorie_publication;
    }

    public void setCategorie_publication(String categorie_publication) {
        this.categorie_publication = categorie_publication;
    }

    public String getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
    }

    public String getImage_publication() {
        return image_publication;
    }

    public void setImage_publication(String image_publication) {
        this.image_publication = image_publication;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", nom_client_id='" + nom_client_id + '\'' +
                ", titre_publication='" + titre_publication + '\'' +
                ", description_publication='" + description_publication + '\'' +
                ", categorie_publication='" + categorie_publication + '\'' +
                ", date_publication=" + date_publication +
                ", image_publication='" + image_publication + '\'' +
                '}';
    }
}
