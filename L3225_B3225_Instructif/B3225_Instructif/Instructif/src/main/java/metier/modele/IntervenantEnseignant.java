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
public class IntervenantEnseignant extends Intervenant{
    String typeEtablissement;

    public IntervenantEnseignant() {
    }

    public IntervenantEnseignant(String nom, String prenom, String telephone, String mail, String motDePasse, Niveau niveauMin, Niveau niveauMax, String typeEtablissement) {
        super(nom, prenom, telephone, mail, motDePasse, niveauMin, niveauMax);
        this.typeEtablissement = typeEtablissement;
    }

    public String getTypeEtablissement() {
        return typeEtablissement;
    }

    public void setTypeEtablissement(String typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }

    @Override
    public String toString() {
        return "IntervenantEnseignant{" + "id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", telephone=" + getTelephone() + ", mail=" + getMail() + ", motDePasse=" + getMotDePasse() + ", niveauMin=" + getNiveauMin() + ", niveauMax=" + getNiveauMax() + "typeEtablissement=" + typeEtablissement + '}';
    }
}
