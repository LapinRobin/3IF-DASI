package vue;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Eleve;
import metier.modele.Intervention;
import metier.modele.Niveau;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acremona
 */
public class ObtenirDemandeSerialisation extends Serialisation {
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject();
        
        JsonObject demandeJson = new JsonObject();
        Intervention demandeEnCours = (Intervention) request.getAttribute("demande");
        
        if (demandeEnCours != null) {
            container.addProperty("demandeEnAttente", true);
            demandeJson.addProperty("matiere", demandeEnCours.getMatiere().getNom());
            demandeJson.addProperty("description", demandeEnCours.getDescription());
            
            Eleve eleve = demandeEnCours.getEleve();
            demandeJson.addProperty("prenom", eleve.getPrenom());
            demandeJson.addProperty("nom", eleve.getNom());
            Niveau niveau = eleve.getNiveau();
            String classe = null;
            switch(niveau){
                case SIXIEME : 
                    classe = "6ème";
                    break;
                case CINQUIEME : 
                    classe = "5ème";
                    break;
                case QUATRIEME : 
                    classe = "4ème";
                    break;
                case TROISIEME : 
                    classe = "3ème";
                    break;
                case SECONDE : 
                    classe = "seconde";
                    break;
                case PREMIERE : 
                    classe = "première";
                    break;
                case TERMINALE : 
                    classe = "terminale";
                    break;
                    
            }
            demandeJson.addProperty("classe", classe);
            demandeJson.addProperty("etablissement", eleve.getEtablissement().getNom());
            
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            String formattedTime = formatter.format(demandeEnCours.getDate());
            demandeJson.addProperty("heure", formattedTime );
            container.add("demande", demandeJson);
        } else {
            container.addProperty("demandeEnAttente", false);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        PrintWriter out = getWriter(response);
        out.println(gson.toJson(container));
        out.close();
    }
}
