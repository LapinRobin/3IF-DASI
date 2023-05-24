package action;


import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Intervention;
import static metier.modele.Intervention_.description;
import static metier.modele.Intervention_.matiere;
import metier.service.ServiceClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acremona
 */
public class FinaliserInterventionAction extends Action {
    public void executer(HttpServletRequest request) {
        ServiceClient service = new ServiceClient();
        HttpSession session = request.getSession(true);
        
        Integer note = Integer.valueOf((String) request.getParameter("note"));
        Long idEleve = (Long) session.getAttribute("idEleve");
        Eleve eleve = service.trouverEleveParId(idEleve);
        Intervention intervention = service.getInterventionEnCours(eleve);
        
        boolean success = false;
        if(intervention != null){
            intervention.setNote(note);
            Date date1 = intervention.getDate(); // Date de départ
            Date date2 = new Date(); // Date d'arrivée
            long diffInMilliseconds = date2.getTime() - date1.getTime();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds);
            intervention.setDuree(diffInMinutes);

            service.finaliserIntervention(intervention);
            success = true;
        }
        request.setAttribute("success", success);       
    }
}
