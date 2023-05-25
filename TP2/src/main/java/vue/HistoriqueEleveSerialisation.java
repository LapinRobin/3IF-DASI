/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Intervention;

/**
 *
 * @author acremona
 */
public class HistoriqueEleveSerialisation extends Serialisation{
     public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("appel de HistoriqueEleveSerialisation");
        JsonObject container = new JsonObject();
        
        List<Intervention> interventions = (List<Intervention>) request.getAttribute("interventions");
        //System.out.println("Pour la sérialisation : ");
        
        JsonArray jsonListeInterventions = new JsonArray();
        
        for(Intervention i : interventions)
        {
            JsonObject jsonIntervention = new JsonObject();
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = formatter.format(i.getDate());
            jsonIntervention.addProperty("date", formattedDate );
            jsonIntervention.addProperty("matiere", i.getMatiere().getNom());
            jsonIntervention.addProperty("description", i.getDescription());
            jsonIntervention.addProperty("duree", i.getDureeEnMinutes());
            jsonIntervention.addProperty("note", i.getNote());
            
            JsonObject jsonIntervenant = new JsonObject();
            jsonIntervenant.addProperty("nom", i.getIntervenant().getNom());
            jsonIntervenant.addProperty("prenom", i.getIntervenant().getPrenom());
            jsonIntervention.add("intervenant", jsonIntervenant);
            
            jsonListeInterventions.add(jsonIntervention);
        }

        container.add("interventions", jsonListeInterventions);
        
        System.out.println(container);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        PrintWriter out = getWriter(response);
        out.println(gson.toJson(container));
        out.close();
    }
}
