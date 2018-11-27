package mx.unam.is20191.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.Material;
import mx.unam.is20191.models.Subcategoria;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(Categoria.class)
public class Categoria_ { 

    public static volatile SingularAttribute<Categoria, String> descripcion;
    public static volatile SetAttribute<Categoria, Material> materialSet;
    public static volatile SingularAttribute<Categoria, Integer> id;
    public static volatile SingularAttribute<Categoria, String> nombre;
    public static volatile SetAttribute<Categoria, Subcategoria> subcategoriaSet;

}