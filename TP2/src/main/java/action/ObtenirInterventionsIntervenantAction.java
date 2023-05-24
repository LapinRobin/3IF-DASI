/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import java.util.Objects;
import metier.modele.Intervenant;
import metier.modele.Intervention;
import static metier.modele.Intervention_.intervenant;
import metier.service.ServiceClient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author acremona
 */
public class ObtenirInterventionsIntervenantAction extends Action{
    public void executer(HttpServletRequest request) {
        System.out.println("appel de ObtenirInterventionsIntervenantAction");
        HttpSession session = request.getSession(true);
        Long idIntervenantSession = (Long) session.getAttribute("idIntervenant");
        
        List<Intervention> interventions = null;
        if(idIntervenantSession != null){
            ServiceClient service = new ServiceClient();
            Intervenant intervenant = service.trouverIntervenantParId(idIntervenantSession);
            interventions = service.listerInterventionsPourIntervenant(intervenant); 
        } 
        else{
            System.out.println("Intervenant pas connecté");
        }
        request.setAttribute("interventions", interventions);       
    }
}
