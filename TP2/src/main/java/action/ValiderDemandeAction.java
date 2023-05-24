package action;


import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
import metier.modele.Intervention;
import metier.modele.Matiere;
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
public class ValiderDemandeAction extends Action {
    public void executer(HttpServletRequest request) {
        ServiceClient service = new ServiceClient();
        HttpSession session = request.getSession(true);
        
        String nomMatiere = (String) request.getParameter("matiere");
        Matiere matiere = null;
        List<Matiere> matieres = service.listerMatieres();
        for(Matiere m : matieres){
            if (m.getNom().equals(nomMatiere)){
                matiere = m;
            }
        }
        
        System.out.println(matiere);
        
        String description = (String) request.getParameter("description");
        Long idEleve = (Long) session.getAttribute("idEleve");
        Eleve eleve = service.trouverEleveParId(idEleve);
        
        Intervention nouvelleIntervention = new Intervention(eleve, matiere, description, new Date());
        
        nouvelleIntervention = service.demandeIntervention(nouvelleIntervention);
        System.out.println(nouvelleIntervention);
        System.out.println(nouvelleIntervention.getIntervenant());
            
        request.setAttribute("intervenant", nouvelleIntervention.getIntervenant());       
    }
}
