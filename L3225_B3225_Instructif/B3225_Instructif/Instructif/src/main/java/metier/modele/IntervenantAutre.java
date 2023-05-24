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
public class IntervenantAutre extends Intervenant {
    private String activite;

    public IntervenantAutre() {
    }

    public IntervenantAutre(String nom, String prenom, String telephone, String mail, String motDePasse, Niveau niveauMin, Niveau niveauMax, String activite) {
        super(nom, prenom, telephone, mail, motDePasse, niveauMin, niveauMax);
        this.activite = activite;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "IntervenantAutre{" + "id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", telephone=" + getTelephone() + ", mail=" + getMail() + ", motDePasse=" + getMotDePasse() + ", niveauMin=" + getNiveauMin() + ", niveauMax=" + getNiveauMax() + "activite=" + activite + '}';
    }
    
    
}
