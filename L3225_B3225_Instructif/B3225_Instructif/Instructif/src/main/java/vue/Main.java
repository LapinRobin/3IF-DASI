/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.JpaUtil;
import java.text.SimpleDateFormat;
import java.util.List;
import metier.service.ServiceClient;
import java.util.Date;
import metier.modele.Eleve;
import metier.modele.Etablissement;
import metier.modele.Intervenant;
import metier.modele.Niveau;
import metier.modele.Intervention;
import metier.modele.Matiere;
import metier.service.ServiceAdmin;
import util.Saisie;

/**
 *
 * @author mvieiraper
 */
public class Main {

    static Eleve eleve = null;
    static Intervenant intervenant = null;
    static Intervention intervention = null;

    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        initialiser();

       /* boolean mRun = true;

        while (mRun) {

            if (intervenant != null) {
                System.out.println("Connecté comme Intervenant " + intervenant.getPrenom() + " " + intervenant.getNom());
            } else if (eleve != null) {
                System.out.println("Connecté comme Eleve " + eleve.getPrenom() + " " + eleve.getNom());
            } else {
                System.out.println("Non connecté");
            }

            System.out.println("Qu'est-ce que vous voulez faire?");
            System.out.println("1  - Inscrire élève");
            System.out.println("2  - Authentifier élève");
            System.out.println("3  - Lister les interventions d'un élève");
            System.out.println("4  - Demander une intervention");
            System.out.println("5  - Finaliser une intervention");
            System.out.println("6  - Authentifier intervenant");
            System.out.println("7  - Voir demande d'intervention en cours d'un intervenant");
            System.out.println("8  - statistiques d'un intervenant");
            System.out.println("9  - Lister les interventions d'un intervenant");
            System.out.println("10 - Accepter une intervention");
            System.out.println("11 - Lister les matières");
            System.out.println("12 - Lister les établissements");
            System.out.println("13 - Lister les élèves");
            System.out.println("14 - Lister les intervenants");
            System.out.println("15 - Lister les interventions");
            System.out.println("16 - Quitter");

            Integer option = Saisie.lireInteger("Option choisie: ");
            System.out.println();

            switch (option) {
                case 1:
                    inscrireEleveInteractif();
                    break;
                case 2:
                    authentifierEleveInteractif();
                    break;
                case 3:
                    listerInterventionsEleve();
                    break;
                case 4:
                    demanderInterventionInteractif();
                    break;
                case 5:
                    finaliserInterventionInteractif();
                    break;
                case 6:
                    authentifierIntervenantInteractif();
                    break;
                case 7:
                    voirDemandeEnCours();
                    break;
                case 8:
                    statistiquesIntervenant();
                    break;
                case 9:
                    listerInterventionsIntervenant();
                    break;
                case 10:
                    accepterIntervention();
                    break;
                case 11:
                    listerMatieres();
                    break;
                case 12:
                    listerEtablissements();
                    break;
                case 13:
                    listerTousEleves();
                    break;
                case 14:
                    listerTousIntervenants();
                    break;
                case 15:
                    listerToutesInterventions();
                    break;
                case 16:
                    mRun = false;
                    break;
                default:
                    System.out.println("Option non valide. Essayez à nouveau.");
            }
        }
        */

