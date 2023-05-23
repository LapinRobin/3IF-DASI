/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import dao.EleveDao;
import dao.IntervenantDao;
import dao.InterventionDao;
import dao.JpaUtil;
import dao.MatiereDao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.IntervenantAutre;
import metier.modele.IntervenantEnseignant;
import metier.modele.IntervenantEtudiant;
import metier.modele.Intervention;
import metier.modele.Matiere;
import metier.modele.Niveau;

/**
 *
 * @author theo
 */
public class ServiceAdmin {

    public ServiceAdmin() {
    }

    public void initialiser() {
        initialiserMatieres();
        initialiserIntervenants();
        initialiserEleves();
        initialiserInterventions();
    }

    void initialiserIntervenants() {
        List<Intervenant> intervenants = new ArrayList<Intervenant>();
        try {
            JpaUtil.creerContextePersistance();

            intervenants.add(new IntervenantEtudiant("Lovelace", "Ada", "07 50 22 03 66", "alovelace@gmail.com", "123456", Niveau.SIXIEME, Niveau.TROISIEME, "INSA Lyon", "Informatique"));
            intervenants.add(new IntervenantEnseignant("Verne", "Jules", "06 23 66 03 44", "jverne@gmail.com", "0000", Niveau.SECONDE, Niveau.TERMINALE, "Lycée"));
            intervenants.add(new IntervenantAutre("Yourcenar", "Simone", "07 22 44 54 00", "syourcenar@gmail.com", "bonjour", Niveau.CINQUIEME, Niveau.PREMIERE, "Retraité"));
            intervenants.add(new IntervenantEnseignant("Bassi", "Laura", "06 59 34 96 47", "laura.bassi@yahoo.fr", "4321", Niveau.SECONDE, Niveau.PREMIERE, "Lycée"));
            intervenants.add(new IntervenantEtudiant("Macron", "Emmanuel", "06 60 70 80 90", "macron@free.fr", "49.3", Niveau.SIXIEME, Niveau.SIXIEME, "Sciences Po", "Politique"));
            intervenants.add(new IntervenantAutre("Turing", "Alan", "07 77 77 99 99", "aturing@gmail.com", "123456", Niveau.QUATRIEME, Niveau.SECONDE, "Ingénieur"));

            IntervenantDao iDao = new IntervenantDao();

            JpaUtil.ouvrirTransaction();
            for (Intervenant i : intervenants) {
                iDao.create(i);
            }
            JpaUtil.validerTransaction();
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    void initialiserMatieres() {
        List<Matiere> matieres = new ArrayList<Matiere>();
        try {
            JpaUtil.creerContextePersistance();

            matieres.add(new Matiere("SVT"));
            matieres.add(new Matiere("Maths"));
            matieres.add(new Matiere("Histoire - Géo"));
            matieres.add(new Matiere("Français"));
            matieres.add(new Matiere("Physique Chimie"));
            matieres.add(new Matiere("Anglais"));
            matieres.add(new Matiere("Technologie"));
            matieres.add(new Matiere("Espagnol"));
            matieres.add(new Matiere("Musique"));

            MatiereDao mDao = new MatiereDao();

            JpaUtil.ouvrirTransaction();
            for (Matiere m : matieres) {
                mDao.create(m);
            }
            JpaUtil.validerTransaction();
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    void initialiserEleves() {
        ServiceClient s = new ServiceClient();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = sdf.parse("03/12/2001");
            s.inscriptionEleve(new Eleve("Hugo", "Victor", date, "vh@gmail.com", "toto", Niveau.SECONDE), "0693331W");
            date = sdf.parse("01/01/2010");
            s.inscriptionEleve(new Eleve("Blaise", "Pascal", date, "bp@gmail.com", "motDePasse", Niveau.QUATRIEME), "0690082P");
            date = sdf.parse("23/04/2008");
            s.inscriptionEleve(new Eleve("Newton", "Isaac", date, "inewton@gmail.com", "pomme", Niveau.SIXIEME), "0691478G");
            date = sdf.parse("24/04/2005");
            s.inscriptionEleve(new Eleve("Markov", "Andrey", date, "markov@yahoo.fr", "aaaa", Niveau.TERMINALE), "0691644M");
            date = sdf.parse("20/05/2005");
            s.inscriptionEleve(new Eleve("Perelman", "Grigori", date, "perelman@yahoo.fr", "0000", Niveau.TERMINALE), "0691644M");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void initialiserInterventions() {
        ServiceClient sc = new ServiceClient();

        List<Eleve> eleves = listerTousEleves();
        List<Matiere> matieres = sc.listerMatieres();
        List<Intervenant> intervenants = listerTousIntervenants();
        List<Intervention> interventions = new ArrayList<Intervention>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            JpaUtil.creerContextePersistance();

            interventions.add(new Intervention(eleves.get(2), matieres.get(4), "je ne comprends pas les chaines de Markov", new Date(), intervenants.get(3), (long)5, 4));
            intervenants.get(3).incrementerNbInterventions();
            interventions.add(new Intervention(eleves.get(4), matieres.get(4), "calculer l'aire d'un cercle", new Date(), null, null, null));
            interventions.add(new Intervention(eleves.get(3), matieres.get(0), "j'aimerais apprendre a compter en anglais", new Date(), intervenants.get(1), (long)6, 1));
            intervenants.get(1).incrementerNbInterventions();
            Date date = sdf.parse("01/04/2023");
            interventions.add(new Intervention(eleves.get(0), matieres.get(8), "comment coder un quicksort", date, intervenants.get(4), (long)4, 4));
            intervenants.get(4).incrementerNbInterventions();
            interventions.add(new Intervention(eleves.get(1), matieres.get(8), "c'est quoi l'algo de Dijstra", new Date(), intervenants.get(4), (long)10, 5));
            intervenants.get(4).incrementerNbInterventions();

            InterventionDao iDao = new InterventionDao();
            IntervenantDao i2Dao = new IntervenantDao();

            JpaUtil.ouvrirTransaction();

            for (Intervention i : interventions) {
                iDao.create(i);
            }

            for (Intervenant i : intervenants) {
                i2Dao.update(i);
            }

            JpaUtil.validerTransaction();
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public List<Intervenant> listerTousIntervenants() {
        List<Intervenant> l;
        try {
            JpaUtil.creerContextePersistance();
            IntervenantDao iDao = new IntervenantDao();
            l = iDao.listerTousIntervenants();
        } catch (Exception e) {
            l = null;
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return l;
    }

    public List<Eleve> listerTousEleves() {
        List<Eleve> l;
        try {
            JpaUtil.creerContextePersistance();
            EleveDao eDao = new EleveDao();
            l = eDao.listerTousEleves();
        } catch (Exception e) {
            l = null;
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return l;
    }

    public List<Intervention> listerToutesInterventions() {
        List<Intervention> l;
        try {
            JpaUtil.creerContextePersistance();
            InterventionDao iDao = new InterventionDao();
            l = iDao.listerToutesInterventions();
        } catch (Exception e) {
            l = null;
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        return l;
    }
}
