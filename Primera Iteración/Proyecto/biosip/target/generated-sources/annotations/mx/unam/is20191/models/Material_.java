package mx.unam.is20191.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.Categoria;
import mx.unam.is20191.models.KitMaterial;
import mx.unam.is20191.models.PrestamoMaterial;
import mx.unam.is20191.models.Subcategoria;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(Material.class)
public class Material_ { 

    public static volatile SingularAttribute<Material, String> descripcion;
    public static volatile SetAttribute<Material, Categoria> categoriaSet;
    public static volatile SingularAttribute<Material, Integer> disponibles;
    public static volatile SetAttribute<Material, KitMaterial> kitMaterialSet;
    public static volatile SetAttribute<Material, PrestamoMaterial> prestamoMaterialSet;
    public static volatile SingularAttribute<Material, Long> id;
    public static volatile SingularAttribute<Material, String> rutaImagen;
    public static volatile SingularAttribute<Material, String> nombre;
    public static volatile SetAttribute<Material, Subcategoria> subcategoriaSet;

}