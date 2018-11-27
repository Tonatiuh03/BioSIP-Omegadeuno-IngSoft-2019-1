package mx.unam.is20191.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(Perfil.class)
public class Perfil_ { 

    public static volatile SingularAttribute<Perfil, String> descripcion;
    public static volatile SingularAttribute<Perfil, Short> id;
    public static volatile SetAttribute<Perfil, Usuario> usuarioSet;
    public static volatile SingularAttribute<Perfil, String> nombre;

}