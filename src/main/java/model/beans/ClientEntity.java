package model.beans;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "client", schema = "user_loop", catalog = "")
public class ClientEntity {
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
    @Column(name = "datesignup")
    private Timestamp datesignup;
    @Basic
    @Column(name = "adresse")
    private String adresse;
    @Basic
    @Column(name = "nbproduct")
    private int nbproduct;
    @Basic
    @Column(name = "histocommand")
    private String histocommand;
    @Basic
    @Column(name = "fidelity")
    private int fidelity;

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

    public Timestamp getDatesignup() {
        return datesignup;
    }

    public void setDatesignup(Timestamp datesignup) {
        this.datesignup = datesignup;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNbproduct() {
        return nbproduct;
    }

    public void setNbproduct(int nbproduct) {
        this.nbproduct = nbproduct;
    }

    public String getHistocommand() {
        return histocommand;
    }

    public void setHistocommand(String histocommand) {
        this.histocommand = histocommand;
    }

    public int getFidelity() {
        return fidelity;
    }

    public void setFidelity(int fidelity) {
        this.fidelity = fidelity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (id != that.id) return false;
        if (nbproduct != that.nbproduct) return false;
        if (fidelity != that.fidelity) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (datesignup != null ? !datesignup.equals(that.datesignup) : that.datesignup != null) return false;
        if (adresse != null ? !adresse.equals(that.adresse) : that.adresse != null) return false;
        if (histocommand != null ? !histocommand.equals(that.histocommand) : that.histocommand != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (datesignup != null ? datesignup.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + nbproduct;
        result = 31 * result + (histocommand != null ? histocommand.hashCode() : 0);
        result = 31 * result + fidelity;
        return result;
    }

    public void setUp(String nom, String password, String mail, String adresse){
        setNom(nom);
        setPassword(password);
        setMail(mail);
        setAdresse(adresse);
        setNbproduct(0);
        setHistocommand("");
        setDatesignup(new Timestamp(System.currentTimeMillis()));
        setFidelity(0);
    }
}
