package mx.unam.is20191.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.PrestamoMaterial;
import mx.unam.is20191.models.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(Prestamo.class)
public class Prestamo_ { 

    public static volatile SingularAttribute<Prestamo, Date> fechaDeAprobacion;
    public static volatile SingularAttribute<Prestamo, Usuario> administradorIdAprobador;
    public static volatile SetAttribute<Prestamo, PrestamoMaterial> prestamoMaterialSet;
    public static volatile SingularAttribute<Prestamo, Date> fechaDeSolicitud;
    public static volatile SingularAttribute<Prestamo, Long> id;
    public static volatile SingularAttribute<Prestamo, Usuario> usuarioId;
    public static volatile SingularAttribute<Prestamo, Date> fechaDeDevolucion;

}