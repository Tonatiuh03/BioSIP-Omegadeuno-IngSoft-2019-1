/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.is20191.dao;

import mx.unam.is20191.models.Categoria;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unam.is20191.models.Material;

/**
 *
 * @author jrcvd
 */
public class MaterialDao extends AbstractDao<Long, Material> {

    public List<Material> searchMaterial(String nombreMaterial, Integer idCategoria, Integer idSubcategoria) {
        CriteriaBuilder cb = createCriteriaBuilder();
        CriteriaQuery<Material> crit = createCriteriaQuery(cb);
        Root<Material> r = createRoot(crit);

        boolean porMaterial = nombreMaterial.isEmpty(),
                porCategoria = idCategoria > -1,
                porSubcategoria = idSubcategoria > -1;
        int criterios=0;
        if(!porMaterial && !porCategoria && !porSubcategoria){
            
        }
        if(!porMaterial && !porCategoria && !porSubcategoria){
            
        }
        if(!porMaterial && !porCategoria && !porSubcategoria){
            
        }
        if (true) {
            return this.findAll(crit, r, cb.asc(r.get("id")));
        }
        
    }

}
