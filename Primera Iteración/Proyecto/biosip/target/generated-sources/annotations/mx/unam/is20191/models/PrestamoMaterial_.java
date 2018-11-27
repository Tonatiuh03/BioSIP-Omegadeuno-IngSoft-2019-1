package mx.unam.is20191.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Prestamo;
import mx.unam.is20191.models.PrestamoMaterialPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(PrestamoMaterial.class)
public class PrestamoMaterial_ { 

    public static volatile SingularAttribute<PrestamoMaterial, Prestamo> prestamo;
    public static volatile SingularAttribute<PrestamoMaterial, Integer> elementosPrestados;
    public static volatile SingularAttribute<PrestamoMaterial, Material> material;
    public static volatile SingularAttribute<PrestamoMaterial, PrestamoMaterialPK> prestamoMaterialPK;

}