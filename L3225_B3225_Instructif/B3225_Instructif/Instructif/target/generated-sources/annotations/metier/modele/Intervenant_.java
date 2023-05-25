package metier.modele;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.modele.Niveau;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-05-25T15:40:23")
@StaticMetamodel(Intervenant.class)
public abstract class Intervenant_ { 

    public static volatile SingularAttribute<Intervenant, String> motDePasse;
    public static volatile SingularAttribute<Intervenant, String> mail;
    public static volatile SingularAttribute<Intervenant, Integer> nbInterventions;
    public static volatile SingularAttribute<Intervenant, String> telephone;
    public static volatile SingularAttribute<Intervenant, Long> id;
    public static volatile SingularAttribute<Intervenant, Boolean> libre;
    public static volatile SingularAttribute<Intervenant, String> nom;
    public static volatile SingularAttribute<Intervenant, String> prenom;
    public static volatile SingularAttribute<Intervenant, Niveau> niveauMin;
    public static volatile SingularAttribute<Intervenant, Niveau> niveauMax;

}