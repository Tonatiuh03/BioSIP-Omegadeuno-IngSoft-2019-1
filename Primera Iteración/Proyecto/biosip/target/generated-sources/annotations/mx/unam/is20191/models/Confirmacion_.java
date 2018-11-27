package mx.unam.is20191.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(Confirmacion.class)
public class Confirmacion_ { 

    public static volatile SingularAttribute<Confirmacion, Date> fechaDeAlta;
    public static volatile SingularAttribute<Confirmacion, Usuario> usuarioId;
    public static volatile SingularAttribute<Confirmacion, String> token;

}