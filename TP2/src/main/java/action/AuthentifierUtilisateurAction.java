package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Eleve;
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
public class AuthentifierUtilisateurAction extends Action {
    public void executer(HttpServletRequest request) {
        
        String login = (String) request.getParameter("login");
        String mdp = (String) request.getParameter("password");

        ServiceClient service = new ServiceClient();
        Eleve eleve = service.authentifierEleve(login, mdp);

        if(eleve != null){
            HttpSession session = request.getSession(true);
            session.setAttribute("idEleve", eleve.getId());
        }
        System.out.println("Pour la sérialisation : "+eleve);
            
        request.setAttribute("eleve", eleve);       
    }
}
