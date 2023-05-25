package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Etablissement;
import metier.modele.Niveau;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-05-25T21:13:33")
@StaticMetamodel(Eleve.class)
public class Eleve_ { 

    public static volatile SingularAttribute<Eleve, String> motDePasse;
    public static volatile SingularAttribute<Eleve, String> mail;
    public static volatile SingularAttribute<Eleve, Date> dateNaissance;
    public static volatile SingularAttribute<Eleve, Etablissement> etablissement;
    public static volatile SingularAttribute<Eleve, Long> id;
    public static volatile SingularAttribute<Eleve, String> nom;
    public static volatile SingularAttribute<Eleve, String> prenom;
    public static volatile SingularAttribute<Eleve, Niveau> niveau;

}