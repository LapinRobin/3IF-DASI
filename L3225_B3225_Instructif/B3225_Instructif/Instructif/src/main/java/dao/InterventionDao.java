/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Intervention;
import metier.modele.Matiere;

/**
 *
 * @author mvieiraper
 */
public class InterventionDao {

    public InterventionDao() {
    }
    
    public void create(Intervention intervention){
        JpaUtil.obtenirContextePersistance().persist(intervention);
    }
    
    public Intervention update(Intervention intervention){
        return JpaUtil.obtenirContextePersistance().merge(intervention);
    }
    
    public void delete(Intervention intervention){
        JpaUtil.obtenirContextePersistance().remove(intervention);
    }
    
    public Intervention trouverInterventionParId(Long id){
        return JpaUtil.obtenirContextePersistance().find(Intervention.class, id);
    }
    
    public List<Intervention> listerInterventionsPourEleve(Eleve eleve){
        String s = "SELECT m FROM Intervention m where m.eleve.id = :eleve";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervention.class);
        query.setParameter("eleve", eleve.getId());
        return query.getResultList();
    }
    
    public List<Intervention> listerInterventionsPourIntervenant(Intervenant intervenant){
        String s = "SELECT i FROM Intervention i where i.intervenant.id = :intervenant";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervention.class);
        query.setParameter("intervenant", intervenant.getId());
        return query.getResultList();
    }
    
    public List<Intervention> listerToutesInterventions(){
        String s = "SELECT i FROM Intervention i";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervention.class);
        return query.getResultList();
    }
    
    public Intervention getDemandeIntervention(Intervenant intervenant) {
        String s = "SELECT i FROM Intervention i where i.intervenant.id = :intervenant AND i.dureeEnMinutes is null";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervention.class);
        query.setParameter("intervenant", intervenant.getId());
        Intervention intervention;
        List<Intervention> result = query.getResultList();
        if(result.isEmpty()){
            intervention = null;
        } else {
            intervention = result.get(0);
        }
        return intervention;
    }
    
    public Intervention getInterventionEnCours(Eleve eleve) {
        String s = "SELECT i FROM Intervention i where i.eleve.id = :eleve AND i.note is null";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervention.class);
        query.setParameter("eleve", eleve.getId());
        return (Intervention)query.getSingleResult();
    }
    
    public Float moyenneIntervenant(Intervenant intervenant) {
        String s1 = "SELECT count(i.note) FROM Intervention i where i.intervenant.id = :intervenant AND i.note is not null";
        TypedQuery query1;
        query1 = JpaUtil.obtenirContextePersistance().createQuery(s1, Integer.class);
        query1.setParameter("intervenant", intervenant.getId());
        Long count = (Long)query1.getSingleResult();
        String s2 = "SELECT sum(i.note) FROM Intervention i where i.intervenant.id = :intervenant AND i.note is not null";
        TypedQuery query2;
        query2 = JpaUtil.obtenirContextePersistance().createQuery(s2, Integer.class);
        query2.setParameter("intervenant", intervenant.getId());
        Long sum = (Long)query2.getSingleResult();
        return sum.floatValue()/count.floatValue();
    }
    
    public Long nbInterventionsParMatieresIntervenant(Matiere matiere) {
        String s = "SELECT count(i.note) FROM Intervention i where i.matiere.id = :matiere AND i.note is not null";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Integer.class);
        query.setParameter("matiere", matiere.getId());
        return (Long)query.getSingleResult();
    }
    
}
