package mx.unam.is20191.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.KitMaterial;
import mx.unam.is20191.models.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(Kit.class)
public class Kit_ { 

    public static volatile SingularAttribute<Kit, Date> fechaDeExpiracion;
    public static volatile SingularAttribute<Kit, Usuario> administradorIdAprobador;
    public static volatile SetAttribute<Kit, KitMaterial> kitMaterialSet;
    public static volatile SingularAttribute<Kit, Usuario> usuarioIdAutor;
    public static volatile SingularAttribute<Kit, Long> id;

}