/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.List;
import metier.service.ServiceClient;
import javax.servlet.http.HttpServletRequest;
import metier.modele.Matiere;


/**
 *
 * @author acremona
 */
public class ObtenirMatieresAction extends Action{
    public void executer(HttpServletRequest request) {
        System.out.println("appel de ObtenirMatieresAction");
        
        List<Matiere> matieres = null;
        ServiceClient service = new ServiceClient();
        matieres = service.listerMatieres(); 
        request.setAttribute("matieres", matieres);       
    }
}
