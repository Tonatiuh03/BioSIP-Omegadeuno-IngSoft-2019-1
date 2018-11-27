package mx.unam.is20191.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mx.unam.is20191.models.Categoria;
import mx.unam.is20191.models.Material;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-25T22:02:13")
@StaticMetamodel(Subcategoria.class)
public class Subcategoria_ { 

    public static volatile SingularAttribute<Subcategoria, String> descripcion;
    public static volatile SetAttribute<Subcategoria, Material> materialSet;
    public static volatile SingularAttribute<Subcategoria, Integer> id;
    public static volatile SingularAttribute<Subcategoria, String> nombre;
    public static volatile SingularAttribute<Subcategoria, Categoria> categoriaId;

}