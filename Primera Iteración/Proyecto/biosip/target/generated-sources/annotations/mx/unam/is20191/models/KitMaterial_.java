package mx.unam.is20191.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.Kit;
import mx.unam.is20191.models.KitMaterialPK;
import mx.unam.is20191.models.Material;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(KitMaterial.class)
public class KitMaterial_ { 

    public static volatile SingularAttribute<KitMaterial, Material> material;
    public static volatile SingularAttribute<KitMaterial, Kit> kit;
    public static volatile SingularAttribute<KitMaterial, KitMaterialPK> kitMaterialPK;
    public static volatile SingularAttribute<KitMaterial, Integer> numElementosRequeridos;

}