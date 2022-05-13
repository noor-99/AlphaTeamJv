package edu.bankiz.entities;


public class Commentaire {

    private int id ;
    private int id_publication_id;

    public Commentaire(int id_publication_id, int nom_client_id, String description_commentaire, String date_commentaire, String fullname, String titre_publication) {
        this.id_publication_id = id_publication_id;
        this.nom_client_id = nom_client_id;
        this.description_commentaire = description_commentaire;
        this.date_commentaire = date_commentaire;
        this.fullname = fullname;
        this.titre_publication = titre_publication;
    }

    private int nom_client_id;
    private String description_commentaire;
    private String date_commentaire;
    private int parent_id;
    private int is_published;
    private String fullname;
    private String titre_publication;

    public Commentaire(String description_commentaire, String date_commentaire) {
        this.description_commentaire = description_commentaire;
        this.date_commentaire = date_commentaire;
    }

    public String getTitre_publication() {
        return titre_publication;
    }

    public void setTitre_publication(String titre_publication) {
        this.titre_publication = titre_publication;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Commentaire() {
    }

    public Commentaire(int id, int id_publication_id, int nom_client_id, String description_commentaire, String date_commentaire) {
        this.id = id;
        this.id_publication_id = id_publication_id;
        this.nom_client_id = nom_client_id;
        this.description_commentaire = description_commentaire;
        this.date_commentaire = date_commentaire;
    }

    public Commentaire(int id_publication_id, int nom_client_id, String description_commentaire, String date_commentaire, int parent_id, int is_published) {
        this.id_publication_id = id_publication_id;
        this.nom_client_id = nom_client_id;
        this.description_commentaire = description_commentaire;
        this.date_commentaire = date_commentaire;
        this.parent_id = parent_id;
        this.is_published = is_published;
    }

    public Commentaire(int id_publication_id, int nom_client_id, String description_commentaire, String date_commentaire) {
        this.id_publication_id = id_publication_id;
        this.nom_client_id = nom_client_id;
        this.description_commentaire = description_commentaire;
        this.date_commentaire = date_commentaire;
    }

    public Commentaire(int id_publication_id,String description_commentaire, String date_commentaire) {
        this.id_publication_id = id_publication_id;
        this.description_commentaire = description_commentaire;
        this.date_commentaire = date_commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_publication_id() {
        return id_publication_id;
    }

    public void setId_publication_id(int id_publication_id) {
        this.id_publication_id = id_publication_id;
    }

    public int getNom_client_id() {
        return nom_client_id;
    }

    public void setNom_client_id(int nom_client_id) {
        this.nom_client_id = nom_client_id;
    }

    public String getDescription_commentaire() {
        return description_commentaire;
    }

    public void setDescription_commentaire(String description_commentaire) {
        this.description_commentaire = description_commentaire;
    }

    public String getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(String date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getIs_published() {
        return is_published;
    }

    public void setIs_published(int is_published) {
        this.is_published = is_published;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", id_publication_id=" + id_publication_id +
                ", nom_client_id=" + nom_client_id +
                ", description_commentaire='" + description_commentaire + '\'' +
                ", date_commentaire='" + date_commentaire + '\'' +
                ", parent_id=" + parent_id +
                ", is_published=" + is_published +
                '}';
    }
}
