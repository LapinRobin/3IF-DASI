/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import metier.modele.Eleve;
import metier.modele.Niveau;
import metier.service.ServiceClient;

public class InscrireEleveAction extends Action {
    public void executer(HttpServletRequest request) {
        
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        String birthdateStr = (String) request.getParameter("birthdate");
        String code = (String) request.getParameter("code");
        String classe = (String) request.getParameter("classe");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdate = sdf.parse(birthdateStr);

            // Create a Niveau object corresponding to the classe
            // Assuming that you have a Niveau enum or class. Modify as per your codebase
            Niveau niveau = Niveau.valueOf(classe.toUpperCase());

            Eleve eleve = new Eleve(nom, prenom, birthdate, email, password, niveau);

            ServiceClient service = new ServiceClient();
            service.inscriptionEleve(eleve, code);

            boolean creation;
        if(eleve.getId() != null) {
            creation = true;
            HttpSession session = request.getSession(true);
            session.setAttribute("idEleve", eleve.getId());
        } else {
            creation = false;
            System.out.println("Error creating student");
            // handle error when student could not be created.
        }
        request.setAttribute("creation", creation);
        request.setAttribute("eleve", eleve);


        } catch (Exception e) {
            // handle exceptions properly
        }
    }
}
