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
public class AccepterDemandeAction extends Action {
    public void executer(HttpServletRequest request) {
        System.out.println("appel de AccepterDemandeAction");
        HttpSession session = request.getSession(true);
        Long idIntervenantSession = (Long) session.getAttribute("idIntervenant");
        
        boolean accepte = false;
        Intervention intervention = null;
        if(idIntervenantSession != null){
            ServiceClient service = new ServiceClient();
            Intervenant intervenant = service.trouverIntervenantParId(idIntervenantSession);
            intervention = service.getDemandeIntervention(intervenant);
            service.accepterIntervention(intervention);
            accepte = true;
            
            if (intervention != null) {
                Long idIntervention = intervention.getId();
                session.setAttribute("idIntervention", idIntervention);
            }
        } 
        else{
            System.out.println("Intervenant pas connecté");
        }
        request.setAttribute("accepte", accepte);       
    }
}
