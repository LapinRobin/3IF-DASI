/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Intervenant;
import metier.modele.Intervention;
import metier.service.ServiceClient;

/**
 *
 * @author ysu
 */
public class ObtenirFinaliserInterventionAction extends Action {
    public void executer(HttpServletRequest request) {
        System.out.println("appel de ObtenirFinaliserInterventionAction");
        HttpSession session = request.getSession(true); 

        Long idIntervenant = (Long) session.getAttribute("idIntervenant");
        System.out.println("Session idIntervenant: " + idIntervenant);

        Long idIntervention = (Long) session.getAttribute("idIntervention");
        System.out.println("Session idIntervention: " + idIntervention);
        
        ServiceClient service = new ServiceClient();
        Intervenant intervenant = service.trouverIntervenantParId(idIntervenant);
        System.out.println("Retrieved Intervenant: " + intervenant);

        Intervention currIntervention = service.getDemandeIntervention(intervenant);
        System.out.println("Current Intervention: " + currIntervention);

        Long currIdIntervention = (currIntervention != null) ? currIntervention.getId() : null;
        System.out.println("Current Intervention ID: " + currIdIntervention);
        
        boolean deconnexion = false;
        if (currIdIntervention == null || !currIdIntervention.equals(idIntervention)) {
            deconnexion = true;
        }
        System.out.println("Deconnexion: " + deconnexion);

        request.setAttribute("deconnexion", deconnexion);       
    }
}
