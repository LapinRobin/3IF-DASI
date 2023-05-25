/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import java.util.Objects;
import metier.modele.Eleve;
import metier.modele.Intervention;
import static metier.modele.Intervention_.eleve;
import metier.service.ServiceClient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author acremona
 */
public class ObtenirInterventionsEleveAction extends Action{
    public void executer(HttpServletRequest request) {
        System.out.println("appel de ObtenirInterventionsEleveAction");
        HttpSession session = request.getSession(true);
        Long idEleveSession = (Long) session.getAttribute("idEleve");
        
        List<Intervention> interventions = null;
        if(idEleveSession != null){
            ServiceClient service = new ServiceClient();
            Eleve eleve = service.trouverEleveParId(idEleveSession);
            interventions = service.listerInterventionsPourEleve(eleve); 
        } 
        else{
            System.out.println("Eleve pas connecté");
        }
        request.setAttribute("interventions", interventions);       
    }
}
