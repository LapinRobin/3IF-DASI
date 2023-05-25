package vue;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Intervenant;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acremona
 */
public class ProfilIntervenantSerialisation extends Serialisation {
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject();
        
        JsonObject intervenantJson = new JsonObject();
        Intervenant intervenant = (Intervenant) request.getAttribute("intervenant");
        System.out.println("Pour la sérialisation : "+intervenant);
        if (intervenant != null) {
            container.addProperty("connexion", true);
            intervenantJson.addProperty("id", intervenant.getId());
            intervenantJson.addProperty("nom", intervenant.getNom());
            intervenantJson.addProperty("prenom", intervenant.getPrenom());
            intervenantJson.addProperty("mail", intervenant.getMail());
            intervenantJson.addProperty("telephone", intervenant.getTelephone());
            
            container.add("intervenant", intervenantJson);
        } else {
            container.addProperty("connexion", false);
        }

        
        
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        PrintWriter out = getWriter(response);
        out.println(gson.toJson(container));
        out.close();
    }
}