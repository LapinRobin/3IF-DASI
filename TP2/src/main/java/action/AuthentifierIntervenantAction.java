package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.modele.Intervenant;
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
public class AuthentifierIntervenantAction extends Action {
    public void executer(HttpServletRequest request) {
        
        String login = (String) request.getParameter("login");
        String mdp = (String) request.getParameter("password");

        ServiceClient service = new ServiceClient();
        Intervenant intervenant = service.authentifierIntervenant(login, mdp);

        if(intervenant != null){
            HttpSession session = request.getSession(true);
            session.setAttribute("idIntervenant", intervenant.getId());
        }
            
        request.setAttribute("intervenant", intervenant);       
    }
}