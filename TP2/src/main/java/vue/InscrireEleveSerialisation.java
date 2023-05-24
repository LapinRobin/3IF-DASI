/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.modele.Eleve;

/**
 *
 * @author ysu
 */
public class InscrireEleveSerialisation extends Serialisation {
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = this.getWriter(response);

        Boolean creation = (Boolean) request.getAttribute("creation");
        Eleve eleve = (Eleve) request.getAttribute("eleve");

        JsonObject container = new JsonObject();

        if (creation != null && creation) {
            JsonObject jsonEleve = new JsonObject();
            jsonEleve.addProperty("id", eleve.getId());
            jsonEleve.addProperty("nom", eleve.getNom());
            jsonEleve.addProperty("prenom", eleve.getPrenom());

            container.addProperty("creation", true);
            container.add("eleve", jsonEleve);
        } else {
            container.addProperty("creation", false);
        }

        out.write(container.toString());
        out.close();
    }
}

