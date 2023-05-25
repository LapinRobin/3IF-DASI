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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acremona
 */
public class ProfilUtilisateurSerialisation extends Serialisation {
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject();
        
        JsonObject eleveJson = new JsonObject();
        Eleve eleve = (Eleve) request.getAttribute("eleve");
        System.out.println("Pour la sérialisation : "+eleve);
        if (eleve != null) {
            container.addProperty("connexion", true);
            eleveJson.addProperty("id", eleve.getId());
            eleveJson.addProperty("nom", eleve.getNom());
            eleveJson.addProperty("prenom", eleve.getPrenom());
            eleveJson.addProperty("mail", eleve.getMail());
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = formatter.format(eleve.getDateNaissance());
            eleveJson.addProperty("dateDeNaissance", formattedDate );
            container.add("eleve", eleveJson);
        } else {
            container.addProperty("connexion", false);
        }

        
        
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        PrintWriter out = getWriter(response);
        out.println(gson.toJson(container));
        out.close();
    }
}
