/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Intervention;
import metier.service.ServiceClient;

/**
 *
 * @author ysu
 */
public class ObtenirFinaliserInterventionAction extends Action {
    public void executer(HttpServletRequest request) {
        
        // Code to be fixed
        Long idEleve = null;
        ServiceClient service = new ServiceClient();
        Eleve eleve = service.trouverEleveParId(idEleve);
        Intervention intervention = service.getInterventionEnCours(eleve);
        
        boolean deconnexion = false;
        if (intervention == null) {
            deconnexion = true;
        }
        request.setAttribute("deconnexion", deconnexion);       
    }
}
