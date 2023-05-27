/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ysu
 */
public class DeconnecterEleveAction extends Action {
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute("idEleve");
        boolean eleveDeconnecte = session.getAttribute("idEleve") == null;
        request.setAttribute("eleveDeconnecte", eleveDeconnecte);
        System.out.println("Eleve deconnecte.");
    }
}

