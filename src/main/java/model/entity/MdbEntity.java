package model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mdb", schema = "stock_loop", catalog = "")
public class MdbEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "marque")
    private String marque;
    @Basic
    @Column(name = "prix")
    private int prix;
    @Basic
    @Column(name = "vendeur")
    private String vendeur;
    @Basic
    @Column(name = "stock")
    private String stock;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "couleur")
    private String couleur;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "sales")
    private int sales;
    @Basic
    @Column(name = "image")
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getVendeur() {
        return vendeur;
    }

    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MdbEntity mdbEntity = (MdbEntity) o;

        if (id != mdbEntity.id) return false;
        if (prix != mdbEntity.prix) return false;
        if (sales != mdbEntity.sales) return false;
        if (nom != null ? !nom.equals(mdbEntity.nom) : mdbEntity.nom != null) return false;
        if (marque != null ? !marque.equals(mdbEntity.marque) : mdbEntity.marque != null) return false;
        if (vendeur != null ? !vendeur.equals(mdbEntity.vendeur) : mdbEntity.vendeur != null) return false;
        if (stock != null ? !stock.equals(mdbEntity.stock) : mdbEntity.stock != null) return false;
        if (type != null ? !type.equals(mdbEntity.type) : mdbEntity.type != null) return false;
        if (couleur != null ? !couleur.equals(mdbEntity.couleur) : mdbEntity.couleur != null) return false;
        if (description != null ? !description.equals(mdbEntity.description) : mdbEntity.description != null)
            return false;
        if (image != null ? !image.equals(mdbEntity.image) : mdbEntity.image != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (marque != null ? marque.hashCode() : 0);
        result = 31 * result + prix;
        result = 31 * result + (vendeur != null ? vendeur.hashCode() : 0);
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (couleur != null ? couleur.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + sales;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
