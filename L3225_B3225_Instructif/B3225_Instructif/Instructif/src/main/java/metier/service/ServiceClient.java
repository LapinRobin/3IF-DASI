/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import com.google.maps.model.LatLng;
import dao.EleveDao;
import dao.IntervenantDao;
import dao.EtablissementDao;
import dao.InterventionDao;
import dao.JpaUtil;
import dao.MatiereDao;
import java.util.Date;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Etablissement;
import metier.modele.Intervention;
import metier.modele.Matiere;
import util.EducNetApi;
import util.GeoNetApi;
import util.Message;

/**
 *
 * @author mvieiraper
 */
public class ServiceClient {

    public ServiceClient() {
    }

    public Intervenant trouverIntervenantParId(Long id) {
        Intervenant i;
        try {
            JpaUtil.creerContextePersistance();
            IntervenantDao eDao = new IntervenantDao();
            i = eDao.trouverIntervenantParId(id);
        } catch (Exception ex) {
            i = null;
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return i;
    }
    
    public Eleve trouverEleveParId(Long id) {
        Eleve e;
        try {
            JpaUtil.creerContextePersistance();
            EleveDao eDao = new EleveDao();
            e = eDao.trouverEleveParId(id);
        } catch (Exception ex) {
            e = null;
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return e;
    }
    
    

    public Intervenant authentifierIntervenant(String mail, String mdp) {
        Intervenant i;
        try {
            JpaUtil.creerContextePersistance();
            IntervenantDao iDao = new IntervenantDao();
            i = iDao.authentifierIntervenant(mail, mdp);
        } catch (Exception ex) {
            i = null;
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return i;
    }

    public void inscriptionEleve(Eleve eleve, String codeUai) {
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            EtablissementDao etablDao = new EtablissementDao();

            Etablissement etablissement = etablDao.trouverEtablissementParUai(codeUai);

            if (etablissement == null) {
                EducNetApi api = new EducNetApi();
                List<String> result = api.getInformationCollege(codeUai);
                if (result == null) {
                    result = api.getInformationLycee(codeUai);
                }
                if (result != null) {
                    String uai = result.get(0);
                    String nom = result.get(1);
                    String secteur = result.get(2);
                    String codeCommune = result.get(3);
                    String nomCommune = result.get(4);
                    String codeDepartement = result.get(5);
                    String nomDepartement = result.get(6);
                    String academie = result.get(7);
                    String ips = result.get(8);

                    LatLng coordsEtablissement = GeoNetApi.getLatLng(nom + ", " + nomCommune);
                    etablissement = new Etablissement(uai, nom, secteur, Integer.parseInt(codeCommune),
                            nomCommune, Integer.parseInt(codeDepartement), nomDepartement,
                            academie, Float.parseFloat(ips), coordsEtablissement.lat, coordsEtablissement.lng);
                    etablDao.create(etablissement);
                }
            }

            if (etablissement != null) {
                eleve.setEtablissement(etablissement);
                EleveDao eleveDao = new EleveDao();
                eleveDao.create(eleve);
                JpaUtil.validerTransaction();
                Message.envoyerMail("instructif@instructif.fr", eleve.getMail(), 
                        "Bienvenue sur le réseau INSTRUCT'IF",
                        "Bonjour " + eleve.getPrenom() +  ", nous te confirmons ton inscription sur le réseau"
                        + "INSTRUCT'IF. Si tu as besoin d'un soutien pour tes leçons ou tes devoirs, rends-toi sur"
                        + "notre site pour une mise en relation avec un intervenant.");
            } else {
                Message.envoyerMail("instructif@instructif.fr", eleve.getMail(), 
                        "Echec de l'inscription sur le réseau INSTRUCT'IF",
                        "Bonjour " + eleve.getPrenom() + ", ton inscription sur le réseau INSTRUCT'IF a"
                         + "malencontreusement echouée... Merci de recommencer ultérieurement.");
                JpaUtil.annulerTransaction();
            }
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            Message.envoyerMail("instructif@instructif.fr", eleve.getMail(), 
                        "Echec de l'inscription sur le réseau INSTRUCT'IF",
                        "Bonjour " + eleve.getPrenom() + ", ton inscription sur le réseau INSTRUCT'IF a"
                         + "malencontreusement echouée... Merci de recommencer ultérieurement.");
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public Eleve authentifierEleve(String mail, String mdp) {
        Eleve e;
        try {
            JpaUtil.creerContextePersistance();
            EleveDao eDao = new EleveDao();
            e = eDao.authentifierEleve(mail, mdp);
        } catch (Exception ex) {
            e = null;
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return e;
    }

    public Eleve authentifierEleve(Long id, String mdp) {
        Eleve e;
        try {
            JpaUtil.creerContextePersistance();
            EleveDao eDao = new EleveDao();
            e = eDao.authentifierEleve(id, mdp);
        } catch (Exception ex) {
            e = null;
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return e;
    }

    public List<Etablissement> listerEtablissements() {
        List<Etablissement> l;
        try {
            JpaUtil.creerContextePersistance();
            EtablissementDao eDao = new EtablissementDao();
            l = eDao.listerEtablissements();
        } catch (Exception e) {
            l = null;
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return l;
    }
    
    public List<Intervention> listerInterventionsPourEleve(Eleve eleve) {
        List<Intervention> l;
        try {
            JpaUtil.creerContextePersistance();
            InterventionDao iDao = new InterventionDao();
            l = iDao.listerInterventionsPourEleve(eleve);
        } catch (Exception e) {
            l = null;
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return l;
    }
    
    public List<Intervention> listerInterventionsPourIntervenant(Intervenant intervenant) {
        List<Intervention> l;
        try {
            JpaUtil.creerContextePersistance();
            InterventionDao iDao = new InterventionDao();
            l = iDao.listerInterventionsPourIntervenant(intervenant);
        } catch (Exception e) {
            l = null;
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return l;
    }
    
    public Intervention getDemandeIntervention(Intervenant intervenant) {
        Intervention demande;
        try{
            JpaUtil.creerContextePersistance();
            InterventionDao iDao = new InterventionDao();
            demande = iDao.getDemandeIntervention(intervenant);
            
        } catch(Exception e){
            demande = null;
            JpaUtil.annulerTransaction();
            e.printStackTrace();
        } finally{
            JpaUtil.fermerContextePersistance();
        }
        return demande;
    }
    
    public Intervention getInterventionEnCours(Eleve eleve) {
        Intervention interventionEnCours;
        try {
            JpaUtil.creerContextePersistance();
            InterventionDao iDao = new InterventionDao();
            interventionEnCours = iDao.getInterventionEnCours(eleve);

        } catch (Exception e) {
            interventionEnCours = null;
            JpaUtil.annulerTransaction();
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return interventionEnCours;
    }
    
    public Intervention demandeIntervention(Intervention intervention) {
        try{
            JpaUtil.creerContextePersistance(); 
            
            InterventionDao interventiontDao = new InterventionDao();
            IntervenantDao intervenantDao = new IntervenantDao();
      
            Intervenant intervenant = intervenantDao.obtenirIntervenantPourIntervention(intervention);
            
            if(intervenant != null){
                intervention.setIntervenant(intervenant);
                intervenant.setLibre(false);
            }
            
            JpaUtil.ouvrirTransaction();
            interventiontDao.create(intervention);
            if(intervenant != null){
                intervenantDao.update(intervenant);
            }
            JpaUtil.validerTransaction();
            
            if(intervenant != null){
                Message.envoyerNotification(intervenant.getTelephone(), 
                    "Bonjour " + intervenant.getPrenom() + ", merci de prendre en charge la demande de soutien en \" " +
                    intervention.getMatiere().getNom() + " \" demandée à " + intervention.getDate().getHours() + "h" +
                    intervention.getDate().getMinutes() + " par " + intervention.getEleve().getPrenom() +
                    " en classe de " + intervention.getEleve().getNiveau().toString());
            }
        } catch(Exception e){
            JpaUtil.annulerTransaction();
            e.printStackTrace();
        } finally{
            JpaUtil.fermerContextePersistance();
            return intervention;
        }
    }
    
    public void accepterIntervention(Intervention intervention) {
        try{
            JpaUtil.creerContextePersistance();
            intervention.setDate(new Date());
            InterventionDao interventiontDao = new InterventionDao();
            JpaUtil.ouvrirTransaction();
            interventiontDao.update(intervention);
            JpaUtil.validerTransaction();
        } catch(Exception e){
            JpaUtil.annulerTransaction();
            e.printStackTrace();
        } finally{
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public void finaliserIntervention(Intervention intervention) {

        try {
            JpaUtil.creerContextePersistance();
            intervention.getIntervenant().setLibre(true);
            intervention.getIntervenant().incrementerNbInterventions();
            IntervenantDao intervenantDao = new IntervenantDao();
            InterventionDao interventiontDao = new InterventionDao();
            JpaUtil.ouvrirTransaction();
            intervenantDao.update(intervention.getIntervenant());
            interventiontDao.update(intervention);
            JpaUtil.validerTransaction();
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public List<Matiere> listerMatieres() {
        List<Matiere> l;
        try {
            JpaUtil.creerContextePersistance();
            MatiereDao eDao = new MatiereDao();
            l = eDao.listerMatieres();
        } catch (Exception e) {
            l = null;
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return l;
    }
    
    public Float moyenneIntervenant(Intervenant intervenant) {
        Float moyenne;
        try {
            JpaUtil.creerContextePersistance();
            InterventionDao iDao = new InterventionDao();
            moyenne = iDao.moyenneIntervenant(intervenant);
        } catch (Exception e) {
            moyenne = null;
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return moyenne;
    }
    
    public Long nbInterventionsParMatieres(Matiere matiere) {
        Long nb;
        try {
            JpaUtil.creerContextePersistance();
            InterventionDao iDao = new InterventionDao();
            nb = iDao.nbInterventionsParMatieresIntervenant(matiere);
        } catch (Exception e) {
            nb = null;
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return nb;
    }
    
}
