package model.beans;

import jakarta.persistence.*;

import java.sql.Date;

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
    private Date datesignup;
    @Basic
    @Column(name = "nbtotalsales")
    private int nbtotalsales;

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

    public Date getDatesignup() {
        return datesignup;
    }

    public void setDatesignup(Date datesignup) {
        this.datesignup = datesignup;
    }

    public int getNbtotalsales() {
        return nbtotalsales;
    }

    public void setNbtotalsales(int nbtotalsales) {
        this.nbtotalsales = nbtotalsales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendeurEntity that = (VendeurEntity) o;

        if (id != that.id) return false;
        if (nbtotalsales != that.nbtotalsales) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (adresse != null ? !adresse.equals(that.adresse) : that.adresse != null) return false;
        if (datesignup != null ? !datesignup.equals(that.datesignup) : that.datesignup != null) return false;

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
        return result;
    }
}