        JpaUtil.fermerFabriquePersistance();
    }

    public static void initialiser() {
        ServiceAdmin s = new ServiceAdmin();
        s.initialiser();
    }
    
    static void inscrireEleveInteractif() {
        ServiceClient s = new ServiceClient();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String nom = Saisie.lireChaine("nom:");
        String prenom = Saisie.lireChaine("prenom:");
        Date date;
        try {
            date = sdf.parse(Saisie.lireChaine("Date de naissance (DD/MM/YYYY):"));
        } catch (Exception ex) {
            System.out.println("mauvaise date");
            date = new Date();
        }
        String adresseMail = Saisie.lireChaine("Adresse mail:");
        String mdp = Saisie.lireChaine("Mot de passe:");
        System.out.println("Niveau: Sixième = 0, Cinquième = 1, ..., Terminale = 6");
        Integer niveau = Saisie.lireInteger("Niveau: ");
        String etablissementUai = Saisie.lireChaine("Code UAI de l'établissement: ");

        Eleve e = new Eleve(nom, prenom, date, adresseMail, mdp, Niveau.values()[niveau]);
        s.inscriptionEleve(e, etablissementUai);
    }
    
    static void authentifierEleveInteractif() {
        // Logout de l'intervenant
        intervenant = null;
        intervention = null;

        ServiceClient s = new ServiceClient();

        String adresseMail = Saisie.lireChaine("Adresse mail:");
        String mdp = Saisie.lireChaine("Mot de passe:");

        eleve = s.authentifierEleve(adresseMail, mdp);

        if (eleve != null) {
            System.out.println("Authentification réussie!");
        } else {
            System.out.println("Echec!");
        }
    }
    
    static void listerInterventionsEleve() {
        if (eleve == null) {
            System.out.println("Vous n'êtes pas authentifié comme élève");
        } else {
            ServiceClient s = new ServiceClient();
            List<Intervention> interventions = s.listerInterventionsPourEleve(eleve);
            if (interventions.size() > 0) {
                System.out.println("Liste des interventions pour l'élève " + eleve.getPrenom() + " " + eleve.getNom());
                for (Intervention i : interventions) {
                    System.out.println(i);
                }
            } else {
                System.out.println("Il n'y a pas d'interventions pour l'élève " + eleve.getPrenom() + " " + eleve.getNom());
            }
        }
        System.out.println();
    }

    static void demanderInterventionInteractif() {
        if (eleve == null) {
            System.out.println("Vous n'êtes pas authentifié comme élève");
        } else {
            ServiceClient s = new ServiceClient();
            System.out.println("demande de soutien");
            List<Matiere> matieres = s.listerMatieres();
            System.out.println("Liste des matieres possibles: ");
            for (int i = 0; i < matieres.size(); i++) {
                System.out.println(i + " : " + matieres.get(i).getNom());
            }
            int indiceMatiere = Saisie.lireInteger("numero de la matière:");
            String description = Saisie.lireChaine("description");
            Intervention intervention = new Intervention(eleve, matieres.get(indiceMatiere), description, new Date());
            intervention = s.demandeIntervention(intervention);
            if (intervention.getIntervenant() == null) {
                System.out.println("Aucun intervenant n'est disponible");
            } else {
                System.out.println("L'intervenant " + intervention.getIntervenant().getPrenom() + " " + intervention.getIntervenant().getNom() + " va venir vous aidez");
            }
        }
        System.out.println();
    }
    
    static void finaliserInterventionInteractif() {
        if (eleve == null) {
            System.out.println("Vous n'êtes pas authentifié comme élève");
        } else {
            ServiceClient s = new ServiceClient();
            Intervention intervention = s.getInterventionEnCours(eleve);
            if (intervention == null) {
                System.out.println("Pas d'intervention en cours");
            } else {
                System.out.println("Intervention en cours:");
                System.out.println(intervention);
                System.out.println("Fin de l'intervention:");
                intervention.setNote(Saisie.lireInteger("Note:"));
                intervention.setDuree(((new Date()).getTime() - intervention.getDate().getTime())/60000);
                s.finaliserIntervention(intervention);
            }
        }
    }

    static void authentifierIntervenantInteractif() {
        // Logout de l'eleve
        eleve = null;
        intervention = null;

        ServiceClient s = new ServiceClient();

        String adresseMail = Saisie.lireChaine("Adresse mail:");
        String mdp = Saisie.lireChaine("Mot de passe:");

        intervenant = s.authentifierIntervenant(adresseMail, mdp);

        if (intervenant != null) {
            System.out.println("Authentification réussie!");
        } else {
            System.out.println("Echec!");
        }
    }
    
    static void voirDemandeEnCours() {
        if (intervenant == null) {
            System.out.println("Vous n'êtes pas authentifié comme intervenant");
        } else {
            ServiceClient s = new ServiceClient();
            Intervention intervention = s.getDemandeIntervention(intervenant);
            if (intervention == null) {
                System.out.println("Pas de Demande En Cours");
            } else {
                System.out.println("Demande en cours:");
                System.out.println(intervention);
            }
        }
        System.out.println();
    }
    
    static void statistiquesIntervenant() {
        if (intervenant == null) {
            System.out.println("Vous n'êtes pas authentifié comme intervenant");
        } else {
            ServiceClient s = new ServiceClient();
            Float moy = s.moyenneIntervenant(intervenant);
            List<Matiere> matieres = s.listerMatieres();
            if (moy == null || matieres == null) {
                System.out.println("il n'y pas d'interventions pour l'intervenant " + intervenant.getPrenom() + " " + intervenant.getNom());
            } else {
                System.out.println("statistique pour l'intervenant " + intervenant.getPrenom() + " " + intervenant.getNom());
                System.out.println("moyenne des notes : " + moy);
                System.out.println();
                System.out.println("statistique générale pour instructif");
                System.out.println("nombre d'interventions par matière");
                for (Matiere m : matieres) {
                    Long nb = s.nbInterventionsParMatieres(m);
                    System.out.println(m.getNom() +" : "+nb);
                }
            }
        }
        System.out.println();
    }

    static void listerInterventionsIntervenant() {
        if (intervenant == null) {
            System.out.println("Vous n'êtes pas authentifié comme intervenant");
        } else {
            ServiceClient s = new ServiceClient();
            List<Intervention> interventions = s.listerInterventionsPourIntervenant(intervenant);
            if (interventions.size() > 0) {
                System.out.println("Liste des interventions pour l'intervenant " + intervenant.getPrenom() + " " + intervenant.getNom());
                for (Intervention i : interventions) {
                    System.out.println(i);
                }
            } else {
                System.out.println("il n'y pas d'interventions pour l'intervenant " + intervenant.getPrenom() + " " + intervenant.getNom());
            }
        }
        System.out.println();
    }
    
    static void accepterIntervention() {
        if (intervenant == null) {
            System.out.println("Vous n'êtes pas authentifié comme intervenant");
        } else {
            ServiceClient s = new ServiceClient();
            Intervention intervention = s.getDemandeIntervention(intervenant);
            if (intervention == null) {
                System.out.println("Pas de Demande En Cours");
            } else {
                System.out.println("Demande en cours:");
                System.out.println(intervention);
                intervention.setDate(new Date());
                s.accepterIntervention(intervention);
                System.out.println("Demande acceptée");
            }
        }
        System.out.println();
    }

    public static void listerMatieres() {
        ServiceClient s = new ServiceClient();
        List<Matiere> matieres = s.listerMatieres();
        System.out.println("Liste des matieres : ");
        for (Matiere m : matieres) {
            System.out.println(m);
        }
        System.out.println();
    }

    public static void listerEtablissements() {
        ServiceClient s = new ServiceClient();
        List<Etablissement> etablissements = s.listerEtablissements();
        System.out.println("Liste des etablissements : ");
        for (Etablissement e : etablissements) {
            System.out.println(e);
        }
        System.out.println();
    }

    public static void listerTousEleves() {
        ServiceAdmin s = new ServiceAdmin();
        List<Eleve> eleves = s.listerTousEleves();
        System.out.println("Liste des eleves : ");
        for (Eleve e : eleves) {
            System.out.println(e);
        }
        System.out.println();
    }

    public static void listerTousIntervenants() {
        ServiceAdmin s = new ServiceAdmin();
        List<Intervenant> interventions = s.listerTousIntervenants();
        System.out.println("Liste des intervenants : ");
        for (Intervenant i : interventions) {
            System.out.println(i);
        }
        System.out.println();
    }

    public static void listerToutesInterventions() {
        ServiceAdmin s = new ServiceAdmin();
        List<Intervention> interventions = s.listerToutesInterventions();
        System.out.println("Liste des interventions : ");
        for (Intervention i : interventions) {
            System.out.println(i);
        }
        System.out.println();
    }

}
