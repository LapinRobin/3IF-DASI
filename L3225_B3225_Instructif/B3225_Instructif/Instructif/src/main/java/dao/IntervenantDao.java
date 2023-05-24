/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Intervenant;
import metier.modele.Intervention;

/**
 *
 * @author mvieiraper
 */
public class IntervenantDao {

    public void create(Intervenant intervenant){
        JpaUtil.obtenirContextePersistance().persist(intervenant);
    }
    
    public Intervenant update(Intervenant intervenant){
        return JpaUtil.obtenirContextePersistance().merge(intervenant);
    }
    
    public void delete(Intervenant intervenant){
        JpaUtil.obtenirContextePersistance().remove(intervenant);
    }
    
    public Intervenant trouverIntervenantParId(Long id){
        return JpaUtil.obtenirContextePersistance().find(Intervenant.class, id);
       
    }
    
    public List<Intervenant> listerTousIntervenants(){
        String s = "SELECT e FROM Intervenant e ORDER BY e.nom ASC";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervenant.class);
        return query.getResultList();
    }
    
    public Intervenant authentifierIntervenant(String mail, String mdp){
        
        String s = "SELECT i FROM Intervenant i WHERE i.mail = :mail AND i.motDePasse = :mdp";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervenant.class);
        query.setParameter("mail", mail);
        query.setParameter("mdp", mdp);
        return (Intervenant)query.getSingleResult(); 
    }
    
    public Intervenant obtenirIntervenantPourIntervention(Intervention intervention){
        String s = "SELECT i FROM Intervenant i WHERE i.niveauMin <= :unNiveau AND i.niveauMax >= :unNiveau AND i.libre = TRUE order by i.nbInterventions";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Intervenant.class);
        query.setParameter("unNiveau", intervention.getEleve().getNiveau());
        
        Intervenant intervenant;
        try{
            intervenant = (Intervenant)query.getResultList().get(0);
        } catch(Exception e){
            intervenant = null;
        }
        
        return intervenant;
    }
    
}
