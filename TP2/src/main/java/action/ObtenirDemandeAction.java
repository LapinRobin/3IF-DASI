/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import metier.modele.Intervenant;
import metier.modele.Intervention;
import metier.service.ServiceClient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author acremona
 */
public class ObtenirDemandeAction extends Action{
    public void executer(HttpServletRequest request) {
        System.out.println("appel de ObtenirDemandeAction");
        HttpSession session = request.getSession(true);
        Long idIntervenantSession = (Long) session.getAttribute("idIntervenant");
        
        Intervention demandeEnCours = null;
        if(idIntervenantSession != null){
            ServiceClient service = new ServiceClient();
            Intervenant intervenant = service.trouverIntervenantParId(idIntervenantSession);
            demandeEnCours = service.getDemandeIntervention(intervenant); 
        } 
        else{
            System.out.println("Intervenant pas connecté");
        }
        request.setAttribute("demande", demandeEnCours);       
    }
}
