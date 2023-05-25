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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Matiere;

/**
 *
 * @author acremona
 */
public class ObtenirStatsSerialisation extends Serialisation{
     public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("appel de ObtenirStatsSerialisation");
        JsonObject container = new JsonObject();
        
        List<Matiere> matieres = (List<Matiere>) request.getAttribute("matieres");
        List<Long> nbsInterventions = (List<Long>) request.getAttribute("nbsInterventions");
        
        JsonArray jsonListeMatieres = new JsonArray();
        
        int i = 0;
        Long total = Long.valueOf(0);
        
        for(Matiere m : matieres)
        {
            Long nbInterventions = nbsInterventions.get(i);
            total += nbInterventions;
            i++;
            
            JsonObject jsonMatiere = new JsonObject();
            
            jsonMatiere.addProperty("name", m.getNom() );
            jsonMatiere.addProperty("interventions", nbInterventions );
            
            jsonListeMatieres.add(jsonMatiere);
        }
        
        container.addProperty("nbTotal", total );

        container.add("matieres", jsonListeMatieres);
        System.out.println(jsonListeMatieres);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        PrintWriter out = getWriter(response);
        out.println(gson.toJson(container));
        out.close();
    }
}