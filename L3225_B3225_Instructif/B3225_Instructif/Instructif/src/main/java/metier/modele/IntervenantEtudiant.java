/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author mvieiraper
 */
@Entity
public class IntervenantEtudiant extends Intervenant {
    String universite;
    String specialite;

    public IntervenantEtudiant() {
    }

    public IntervenantEtudiant(String nom, String prenom, String telephone, String mail, String motDePasse, Niveau niveauMin, Niveau niveauMax, String universite, String specialite) {
        super(nom, prenom, telephone, mail, motDePasse, niveauMin, niveauMax);
        this.universite = universite;
        this.specialite = specialite;
    }

    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "IntervenantEtudiant{" + "id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", telephone=" + getTelephone() + ", mail=" + getMail() + ", motDePasse=" + getMotDePasse() + ", niveauMin=" + getNiveauMin() + ", niveauMax=" + getNiveauMax() + "universite=" + universite + ", specialite=" + specialite + '}';
    }
    
}
