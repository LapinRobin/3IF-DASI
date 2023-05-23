/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.modele.Etablissement;

/**
 *
 * @author mvieiraper
 */
public class EtablissementDao {
    public void create(Etablissement etablissement){
        JpaUtil.obtenirContextePersistance().persist(etablissement);
    }
    
    public Etablissement update(Etablissement etablissement){
        return JpaUtil.obtenirContextePersistance().merge(etablissement);
    }
    
    public void delete(Etablissement etablissement){
        JpaUtil.obtenirContextePersistance().remove(etablissement);
    }
    
    public Etablissement trouverEtablissementParUai(String uai){
        return JpaUtil.obtenirContextePersistance().find(Etablissement.class, uai);
    }
    
    public List<Etablissement> listerEtablissements(){
        String s = "SELECT e FROM Etablissement e ORDER BY e.nom";
        TypedQuery query;
        query = JpaUtil.obtenirContextePersistance().createQuery(s, Etablissement.class);
        return query.getResultList();
    }
}
