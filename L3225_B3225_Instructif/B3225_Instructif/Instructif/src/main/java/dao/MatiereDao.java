/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Matiere;

/**
 *
 * @author mvieiraper
 */
public class MatiereDao {
    public void create(Matiere matiere){
        JpaUtil.obtenirContextePersistance().persist(matiere);
    }
    
    public Matiere update(Matiere matiere){
        return JpaUtil.obtenirContextePersistance().merge(matiere);
    }
    
    public void delete(Matiere matiere){
        JpaUtil.obtenirContextePersistance().remove(matiere);
    }
    
    public Matiere trouverMatiereParId(Long id){
        return JpaUtil.obtenirContextePersistance().find(Matiere.class, id);
    }
    
    public List<Matiere> listerMatieres(){
        String s = "SELECT m FROM Matiere m ORDER BY m.nom ASC";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Matiere.class);
        return query.getResultList();
    }
}
