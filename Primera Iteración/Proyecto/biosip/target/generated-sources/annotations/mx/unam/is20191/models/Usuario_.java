package mx.unam.is20191.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.Confirmacion;
import mx.unam.is20191.models.Kit;
import mx.unam.is20191.models.Perfil;
import mx.unam.is20191.models.Prestamo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SetAttribute<Usuario, Prestamo> prestamoSet1;
    public static volatile SetAttribute<Usuario, Perfil> perfilSet;
    public static volatile SingularAttribute<Usuario, Integer> bloqueado;
    public static volatile SingularAttribute<Usuario, String> nombreCompleto;
    public static volatile SingularAttribute<Usuario, String> userName;
    public static volatile SingularAttribute<Usuario, Confirmacion> confirmacion;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SetAttribute<Usuario, Kit> kitSet1;
    public static volatile SetAttribute<Usuario, Prestamo> prestamoSet;
    public static volatile SingularAttribute<Usuario, Date> fechaDeDesbloqueo;
    public static volatile SetAttribute<Usuario, Kit> kitSet;
    public static volatile SingularAttribute<Usuario, Boolean> validado;
    public static volatile SingularAttribute<Usuario, Long> id;
    public static volatile SingularAttribute<Usuario, String> rutaImagen;
    public static volatile SingularAttribute<Usuario, String> correoCiencias;

}