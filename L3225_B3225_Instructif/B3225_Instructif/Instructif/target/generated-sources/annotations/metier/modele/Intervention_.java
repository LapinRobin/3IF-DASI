package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Eleve;
import metier.modele.Intervenant;
import metier.modele.Matiere;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-05-31T17:46:21")
@StaticMetamodel(Intervention.class)
public class Intervention_ { 

    public static volatile SingularAttribute<Intervention, Date> date;
    public static volatile SingularAttribute<Intervention, Integer> note;
    public static volatile SingularAttribute<Intervention, String> description;
    public static volatile SingularAttribute<Intervention, Long> id;
    public static volatile SingularAttribute<Intervention, Eleve> eleve;
    public static volatile SingularAttribute<Intervention, Long> dureeEnMinutes;
    public static volatile SingularAttribute<Intervention, Intervenant> intervenant;
    public static volatile SingularAttribute<Intervention, Matiere> matiere;

}