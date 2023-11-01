package model.beans;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "vendeur", schema = "user_loop", catalog = "")
public class VendeurEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "adresse")
    private String adresse;
    @Basic
    @Column(name = "datesignup")
    private Timestamp datesignup;
    @Basic
    @Column(name = "nbtotalsales")
    private int nbtotalsales;
    @Basic
    @Column(name = "addRight")
    private int addRight;
    @Basic
    @Column(name = "removeRight")
    private int removeRight;
    @Basic
    @Column(name = "modifyRight")
    private int modifyRight;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Timestamp getDatesignup() {
        return datesignup;
    }

    public void setDatesignup(Timestamp datesignup) {
        this.datesignup = datesignup;
    }

    public int getNbtotalsales() {
        return nbtotalsales;
    }

    public void setNbtotalsales(int nbtotalsales) {
        this.nbtotalsales = nbtotalsales;
    }

    public int getAddRight() {
        return addRight;
    }

    public void setAddRight(int addRight) {
        this.addRight = addRight;
    }

    public int getRemoveRight() {
        return removeRight;
    }

    public void setRemoveRight(int removeRight) {
        this.removeRight = removeRight;
    }

    public int getModifyRight() {
        return modifyRight;
    }

    public void setModifyRight(int modifyRight) {
        this.modifyRight = modifyRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendeurEntity vendeur = (VendeurEntity) o;

        if (id != vendeur.id) return false;
        if (nbtotalsales != vendeur.nbtotalsales) return false;
        if (addRight != vendeur.addRight) return false;
        if (removeRight != vendeur.removeRight) return false;
        if (modifyRight != vendeur.modifyRight) return false;
        if (nom != null ? !nom.equals(vendeur.nom) : vendeur.nom != null) return false;
        if (password != null ? !password.equals(vendeur.password) : vendeur.password != null) return false;
        if (mail != null ? !mail.equals(vendeur.mail) : vendeur.mail != null) return false;
        if (adresse != null ? !adresse.equals(vendeur.adresse) : vendeur.adresse != null) return false;
        if (datesignup != null ? !datesignup.equals(vendeur.datesignup) : vendeur.datesignup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (datesignup != null ? datesignup.hashCode() : 0);
        result = 31 * result + nbtotalsales;
        result = 31 * result + addRight;
        result = 31 * result + removeRight;
        result = 31 * result + modifyRight;
        return result;
    }
}
