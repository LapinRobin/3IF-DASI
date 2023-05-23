/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.*;

/**
 *
 * @author mvieiraper
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Intervenant {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    private String telephone;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private Niveau niveauMin;
    private Niveau niveauMax;
    private boolean libre;
    private Integer nbInterventions;

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }
    
    protected Intervenant(){};

    public Intervenant(String nom, String prenom, String telephone, String mail, String motDePasse, Niveau niveauMin, Niveau niveauMax) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.niveauMin = niveauMin;
        this.niveauMax = niveauMax;
        this.libre = true;
        this.nbInterventions = 0;
    }

    public int getNbInterventions() {
        return nbInterventions;
    }

    public void setNbInterventions(int nbInterventions) {
        this.nbInterventions = nbInterventions;
    }
    
    public void incrementerNbInterventions() {
        this.nbInterventions++;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Niveau getNiveauMin() {
        return niveauMin;
    }

    public void setNiveauMin(Niveau niveauMin) {
        this.niveauMin = niveauMin;
    }

    public Niveau getNiveauMax() {
        return niveauMax;
    }

    public void setNiveauMax(Niveau niveauMax) {
        this.niveauMax = niveauMax;
    }

    @Override
    public String toString() {
        return "Intervenant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", mail=" + mail + ", motDePasse=" + motDePasse + ", niveauMin=" + niveauMin + ", niveauMax=" + niveauMax + '}';
    }
    
}
