package controleur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import action.AuthentifierIntervenantAction;
import vue.ProfilUtilisateurSerialisation;
import action.AuthentifierUtilisateurAction;
import action.InscrireEleveAction;
import action.ObtenirInterventionsEleveAction;
import action.ObtenirInterventionsIntervenantAction;
import action.ObtenirMatieresAction;
import action.ValiderDemandeAction;
import dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vue.HistoriqueEleveSerialisation;
import vue.HistoriqueIntervenantSerialisation;
import vue.InscrireEleveSerialisation;
import vue.ObtenirMatieresSerialisation;
import vue.ProfilIntervenantSerialisation;
import vue.ValiderDemandeSerialisation;

/**
 *
 * @author acremona
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        JpaUtil.fermerFabriquePersistance();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println(" [TEST] Appel de l’ActionServlet");
        request.getSession(true);
        String todo = request.getParameter("todo");
        System.out.println("Paramètre todo : " + todo);
        switch(todo) {
            case "connecter-eleve" : {
                new AuthentifierUtilisateurAction().executer(request);
                new ProfilUtilisateurSerialisation().serialiser(request, response);
                break;
            }
            case "connecter-intervenant" : {
                new AuthentifierIntervenantAction().executer(request);
                new ProfilIntervenantSerialisation().serialiser(request, response);
                break;
            }
            case "consulter-historique-eleve" : {
                new ObtenirInterventionsEleveAction().executer(request);
                new HistoriqueEleveSerialisation().serialiser(request, response);
                break;
            }
            case "consulter-historique-intervenant" : {
                new ObtenirInterventionsIntervenantAction().executer(request);
                new HistoriqueIntervenantSerialisation().serialiser(request, response);
                break;
            }
            case "inscrire-eleve" : {
                new InscrireEleveAction().executer(request);
                new InscrireEleveSerialisation().serialiser(request, response);
                break;
            }
            case "obtenir-matieres" : {
                new ObtenirMatieresAction().executer(request);
                new ObtenirMatieresSerialisation().serialiser(request, response);
                break;
            }
            case "valider-demande" : {
                new ValiderDemandeAction().executer(request);
                new ValiderDemandeSerialisation().serialiser(request, response);
                break;
            }
            default : {
                break;
            }
        }
            /*
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedNow = now.format(formatter);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
            out.println("<p> Date et heure : " + formattedNow);        
            out.println("</p>");
            out.println("</body>");
            out.println("</html>");
            */

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
