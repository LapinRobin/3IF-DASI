/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.ArrayList;
import java.util.List;
import metier.service.ServiceClient;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Matiere;


/**
 *
 * @author acremona
 */
public class ObtenirStatsAction extends Action{
    public void executer(HttpServletRequest request) {
        System.out.println("appel de ObtenirStatsAction");
        
        List<Matiere> matieres = null;
        ServiceClient service = new ServiceClient();
        matieres = service.listerMatieres();
        
        List<Long> nbsInterventions = new ArrayList<>(matieres.size());
        for(Matiere matiere : matieres)
            nbsInterventions.add(service.nbInterventionsParMatieres(matiere));
         

        request.setAttribute("matieres", matieres);     
        request.setAttribute("nbsInterventions", nbsInterventions); 
    }
}
