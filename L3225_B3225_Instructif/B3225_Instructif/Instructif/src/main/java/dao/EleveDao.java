/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Eleve;
import metier.modele.Intervention;

/**
 *
 * @author mvieiraper
 */
public class EleveDao {
    public void create(Eleve eleve){
        JpaUtil.obtenirContextePersistance().persist(eleve);
    }
    
    public Eleve update(Eleve eleve){
        return JpaUtil.obtenirContextePersistance().merge(eleve);
    }
    
    public void delete(Eleve eleve){
        JpaUtil.obtenirContextePersistance().remove(eleve);
    }
    
    public Eleve trouverEleveParId(Long id){
        return JpaUtil.obtenirContextePersistance().find(Eleve.class, id);
    }
    
    public Eleve authentifierEleve(String mail, String mdp){
        String s = "SELECT e FROM Eleve e WHERE e.mail = :mail AND e.motDePasse = :mdp";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Eleve.class);
        query.setParameter("mail", mail);
        query.setParameter("mdp", mdp);
        return (Eleve) query.getSingleResult();
    }
    
    public Eleve authentifierEleve(Long id, String mdp){
        String s = "SELECT e FROM Eleve e WHERE e.id = :id AND e.motDePasse = :mdp";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Eleve.class);
        query.setParameter("id", id);
        query.setParameter("mdp", mdp);
        return (Eleve) query.getSingleResult();
    }
    
    public List<Eleve> listerTousEleves(){
        String s = "SELECT e FROM Eleve e ORDER BY e.nom ASC";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Eleve.class);
        return query.getResultList();
    }
    
    public Intervention getInterventionEnCours(Eleve eleve) {
        String s = "SELECT i FROM Intervention i where i.eleve.id = :intervenant AND i.dureeMinutes is null";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervention.class);
        query.setParameter("intervenant", eleve.getId());
        
        Intervention intervention;
        try{
            intervention = (Intervention)query.getSingleResult();
        } catch(Exception e){
            intervention = null;
        }
        return intervention;
    }
}
