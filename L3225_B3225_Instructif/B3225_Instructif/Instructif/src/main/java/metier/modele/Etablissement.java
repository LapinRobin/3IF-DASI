/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mvieiraper
 */

@Entity
public class Etablissement {
    @Id
    private String uai;
    String nom;
    String secteur;
    Integer codeCommune;
    String nomCommune;
    Integer codeDepartement;
    String nomDepartement;
    String academie;
    Float ips;
    Double lat;
    Double lng;

    public Etablissement() {
    }

    public Etablissement(String uai, String nom, String secteur, Integer codeCommune, String nomCommune, Integer codeDepartement, String nomDepartement, String academie, Float ips, Double lat, Double lng) {
        this.uai = uai;
        this.nom = nom;
        this.secteur = secteur;
        this.codeCommune = codeCommune;
        this.nomCommune = nomCommune;
        this.codeDepartement = codeDepartement;
        this.nomDepartement = nomDepartement;
        this.academie = academie;
        this.ips = ips;
        this.lat = lat;
        this.lng = lng;
    }
    

    public String getUai() {
        return uai;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public Integer getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(Integer codeCommune) {
        this.codeCommune = codeCommune;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public Integer getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(Integer codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public String getAcademie() {
        return academie;
    }

    public void setAcademie(String academie) {
        this.academie = academie;
    }

    public Float getIps() {
        return ips;
    }

    public void setIps(Float ips) {
        this.ips = ips;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "uai=" + uai + ", nom=" + nom + ", secteur=" + secteur + ", codeCommune=" + codeCommune + ", nomCommune=" + nomCommune + ", codeDepartement=" + codeDepartement + ", nomDepartement=" + nomDepartement + ", academie=" + academie + ", ips=" + ips + ", lat=" + lat + ", lng=" + lng + '}';
    }  
}
