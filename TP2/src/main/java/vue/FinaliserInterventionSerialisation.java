package vue;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
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
public class FinaliserInterventionSerialisation extends Serialisation {
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject();
        
        boolean success = (boolean) request.getAttribute("succes");
        container.addProperty("success", success);
   

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        PrintWriter out = getWriter(response);
        out.println(gson.toJson(container));
        out.close();
    }
}
