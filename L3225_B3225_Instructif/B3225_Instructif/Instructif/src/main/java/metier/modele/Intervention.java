/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mvieiraper
 */
@Entity
public class Intervention {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Eleve eleve;
    @ManyToOne
    private Matiere matiere;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    private Intervenant intervenant;
    private Long dureeEnMinutes;
    private Integer note;
    
    
    public Intervention() {
    }

    public Intervention(Eleve eleve, Matiere matiere, String description, Date date) {
        this.eleve = eleve;
        this.description = description;
        this.date = date;
        this.matiere = matiere;
    }

    public Intervention(Eleve eleve, Matiere matiere, String description, Date date, Intervenant intervenant, Long dureeEnMinutes, Integer note) {
        this.eleve = eleve;
        this.matiere = matiere;
        this.description = description;
        this.date = date;
        this.intervenant = intervenant;
        this.dureeEnMinutes = dureeEnMinutes;
        this.note = note;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDuree(Long dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    @Override
    public String toString() {
        return "Intervention{" + "id=" + id + ", eleve=" + eleve + ", matiere=" + matiere + ", description=" + description + ", date=" + date + ", intervenant=" + intervenant + ", dureeEnMinutes=" + dureeEnMinutes + ", note=" + note + '}';
    }
    
}
